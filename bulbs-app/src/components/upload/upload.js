import React, {useState} from 'react';
import './upload.css';
import {upload, validateForm, validateSelection} from './upload_logic';

const Upload = () => {
  const [file, setFile] = useState(undefined);
  const [error, setError] = useState('');
  const [requesting, setRequesting] = useState(false);
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm(file)) {
      setError("Please select a file first");
      return;
    }

    setError('');
    setMessage('');
    setRequesting(true);

    try {
      await upload(file);
      setMessage('File uploaded successfully');
    } catch (error) {
      console.log(error.message);
    } finally {
      setRequesting(false);
    }
  }

  const handleFileSelected = (e) => {
    const [file] = e.target.files;

    if (validateSelection(file)) {
      setFile(undefined);
      setError("Please select a valid TXT file");
      return;
    }

    setMessage('');
    setError('');
    setFile(file);
  }

  return (
    <>
      <div className="mt-3 row">
        <div className="col-12 text-center">
          <h4>Upload a Room's blueprint</h4>
        </div>
      </div>
      {message.length > 0 &&
        <div className="alert alert-success" role="alert">
          {message}
        </div>
      }
      <form onSubmit={handleSubmit}>
        <div className="row">
          <div className="col-12">
            <div>
              <label htmlFor="formFileLg" className="form-label">Select a TXT file from your system</label>
              <input
                onChange={handleFileSelected}
                className="form-control form-control-lg"
                name="txtblueprint"
                id="txtblueprint"
                type="file"
                accept=".txt"
              />
              {error !== '' && (
                <small className="field-error">{error}</small>
              )}
            </div>
          </div>
        </div>
        <div className="row mt-2">
          <div className="col-12">
            <div>
              <button
                className="btn btn-outline-success"
                type="submit"
                disabled={requesting ? true : false}
                >Upload</button>
            </div>
          </div>
        </div>
      </form>
    </>
  );
};

export default Upload;

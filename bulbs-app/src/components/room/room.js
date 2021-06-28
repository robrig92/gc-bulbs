import React, {useState, useEffect} from 'react';
import './room.css';
import {Link} from 'react-router-dom';
import {fetchUnsolved, fetchSolved} from './room_logic';
import Blueprint from './blueprint/blueprint';

const Room = () => {
  const [blueprint, setBlueprint] = useState([]);
  const [solved, setSolved] = useState([]);
  const [requesting, setRequesting] = useState(false);

  useEffect(() => {
    (async () => {
      await initBlueprint();
    })();
  }, []);

  const initBlueprint = async () => {
    try {
      await fetchUnsolved(setBlueprint);
    } catch (error) {
      console.log(error.message);
    } finally {
      setRequesting(false);
    }
  }

  const handleOnClick = async () => {
    setRequesting(true);
    setSolved([]);

    try {
      await fetchSolved(setSolved);
    } catch (error) {
      console.log(error.message);
    } finally {
      setRequesting(false);
    }
  }

  return (
    <>
      <div className="row mt-3">
        <div className="col-12 text-center">
          <h4>Room's blueprint</h4>
        </div>
      </div>
      <div className="row">
        <div className="col-12 text-center">
          <button
            className={`btn btn-outline-success` + (requesting ? " button-disabled" : "")}
            onClick={handleOnClick}
            disabled={requesting ? true : false}
          >
            { requesting ? 'Getting...' : 'Solve blueprint' }
          </button>
        </div>
      </div>
      <div className="row mt-2">
        <div className="col-8 offset-2 text-center">
          <div className="room-container">
            { solved.length > 0
              ? <Blueprint blueprint={solved} solved={true}/>
              : solved.length === 0 && blueprint.length === 0
                ? (<span>{'There is not a valid TXT loaded yet, plase '}<Link to="/upload">upload</Link>{' a TXT file and try again.'}</span>)
                : <Blueprint blueprint={blueprint} solved={false}/>
            }
          </div>
        </div>
      </div>
    </>
  );
}

export default Room;

import axios from 'axios';

export const upload = async (file) => {
    const formData = new FormData();
    formData.append('file', file);

    await axios.post(`${process.env.REACT_APP_API_URL}/blueprints`, formData);
}

export const validateForm = (file) => {
  return file !== undefined;
}

export const validateSelection = (file) => {
  return file.type !== 'text/plain';
}

import axios from 'axios';

export const fetchUnsolved = async (setBlueprint) => {
  await fetchBlueprint('plain', setBlueprint);
}

export const fetchSolved = async (setSolved) => {
  await fetchBlueprint('solved', setSolved);
}

const fetchBlueprint = async (type, setCollection) => {
    const response = await axios.get(`http://localhost:3000/api/blueprints/${type}`);
  setCollection(response.data.room);
}

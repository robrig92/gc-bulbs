import axios from 'axios';

export const fetchUnsolved = async (setBlueprint) => {
  await fetchBlueprint('plain', setBlueprint);
}

export const fetchSolved = async (setSolved) => {
  await fetchBlueprint('solved', setSolved);
}

const fetchBlueprint = async (type, setCollection) => {
  const response = await axios.get(`${process.env.REACT_APP_API_URL}/rooms/${type}`);
  setCollection(response.data.blueprint);
}

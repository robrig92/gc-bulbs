import Room from './components/room/room';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Menu from './components/menu/menu';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Upload from './components/upload/upload';

function App() {

  return (
    <Router>
      <div className="container app-container">
        <Menu />
        <Switch>
          <Route exact path="/">
            <Room />
          </Route>
          <Route path="/generate">
            <Room />
          </Route>
          <Route path="/upload">
            <Upload />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;

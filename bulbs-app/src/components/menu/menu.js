import React from 'react';
import './menu.css';
import {Link} from 'react-router-dom';

const Menu = () => {
  return (
    <nav className="navbar navbar-expand-sm navbar-light bg-light">
      <Link className="navbar-brand" to="/">Bulbs blueprint</Link>
      <ul className="navbar-nav">
        <li className="nav-item">
          <Link className="nav-link" to="/generate">Generate</Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/upload">Upload</Link>
        </li>
      </ul>
    </nav>
  );
};

export default Menu;

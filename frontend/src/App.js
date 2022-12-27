import "./App.css";
import React, { Fragment } from "react";
import "./index.css";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import Home from "./home/Home";
import Ssd from "./formulario/Ssd"; 
import SsdList from "./formulario/SsdList"; 

export default function App() {
  return (
    <Router>
      <main>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">About</Link>
            </li>
            <li>
              <Link to="/contact">Contact</Link>
            </li>
          </ul>
        </nav>
        <Route path="/" exact component={Home}></Route>
        <Route path="/ssd" component={Ssd}></Route>
        <Route path="/ssdlist" component={SsdList}></Route>
      </main>
    </Router>
  );
}
// Home Page
// const Home = () => (
//   <Fragment>
//     <h1>Home</h1>
//     <FakeText />
//   </Fragment>
// );
// About Page
const About = () => (
  <Fragment>
    <h1>About</h1>
    <FakeText />
  </Fragment>
);
// Contact Page
const Contact = () => (
  <Fragment>
    <h1>Contact</h1>
    <FakeText />
  </Fragment>
);

const FakeText = () => (
  <p>
    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
    non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
  </p>
);

import "./App.css";
import React, { Fragment } from "react";
import "./index.css";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import Home from "./home/Home";
import Ssd from "./formulario/Ssd";
import SsdList from "./formulario/SsdList";
import Ram from "./formulario/Ram";
import RamList from "./formulario/RamList";
import Cpu from "./formulario/Cpu";
import CpuList from "./formulario/CpuList";
export default function App() {
  return (
    <Router>
      <main>
        <nav>
          <ul>
            <tr>
              {/* <th>
                <Link to="/">Ssd</Link>
              </th>
              <th>
                <Link to="/ram">Ram</Link>
              </th>
              <th>
                <Link to="/cpu">Cpu</Link>
              </th> */}
            </tr>
          </ul>
        </nav>
        {/* exact faz renderizar aquele determinado component */}
        <tr>
          <th>
            <Route path="/" exact component={Home}></Route>
            <Route path="/ssd" exact component={Ssd}></Route>
            <Route path="/ssdlist" exact component={SsdList}></Route>

            <Route path="/ram" exact component={Ram}></Route>
            <Route path="/ramlist" exact component={RamList}></Route>

            <Route path="/cpu" exact component={Cpu}></Route>
            <Route path="/cpulist" exact component={CpuList}></Route>
          </th>
        </tr>
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

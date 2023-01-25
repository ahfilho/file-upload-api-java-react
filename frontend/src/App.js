import "./App.css";
import React, { Fragment } from "react";
import "./index.css";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import Home from "./home/Home";
import Ssd from "./pages/ssd/Ssd";
import SsdList from "./pages/ssd/SsdList";
import Ram from "./pages/Ram";
import RamList from "./pages/RamList";
import Cpu from "./pages/Cpu";
import CpuList from "./pages/CpuList";

export default function App() {
  return (
    <Router>
      <div className="main">
        {/* <th>
                <Link to="/">Ssd</Link>
              </th>
              <th>
                <Link to="/ram">Ram</Link>
              </th>
              <th>
                <Link to="/cpu">Cpu</Link>
              </th> */}

        {/* exact faz renderizar aquele determinado component */}

        <div className="opcoes">
          <Route path="/" exact component={Home}></Route>
          <Route path="/ssd" exact component={Ssd}></Route>
          <Route path="/ssdlist" exact component={SsdList}></Route>

          <Route path="/ram" exact component={Ram}></Route>
          <Route path="/ramlist" exact component={RamList}></Route>

          <Route path="/cpu" exact component={Cpu}></Route>
          <Route path="/cpulist" exact component={CpuList}></Route>
        </div>
      </div>
    </Router>
  );
}

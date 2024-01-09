import "./App.css";
import React, { Fragment } from "react";
import "./index.css";
import {
  BrowserRouter,
  Switch,
  Route,
  Link
} from "react-router-dom";import Home from "./home/Home";
import Ssd from "./pages/ssd/Ssd";
import SsdList from "./pages/ssd/SsdList";
import Ram from "./pages/Ram";
import RamList from "./pages/RamList";
import Cpu from "./pages/cpu/Cpu";
import CpuList from "./pages/cpu/CpuList";
import SsdEdit from "./pages/ssd/SsdEdit";
import LoginPage from "./pages/LoginPage";
import { Dashboard } from './pages/dashboard/dashboard';
import NavBar from "./navbar/NavBar";
import Client from "./client/Client";
import ClientList from "./client/ClientList";
import CpuEdit from "./pages/cpu/CpuEdit";
import ClientEdit from "./client/ClientEdit";
import ClientCpfSearch from "./client/ClientCpfSearch";
import ListClient from "./client/ClientCpfSearch";
import Garantia from "./pages/Garantia";
import RamEdit from "./pages/RamEdit";

function App() {
  return (
    <BrowserRouter>
      <Switch> 
        {/* exact faz renderizar aquele determinado component */}

        {/* <div className="opcoes"> */}
          {/* <header> */}
          <Switch>
          <Route path="/navbar" exact component={NavBar} />
          <Route path="/client" exact component={Client} />
          <Route path="/clientList" exact component={ClientList} />
          <Route path="/clientEdit/:id" exact component={ClientEdit} />
          <Route path="/clientCpfSearch" exact component={ClientCpfSearch} />
          <Route path="/search/:cpf" component={ListClient} />

          <Route path="/" exact component={LoginPage} />
          <Route path="/dashboard" exact component={Dashboard} />

          <Route path="/home" exact component={Home} />
          <Route path="/ssd" exact component={Ssd} />
          <Route path="/ssdlist" exact component={SsdList} />
          <Route path="/ssdEdit/:id" exact component={SsdEdit} />

          <Route path="/ram" exact component={Ram} />
          <Route path="/ramlist" exact component={RamList} />
          <Route path="/ramEdit/:id" exact component={RamEdit} />


          <Route path="/cpu" exact component={Cpu} />
          <Route path="/cpulist" exact component={CpuList} />
          <Route path="/cpuEdit/:id" exact component={CpuEdit} />

          <Route path="/garantia" exact component={Garantia} />


          </Switch>
          {/* /</header>
           */}
        {/* </div> */}
      </Switch>
    </BrowserRouter>
    );
}
export default App;
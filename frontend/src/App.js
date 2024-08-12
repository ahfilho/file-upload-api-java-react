import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./home/Home";
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
import NewUser from "./pages/dashboard/NewUser";
import Listar from "./pages/dashboard/Listar";
import ForgotPass from "./reset/ResetPassword";

function App() {
  return (
    <Routes>
      <Route path="/navbar" element={<NavBar />} />
      <Route path="/client" element={<Client />} />
      <Route path="/clientList" element={<ClientList />} />
      <Route path="/clientEdit/:id" element={<ClientEdit />} />
      <Route path="/clientCpfSearch" element={<ClientCpfSearch />} />
      <Route path="/search/:cpf" element={<ListClient />} />

      <Route path="/" element={<LoginPage />} />
      <Route path="/dashboard" element={<Dashboard />} />

      <Route path="/" element={<Home />} />
      <Route path="/ssd" element={<Ssd />} />
      <Route path="/ssdlist" element={<SsdList />} />
      <Route path="/ssdEdit/:id" element={<SsdEdit />} />

      <Route path="/ram" element={<Ram />} />
      <Route path="/ramlist" element={<RamList />} />
      <Route path="/ramEdit/:id" element={<RamEdit />} />

      <Route path="/cpu" element={<Cpu />} />
      <Route path="/cpulist" element={<CpuList />} />
      <Route path="/cpuEdit/:id" element={<CpuEdit />} />

      <Route path="/garantia" element={<Garantia />} />
      <Route path="/new/user" element={<NewUser />} />
      <Route path="/list" element={<Listar />} />

      <Route path="/reset/pass"  element={<ForgotPass/>}/> 
      <Route path="/home/Home" element={<Home />} />

    </Routes>
  );
}

export default App;

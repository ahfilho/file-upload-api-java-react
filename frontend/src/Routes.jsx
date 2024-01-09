import { BrowserRouter, Routes, Route } from "react-router-dom"

import Ram from "./formulario/Ram";

import Ssd from "./formulario/Ssd";
import SsdEdit from "./formulario/SsdEdit";
import SsdList from "./formulario/SsdList";
import Home from "./home/Home";
import RamEdit from "./pages/RamEdit";
import RamList from "./pages/RamList";


function RoutesApp() {
  return (

    <BrowserRouter>

      <Routes path="/home" element={<Home />} dasdas />


      <Routes path="/ssd" element={<Ssd />} />
      <Routes path="/ssdList" element={<SsdList />} />
      <Routes path="/ssdEdit/" element={<SsdEdit />} />

      <Routes path="/ram" element={<Ram />} />
      <Routes path="/ramList" element={<RamList />} />
      <Routes path="/ramEdit" element={<RamEdit />} />

      <Routes path="/cpu" element={<Ssd />} />
      <Routes path="/cpuList" element={<SsdList />} />
      <Routes path="/cpuEdit/" element={<SsdEdit />} />

      <Routes path="/home" element={<Home />} />
    </BrowserRouter>
  )
}
export default RoutesApp
import { BrowserRouter, Routes, Route } from "react-router-dom"

import Ram from "./formulario/Ram";

import Ssd from "./formulario/Ssd";
import SsdEdit from "./formulario/SsdEdit";
import SsdList from "./formulario/SsdList";
import Home from "./home/Home";


function RoutesApp(){
    return(
      
      <BrowserRouter>
      
      <Routes path="/home" element={<Home />}dasdas />
      

        <Routes path="/ssd" element={<Ssd />} />
        <Routes path="/ssd/list" element={<SsdList />} />
        <Routes path="/ssd/update" element={<SsdEdit />} />

        <Routes path="/ram" element={<Ssd />} />
        <Routes path="/ram/list" element={<SsdList />} />
        <Routes path="/ram/update" element={<SsdEdit />} />

        <Routes path="/cpu" element={<Ssd />} />
        <Routes path="/cpu/list" element={<SsdList />} />
        <Routes path="/cpu/update" element={<SsdEdit />} />

        <Routes path="/home" element={<Home />} />
      </BrowserRouter>
    )
    }
    export default RoutesApp
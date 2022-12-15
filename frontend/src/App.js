import "./App.css";
import List from "./listagem/List";
import { useEffect, useState } from "react";
import axios from "axios";

import React from "react";
import { Routes, Route } from "react-router-dom";

import Ram from "./formulario/Ram";

import Ssd from "./formulario/Ssd";
import SsdEdit from "./formulario/SsdEdit";
import SsdList from "./formulario/SsdList";

const url = "http://localhost:9090/ssd/new";

const App = () => {
  return (
    <div className="">
      <Routes>
        <Route path="/home" element={<home />} />

        <Route path="/ssd" element={<Ssd />} />
        <Route path="/ssd/list" element={<SsdList />} />
        <Route path="/ssd/update" element={<SsdEdit />} />

        <Route path="/ram" element={<Ssd />} />
        <Route path="/ram/list" element={<SsdList />} />
        <Route path="/ram/update" element={<SsdEdit />} />

        <Route path="/cpu" element={<Ssd />} />
        <Route path="/cpu/list" element={<SsdList />} />
        <Route path="/cpu/update" element={<SsdEdit />} />
      </Routes>
    </div>
  );
};

export default App;

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';

import List from './listagem/List';
import Ssd from './pages/ssd/Ssd';
import Client from './client/Client';
import ListClient from './client/ListClient';
import SsdList from './pages/ssd/SsdList';
import Home from './home/Home';
import CpuList from './pages/CpuList';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
      {/* <List></List> */}
      {/* <Ssd/> */}
      {/* <Client></Client> */}
      {/* <ListClient/> */}
      {/* <SsdList></SsdList> */}
    {/* <CpuList></CpuList> */}
      </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

import { BrowserRouter, Link, Route, Routes } from "react-router-dom";


const NavBar = () => {
  return (

    <nav class="nav-pills nav-fill">

<div class="btn-group" role="group">
<button type="submit" className="btn px-4">
<a class="nav-item nav-link">
          <Link to="/">Início</Link>
        </a></button>
     
        <button type="submit" className="btn px-4">
        <a class="nav-item nav-link">
          <Link to="/clientList">Listar Clientes</Link>
        </a> </button>

        <button type="submit" className="btn px-4">
        <a class="nav-item nav-link">
          <Link to="/cpulist">Listar Cpus</Link>
        </a> </button>
    
        <button type="submit" className="btn px-4">
        <a class="nav-item nav-link">
          <Link to="/ssdlist">Listar Ssd</Link>
        </a> </button>

        <button type="submit" className="btn px-4">
        <a class="nav-item nav-link">
          <Link to="/ramList">Listar Ram</Link>
        </a> </button>

        <button type="submit" className="btn px-4">
        <a class="nav-item nav-link">
          <Link to="home">Pesquisar produto</Link>
        </a> </button>
        <button type="submit" className="btn px-4">
        <a class="nav-item nav-link">
          <Link to="/dashboard">Dashboard</Link>
        </a> </button>
  </div>
    </nav>
  )
}
export default NavBar;
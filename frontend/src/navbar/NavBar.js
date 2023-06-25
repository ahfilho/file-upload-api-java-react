import { BrowserRouter, Link, Route, Routes } from "react-router-dom";


const NavBar = () =>{
return(

<nav class="nav-pills nav-fill">
<button type="button" class="btn btn-primary">
  <a class="nav-item nav-link">
    <Link to="/home">Início</Link>
  </a></button>
<button type="button" class="btn btn-primary">
  <a class="nav-item nav-link">
    <Link to="/ssdlist">Listar SSDs</Link>
  </a> </button>
  <button type="button" class="btn btn-primary">
  <a class="nav-item nav-link">
    <Link to="/cpulist">Listar Cpus</Link>
  </a> </button>
  <button type="button" class="btn btn-primary">
  <a class="nav-item nav-link">
    <Link to="/clientList">Listar Clientes</Link>
  </a> </button>
<button type="button" class="btn btn-primary">
  <a class="nav-item nav-link">
    <Link to="//">Pesquisar produto</Link>
  </a> </button>
<button type="button" class="btn btn-primary">
  <a class="nav-item nav-link">
    <Link to="/dashboard">Dashboard</Link>
  </a> </button>
</nav>

)
}
export default NavBar;
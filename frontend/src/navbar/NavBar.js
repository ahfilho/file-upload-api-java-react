import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import { Dropdown, DropdownButton } from 'react-bootstrap';
import "./navbar.css";

const NavBar = () => {
  return (

    <nav class="nav-pills nav-fill">

      <div class="btn-group" role="group">
        <div className='bold-text'>

          <button type="button" class="btn px-2">
            <a class="nav-item nav-link">
              <Link to="/">Início</Link>
            </a></button>


          <DropdownButton id="dropdown-client" title="Cliente" className="btn px-2">
            <Dropdown.Item>
              <Link className="nav-link" to="/client">
                <button className="btn">Cadastrar</button>
              </Link>
            </Dropdown.Item>
            <Dropdown.Item>
              <Link className="nav-link" to="/clientList">
                <button className="btn">Listar</button>
              </Link>
            </Dropdown.Item>
          </DropdownButton>
      

          <DropdownButton id="dropdown-cpu" title="Cpu" className="btn px-2">
            <Dropdown.Item>
              <Link className="nav-link" to="/cpu">
                <button className="btn">Cadastrar</button>
              </Link>
            </Dropdown.Item>
            <Dropdown.Item>
              <Link className="nav-link" to="/cpulist">
                <button className="btn">Listar</button>
              </Link>
            </Dropdown.Item>
          </DropdownButton>

          <DropdownButton id="dropdown-ram" title="Ram" className="btn px-2">
            <Dropdown.Item>
              <Link className="nav-link" to="/ram">
                <button className="btn">Cadastrar</button>
              </Link>
            </Dropdown.Item>
            <Dropdown.Item>
              <Link className="nav-link" to="/ramlist">
                <button className="btn">Listar</button>
              </Link>
            </Dropdown.Item>
          </DropdownButton>

          <DropdownButton id="dropdown-ssd" title="Ssd" className="btn px-2">
            <Dropdown.Item>
              <Link className="nav-link" to="/ssd">
                <button className="btn">Cadastrar</button>
              </Link>
            </Dropdown.Item>
            <Dropdown.Item>
              <Link className="nav-link" to="/ssdlist">
                <button className="btn">Listar</button>
              </Link>
            </Dropdown.Item>
          </DropdownButton>
          <DropdownButton id="dropdown-user" title="Usuário" className="btn px-2">
            <Dropdown.Item>
              <Link className="nav-link" to="/new/user">
                <button className="btn">Cadastrar</button>
              </Link>
            </Dropdown.Item>
            <Dropdown.Item>
              <Link className="nav-link" to="/list">
                <button className="btn">Listar</button>
              </Link>
            </Dropdown.Item>
          </DropdownButton>
          <DropdownButton id="dropdown-dashboard" title="Meus dados" className="btn px-2">
            <Dropdown.Item>
              <Link className="nav-link" to="/dashboard">Dashboard</Link>
            </Dropdown.Item>
            <Dropdown.Item>
              <Link className="nav-link" to="/">Em construção</Link>
            </Dropdown.Item>
          </DropdownButton>

        </div>
      </div>
    </nav>
  )
}
export default NavBar;
import "./Home.css";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Home = () => {
  return (

    <div className="flex-container">

      <div class="container mt-3">
        <h2>Meus produtos</h2>
        <p>Gerenciador de hardware novo/usado.
         Cadastrar cliente/máquina.
        </p>
        <ul class="nav">
          <li class="nav-item">
            <a href="/cpu" class="animated-button9">CPU</a>
          </li>
          <li class="nav-item">
            <a href="/ram" class="animated-button9">RAM</a>
          </li>
          <li class="nav-item">
            <a href="/ssd" class="animated-button9">SSD</a>
          </li>
          <li class="nav-item">
          <a href="#" class="animated-button9">M.2</a>
          </li>
        </ul>
      </div>

      {/*       
      <div className="animated-button2">
        <a href="/ram" class="animated-button1">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Ram
        </a>
      </div>
      <div className="animated-button3">
        <a href="/ssd" class="animated-button2">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Ssd
        </a>
      </div> */}

      {/* <div className="a1">
        <Link to="/ssd">Ssd</Link>

        <Link to="/ram">Ram</Link>
      </div>
      <div className="a1">
        <Link to="/cpu">Cpu</Link>
      </div> */}
    </div>
  );
};
export default Home;

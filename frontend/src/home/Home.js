import "./Home.css";
import { Link } from "react-router-dom";
import axios from "axios";
import { useState } from "react";
import { useHistory } from "react-router-dom"; // import do hook

const Home = () => {
  const history = useHistory();
  const [cpf, setCpf] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = `http://localhost:9090/client/search/${cpf}`;

    try {
      const response = await axios.get(url);
      if (response && response.data) {
        console.log("Cliente encontrado:", response.data);
        history.push({
          pathname: `/search/${cpf}`,
          state: { clients: response.data },
        });
      } else {
        console.log("Nenhum cliente encontrado.");
      }
      setCpf("");
    } catch (error) {
      console.error("Erro ao pesquisar CPF:", error);
    }
  };

  return (
    <div className="flex-container">
      <div className="container mt-3">
        <p>
          Gerenciador de hardware novo/usado. Cadastrar cliente, máquina e produtos
          para venda.
        </p>
        <ul className="nav">
          <li className="nav-item">
            <Link to="/client" className="animated-button9">
              Cliente
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/cpu" className="animated-button9">
              CPU
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/ram" className="animated-button9">
              RAM
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/ssd" className="animated-button9">
              SSD
            </Link>
          </li>
          <li className="nav-item">
            <Link to="#" className="animated-button9">
              M.2
            </Link>
          </li>
        </ul>
      </div>
      <div className="forms">
        <form id="formulario" onSubmit={handleSubmit}>
          <div className="teste">
            <input
              type="text"
              name="cpf"
              id="cpf"
              value={cpf}
              className="form-control"
              placeholder="Pesquisar CPF"
              onChange={(e) => setCpf(e.target.value)}
            />
            <button type="submit" className="btn btn-primary">
              <i className="fa-solid fa-check"></i> Pesquisar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Home;

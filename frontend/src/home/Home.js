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
      <div className="container mt-4">
        <p>
          Gerenciador de hardware novo/usado. Cadastrar cliente, máquina e produtos
          para venda.
        </p>
        <ul className="nav">
          <li className="nav-item">
            <button type="button" class="btn btn-primary">

              <Link to="/client" className="animated-button9">
                Cliente
              </Link>
            </button>
          </li>

          <li className="nav-item">
            <button type="button" class="btn btn-primary">
              <Link to="/cpu" className="animated-button9">
                CPU
              </Link>
            </button>
          </li>

          <li className="nav-item">
            <button type="button" class="btn btn-primary">

              <Link to="/ram" className="animated-button9">
                RAM
              </Link>
            </button>
          </li>

          <li className="nav-item">
            <button type="button" class="btn btn-primary">
              <Link to="/ssd" className="animated-button9">
                SSD
              </Link>
            </button>
          </li>
          <li className="nav-item">
            <button type="button" class="btn btn-primary">

              <Link to="#" className="animated-button9">
                M.2
              </Link>
            </button>
          </li>
        </ul>
      </div>
      <div className="forms">
        <form id="formulario" onSubmit={handleSubmit}>
          <div className="teste">
            <div className="name">
              Pesquisar cliente
            </div>
            <input
              type="text"
              name="cpf"
              id="cpf"
              value={cpf}
              className="form-control"
              placeholder="Informe o cpf"
              onChange={(e) => setCpf(e.target.value)}
            />
            <br></br>
            <button type="submit" className="btn btn-primary">
              <i class="fa-solid fa-magnifying-glass"></i> Pesquisar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Home;

import "./Home.css";
import { Link } from "react-router-dom";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import NavBar from "../navbar/NavBar";
const Home = () => {
  const history = useNavigate();
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
    <div className="meu-form">
          <NavBar></NavBar>
      <div class="d-flex flex-column align-items-center">
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

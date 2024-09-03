import "./Home.css";
import { Link } from "react-router-dom";
import axios from "axios";
import { useState } from "react";
import NavBar from "../navbar/NavBar";

const Home = () => {
  const [cpf, setCpf] = useState("");
  const [client, setClient] = useState(null); // Estado para armazenar os dados do cliente

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = `http://localhost:9090/client/search/${cpf}`;

    try {
      const response = await axios.get(url);
      if (response && response.status === 200 && response.data) {
        console.log("Cliente encontrado:", response.data);
        setClient(response.data); // Armazena os dados do cliente
      } else {
        console.log("Nenhum cliente encontrado.");
        setClient(null); // Reseta o estado se nenhum cliente for encontrado
      }
      setCpf("");
    } catch (error) {
      console.error("Erro ao pesquisar CPF:", error);
      setClient(null); // Reseta o estado em caso de erro
    }
  };
  const remove = async (id) => {
    alert("Deseja mesmo excluir?");
    try {
      await axios.delete(`http://localhost:9090/client/${id}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });
      setClient(null);
      console.log('Cliente excluído com sucesso.');
    } catch (error) {
      console.error("Erro ao excluir o cliente:", error);
    }
  }
  return (
    <div className="meu-form">
      <NavBar />
  
      <div className="row">
        <div className="col">
          <div className="d-flex flex-column align-items-center">
            <form id="formulario" onSubmit={handleSubmit}>
              <div className="teste">
                <ul>
                  Cadastrar clientes, peças, serviços realizados
                  Cadastro de hardware
               
                </ul>
                <div className="pesquisa">
                  Pesquisar cliente
                  <input
                    type="text"
                    name="cpf"
                    id="cpf"
                    value={cpf}
                    className="form-control"
                    placeholder="Informe o CPF"
                    onChange={(e) => setCpf(e.target.value)}
                  />
                </div>
                <button type="submit" className="btn btn-primary">
                  <i className="fa-solid fa-magnifying-glass"></i> Pesquisar
                </button>
              </div>
            </form>
            {client && ( // Renderiza a tabela apenas se o cliente for encontrado
              <div className="tabela">
                <table>
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Nome</th>
                      <th>E-mail</th>
                      <th>Cpf</th>
                      <th>Contato</th>
                      <th>Rua</th>
                      <th>Nº</th>
                      <th>Estado</th>
                      <th>Cidade</th>
                      <th>Data cadastro</th>
                      <th>Ações</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr key={client.id}>
                      <td>{client.id}</td>
                      <td>{client.name}</td>
                      <td>{client.email}</td>
                      <td>{client.cpf || "N/A"}</td>
                      <td>{client.contact}</td>
                      <td>{client.address ? client.address.street : ""}</td>
                      <td>{client.address ? client.address.number : ""}</td>
                      <td>{client.address ? client.address.district : ""}</td>
                      <td>{client.address ? client.address.city : ""}</td>
                      <td>{client.dataRegister}</td>
                      <td>
                        <button>
                          <Link to={`/clientEdit/${client.id}`} className="btn btn-success">
                            <i className="far fa-edit"></i>
                          </Link>
                        </button>
                      </td>
                      <td>
                        <button onClick={() => remove(client.id)} className="btn btn-danger">
                          <i className="fas fa-eraser"></i>
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;

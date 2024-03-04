import React, { Component } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import NavBar from "../navbar/NavBar";
import "./ClientList.css";

class ListClient extends Component {
  state = {
    clients: [],
  };

  async remove(id) {
    alert("Deseja mesmo excluir?");
    axios
      .delete(`http://localhost:9090/client/${id}`, {
        method: "DELETE",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      })
      .then(() => {
        let updateClient = [...this.state.clients].filter((i) => i.id !== id);
        this.setState({ clients: updateClient });
      });
  }

  componentDidMount() {
    axios.get("http://localhost:9090/client").then((res) => {
      const clients = res.data;
      this.setState({ clients });
    });
  }

  render() {
    return (
      <tbody>
        <div className="tabela">
          <NavBar />
          <br></br>
          <div className="title">Clientes</div>
          <br></br>
          <hr></hr>

        </div>
        <div className="botoes"></div>
        <table>
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
          {this.state.clients.map((client) => (
            <tr key={client.id}>
              <td>{client.id}</td>
              <td>{client.name}</td>
              <td>{client.email}</td>
              <td>{client.cpf ? client.cpf : "N/A"}</td>
              <td>{client.contact}</td>

              <td>{client.address ? client.address.street : ""}</td>
              <td>{client.address ? client.address.number : ""}</td>
              <td>{client.address ? client.address.district : ""}</td>
              <td>{client.address ? client.address.city : ""}</td>
              <td>{client.address ? client.dataRegister : ""}</td>
              <td>
                <button>
                  <Link to={`/clientEdit/${client.id}`} className="btn btn-sucess">
                    <i className="far fa-edit"></i>
                  </Link>
                </button>
              </td>
              <td>
                <button onClick={() => this.remove(client.id)} className="btn btn-danger">
                  <i className="fas fa-eraser"></i>
                </button>
              </td>
            </tr>
          ))}
        </table>
      </tbody>
    );
  }
}

export default ListClient;

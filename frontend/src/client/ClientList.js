import React, { Component } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import NavBar from "../navbar/NavBar";
import "../client/ClientList.css";

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

  async componentDidMount() {
    try {
      const res = await axios.get("http://localhost:9090/client");
      const clients = res.data;
      if (clients.length === 0) {
        this.setState({ clients: [], listaVazia: true });
      } else {
        this.setState({ clients: clients, listaVazia: false });
      }
    } catch (error) {
      console.log("Erro ao buscar todos os clientes.");
      this.setState({ error: "Erro ao listar clientes" });
    }

  }
  render() {
    return (
      <div className="client-list">
      <NavBar></NavBar>
        <table>
           <div className="client-table">
           <tr>
              <th id="th-1">Id</th>
              <th id="th-2">Nome</th>
              <th id="th-3">E-mail</th>
              <th id="th-4">Cpf</th>
              <th id="th-5">Contato</th>
              <th id="th-6">Rua</th>
              <th id="th-7">Nº</th>
              <th id="th-8">Estado</th>
              <th id="th-9">Cidade</th>
              <th id="th-0">Data cadastro</th>
              <th id="th-00"> Opções</th>
            </tr>
            </div>
          <tbody>
            {this.state.clients.map((client) => (
              <tr key={client.id}>
                <td id="td-1">{client.id}</td>
                <td id="td-2">{client.name}</td>
                <td id="td-3">{client.email}</td>
                <td id="td-4">{client.cpf ? client.cpf : "N/A"}</td>
                <td id="td-5">{client.contact}</td>
                <td id="td-6">{client.address ? client.address.street : ""}</td>
                <td id="td-7">{client.address ? client.address.number : ""}</td>
                <td id="td-8">{client.address ? client.address.district : ""}</td>
                <td id="td-9">{client.address ? client.address.city : ""}</td>
                <td id="td-0">{client.address ? client.dataRegister : ""} </td>
                <td id="td-00">
                  <button>
                    <Link to={`/clientEdit/${client.id}`} className="btn btn-success btn-link">
                      <i className="fas fa-edit"></i>
                    </Link>
                  </button>
                  <button onClick={() => this.remove(client.id)} className="btn btn-danger">
                    <i className="fas fa-eraser"></i>
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    
    );
  }
}

export default ListClient;

import React, { Component } from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import { useHistory, useParams } from "react-router-dom";

import "./SsdList.css";
import { Button } from "bootstrap";

class SsdList extends Component {
  
  state = {
    ssds: [],
  };

  async remove(id) {
      alert("Deseja mesmo excluir?")
    axios.delete(`http://localhost:9090/ssd/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updateSsd = [...this.state.ssds].filter(i => i.id !== id);
      this.setState({ ssds: updateSsd });
    })

  }

  componentDidMount() {
    axios.get("http://localhost:9090/ssd/").then((res) => {
      const ssds = res.data;
      this.setState({ ssds });

    });

  }

  render() {
    return (
      <tbody>
        <div className="tabela">Ssd's Cadastrados

          <button type="button" class="btn">
            <Link to="/">
              Home <i class="fa-regular fa-user"></i>
            </Link>
          </button>
          <button type="button" class="btn">
            <Link to="/ssdlist">Listar todos</Link>
          </button>
        </div>
        <div className="botoes">

        </div>
        <table>
          <tr>
            <th>Id</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Nº de série</th>
            <th>Capacidade</th>
            <th>Data de compra</th>
            <th>Preço de compra</th>
            <th>Data de venda</th>
            <th>Preço de venda</th>
            <th>Url</th>
          </tr>
          {this.state.ssds.map((ssd) => (
            <tr>
              <td>{ssd.id}</td>
              <td>{ssd.brand}</td>
              <td>{ssd.model}</td>
              <td>{ssd.serialNumber}</td>
              <td>{ssd.size}</td>
              <td>{ssd.purchaseDate}</td>
              <td>{ssd.purchasePrice}</td>
              <td>{ssd.arrivalDate}</td>
              <td>{ssd.saleValue}</td>
              <td>
                <a href={ssd.url}>url</a>
              </td>

              <td><button><Link to={`/ssdEdit/${ssd.id}`} className="btn btn-sucess">Editar </Link></button></td>
              <td><button onClick={() => this.remove(ssd.id)} className="btn btn-danger">Excluir </button></td>
            </tr>
          ))}
        </table>
      </tbody>
    );
  }
}
export default SsdList;

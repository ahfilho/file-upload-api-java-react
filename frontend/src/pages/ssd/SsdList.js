import React, { Component } from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import { useHistory, useParams } from "react-router-dom";
import NavBar from "../../navbar/NavBar";
import "./SsdList.css";
import { Button } from "react-bootstrap";


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
    axios.get("http://localhost:9090/ssd").then((res) => {
      const ssds = res.data;
      this.setState({ ssds });

    });

  }

  render() {
    return (
      <tbody>
        <div className="tabela">
          <NavBar></NavBar>
          <br></br>
          <div className="title">SSD SATA</div>
          <br></br>
          <hr></hr>
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
            <th>Unidade(s)</th>
            <th>Data de compra</th>
            <th>Preço de compra</th>
            <th>Data de venda</th>
            <th>Preço de venda</th>
            <th>Mídia</th>
            <th>Bytes</th>
          </tr>
          {this.state.ssds.map((ssd) => (
            <tr>
              <td>{ssd.id}</td>
              <td>{ssd.brand}</td>
              <td>{ssd.model}</td>
              <td>{ssd.serialNumber}</td>
              <td>{ssd.size}</td>
              <td>{ssd.amount}</td>
              <td>{ssd.purchaseDate}</td>
              <td>{ssd.purchasePrice}</td>
              <td>{ssd.arrivalDate ? ssd.arrivalDate : "N/A"}</td>
              <td>{ssd.saleValue}</td>
              <td>{ssd.imgSsd ? ssd.imgSsd.fileName : "N/A "}</td>
              <td>{ssd.imgSsd ? ssd.imgSsd.fileSize : "N/A "}</td>

              <td>
                <a href={ssd.url}><i class="fas fa-download"></i></a>
              </td>
              <td><button> <Link to={`/ssdEdit/${ssd.id}`} className="btn btn-sucess"><i class='far fa-edit'></i>
              </Link></button></td>
              <td><button onClick={() => this.remove(ssd.id)} className="btn btn-danger"><i class="fas fa-eraser"></i> </button></td>
            </tr>
          ))}
        </table>
      </tbody>
    );
  }
}
export default SsdList;

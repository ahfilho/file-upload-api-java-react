import React, { Component } from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import { useHistory, useParams } from "react-router-dom";
import NavBar from "../../src/navbar/NavBar";
import "./Cpu.css";
import { Button } from "bootstrap";

class CpuList extends Component {

  state = {
    cpus: [],
  };

  async remove(id) {
    alert("Deseja mesmo excluir?")
    axios.delete(`http://localhost:9090/cpu/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updateCpu = [...this.state.cpus].filter(i => i.id !== id);
      this.setState({ cpus: updateCpu });
    })

  }

  componentDidMount() {
    axios.get("http://localhost:9090/cpu").then((res) => {
      const cpus = res.data;
      this.setState({ cpus });

    });

  }

  render() {
    return (
      <tbody>
        <div className="tabela">
          <NavBar></NavBar>
          <br></br>
          <div className="title">CPU</div>
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
            <th>Referência</th>
            <th>Cores</th>
            <th>Threads</th>
            <th>Clock</th>
            <th>Data de compra</th>
            <th>Preço de compra</th>
            <th>Data de venda</th>
            <th>Preço de venda</th>
            <th>Mídia</th>
            <th>Bytes</th>
          </tr>
          {this.state.cpus.map((cpu) => (
            <tr>
              <td>{cpu.id}</td>
              <td>{cpu.brand}</td>
              <td>{cpu.model}</td>
              <td>{cpu.serialNumber}</td>
              <td>{cpu.coreCount}</td>
              <td>{cpu.threadCount}</td>
              <th>{cpu.clockCount}</th>
              <td>{cpu.purchaseDate}</td>
              <td>{cpu.purchasePrice}</td>
              <td>{cpu.saleValue}</td>
              <td>{cpu.image ? cpu.image.fileName : ""}</td>
              <td>{cpu.image ? cpu.image.fileSize : ""}</td>

              <td>
                <a href={cpu.url}><i class="fas fa-download"></i></a>
              </td>
              <td><button> <Link to={`/cpuEdit/${cpu.id}`} className="btn btn-sucess"><i class='far fa-edit'></i>
              </Link></button></td>
              <td><button onClick={() => this.remove(cpu.id)} className="btn btn-danger"><i class="fas fa-eraser"></i> </button></td>
            </tr>
          ))}
        </table>
      </tbody>
    );
  }
}
export default CpuList;

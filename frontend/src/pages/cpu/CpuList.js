import React, { Component } from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import { useHistory, useParams } from "react-router-dom";
import NavBar from "../../navbar/NavBar";
import "./CpuList.css";
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

  async componentDidMount() {
    try {
      const res = await axios.get("http://localhost:9090/cpu");
      const cpus = res.data;
      if (cpus.length === 0) {
        this.setState({ cpus: [], listaVazia: true });
      } else {
        this.setState({ cpus: cpus, listaVazia: false });
      }
    } catch (error) {
      console.log("Erro ao buscar todos os cpu's.");
      this.setState({ error: "Erro ao listar cpu" });
    }

  }

  render() {
    return (
      <tbody>
        <div className="tabela">
          <NavBar></NavBar>
          <br></br>
          <div className="title">Cpu</div>
          <br></br>
          <hr></hr>
        </div>
        <div className="botoes">

        </div>
        <table>
          <tr>
            <th>Id</th>
            <th>Fabricante</th>
            <th>Modelo</th>
            <th>Referência</th>
            <th>Cores</th>
            <th>Threads</th>
            <th>Clock</th>
            <th>Data de compra</th>
            <th>Preço compra</th>
            {/* <th>Data de venda</th> */}
            <th>Preço venda</th>
            <th>Mídia</th>
            <th>Bytes</th>
            <th>Visualisar</th>
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
              {/* <td>{cpu.dateOfSale}</td> */}

              <td>{cpu.saleValue}</td>
              <td>{cpu.imgCpu ? cpu.imgCpu.fileName : ""}</td>
              <td>{cpu.imgCpu ? cpu.imgCpu.fileSize : ""}</td>

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

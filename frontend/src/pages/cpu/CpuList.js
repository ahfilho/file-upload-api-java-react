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
      <div className="cpu-list">
        <NavBar></NavBar>
        <table>
          <div className="client-table">

            <tr>
              <th id="th-1">Id</th>
              <th id="th-2">Marca</th>
              <th id="th-3">Modelo</th>
              <th id="th-4">N/Série</th>
              <th id="th-5">Cores</th>
              <th id="th-6">Threads</th>
              <th id="th-7">Clock</th>
              <th id="th-8">Dt/Compra</th>
              <th id="th-9">Prç/Compra</th>
              <th id="th-0">Prç/Venda</th>
              <th id="th-00">Mídia</th>
              <th id="th-01">Bytes</th>
              <th id="th-02">Ver</th>
              <th id="th-03"></th>
            </tr>
          </div>
          <tbody>
            {this.state.cpus.map((cpu) => (
              <tr>
                <td id="td-1">{cpu.id}</td>
                <td id="td-2">{cpu.brand}</td>
                <td id="td-3">{cpu.model}</td>
                <td id="td-4">{cpu.serialNumber}</td>
                <td id="td-5">{cpu.coreCount}</td>
                <td id="td-6">{cpu.threadCount}</td>
                <td id="td-7">{cpu.clockCount}</td>
                <td id="td-8">{cpu.purchaseDate}</td>
                <td id="td-9">{cpu.purchasePrice}</td>
                {/* <td>{cpu.dateOfSale}</td> */}

                <td id="td-0">{cpu.saleValue}</td>
                <td id="td-01">{cpu.imgCpu ? cpu.imgCpu.fileName : ""}</td>
                <td id="td-02">{cpu.imgCpu ? cpu.imgCpu.fileSize : ""}</td>

                <td id="td-03">
                  <a href={cpu.url}><i class="fas fa-download"></i></a>
                </td>
                <td id="td-04">
                  <button> 
                    <Link to={`/cpuEdit/${cpu.id}`} className="btn btn-sucess"><i class='far fa-edit'></i>
                  </Link></button>
                  <button onClick={() => this.remove(cpu.id)} className="btn btn-danger"><i class="fas fa-eraser"></i> </button></td>
              </tr>
            ))}
          </tbody>
        </table>

      </div>
    );
  }
}
export default CpuList;

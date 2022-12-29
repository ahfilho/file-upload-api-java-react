import React from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";

import "./RamList.css";

export default class CpuList extends React.Component {
  state = {
    cpus: [],
  };

  componentDidMount() {
    axios.get("http://localhost:9090/cpu/list").then((res) => {
      const cpus = res.data;
      this.setState({ cpus });
    });
  }
  render() {
    return (
      <tbody>
        <div className="tabela">
          Cpu's Cadastradas
          <Link to="/">Início</Link>
        </div>
        <div className="botoes"></div>
        <table>
          <tr>
            <th>Id</th>
            <th>Marca</th>
            <th>Frequência</th>
            <th>Modelo</th>
            <th>Nº de série</th>
            <th>Capacidade</th>
            <th>Data de compra</th>
            <th>Preço de compra</th>
            <th>Data de venda</th>
            <th>Preço de venda</th>
            <th>Categoria do produto</th>
            <th>Url</th>
            <th></th>
          </tr>
          {this.state.cpus.map((cpu) => (
            <tr>
              <td>{cpu.id}</td>
              <td>{cpu.brand}</td>
              <td>{cpu.mhz}</td>
              <td>{cpu.model}</td>
              <td>{cpu.serialNumber}</td>
              <td>{cpu.size}</td>
              <td>{cpu.purchaseDate}</td>
              <td>{cpu.purchasePrice}</td>
              <td>{cpu.arrivalDate}</td>
              <td>{cpu.saleValue}</td>
              <td>{cpu.productCategory}</td>
              <td>
                <a href={cpu.url}>url</a>
              </td>

              <td>Alterar</td>
              <td>Excluir</td>
            </tr>
          ))}
          {/* {this.state.categories.map((cate) => (
            <tr>
              <td>{cate.productCategory}</td>
            </tr>
          ))} */}
        </table>
      </tbody>
    );
  }
}

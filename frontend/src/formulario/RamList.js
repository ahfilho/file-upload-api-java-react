import React from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";

import "./RamList.css";

export default class RamList extends React.Component {
  state = {
    rams: [],
  };

  componentDidMount() {
    axios.get("http://localhost:9090/ram/list").then((res) => {
      const rams = res.data;
      this.setState({ rams });
    });
  }
  render() {
    return (
      <tbody>
        <div className="tabela">
          Ram Cadastradas
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
          {this.state.rams.map((ram) => (
            <tr>
              <td>{ram.id}</td>
              <td>{ram.brand}</td>
              <td>{ram.mhz}</td>
              <td>{ram.model}</td>
              <td>{ram.serialNumber}</td>
              <td>{ram.size}</td>
              <td>{ram.purchaseDate}</td>
              <td>{ram.purchasePrice}</td>
              <td>{ram.arrivalDate}</td>
              <td>{ram.saleValue}</td>
              <td>{ram.productCategory}</td>
              <td>
                <a href={ram.url}>url</a>
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

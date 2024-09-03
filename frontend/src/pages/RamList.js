import React from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import NavBar from "../navbar/NavBar";

import "./RamList.css";

export default class RamList extends React.Component {
  state = {
    rams: [],
  };

  async remove(id) {
    alert("Deseja mesmo excluir?")
    axios.delete(`http://localhost:9090/ram/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updateRam = [...this.state.rams].filter(i => i.id);
      this.setState({ rams: updateRam });

    })

  }

  async componentDidMount() {
    try {
      const res = await axios.get("http://localhost:9090/ram");
      const rams = res.data;
      if (rams.length === 0) {
        this.setState({ rams: [], listaVazia: true });
      } else {
        this.setState({ rams: rams, listaVazia: false });
      }
    } catch (error) {
      console.log("Erro ao buscar a lista de RAM", error);
      this.setState({ error: "Erro na lista de ram" });
    }
  }
  render() {
    return (
      <div className="teste">
        <NavBar></NavBar>
        <table>
          <tr>
            <th>Id</th>
            <th>Marca</th>
            <th>Frequência</th>
            <th>Modelo</th>
            <th>Nº de série</th>
            <th>Capacidade</th>
            <th>Data compra</th>
            <th>Preço compra</th>
            <th>Data venda</th>
            <th>Preço venda</th>
            <th>Mídia</th>
            <th>Bytes</th>
            <th>Url</th>
          </tr>
          <tbody>
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
                <td>{ram.imgRam ? ram.imgRam.fileName : "N/A"}</td>
                <td>{ram.imgRam ? ram.imgRam.fileSize : "N/A"}</td>
                <td>{ <a href={ram.url}><i class="fas fa-download"></i></a>}</td>
                <td>{<button>
                  <Link to={`/ramEdit/${ram.id}`} className="btn btn-sucess">
                    <i class='far fa-edit'></i>
                  </Link>
                  <button onClick={() => this.remove(ram.id)} className="btn btn-danger">
                    <i className="fas fa-eraser"></i>
                  </button>
                </button>
                }
                </td>  
              </tr>
              
            ))}
            {/* {this.state.categories.map((cate) => (
            <tr>
              <td>{cate.productCategory}</td>
            </tr>
          ))} */}
          </tbody>
        </table>
      </div>
    );
  }
}

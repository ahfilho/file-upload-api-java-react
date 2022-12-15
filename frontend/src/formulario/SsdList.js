import React from "react";
import axios from "axios";
import "./SsdList.css";

export default class SsdList extends React.Component {
  state = {
    ssds: [],
  };
 
  componentDidMount() {
    axios.get("http://localhost:9090/ssd/list").then((res) => {
      const ssds = res.data;
      this.setState({ ssds });
      
    });
  }
  render() {
    return (
      <tbody>
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
            <th>Categoria do produto</th>
            <th>Url</th>
            <th></th>
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
              <td>{ssd.productCategory}</td>
              <td>
                <a href={ssd.url}>url</a>
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

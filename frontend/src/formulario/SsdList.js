import React from "react";
import axios from "axios";

export default class SsdList extends React.Component {
  state = {
    ssds: [],
  };
  // state = {
  //   categories: []
  // }

  componentDidMount() {
    axios.get("http://localhost:9090/ssd/list").then((res) => {
      const ssds = res.data;
      this.setState({ ssds });

      // const categories = res.data;
      // this.setCategories ({ categories});
    });
  }
  render() {
    return (
      <ul>
        {this.state.ssds.map((ssd) => (
          <li>
            <div className="Todos"></div>
            <label></label>
            <li>
              {ssd.id}
              <br></br>
              {ssd.brand}
              <br></br>
              {ssd.model}
              <br></br>
              {ssd.serialNumber}
              <br></br>
              {ssd.purchaseDate}
              <br></br>
              {ssd.purchasePrice}
              <br></br>
              {ssd.arrivalDate}
              <br></br>
              {ssd.saleValue}
            </li>
            ------------- TESTE AQUI -------------------
          </li>
        ))}
      </ul>
    );
  }
}

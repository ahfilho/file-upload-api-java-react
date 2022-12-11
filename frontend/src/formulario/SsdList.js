import React from "react";
import axios from "axios";
import "./SsdList.css";

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
            <div className="todos"></div>
            <label></label>
            <li>
              ID
              {ssd.id}
              {ssd.brand}
              {ssd.model}
              {ssd.size}
              {ssd.serialNumber}
              {ssd.purchaseDate}
              {ssd.purchasePrice}
              {ssd.arrivalDate}
              {ssd.saleValue}
            <a href={ssd.url}>url</a>
            </li>
          </li>
        ))}
      </ul>
    );
  }
}

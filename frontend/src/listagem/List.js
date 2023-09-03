import React from "react";
import axios from "axios";

export default class List extends React.Component {

  state = {
    ssds: []
  }
  // state = {
  //   categories: []
  // }

  componentDidMount() {
    axios.get('http://localhost:9090/ssd/list').then(res => {
      const ssds = res.data;
      this.setState({ ssds });

      // const categories = res.data;
      // this.setCategories ({ categories});
    })
  }
  render() {
    return (
      <ul>
        {this.state.ssds.map((ssd) => (
          <li>
            <label></label>
            <li key={ssd.id}>{ssd.id}+
              {ssd.brand}+
              {ssd.model}+
              {ssd.size}+
              {ssd.purchasePrice}+
              {ssd.purchaseDate}+
              {ssd.arrivalDate}+
              {ssd.saleValue}+
              {ssd.url}</li>
            ------------- CATEGORIA AQUI -------------------
            <li key={ssd.id}>{ssd.productCategory}</li>
          </li>
        ))}
        {/* {this.state.categories.map((category) => (
        <li>
          <label></label>
          <li key={category.id}>{category.id}</li>

          <li key={category.id}>{category.productCategory}</li>
        </li>
      ))} */}
      </ul>
    );
  }

}

import axios from "axios";
import React, { useState } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import "./Ssd.css";
import NavBar from "../../navbar/NavBar";

const url = "http://localhost:9090/ssd";

const AddSsd = () => {
  //SSD
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [serialNumber, setSerialNumber] = useState("");
  const [size, setSize] = useState("");
  const [purchaseDate, setPurchaseDate] = useState("");
  const [purchasePrice, setPurchasePrice] = useState("");
  const [arrivalDate, setArrivalDate] = useState("");
  const [saleValue, setSaleValue] = useState("");
  const [amount, setAmount] = useState("");

  //CATEGORY
  const [productCategory, setProductCategory] = useState("");

  //file
  const [file, setFile] = useState(null);

  const handleImage = (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("file", file);
    formData.append("brand", brand);
    formData.append("model", model);
    formData.append("serialNumber", serialNumber);
    formData.append("purchaseDate", purchaseDate);
    formData.append("purchasePrice", purchasePrice);
    formData.append("arrivalDate", arrivalDate);
    formData.append("saleValue", saleValue);
    formData.append("size", size);
    formData.append("amount",amount);
    formData.append("productCategory", productCategory);

    console.log(formData);

    try {
      const response = await axios.post(url, formData, {
      });

      console.log(response.data);

      // alert("SALVO COM SUCESSO -- apenas para testes!");
      setBrand("");
      setModel("");
      setSerialNumber("");
      setSize("");
      setPurchaseDate("");
      setPurchasePrice("");
      setArrivalDate("");
      setSaleValue("");
      setProductCategory("");
      setAmount("");
      setFile(null);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <NavBar></NavBar>
      <br></br>
      <div className="title">Cadastro</div>
      <br></br>
      <hr></hr>
      <form id="formulario" onSubmit={handleSubmit}>
        <div className="file">
          <input type="file" name="file" onChange={handleImage} />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="brand"
            id="brand"
            value={brand}
            className="form-control"
            placeholder="Marca"
            onChange={(e) => setBrand(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="model"
            id="model"
            className="form-control"
            value={model}
            placeholder="Modelo"
            onChange={(e) => setModel(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="serialNumber"
            id="serialNumber"
            className="form-control"
            value={serialNumber}
            placeholder="Nº de série"
            onChange={(e) => setSerialNumber(e.target
              .value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="size"
            id="size"
            className="form-control"
            value={size}
            placeholder="Capacidade/GB"
            onChange={(e) => setSize(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="amount"
            id="amount"
            value={amount}
            className="form-control"
            placeholder="Quantidade"
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>
        <div className="inputs">
          Data de compra
          <input
            type={"date"}
            name="purchaseDate"
            id="purchaseDate"
            className="form-control"
            value={purchaseDate}
            placeholder="Data de compra"
            onChange={(e) => setPurchaseDate(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="purchasePrice"
            id="purchasePrice"
            className="form-control"
            value={purchasePrice}
            placeholder="Preço de compra"
            onChange={(e) => setPurchasePrice(e.target.value)}
          />
        </div>
        <div className="inputs">
          Data de venda
          <input
            type={"date"}
            name="arrivalDate"
            id="arrivalDate"
            className="form-control"
            value={arrivalDate}
            placeholder="Arrival date"
            onChange={(e) => setArrivalDate(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="saleValue"
            id="saleValue"
            value={saleValue}
            className="form-control"
            placeholder="Preço de venda"
            onChange={(e) => setSaleValue(e.target.value)}
          />
        </div>
        <br></br>
        <div className="inputs">

          <input class="btn btn-primary" type="submit" value="Submit" onChange={(e) => this.handleSubmit(e)}></input>
        </div>
      </form>

    </div>
  );
};
export default AddSsd;

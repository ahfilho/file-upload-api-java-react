import axios from "axios";
import React, { useState } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import "./Cpu.css";
import NavBar from "../../navbar/NavBar";
import { useTheme } from "styled-components";

const url = "http://localhost:9090/cpu";

const AddCpu = () => {
  //SSD
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [serialNumber, setSerialNumber] = useState("");
  const [purchaseDate, setPurchaseDate] = useState("");
  const [purchasePrice, setPurchasePrice] = useState("");
  const [arrivalDate, setArrivalDate] = useState("");
  const [saleValue, setSaleValue] = useState("");
  const [amount, setAmount] = useState("");
  const [coreCount, setCoreCount] = useState("");
  const [threadCount, setThreadCount] = useState("");
  const [clockCount, setClockCount] = useState("");

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
    formData.append("purchasePrice", parseFloat(purchasePrice));
    formData.append("arrivalDate", arrivalDate);
    formData.append("saleValue", saleValue);
    formData.append("amount", amount);
    formData.append("productCategory", productCategory);
    formData.append("coreCount", parseInt(coreCount));
    formData.append("threadCount", parseInt(threadCount));
    formData.append("clockCount", parseFloat(clockCount));

    console.log(formData);

    try {
      const response = await axios.post(url, formData, {
      });
      console.log(response.data);

      // alert("SALVO COM SUCESSO -- apenas para testes!");
      setBrand("");
      setModel("");
      setSerialNumber("");
      setPurchaseDate("");
      setPurchasePrice("");
      setArrivalDate("");
      setSaleValue("");
      setProductCategory("");
      setAmount("");
      setCoreCount("");
      setThreadCount("");
      setClockCount("");
      setFile(null);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <NavBar></NavBar>
      <div className="d-flex flex-column justify-content-center align-items-center vh-100">
        <form id="formulario" onSubmit={handleSubmit} className="w-50">
          <div className="file">
            <input type="file" name="file" onChange={handleImage} />
          </div>
          <div className="form-group mb-3">
            <div className="row">
              <div className="col">
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
              <div className="col mb-1">
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
            </div>
            <div className="col mb-1">
              <input
                type={"text"}
                name="serialNumber"
                id="serialNumber"
                className="form-control"
                value={serialNumber}
                placeholder="Número de série"
                onChange={(e) => setSerialNumber(e.target
                  .value)}
              />
            </div>
            <div className="row form-group mb-1">
            <div className="col">
              <input
                type={"text"}
                name="coreCount"
                id="coreCount"
                value={coreCount}
                className="form-control"
                placeholder="Cores"
                onChange={(e) => setCoreCount(e.target.value)}
              />
            </div>
            <div className="col">
              <input
                type={"text"}
                name="threadCount"
                id="threadCount"
                value={threadCount}
                className="form-control"
                placeholder="Threads"
                onChange={(e) => setThreadCount(e.target.value)}
              />
            </div>
            <div className="col">
              <input
                type={"text"}
                name="clockSpeed"
                id="clockSpeed"
                value={clockCount}
                className="form-control"
                placeholder="Clock"
                onChange={(e) => setClockCount(e.target.value)}
              />
            </div>
            </div>
            <div className="col">
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
            <div className="col">
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
            <div className="col">
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

            <div className="col">
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


          </div>
          <div className="inputs">

            <input class="btn btn-primary" type="submit" value="Submit" onChange={(e) => this.handleSubmit(e)}></input>
          </div>
        </form>
      </div>
    </div>
  );
};
export default AddCpu;

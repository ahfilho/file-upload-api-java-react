import axios from "axios";
import React from "react";
import { useState } from "react";
import "./ssd/Ssd.css";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";

const url = "http://localhost:9090/cpu/new";

const AddCpu = () => {
  //CPU
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [serialNumber, setSerialNumber] = useState("");
  const [core, setCore] = useState("");
  const [threads, setThreads] = useState("");
  const [clock, setClock] = useState("");

  const [overClock, setOverclock] = useState("");
  const [purchaseDate, setPurchaseDate] = useState("");
  const [purchasePrice, setPurchasePrice] = useState("");
  const [arrivalDate, setArrivalDate] = useState("");
  const [saleValue, setSaleValue] = useState("");

  //CATEGORY
  const [productCategory, setProductCategory] = useState("");

  //file
  const [file, setFile] = useState("");

  const handleImage = (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const cpu = {
      brand,
      model,
      core,
      clock,
      threads,
      overClock,
      serialNumber,
      purchaseDate,
      purchasePrice,
      arrivalDate,
      saleValue,
    };

    const category = {
      productCategory,
    };
    const formData = new FormData();
    formData.append("file", file);
    formData.append("brand", brand);
    formData.append("model", model);
    formData.append("serialNumber", serialNumber);
    formData.append("purchaseDate", purchaseDate);
    formData.append("purchasePrice", purchasePrice);
    formData.append("arrivalDate", arrivalDate);
    formData.append("saleValue", saleValue);
    formData.append("clock", clock);
    formData.append("core", core);
    formData.append("overclock", overClock);
    formData.append("threads", threads);

    formData.append("productCategory", productCategory);

    console.log(cpu, file, category);

    try {
      const response = await axios.post(url, formData, cpu, category, {
        brand: brand,
        model: model,
        threads: threads,
        core: core,
        clock: clock,
        overClock: overClock,
        serialNumber: serialNumber,
        purchaseDate: purchaseDate,
        purchasePrice: purchasePrice,
        arrivalDate: arrivalDate,
        saleValue: saleValue,

        productCategory: productCategory,
      });
      console.log(response.data);
      alert("SALVO COM SUCESSO -- apenas para testes!");
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <div className="form-row">
        <form id="meuForm" onSubmit={(e) => handleSubmit(e)}>
          <div className="title">Cadastrar novo CPU</div>

          <button>
            <Link to="/">Home</Link>
          </button>
          <button>
            <Link to="/cpulist">Listar todos</Link>
          </button>
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
              y
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
              onChange={(e) => setSerialNumber(e.target.value)}
            />
          </div>

          <div className="inputs">
            <input
              type={"text"}
              name="core"
              id="core"
              className="form-control"
              value={core}
              placeholder="Cores"
              onChange={(e) => setCore(e.target.value)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="clocl"
              id="clock"
              className="form-control"
              value={clock}
              placeholder="Clock"
              onChange={(e) => setClock(e.target.value)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="threads"
              id="threads"
              className="form-control"
              value={threads}
              placeholder="Nº de threads"
              onChange={(e) => setThreads(e.target.value)}
            />
          </div>

          <div className="inputs">
            {" "}
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
          <button
            type="submit"
            className="btn btn-success"
            onChange={(e) => this.handleSubmit(e)}
            // onClick={() => resetForm()}
          >
            Salvar
          </button>
        </form>
      </div>
    </div>
  );
};
export default AddCpu;

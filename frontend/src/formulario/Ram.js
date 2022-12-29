import axios from "axios";
import React from "react";
import { useState } from "react";
import "./Ssd.css";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";

const url = "http://localhost:9090/ram/new";

const AddRam = () => {
  //RAM
  const [brand, setBrand] = useState("");
  const [mhz, setMhz] = useState("");
  const [model, setModel] = useState("");
  const [serialNumber, setSerialNumber] = useState("");
  const [size, setSize] = useState("");
  const [purchaseDate, setPurchaseDate] = useState("");
  const [purchasePrice, setPurchasePrice] = useState("");
  const [arrivalDate, setArrivalDate] = useState("");
  const [saleValue, setSaleValue] = useState("");

  //CATEGORY
  const [productCategory, setProductCategory] = useState("");

  //FILE
  const [file, setFile] = useState("");

  const handleImage = (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const ram = {
      brand,
      mhz,
      model,
      serialNumber,
      size,
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
    formData.append("mhz", mhz);
    formData.append("model", model);
    formData.append("serialNumber", serialNumber);
    formData.append("purchaseDate", purchaseDate);
    formData.append("purchasePrice", purchasePrice);
    formData.append("arrivalDate", arrivalDate);
    formData.append("saleValue", saleValue);
    formData.append("size", size);

    formData.append("productCategory", productCategory);

    console.log(ram, file, category);

    try {
      const response = await axios.post(url, formData, ram, category, {
        brand: brand,
        mhz: mhz,
        model: model,
        serialNumber: serialNumber,
        size: size,
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
          <div className="title">Cadastrar novo SSD</div>

          <button>
            <Link to="/">Home</Link>
          </button>
          <button>
            <Link to="/ramlist">Listar todos</Link>
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
              name="mhz"
              id="mhz"
              className="form-control"
              value={mhz}
              placeholder="Mhz"
              onChange={(e) => setMhz(e.target.value)}
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
              name="size"
              id="size"
              className="form-control"
              value={size}
              placeholder="Capacidade/GB"
              onChange={(e) => setSize(e.target.value)}
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
export default AddRam;

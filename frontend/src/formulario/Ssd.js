import axios from "axios";
import React from "react";
import { useState } from "react";
import "./Ssd.css";

const url = "http://localhost:9090/ssd/new";

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

  //CATEGORY
  const [productCategory, setProductCategory] = useState("");

  //IMAGE
  const [file, setFile] = useState("");

  const fun = () => {
    document.getElementById("brand").value = "";
    document.getElementById("model").value = "";
    document.getElementById("serialNumber").value = "";
    document.getElementById("size").value = "";
    document.getElementById("purchaseDate").value = "";
    document.getElementById("purchasePrice").value = "";
    document.getElementById("arrivalDate").value = "";
    document.getElementById("saleValue").value = "";
  };
  const handleImage = (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };
  const apagaInput = () => {
    document.getElementById("brand").reset();
  };
  const resetForm = () => {
    setBrand("");
    setModel("");
    setSerialNumber("");
    setSize("");
    setPurchaseDate("");
    setPurchasePrice("");
    setArrivalDate("");
    setProductCategory("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    const ssd = {
      brand,
      model,
      serialNumber,
      size,
      purchaseDate,
      purchasePrice,
      arrivalDate,
      saleValue,
    };
    const resetForm = () => {
      setBrand("");
      setModel("");
      setSerialNumber("");
      setSize("");
      setPurchaseDate("");
      setPurchasePrice("");
      setArrivalDate("");
      setProductCategory("");
    };
    const category = {
      productCategory,
    };
    const formData = new FormData();
    formData.append("file", file);

    formData.append("brand", brand);
    formData.append("model", model);
    formData.append("size", size);
    formData.append("serialNumber", serialNumber);
    formData.append("purchaseDate", purchaseDate);
    formData.append("purchasePrice", purchasePrice);
    formData.append("arrivalDate", arrivalDate);
    formData.append("saleValue", saleValue);

    formData.append("productCategory", productCategory);

    console.log(ssd, file, category);

    try {
      const response = await axios.post(url, formData, ssd, category, {
        brand: brand,
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
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <div className="form-row">
        <form id="meuForm" onSubmit={(e) => handleSubmit(e)}>
          <div className="title">Cadastrar novo SSD</div>
          <div className="file">
            <input type="file" name="file" onChange={handleImage} />
          </div>
          <div className="inputs"></div>
          <input
            type={"text"}
            name="productCategory"
            id="productCategory"
            className="form-control"
            value={productCategory}
            placeholder="Categoria do produto"
            onChange={(e) => setProductCategory(e.target.value)}
            onClick={apagaInput}
          />
          <input
            type={"text"}
            name="brand"
            id="brand"
            value={brand}
            className="form-control"
            placeholder="Marca"
            onChange={(e) => setBrand(e.target.value)}
            onClick={apagaInput}
          />

          <input
            type={"text"}
            name="model"
            id="model"
            className="form-control"
            value={model}
            placeholder="Modelo"
            onChange={(e) => setModel(e.target.value)}
          />
          <input
            type={"text"}
            name="serialNumber"
            id="serialNumber"
            className="form-control"
            value={serialNumber}
            placeholder="Nº de série"
            onChange={(e) => setSerialNumber(e.target.value)}
          />
          <input
            type={"text"}
            name="size"
            id="size"
            className="form-control"
            value={size}
            placeholder="Capacidade/GB"
            onChange={(e) => setSize(e.target.value)}
          />
          <div className="inputs"> Data de compra</div>
          <input
            type={"date"}
            name="purchaseDate"
            id="purchaseDate"
            className="form-control"
            value={purchaseDate}
            placeholder="Data de compra"
            onChange={(e) => setPurchaseDate(e.target.value)}
          />
          <input
            type={"text"}
            name="purchasePrice"
            id="purchasePrice"
            className="form-control"
            value={purchasePrice}
            placeholder="Preço de compra"
            onChange={(e) => setPurchasePrice(e.target.value)}
          />
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
          <input
            type={"text"}
            name="saleValue"
            id="saleValue"
            value={saleValue}
            className="form-control"
            placeholder="Preço de venda"
            onChange={(e) => setSaleValue(e.target.value)}
          />
          <div className="input">
            <br></br>
            <button
              type="submit"
              className="btn btn-success"
              onChange={(e) => this.handleSubmit(e)}
              onClick={() => resetForm()}
            >
              Salvar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default AddSsd;
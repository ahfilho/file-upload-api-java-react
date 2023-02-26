import axios from "axios";
import React from "react";
import { useState, useEffect, useNavigate } from "react";
import { BrowserRouter as Router, Route, Link, Switch, useParams } from "react-router-dom";

import "./Ssd.css";

const url = "http://localhost:9090/ssd";

export default function SsdEdit() {

  const { id } = useParams();


  const [ssd, setSsd] = useState({
    id:"",
    brand: "",
    model: "",
    serialNumber: "",
    size: "",
    purchaseDate: "",
    purchasePrice: "",
    arrivalDate: "",
    saleValue: "",
  });

  //SSD
  const { brand, model, serialNumber, size,
    purchaseDate, purchasePrice, arrivalDate, saleValue } = ssd;

  //CATEGORY
  const [productCategory, setProductCategory] = useState("");

  //IMAGE
  const [file, setFile] = useState("");

  const onInputChange = (e) => {
    setSsd({
      ...ssd, [e.target.brand]: e.target.value,
      [e.target.model]: e.target.value,
      [e.target.size]: e.target.value,
      [e.target.serialNumber]: e.target.value,
      [e.target.purchaseDate]: e.target.value,
      [e.target.purchasePrice]: e.target.value,
      [e.target.arrivalDate]: e.target.value,
      [e.target.saleValue]: e.target.value,
      [e.target.file]: e.target.value
    });
  };

  const handleImage = (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };

  useEffect(() => {
    load();
  }, []);

  const load = async () => {
    const result = await axios.get(`http://localhost:9090/ssd/${id}`);
    setSsd(result.data);
  }

  const UpdatehandleSubmit = async (e) => {
    e.preventDefault();

    const category = {
      productCategory,
    };
    const formData = new FormData();
    formData.append("file", file);

    formData.append("id",id);
    formData.append("brand", brand);
    formData.append("model", model);
    formData.append("serialNumber", serialNumber);
    formData.append("purchaseDate", purchaseDate);
    formData.append("purchasePrice", purchasePrice);
    formData.append("arrivalDate", arrivalDate);
    formData.append("saleValue", saleValue);
    formData.append("size", size);

    formData.append("productCategory", productCategory);

    try {
      const response = await axios.put(`${url}/id`, formData, ssd, file, {

      });
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <div className="form-row">

        <form id="meuForm" onSubmit={(e) => UpdatehandleSubmit(e)}>
          <div className="title">Alteração de produto</div>
          <div className="botoes">
            <button type="button" class="btn">
              <Link to="/">
                Home <i class="fa-regular fa-user"></i>
              </Link>
            </button>
            <button type="button" class="btn">
              <Link to="/ssdlist">Listar todos</Link>
            </button>
          </div>
          <div className="file">
            <input type="file" name="file"
              defaultValue={file}
              onChange={handleImage} />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="brand"
              id="brand"
              defaultValue={brand}
              className="form-control"
              placeholder="Marca"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="model"
              id="model"
              className="form-control"
              defaultValue={model}
              placeholder="Modelo"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="serialNumber"
              id="serialNumber"
              className="form-control"
              defaultValue={serialNumber}
              placeholder="Nº de série"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="size"
              id="size"
              className="form-control"
              defaultValue={size}
              placeholder="Capacidade/GB"
              onChange={(e) => onInputChange(e)}
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
              defaultValue={purchaseDate}
              placeholder="Data de compra"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="purchasePrice"
              id="purchasePrice"
              className="form-control"
              defaultValue={purchasePrice}
              placeholder="Preço de compra"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="inputs">
            Data de venda
            <input
              type={"date"}
              name="arrivalDate"
              id="arrivalDate"
              className="form-control"
              defaultValue={arrivalDate}
              placeholder="Arrival date"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="inputs">
            <input
              type={"text"}
              name="saleValue"
              id="saleValue"
              defaultValue={saleValue}
              className="form-control"
              placeholder="Preço de venda"
              onChange={(e) => onInputChange(e)}
            />
          </div>
          <div className="botaoSave">
            <br></br>
            <button
              type="submit"
              className="btn btn-success"
              onChange={(e) => this.handleSubmit(e)}
            // onClick={() => resetForm()}
            >
              Atualizar
            </button>
            <button class="btn btn-danger">
              <Link to="/ssdlist">Cancelar</Link>
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

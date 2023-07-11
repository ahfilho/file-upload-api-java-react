import axios from "axios";
import React from "react";
import { useState, useEffect, useNavigate } from "react";
import { BrowserRouter as Router, Route, Link, Switch, useParams } from "react-router-dom";
import "./Cpu.css";
import NavBar from "../navbar/NavBar";

const url = "http://localhost:9090/cpu";

const CpuEdit = () => {
  const { id } = useParams();
  const [cpu, setCpu] = useState({
    brand: "",
    model: "",
    serialNumber: "",
    coreCount: "",
    threadCount: "",
    clockCount: "",
    purchaseDate: "",
    purchasePrice: "",
    arrivalDate: "",
    saleValue: "",
    amount: ""
  });
  const [file, setFile] = useState("");

  useEffect(() => {
    axios.get(`${url}/${id}`)
      .then((result) => {
        setCpu(result.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [id]);

  const onInputChange = (e) => {
    setCpu({
      ...cpu,
      [e.target.name]: e.target.value,
    });
  };

  const handleImage = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();

    formData.append("file", file);
    formData.append("brand", cpu.brand);
    formData.append("model", cpu.model);
    formData.append("serialNumber", cpu.serialNumber);
    formData.append("coreCount", cpu.coreCount);
    formData.append("threadCount", cpu.threadCount);
    formData.append("clockCount", cpu.clockCount);
    formData.append("purchasePrice", cpu.purchasePrice);
    formData.append("arrivalDate", cpu.arrivalDate);
    formData.append("saleValue", cpu.saleValue);
    formData.append("amount", cpu.amount);

    axios
      .put(`${url}/${id}`, formData)
      .then((response) => {
        alert("Alterado com sucesso!")

        console.log(response);
      })
      .catch((error) => {
        console.log(error.response);
      });
  };

  return (
    <div className="meuForm">
      <NavBar></NavBar>
      <br></br>
      <div className="title">Alteração</div>
      <br></br>
      <hr></hr>
      <form id="formulario" onSubmit={handleSubmit}>
        <div className="file">
          <input type="file" name="file"
            onChange={handleImage} />
        </div>

        <div className="inputs">
          <input
            type={"text"}
            name="brand"
            id="brand"
            defaultValue={cpu.brand}
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
            defaultValue={cpu.model}
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
            defaultValue={cpu.serialNumber}
            placeholder="Referência"
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="coreCount"
            id="coreCount"
            className="form-control"
            defaultValue={cpu.coreCount}
            placeholder="Cores(Núcleos)"
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="threadCount"
            id="coreCouthreadCountnt"
            className="form-control"
            defaultValue={cpu.threadCount}
            placeholder="Threads"
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="clockCount"
            id="clockCount"
            className="form-control"
            defaultValue={cpu.clockCount}
            placeholder="Velocidade"
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="amount"
            id="amount"
            className="form-control"
            defaultValue={cpu.amount}
            placeholder="Quantidade(UN)"
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
            defaultValue={cpu.purchaseDate}
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
            defaultValue={cpu.purchasePrice}
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
            defaultValue={cpu.arrivalDate}
            placeholder="Arrival date"
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="saleValue"
            id="saleValue"
            defaultValue={cpu.saleValue}
            className="form-control"
            placeholder="Preço de venda"
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="botaoSave">
          <br></br>
          <button
            type="submit"
            className="btn btn-primary"
            onChange={(e) => this.handleSubmit(e)}
          >
            Atualizar
          </button>
          <button class="btn btn-danger">
            <Link to="/ssdlist">Cancelar</Link>
          </button>
        </div>
      </form>
    </div>
  );
};
export default CpuEdit;

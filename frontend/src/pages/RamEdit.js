import axios from "axios";
import React from "react";
import { useState, useEffect, useNavigate } from "react";
import { BrowserRouter as Router, Route, Link, Switch, useParams } from "react-router-dom";
import "./Ram.css";
import NavBar from "../navbar/NavBar";
import { useHistory } from "react-router-dom"; // import do hook

const url = "http://localhost:9090/ram";
const url2 = "http://localhost:9090/ram/redirect";


const RamEdit = () => {
  const history = useHistory();
  const { id } = useParams();
  const [ram, setRam] = useState({
    brand: "",
    model: "",
    mhz: "",
    size: "",
    purchaseDate: "",
    purchasePrice: "",
    arrivalDate: "",
    saleValue: "",
  });
  const [file, setFile] = useState("");

  useEffect(() => {
    axios.get(`${url2}/${id}`)
      .then((result) => {
        setRam(result.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [id]);

  const onInputChange = (e) => {
    setRam({
      ...ram,
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
    formData.append("brand", ram.brand);
    formData.append("model", ram.model);
    formData.append("mhz", ram.mhz);
    formData.append("size", ram.size);
    formData.append("purchaseDate", ram.purchaseDate);
    formData.append("purchasePrice", ram.purchasePrice);
    formData.append("arrivalDate", ram.arrivalDate);
    formData.append("saleValue", ram.saleValue);
    formData.append("serialNumber",ram.serialNumber);

    axios
      .put(`${url}/${id}`, formData)
      .then((response) => {
        alert("Alterado com sucesso!");
        setRam({
          brand: "",
          model: "",
          serialNumber: "",
          size: "",
          purchaseDate: "",
          purchasePrice: "",
          arrivalDate: "",
          saleValue: "",
          amount: ""
        });
        setFile("");
        console.log(response);
      })
      .catch((error) => {
        console.log(error.response);
      });
  };
  const handleCancel = () => {
    history.push("/RamList");
  };

  return (
    <div className="meuForm">
      <NavBar></NavBar>
      <br></br>
      <div className="title">Alteração</div>
      <br></br>
      <hr></hr>
      <form id="formulario" onSubmit={handleSubmit}>
    
        {/* <div className="file">
        File
          <input type="text" name="file"
          defaultValue={ssd.imgSsd.fileName}
          // <td>{ssd.imgSsd ? ssd.imgSsd.fileName : "N/A "}</td>

            onChange={handleImage} />
        </div> */}
        <div className="file">
          <input type="file" name="file"
            onChange={handleImage} />
        </div>

        <div className="inputs">
          <input
            type={"text"}
            name="brand"
            id="brand"
            defaultValue={ram.brand}
            className="form-control"
            placeholder="Marca"
            onChange={(e) => onInputChange(e)}
          />
          <input
            type={"text"}
            name="model"
            id="model"
            className="form-control"
            defaultValue={ram.model}
            placeholder="Modelo"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="serialNumber"
            id="serialNumber"
            className="form-control"
            defaultValue={ram.serialNumber}
            placeholder="Nº de série"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="size"
            id="size"
            className="form-control"
            defaultValue={ram.size}
            placeholder="Capacidade/GB"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="amount"
            id="amount"
            className="form-control"
            defaultValue={ram.amount}
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
            defaultValue={ram.purchaseDate}
            placeholder="Data de compra"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="purchasePrice"
            id="purchasePrice"
            className="form-control"
            defaultValue={ram.purchasePrice}
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
            defaultValue={ram.arrivalDate}
            placeholder="Arrival date"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="saleValue"
            id="saleValue"
            defaultValue={ram.saleValue}
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
            onChange={(e) => handleSubmit(e)}
          ><i class="fa-solid fa-check"></i>

          </button>
          {/* <button class="btn btn-danger">
            <Link to="/ssdlist"><i class="fa-solid fa-ban"></i></Link>
          </button> */}

          <button
            type="submit"
            className="btn btn-danger"

            onClick={handleCancel}
          ><i class="fa-solid fa-ban"></i>


          </button>
        </div>
      </form>
    </div>
  );
};
export default RamEdit;

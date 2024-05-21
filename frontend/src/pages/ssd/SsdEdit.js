import axios from "axios";
import React from "react";
import { useState, useEffect, useNavigate } from "react";
import { BrowserRouter as Router, Route, Link, Switch, useParams } from "react-router-dom";
import "./Ssd.css";
import NavBar from "../../navbar/NavBar";
import { useHistory } from "react-router-dom"; // import do hook

const url = "http://localhost:9090/ssd/redirect";
const urlEdit = "http://localhost:9090/ssd";

const SsdEdit = () => {
  const history = useNavigate();
  const { id } = useParams();
  const [ssd, setSsd] = useState({
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
  const [file, setFile] = useState("");

  useEffect(() => {
    axios.get(`${url}/${id}`)
      .then((result) => {
        setSsd(result.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [id]);

  const onInputChange = (e) => {
    setSsd({
      ...ssd,
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
    formData.append("brand", ssd.brand);
    formData.append("model", ssd.model);
    formData.append("serialNumber", ssd.serialNumber);
    formData.append("size", ssd.size);
    formData.append("purchaseDate", ssd.purchaseDate);
    formData.append("purchasePrice", ssd.purchasePrice);
    formData.append("arrivalDate", ssd.arrivalDate);
    formData.append("saleValue", ssd.saleValue);
    formData.append("amount", ssd.amount);

    axios
      .put(`${urlEdit}/${id}`, formData)
      .then((response) => {
        alert("Alterado com sucesso!");
        setSsd({
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
    history.push("/SsdList");
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
            defaultValue={ssd.brand}
            className="form-control"
            placeholder="Marca"
            onChange={(e) => onInputChange(e)}
          />
          <input
            type={"text"}
            name="model"
            id="model"
            className="form-control"
            defaultValue={ssd.model}
            placeholder="Modelo"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="serialNumber"
            id="serialNumber"
            className="form-control"
            defaultValue={ssd.serialNumber}
            placeholder="Nº de série"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="size"
            id="size"
            className="form-control"
            defaultValue={ssd.size}
            placeholder="Capacidade/GB"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="amount"
            id="amount"
            className="form-control"
            defaultValue={ssd.amount}
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
            defaultValue={ssd.purchaseDate}
            placeholder="Data de compra"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="purchasePrice"
            id="purchasePrice"
            className="form-control"
            defaultValue={ssd.purchasePrice}
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
            defaultValue={ssd.arrivalDate}
            placeholder="Arrival date"
            onChange={(e) => onInputChange(e)}
          />

          <input
            type={"text"}
            name="saleValue"
            id="saleValue"
            defaultValue={ssd.saleValue}
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
export default SsdEdit;

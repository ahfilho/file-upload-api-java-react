import axios from "axios";
import React from "react";
import { useState } from "react";
import Upload from './Upload'
        const url = 'http://localhost:9090/ssd/new';

const AddSsd = () => {
    const [name, setName] = useState("");
    const [result, setResult] = useState("");

    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [serialNumber, setSerialNumber] = useState("");
    const [size, setSize] = useState("");
    const [purchaseDate, setPurchaseDate] = useState("");
    const [purchasePrice, setPurchasePrice] = useState("");
    const [arrivalDate, setArrivalDate] = useState("");
    



const handleSubmit = async(e) =>{
    
    e.preventDefault();
    try{
        const response = await axios.post(url, {
          brand: brand,
          model: model,
          serialNumber: serialNumber,
          size: size,
          purchaseDate: purchaseDate,
          purchasePrice: purchasePrice,
          arrivalDate: arrivalDate,
          file: name,
          FormData,
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }) 
        console.log(response.data);
    } catch (error){
        console.log(error.response);
    }
}

return (
  <div className="meuForm">
    <div className="form-row">
      <form onSubmit={handleSubmit}>
        <div className="col-md-6 offset-md-3">
          <label className="form-label">Imagem JPEG ou JPG</label>

          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="brand"
            id="brand"
            value={brand}
            className="form-control"
            placeholder="Brand"
            onChange={(e) => setBrand(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="model"
            id="model"
            className="form-control"
            value={model}
            placeholder="Model"
            onChange={(e) => setModel(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="serialNumber"
            id="serialNumber"
            className="form-control"
            value={serialNumber}
            placeholder="Serial Number"
            onChange={(e) => setSerialNumber(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="size"
            id="size"
            className="form-control"
            value={size}
            placeholder="Size"
            onChange={(e) => setSize(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="purchaseDate"
            id="purcaseDate"
            className="form-control"
            value={purchaseDate}
            placeholder="Purcase date"
            onChange={(e) => setPurchaseDate(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="purchasePrice"
            id="purchasePrice"
            className="form-control"
            value={purchasePrice}
            placeholder="Purcase price"
            onChange={(e) => setPurchasePrice(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="arrivalDate"
            id="arrivalDate"
            className="form-control"
            value={arrivalDate}
            placeholder="Arrival date"
            onChange={(e) => setArrivalDate(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <button type="submit" className="btn btn-success">
            Salvar
          </button>
        </div>
      </form>
    </div>
  </div>
);
};
export default AddSsd;
import axios from "axios";
import React from "react";
import { useState } from "react";

const url = "http://localhost:9090/ssd/new";

const AddSsd = () => {

  const [brand, setBrand] = useState('');
  const [model, setModel] = useState('');
  const [serialNumber, setSerialNumber] = useState('');
  const [size, setSize] = useState('');
  const [purchaseDate, setPurchaseDate] = useState('');
  const [purchasePrice, setPurchasePrice] = useState('');
  const [arrivalDate, setArrivalDate] = useState('');
  const [saleValue, setSaleValue] = useState('');

  const [productCategory, setProductCategory] = useState('');
  const [file, setFile] = useState('');

  const handleImage = (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
      console.log(brand, model)
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
    const category = {
      productCategory,
    };

    const formData = new FormData();
    formData.append("file", file);
    try {
      const response = await axios.post(url, formData, ssd, category, {
        body: ssd,
        // brand: brand,
        // model: model,
        // serialNumber: serialNumber,
        // size: size,
        // purchaseDate: purchaseDate,
        // purchasePrice: purchasePrice,
        // arrivalDate: arrivalDate,
        // saleValue: saleValue,
      });
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <div className="form-row">
        <form onSubmit={(e) => handleSubmit(e)}>
          <div className="col-md-6 offset-md-3">
            <input type="file" name="file" onChange={handleImage} />
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
              placeholder="Purchase price"
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
            <input
              type={"text"}
              name="productCategory"
              id="productCategory"
              className="form-control"
              value={productCategory}
              placeholder="Product category"
              onChange={(e) => setProductCategory(e.target.value)}
            />
          </div>
          <div>
            <input
              type={"text"}
              name="saleValue"
              id="saleValue"
              value={saleValue}
              className="form-control"
              placeholder="saleValue"
              onChange={(e) => setSaleValue(e.target.value)}
            />
          </div>

          <div className="col-md-6 offset-md-3">
            <button
              type="submit"
              className="btn btn-success"
              onChange={(e) => this.handleSubmit(e)}
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

import { useEffect, useState } from "react";
import axios from "axios";

const url = "http://localhost:9090/client/new";

const Address = () => {
//   const [name, setName] = useState("");
//   const [email, setEmail] = useState("");
//   const [cpf, setCpf] = useState("");
//   const [contact, setContact] = useState("");

  const [street, setStret] = useState("");
  const [number, setNumber] = useState("");
  const [district, setDistrict] = useState("");
  const [city, setCity] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(url, {
        // name: name,
        // email: email,
        // cpf: cpf,
        // cpf,
        // contact: contact,
        street: street,
        number: number,
        district: district,
        city: city,
      });
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <div className="meuForm">
      <div className="form-row">
        <form onSubmit={handleSubmit}>
          <div className="col-md-6 offset-md-3">{/* <Upload></Upload> */}</div>

          {/* <div className="col-md-6 offset-md-3"> */}
            {/* <input
              type={"text"}
              name="name"
              id="name"
              value={name}
              className="form-control"
              placeholder="Nome"
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="email"
              id="email"
              className="form-control"
              value={email}
              placeholder="E-mail"
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="contact"
              id="contact"
              className="form-control"
              value={contact}
              placeholder="Contact"
              onChange={(e) => setContact(e.target.value)}
            />
          </div>
          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="cpf"
              id="cpf"
              className="form-control"
              value={cpf}
              placeholder="Cpf"
              onChange={(e) => setCpf(e.target.value)}
            />
          </div> */}

          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="street"
              id="street"
              className="form-control"
              value={street}
              placeholder="Street"
              onChange={(e) => setStret(e.target.value)}
            />
          </div>

          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="number"
              id="number"
              className="form-control"
              value={number}
              placeholder="Number"
              onChange={(e) => setNumber(e.target.value)}
            />
          </div>

          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="district"
              id="district"
              className="form-control"
              value={district}
              placeholder="District"
              onChange={(e) => setDistrict(e.target.value)}
            />
          </div>

          <div className="col-md-6 offset-md-3">
            <input
              type={"text"}
              name="city"
              id="city"
              className="form-control"
              value={city}
              placeholder="city"
              onChange={(e) => setCity(e.target.value)}
            />
          </div>
{/* 
          <div className="col-md-6 offset-md-3">
            <button
              type="submit"
              className="btn btn-success"
              onChange={(e) => this.handleSubmit(e)}
            >
              Salvar
            </button>
          </div> */}
        </form>
      </div>
    </div>
  );
};
export default Address;

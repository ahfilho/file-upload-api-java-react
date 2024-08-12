import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import NavBar from "../navbar/NavBar";
import './Client.css';
const url = "http://localhost:9090/client";

const AddClient = () => {
  const history = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [contact, setContact] = useState("");

  const [street, setStreet] = useState("");
  const [number, setNumber] = useState("");
  const [district, setDistrict] = useState("");
  const [city, setCity] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const addressDto = {
      street,
      number,
      district,
      city
    };

    const clientDto = {
      name,
      email,
      cpf,
      contact,
      addressDto
    };

    try {
      const response = await axios.post(url, clientDto);

      console.log(response.data);

      // Limpar os campos após o sucesso
      setName("");
      setEmail("");
      setCpf("");
      setCity("");
      setContact("");
      setStreet("");
      setNumber("");
      setDistrict("");

      // Redirecionar para a página desejada após o cadastro
      history.push("/client");
    } catch (error) {
      console.error('Erro ao salvar cliente e endereço:', error);
    }
  };

  return (
      <div class="d-flex flex-column align-items-center">
             <NavBar></NavBar>
        <form id="formulario" onSubmit={handleSubmit} className="w-50">
          <p>Cadastrar cliente</p>
          <div className="row">
            <div className="col">
            <h3><label>Dados pessoais</label> </h3>
              <input
                type="text"
                name="name"
                id="name"
                value={name}
                className="form-control"
                placeholder="Nome"
                onChange={(e) => setName(e.target.value)}
              />
              <input
                type="text"
                name="email"
                id="email"
                className="form-control"
                value={email}
                placeholder="E-mail"
                onChange={(e) => setEmail(e.target.value)}
              />
              <input
                type="text"
                name="cpf"
                id="cpf"
                className="form-control"
                value={cpf}
                placeholder="Cpf"
                onChange={(e) => setCpf(e.target.value)}
              />
              <input
                type="text"
                name="contact"
                id="contact"
                className="form-control mb-3"
                value={contact}
                placeholder="Contato"
                onChange={(e) => setContact(e.target.value)}
              />
            </div>
          </div>

          <div className="form-group mb-">
            <div className="row">
              <div className="col">
              <h3><label>Endereço</label> </h3>

                <input
                  type="text"
                  name="street"
                  id="street"
                  value={street}
                  className="form-control"
                  placeholder="Rua"
                  onChange={(e) => setStreet(e.target.value)}
                />
                <input
                  type="text"
                  name="number"
                  id="number"
                  className="form-control"
                  value={number}
                  placeholder="Nº casa"
                  onChange={(e) => setNumber(e.target.value)}
                />
                <div className="col">
                  <input
                    type="text"
                    name="district"
                    id="district"
                    className="form-control"
                    value={district}
                    placeholder="Estado"
                    onChange={(e) => setDistrict(e.target.value)}
                  />
                  <div className="col">
                    <input
                      type="text"
                      name="city"
                      id="city"
                      className="form-control"
                      value={city}
                      placeholder="Cidade"
                      onChange={(e) => setCity(e.target.value)}
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
          <br></br>
          <div className="form-group">
            <button type="submit" className="btn btn-primary" value="Cadastrar">
              Cadastrar
            </button>
          </div>
        </form>
        </div>

  );
};

export default AddClient;

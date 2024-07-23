import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import NavBar from "../navbar/NavBar";

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
    
    <div className="meuForm">
      <NavBar></NavBar>
      
      <br></br>
      <div className="title">Cadastro</div>
      <br></br>
      <hr></hr>
      <form id="formulario" onSubmit={handleSubmit}>
        <p>Dados pessoais</p>
        <div className="inputs">
          <input
            type="text"
            name="name"
            id="name"
            value={name}
            className="form-control"
            placeholder="Nome"
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type="text"
            name="email"
            id="email"
            className="form-control"
            value={email}
            placeholder="E-mail"
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type="text"
            name="cpf"
            id="cpf"
            className="form-control"
            value={cpf}
            placeholder="Cpf"
            onChange={(e) => setCpf(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type="text"
            name="contact"
            id="contact"
            className="form-control"
            value={contact}
            placeholder="Celular/whatsapp"
            onChange={(e) => setContact(e.target.value)}
          />
        </div>
        <div className="inputs">
          Endereço
          <input
            type="text"
            name="street"
            id="street"
            value={street}
            className="form-control"
            placeholder="Rua"
            onChange={(e) => setStreet(e.target.value)}
          />
        </div>

        <div className="inputs">
          <input
            type="text"
            name="number"
            id="number"
            className="form-control"
            value={number}
            placeholder="Nº casa"
            onChange={(e) => setNumber(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="district"
            id="district"
            className="form-control"
            value={district}
            placeholder="Estado"
            onChange={(e) => setDistrict(e.target.value)}
          />
        </div>
        <div className="inputs">
          <input
            type={"text"}
            name="city"
            id="city"
            className="form-control"
            value={city}
            placeholder="Cidade"
            onChange={(e) => setCity(e.target.value)}
          />
        </div>
        
        <br></br>
        <div className="inputs">

          <input class="btn btn-primary" type="submit" value="Submit"></input>
        </div>
      </form>

    </div>
  );
};
export default AddClient;

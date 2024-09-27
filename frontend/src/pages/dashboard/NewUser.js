import React, { useState } from "react";
import axios from "axios";
import './NewUser.css';
import { Link } from "react-router-dom";
import NavBar from "../../navbar/NavBar";

const url = "http://localhost:9090/new/user";

const NewUser = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [userName, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [cpf, setCpf] = useState("");
  const [profile, setProfile] = useState("USER"); // Valor padrão

  const formatCpf = (value) => {
    // Remove caracteres não numéricos
    const cleanedValue = value.replace(/[^\d]/g, "");

    // Aplica a máscara
    const formattedValue = cleanedValue.replace(
      /^(\d{3})(\d{3})(\d{3})(\d{2})$/,
      "$1.$2.$3-$4"
    );

    return formattedValue;
  };

  const handleCpfChange = (e) => {
    const formattedCpf = formatCpf(e.target.value);
    setCpf(formattedCpf);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = {
      firstName,
      lastName,
      userName,
      email,
      password,
      cpf,
      profile,
    };

    try {
      const response = await axios.post(url, user);

      console.log(response.data);

      alert("SALVO COM SUCESSO -- apenas para testes!");
      setFirstName("");
      setLastName("");
      setUsername("");
      setEmail("");
      setPassword("");
      setCpf("");
      setProfile("USER"); // Resetar para o valor padrão
    } catch (error) {
      console.error("Erro ao salvar usuário:", error);
    }
  };

  return (
    <section className="h-100">
      <NavBar />
      <div className="d-flex flex-column justify-content-center align-items-center vh-100 move-up">
        <form id="formulario" onSubmit={handleSubmit} className="login-form">
          <p>Novo usuário</p>
          <div className="row">
            <div className="col">
              <input
                type="text"
                name="firstName"
                id="firstName"
                value={firstName}
                className="form-control"
                placeholder="Nome"
                onChange={(e) => setFirstName(e.target.value)}
              />
                <input
                  type="text"
                  name="lastName"
                  id="LastName"
                  value={lastName}
                  className="form-control"
                  placeholder="Sobrenome"
                  onChange={(e) => setLastName(e.target.value)}
                />
            </div></div>

          <input
            type="text"
            name="userName"
            id="userName"
            value={userName}
            className="form-control"
            placeholder="Usuário"
            onChange={(e) => setUsername(e.target.value)}
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
            type="password"
            name="password"
            id="password"
            className="form-control"
            value={password}
            placeholder="Senha"
            onChange={(e) => setPassword(e.target.value)}
          />

          <input
            type="text"
            name="cpf"
            id="cpf"
            className="form-control"
            value={cpf}
            placeholder="Cpf"
            onChange={handleCpfChange}
          />

          <div className="radio">
            <p>Perfil</p>
            <div>
              <label>
                <input
                  type="radio"
                  name="profile"
                  value="admin"
                  checked={profile === "admin"}
                  onChange={() => setProfile("admin")}
                />
                Administrador
              </label>
              <label>
                <input
                  type="radio"
                  name="profile"
                  value="usuario"
                  checked={profile === "usuario"}
                  onChange={() => setProfile("usuario")}
                />
                Usuário
              </label>
            </div>
          </div>
          <br></br>
          <div className="login-container">
            <input
              className="btn btn-primary"
              type="submit"
              value="Cadastrar"
            >
            </input>
          </div>
        </form>
      </div>
    </section>
  );
};

export default NewUser;

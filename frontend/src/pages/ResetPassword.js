import axios from "axios";
import './Forgot.css';
import { Link } from "react-router-dom";
import React, { useState, useEffect } from "react";


const url = "http://localhost:9090/new/reset";


const ResetPassword = () => {
  // const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [cpf, setCpf] = useState("");
  // const [profile, setProfile] = useState("USER"); // Valor padrão

  useEffect(() => {
    console.log("ResetPassword component mounted");
  }, []);

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
      // userName,
      email,
      password,
      cpf,
      // profile,
    };

    try {
      const response = await axios.put(url, user);

      console.log(response.data);

      alert("Senha alterada com sucesso!");
      // setUserName("");
      setEmail("");
      setPassword("");
      setCpf("");
      // setProfile("USER"); // Resetar para o valor padrão
    } catch (error) {
      console.error("Erro ao salvar usuário:", error);
    }
  };

  return (
    <div className="login-page">

      <section className="h-100">
        <div className="container h-100">
          <div className="row justify-content-md-center h-100">
            <div className="card-wrapper">
              <div className="card fat">
                <div className="card-body">
                  <h4 className="card-title">Alteração de senha</h4>
                  <form id="formulario" onSubmit={handleSubmit} className="login-form">
                    <div className="form-group">
                      {/* <input
                        type="text"
                        name="userName"
                        id="userName"
                        value={userName}
                        className="form-control"
                        placeholder="Nome"
                        onChange={(e) => setUserName(e.target.value)}
                      /> */}
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
                        name="password"
                        id="password"
                        className="form-control"
                        value={password}
                        placeholder="Senha"
                        onChange={(e) => setPassword(e.target.value)}
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
                        onChange={handleCpfChange}
                      />
                    </div>
                    {/* <div className="inputs">
                      <label>Perfil</label>
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
                    </div> */}
                    <br></br>
                    <div className="login-container">
                      <input
                        className="btn btn-primary"
                        type="submit"
                        value="Salvar"
                      ></input>


                    </div>

                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default ResetPassword;

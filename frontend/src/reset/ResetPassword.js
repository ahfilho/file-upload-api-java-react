import React, { useState } from 'react';
import { connect } from 'react-redux';
import axios from "axios";
import { authenticate, authFailure, authSuccess } from '../redux/authActions.js';
import './ResetPassword.css';
import { userLogin } from '../api/authenticationService.js';
import { Alert, Navbar, Spinner } from 'react-bootstrap';
import NavBar from "../navbar/NavBar.js";
import { Await } from 'react-router-dom';


const url = "http://localhost:9090/new/reset";

const ResetPassword = ({ loading, error, ...props }) => {
    const [userName, setUserName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [profile, setProfile] = useState("USER"); // Valor padrão
    const [cpf, setCpf] = useState("");

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

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        props.authenticate();

        const user = {
            userName,
            email,
            password,
            profile,
            cpf,
        };
        try {
            const response = await axios.put(url, user);


            console.log(response.data);

            alert("Senha alterada com sucesso!");
            setUserName("");
            setEmail("");
            setPassword("");
            setCpf("");
            setProfile("USER"); // Resetar para o valor padrão
        } catch (error) {
            console.error("Erro ao alterar senha:", error);
        }
    };

    return (
        <div className="a">
            <section className="b">
                <Navbar></Navbar>
                <form className="my-login-validation" onSubmit={handleSubmit} noValidate={false}>
                    <div className="user">
                        <label htmlFor="usuario">Usuário</label>

                        <input
                            id="userName"
                            type="text"
                            className="form-control"
                            minLength={5}
                            value={userName}
                            onChange={(e) => setUserName(e.target.value)}
                            name="username"
                            required
                        />
                        <div className="invalid-feedback">
                            UserId is invalid
                        </div>
                    </div>
                    <div className='email'>
                        <label htmlFor="email">E-mail</label>

                        <input
                            id="email"
                            type="text"
                            className="form-control"
                            minLength={5}
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            name="email"
                            required
                        />
                    </div>
                    <div className="senha">
                        <label htmlFor="senha">Senha</label>

                        <input
                            id="password"
                            type="password"
                            className="form-control"
                            minLength={6}
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            name="senha"
                            required
                        />

                    </div>
                    <div className="senha">
                        <label htmlFor="cpf">Cpf</label>

                        <div className="inputs">
                            <input
                                type="text"
                                name="cpf"
                                id="cpf"
                                className="form-control"
                                minLength={11}
                                value={cpf}
                                // placeholder="Cpf"
                                onChange={handleCpfChange}
                            />
                        </div>
                    </div>
                    <div className="invalid-feedback">
                        Password is required
                    </div>

                    <div className="form-group">
                        <div className="custom-control custom-checkbox">
                            {/* <input type="checkbox" className="custom-control-input" id="customCheck1" /> */}
                            {/* <label className="custom-control-label" htmlFor="customCheck1">Remember me</label> */}
                        </div>
                    </div>
                    <div className="botao">
                        <button type="submit" className="btn btn-primary">
                            Atualizar
                            {loading && (
                                <Spinner
                                    as="span"
                                    animation="border"
                                    size="sm"
                                    role="status"
                                    aria-hidden="true"
                                />
                            )}
                        </button>
                    </div>
                </form>
                {error && (
                    <Alert style={{ marginTop: '20px' }} variant="danger">
                        {error}
                    </Alert>
                )}
            </section>
        </div>
    );
};

const mapStateToProps = ({ auth }) => {
    console.log("state ", auth);
    return {
        loading: auth.loading,
        error: auth.error
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        authenticate: () => dispatch(authenticate()),
        setUser: (data) => dispatch(authSuccess(data)),
        loginFailure: (message) => dispatch(authFailure(message))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(ResetPassword);

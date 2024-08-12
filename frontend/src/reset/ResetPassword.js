import React, { useState } from 'react';
import { connect } from 'react-redux';
import { authenticate, authFailure, authSuccess } from '../redux/authActions.js';
import './ResetPassword.css';
import { userLogin } from '../api/authenticationService.js';
import { Alert, Navbar, Spinner } from 'react-bootstrap';
import NavBar from "../navbar/NavBar.js";



const ResetPassword = ({ loading, error, ...props }) => {
    const [values, setValues] = useState({
        nome: '',
        senha: ''
    });

    const handleSubmit = (evt) => {
        evt.preventDefault();
        props.authenticate();

        userLogin(values).then((response) => {
            console.log("response", response);
            if (response.status === 200) {
                props.setUser(response.data);
                props.history.push('/dashboard');
            } else {
                props.loginFailure('Something Wrong!Please Try Again');
            }
        }).catch((err) => {
            if (err && err.response) {
                switch (err.response.status) {
                    case 401:
                        console.log("401 status");
                        props.loginFailure("Authentication Failed.Bad Credentials");
                        break;
                    default:
                        props.loginFailure('Something Wrong!Please Try Again');
                }
            } else {
                props.loginFailure('Something Wrong!Please Try Again');
            }
        });
    };

    const handleChange = (e) => {
        e.persist();
        setValues(values => ({
            ...values,
            [e.target.name]: e.target.value
        }));
    };

    return (
        <div className="a">
            <section className="b">
                <Navbar></Navbar>
                <form className="my-login-validation" onSubmit={handleSubmit} noValidate={false}>
                    <div className="user">
                        <label htmlFor="usuario">Usuário</label>

                        <input
                            id="nome"
                            type="text"
                            className="form-control"
                            minLength={5}
                            value={values.nome}
                            onChange={handleChange}
                            name="nome"
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
                            value={values.email}
                            onChange={handleChange}
                            name="nome"
                            required
                        />
                    </div>
                    <div className="senha">
                        <label htmlFor="senha">Senha</label>

                        <input
                            id="senha"
                            type="password"
                            className="form-control"
                            minLength={6}
                            value={values.senha}
                            onChange={handleChange}
                            name="senha"
                            required
                        />

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

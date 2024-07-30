import React, { useState } from 'react';
import { connect } from 'react-redux';
import { authenticate, authFailure, authSuccess } from '../redux/authActions';
import './loginpage.css';
import { userLogin } from '../api/authenticationService';
import { Alert, Spinner } from 'react-bootstrap';
import NewUser from './dashboard/NewUser';
import { useNavigate } from 'react-router-dom';
import { Link } from "react-router-dom";

const LoginPage = ({ loading, error, ...props }) => {
    const navigate = useNavigate();

    const [values, setValues] = useState({
        userName: '',
        password: ''
    });

    const handleSubmit = (evt) => {
        evt.preventDefault();
        props.authenticate();

        userLogin(values).then((response) => {
            console.log("response", response);
            if (response.status === 200) {
                props.setUser(response.data);
                navigate('/home');
            } else if (response.status === 404) {
                props.authFailure('Usuário não cadastrado.');
            } else {
                props.loginFailure('Algo saiu errado. Tente novamente.');
            }
        }).catch((err) => {
            if (err && err.response) {
                switch (err.response.status) {
                    case 401:
                        console.log("401 status");
                        props.loginFailure("Authentication Failed. Bad Credentials");
                        break;
                    default:
                        props.loginFailure('Algo saiu errado. Tente novamente.');
                }
            } else {
                props.loginFailure('Algo saiu errado. Tente novamente.');
            }
        });
    }

    const handleChange = (e) => {
        e.persist();
        setValues(values => ({
            ...values,
            [e.target.name]: e.target.value
        }));
    };

    console.log("Loading ", loading);

    return (
        <div class="d-flex flex-column align-items-center">
            <section className="h-100">
                <div className="botoes">
                    <button type="submit" className="btn btn-primary px-2">

                        <Link to="/client" style={{ color: 'white', textDecoration: 'none' }}>
                            Cliente
                        </Link>
                    </button>

                    <button type="submit" className="btn btn-primary px-2">

                        <Link to="/new/user" style={{ color: 'white', textDecoration: 'none' }}>
                            Usuário
                        </Link>
                    </button>
                    <button type="submit" className="btn btn-primary px-2">

                        <Link to="/ssd" style={{ color: 'white', textDecoration: 'none' }}>
                            Ssd
                        </Link>
                    </button>
                    <button type="submit" className="btn btn-primary px-2">

                        <Link to="/ram" style={{ color: 'white', textDecoration: 'none' }}>
                            Ram
                        </Link>
                    </button>
                    <button type="submit" className="btn btn-primary px-2">

                        <Link to="/cpu" style={{ color: 'white', textDecoration: 'none' }}>
                            Cpu
                        </Link>
                    </button>
                    <button type="button" class="btn btn-primary px-2">
                        <a class="nav-item nav-link">
                            <Link to="/dashboard">Dashboard</Link>
                        </a> </button>
                </div>
                <div className="row justify-content">
                    <div className="card-wrapper">

                        <div className="algumacoisa">Login</div>
                        <form className="my-login-validation" onSubmit={handleSubmit} noValidate={false}>
                            <div class="d-flex flex-column align-items-center">
                                <div className="form-group">
                                    <label htmlFor="username">Usuário</label>
                                </div>
                                <input
                                    id="username"
                                    type="text"
                                    className="form-control"
                                    minLength={5}
                                    value={values.userName}
                                    onChange={handleChange}
                                    name="userName"
                                    required
                                />
                                <div className="invalid-feedback">
                                    UserId is invalid
                                </div>

                                <br />
                                <div className="password">
                                    <label htmlFor="password">Senha</label>

                                </div>
                                <input
                                    id="password"
                                    type="password"
                                    className="form-control"
                                    minLength={8}
                                    value={values.password}
                                    onChange={handleChange}
                                    name="password"
                                    required
                                />
                                <div className="invalid-feedback">
                                    Password is required
                                </div>
                            </div>

                            <br />
                            <div className="input">
                                <button type="submit" className="btn btn-primary">
                                    Login
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
                        <br />
                        <div className="forgot">
                            <a href="forgot.html" className="float-right">
                                Forgot Password?
                            </a>
                            <br />
                        </div>

                        {error &&
                            <Alert style={{ marginTop: '20px' }} variant="danger">
                                {error}
                            </Alert>
                        }
                    </div>
                </div>
            </section>
        </div>
    );
}

const mapStateToProps = ({ auth }) => {
    console.log("state ", auth);
    return {
        loading: auth.loading,
        error: auth.error
    };
}

const mapDispatchToProps = (dispatch) => {
    return {
        authenticate: () => dispatch(authenticate()),
        setUser: (data) => dispatch(authSuccess(data)),
        loginFailure: (message) => dispatch(authFailure(message))
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginPage);

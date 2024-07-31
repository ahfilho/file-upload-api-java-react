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
                <div className='bold-text'>
                    <button type="submit" className="btn px-4">
                        <Link to="/client">
                            Cliente
                        </Link>
                    </button>
                    <button type="submit" className="btn px-4">

                        <Link to="/new/user">
                            Usuário
                        </Link>
                    </button>
                    <button type="submit" className="btn px-4">

                        <Link to="/ssd">
                            Ssd
                        </Link>
                    </button>
                    <button type="submit" className="btn px-4">

                        <Link to="/ram">
                            Ram
                        </Link>
                    </button>
                    <button type="submit" className="btn px-4">

                        <Link to="/cpu">
                            Cpu
                        </Link>
                    </button>
                    <button type="submit" className="btn px-">
                        <Link to="/dashboard">Dashboard</Link>
                    </button></div>

                <form className="my-login-validation" onSubmit={handleSubmit} noValidate={false}>
                    <div className="algumacoisa">Login</div>
                    <div class="input-flex flex-column align-items-center">
                        <div className="user">
                            Usuário
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
                        <div className="senha">Senha</div>

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
                <div className='forgotpass'>
                    <button type="submit" className="btn px-2">
                        <Link to="/forgot">
                            Esqueci a senha
                        </Link>
                    </button>
                </div>
                {error &&
                    <Alert style={{ marginTop: '20px' }} variant="danger">
                        {error}
                    </Alert>
                }
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

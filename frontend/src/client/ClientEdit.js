import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import NavBar from "../navbar/NavBar";
import { useHistory } from 'react-router-dom'; // import do hook
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";

const url = "http://localhost:9090/client";
const url2 = "http://localhost:9090/client/find";

const ClientEdit = () => {
    const { id } = useParams();
    const history = useHistory();
    const [client, setClient] = useState({
        name: "",
        email: "",
        cpf: "",
        dataRegister: "",
        contact: "",
        address: {
            street: "",
            number: "",
            district: "",
            city: ""
        }
    });

    useEffect(() => {
        axios.get(`${url2}/${id}`)
            .then((result) => {
                setClient(result.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [id]);


    const onInputChange = (e) => {
        if (e.target.name.startsWith("address")) {
            setClient({
                ...client,
                address: {
                    ...client.address,
                    [e.target.name.split(".")[1]]: e.target.value,
                },
            });
        } else {
            setClient({
                ...client,
                [e.target.name]: e.target.value,
            });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        axios.put(`${url}/${id}`, client).then((response) => {
            alert("Alterado com sucesso!")
            console.log(response);
        })
            .catch((error) => {
                console.log(error.response);
            });
    };
    const handleCancel = () => {
        history.push("/ClientList");
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
                        className="form-control"
                        defaultValue={client.name}
                        placeholder="Nome"
                        onChange={(e) => onInputChange(e)}
                    />
                    <input
                        type="text"
                        name="email"
                        id="email"
                        className="form-control"
                        defaultValue={client.email}
                        placeholder="E-mail"
                        onChange={(e) => onInputChange(e)}
                    />
                    <input
                        type="text"
                        name="cpf"
                        id="cpf"
                        className="form-control"
                        defaultValue={client.cpf}
                        placeholder="Cpf"
                        onChange={(e) => onInputChange(e)}
                    />
                    <input
                        type="text"
                        name="contact"
                        id="contact"
                        className="form-control"
                        defaultValue={client.contact}
                        placeholder="Celular/whatsapp"
                        onChange={(e) => onInputChange(e)}
                    />
                </div>
                <div className="inputs">
                    Endereço
                    <input
                        type="text"
                        name="address.street"
                        id="street"
                        className="form-control"
                        defaultValue={client.address.street}
                        placeholder="Rua"
                        onChange={(e) => onInputChange(e)}
                    />
                    <input
                        type="text"
                        name="address.number"
                        id="number"
                        className="form-control"
                        defaultValue={client.address.number}
                        placeholder="Nº casa"
                        onChange={(e) => onInputChange(e)}
                    />
                    <input
                        type={"text"}
                        name="address.district"
                        id="district"
                        className="form-control"
                        defaultValue={client.address.district}
                        placeholder="Estado"
                        onChange={(e) => onInputChange(e)}
                    />
                    <input
                        type={"text"}
                        name="address.city"
                        id="city"
                        className="form-control"
                        defaultValue={client.address.city}
                        placeholder="Cidade"
                        onChange={(e) => onInputChange(e)}
                    />
                </div>
                <br></br>
                <div className="inputs">
                    <button
                        type="submit"
                        className="btn btn-primary"
                        onChange={(e) => handleSubmit(e)}
                    ><i class="fa-solid fa-check"></i>

                    </button>

                    <button
                        type="submit"
                        className="btn btn-danger"

                        onClick={handleCancel}
                    ><i class="fa-solid fa-ban"></i>


                    </button>
                </div>
            </form>
        </div>
    );
};

export default ClientEdit;

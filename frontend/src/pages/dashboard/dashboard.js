import React, { useState } from 'react';
import { Button, Container } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import styled from 'styled-components';
import { fetchUserData } from '../../api/authenticationService';
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import './dashboard.css';
import NavBar from '../../navbar/NavBar';
const MainWrapper = styled.div`
    padding-top:40px;
`;

export const Dashboard = (props) => {

  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState({});

  React.useEffect(() => {
    fetchUserData().then((response) => {
      setData(response.data);
    }).catch((e) => {
      localStorage.clear();
      props.history.push('/');
    })
  }, [])

  const logOut = () => {

    localStorage.clear();
    props.history.push('/');

  }

  return (

    <Container>
      <MainWrapper>

        <div className='titulo'>   <h3> Olá, {data && `${data.firstName} ${data.lastName}.
        `}</h3>
        </div>
        <div className="button-container">
          <button type="button" className="btn btn-primary">
            <a className="nav-item nav-link" href="/home">
              Início
            </a>
          </button>
          <div className="t2">
            <Button style={{ marginTop: '5px' }} onClick={() => logOut()}>
              Logout
            </Button>
          </div>
        </div>


        {/* <NavBar></NavBar> */}
        <br></br>
        {data && data.roles && data.roles.filter(value => value.roleCode === 'ADMIN').length > 0 && <Button type="variant">Add User</Button>}
        <br></br>
        <br></br>
      </MainWrapper>
    </Container>

  )
}
import "./home.css";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <>
      <div className="teste">
        <h2>Cadastro de produtos importados</h2>
      </div>
      <div className="botoes">
        <Link to="/ssd">Novo ssd</Link>

        <Link to="/ssdlist">Listar todos</Link>
      </div>
    </>
  );
};
export default Home;

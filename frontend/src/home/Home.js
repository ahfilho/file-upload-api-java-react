import "./Home.css";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <>
      <div className="flex-container">
        <h2>Cadastro de produtos importados</h2>
        <h5>Opções de menu</h5>
      <div className="botao1">
        <Link to="/ssd">Ssd</Link>
      </div>
      <div className="botao2">
        <Link to="/ram">Ram</Link>
      </div>
      <div className="botao3">
        <Link to="/cpu">Cpu</Link>
      </div>
      </div>
    </>
  );
};
export default Home;

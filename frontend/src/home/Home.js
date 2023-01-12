import "./Home.css";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Home = () => {
  return (
    <div className="flex-container">
      <h2>Alimports</h2>
      <div className="a1">
        <Link to="/ssd">Ssd</Link>
      </div>
      <div className="a1">
        <FontAwesomeIcon icon="fa-solid fa-shield-halved" />
        <Link to="/ram">Ram</Link>
      </div>
      <div className="a1">
        <Link to="/cpu">Cpu</Link>
      </div>
    </div>
  );
};
export default Home;

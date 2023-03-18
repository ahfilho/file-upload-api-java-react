import "./Home.css";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Home = () => {
  return (
    <div className="flex-container">
      <h2>Alimports</h2>
      <div className="animated-button1">
        <a href="/cpu" class="animated-button3">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Cpu
        </a>
      </div>
      <div className="animated-button2">
        <a href="/ram" class="animated-button1">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Ram
        </a>
      </div>
      <div className="animated-button3">
        <a href="/ssd" class="animated-button2">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Ssd
        </a>
      </div>
      {/* <div className="a1">
        <Link to="/ssd">Ssd</Link>

        <Link to="/ram">Ram</Link>
      </div>
      <div className="a1">
        <Link to="/cpu">Cpu</Link>
      </div> */}
    </div>
  );
};
export default Home;

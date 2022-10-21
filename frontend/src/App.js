import "./App.css";
import Formulario from "./formulario/Formulario";
import List from "./listagem/List";
import { useEffect, useState } from "react";

function App() {
  // UseState
  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [ssds, setSsds] = useState([]);

  // UseEffect
  useEffect(() => {
    fetch("http://localhost:9090/ssd/list")
      .then((retorno) => retorno.json())
      .then((retorno_convertido) => setSsds(retorno_convertido));
  }, []); // as chaves vão permitir fazer apenas uma requisição. Se não tiver, entra em loop.

  // Retorno
  return (
    <div className="App">
      <h3>Cadastro de produtos: SSD</h3>
      <p>{JSON.stringify(ssds)}</p>

      <Formulario botao={btnCadastrar} />
      <List vetor={ssds} />
      {/* <h3>Produtos cadastrados</h3> */}
    </div>
  );
}
export default App;

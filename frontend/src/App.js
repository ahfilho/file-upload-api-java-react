import "./App.css";
import Formulario from "./formulario/Formulario";
import List from "./listagem/List";
import { useEffect, useState } from "react";

function App() {

//objeto SSD
const ssd = {
  brand : '',
  model :'',
  serialNumber : '',
  size : '',
  purchasePrice :'',
  purchaseDate : '',
  saleValue : '',
  arrivalDate :''
 
}
  // UseState = cria  um objeto de maneira dinâmica
  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [ssds, setSsds] = useState([]);
  const [objetoSsd, setObjetoSsd] = useState(ssd);  

  // UseEffect
  useEffect(() => {
    fetch("http://localhost:9090/ssd/list")
      .then((retorno) => retorno.json())
      .then((retorno_convertido) => setSsds(retorno_convertido));
  }, []); // as chaves vão permitir fazer apenas uma requisição. Se não tiver, entra em loop.

//pegando os dados do formulário


const teste = (e) => {
  console.log(e.target);
  setObjetoSsd({...objetoSsd, [e.target.brand]:e.target.model}); //para editar o objeto
}


  // Retorno
  return (
    <div className="App">
      <h3>Cadastro de produtos: </h3>
      {/* <p>{JSON.stringify(ssds)}</p> */}
      <p>{JSON.stringify(objetoSsd)}</p>

      <Formulario botao={btnCadastrar} eventoTeste={teste} />

      <List vetor={ssds} />
      {/* <h3>Produtos cadastrados</h3> */}
    </div>
  );
}
export default App;

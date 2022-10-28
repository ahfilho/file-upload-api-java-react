import "./App.css";
import Formulario from "./formulario/Formulario";
import List from "./listagem/List";
import { useEffect, useState } from "react";

function App() {

//objeto SSD
const ssd = {
  brand: '',
  model : '',
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

//setState
const aoDiditar = (e) => {
    setObjetoSsd({...ssds,[e.target.brand]:e.target.value});
    console.log(e.target.value);
  }
  //para editar o objeto


const cadastrar = () => {
  console.log("testee");
  fetch("http://localhost:9090/ssd/new", {
    method: "post",
    body: JSON.stringify(objetoSsd),
    headers: {
      "Content-type": "application/json",
      Accept: "application/json",
    },
  })
    .then((retorno) => retorno.json())
    .then((retorno_convertido) => {
      if (retorno_convertido.mensagem !== undefined) {
        alert(retorno_convertido.mensagem);
      } else {
        setSsds([...ssds, retorno_convertido]);
        alert("Produto cadastrado com sucesso!");
        // limparFormulario();
      }
    });
};



  // Retorno
  return (
    <div className="App">
      <h3>Cadastro de produtos: </h3>
      {/* <p>{JSON.stringify(ssds)}</p> */}
      <p>{JSON.stringify(objetoSsd)}</p>

      <Formulario botao={btnCadastrar} eventoTeste={aoDiditar} obj={objetoSsd}  cadastrar={cadastrar} />

      <List vetor={ssds} />
      {/* <h3>Produtos cadastrados</h3> */}
    </div>
  );
}
export default App;

import "./App.css";
import List from "./listagem/List";
import { useEffect, useState } from "react";
import axios from "axios";

function App() {


return(

  <div className="App">
    <List/>
    
  </div>
)










}

// //objeto SSD
// const ssd = {
//   brand: '',
//   model : '',
//   serialNumber : '',
//   size : '',
//   purchasePrice :'',
//   purchaseDate : '',
//   saleValue : '',
//   arrivalDate :''
 
// }
// const categoria = {
//   producoCategoria : ''
// }

//   // UseState = cria  um objeto de maneira dinâmica
//   const [btnCadastrar, setBtnCadastrar] = useState(true);
//   const [ssds, setSsds] = useState([]);
//   const [objetoSsd, setObjetoSsd] = useState(ssd);   
//   const [category, setCategoria] = useState();


//   // UseEffect
//   useEffect(() => {
//     axios.get("http://localhost:9090/ssd/list")
//       .then((retorno) => retorno.json())
//       .then((retorno_convertido) => setSsds(retorno_convertido));
//   }, []); // as chaves vão permitir fazer apenas uma requisição. Se não tiver, entra em loop.

// //pegando os dados do formulário

// //setState
// const aoDiditar = (e) => {
//     setObjetoSsd({ ...objetoSsd, [e.target.brand]:e.target.value });

//     console.log(e.target);
//   }
 
//   // const eventoCadastrar = () => {
//   //   //objeto SSD
//   //   const ssd = {
//   //     brand: "",
//   //     model: "",
//   //     serialNumber: "",
//   //     size: "",
//   //     purchasePrice: "",
//   //     purchaseDate: "",
//   //     saleValue: "",
//   //     arrivalDate: "",
//   //   };

//   //   const category = {
//   //     productCategory: ""
//   //   }
//   //   const urll = "http://localhost:9090/ssd/new";

//   //   fetch(urll, {
//   //     method: "POST",
//   //     body: JSON.stringify(objetoSsd),
//   //     headers: {
//   //       "Content-type": "application/json",
//   //       Accept: "application/json",
//   //     },
//   //   })
//   //     .then((response) => response.json())
//   //     .then((json) => {
//   //       console.log("JOSOONNN", json);
//   //       setSsds(json);
//   //     })
//   //     .catch((e) => {
//   //       console.log("e", e);
//   //     });
//   // }



// const cadastrar = () => {
//   fetch('http://localhost:9090/ssd', {
//       method:'post',
//       body:JSON.stringify(objetoSsd),
//       headers:{
//         'Content-type':'application/json',
//         'Accept':'application/json'
//       }
//     })
//     .then(retorno => retorno.json())
//     .then(retorno_convertido => {
      
//       if(retorno_convertido.mensagem !== undefined){
//         alert(retorno_convertido.mensagem);
//       }else{
//         setSsds([...ssds, retorno_convertido]);
//         alert('Produto cadastrado com sucesso!');
//         // limparFormulario();
//       }
      
//     })
//   }


//   // Retorno
//   return (
//     <div className="App">
//       <h3>Cadastro de produtos: </h3>
//       <p>{JSON.stringify(ssds)}</p>
//       <p>{JSON.stringify(objetoSsd)}</p>

//       <Formulario
//         botao={btnCadastrar}
//         eventoTeste={aoDiditar}
//         cadastrar={cadastrar}
        
//       />

//       <List vetor={ssds} />
//       {/* <h3>Produtos cadastrados</h3> */}
//     </div>
//   );
// }
export default App;
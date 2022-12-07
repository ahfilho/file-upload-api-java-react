import "./App.css";
import Formulario from "./formulario/Formulario";
import List from "./listagem/List";
import { useEffect, useState } from "react";
import axios from "axios";
  
const url = 'http://localhost:9090/ssd/new';

const App = () => {
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [serialNumber, setSerialNumber] = useState("");
    const [size, setSize] = useState("");
    const [purchaseDate, setPurchaseDate] = useState("");
    const [purchasePrice, setPurchasePrice] = useState("");
    const [arrivalDate, setArrivalDate] = useState("");
    const [productCategory, setProductCategory] = useState("");

   const [file, setFile] = useState("");

  function handleChange(e) {
  setFile(e.target.files[0]);
  
}

const handleSubmit = async(e) =>{
    
    e.preventDefault();
    try{
       const formData = new FormData();
       formData.append("name", file);
       formData.append("fileName", file.name);
       const config = {
         headers: {
           "content-type": "multipart/form-data",
         },
       };
        const response = await axios.post(url,formData, config,{
          brand: brand,
          model: model,
          serialNumber: serialNumber,
          size: size,
          purchaseDate: purchaseDate,
          purchasePrice: purchasePrice,
          arrivalDate: arrivalDate,
          file: file,


          productCategory: productCategory,

        }).then((response => {
        console.log(response.data);
        })); 
        console.log(response.data);
    } catch (error){
        console.log(error.response);
    }
}

return (
  <div className="meuForm">
    <div className="form-row">
      <form onSubmit={handleSubmit}>
        <div className="col-md-6 offset-md-3">
          {/* <Upload></Upload> */}
          <input type="file" className="form-control" onChange={handleChange} 
          />
        </div>
    
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="brand"
            id="brand"
            value={brand}
            className="form-control"
            placeholder="Brand"
            onChange={(e) => setBrand(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="model"
            id="model"
            className="form-control"
            value={model}
            placeholder="Model"
            onChange={(e) => setModel(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="serialNumber"
            id="serialNumber"
            className="form-control"
            value={serialNumber}
            placeholder="Serial Number"
            onChange={(e) => setSerialNumber(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="size"
            id="size"
            className="form-control"
            value={size}
            placeholder="Size"
            onChange={(e) => setSize(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="purchaseDate"
            id="purcaseDate"
            className="form-control"
            value={purchaseDate}
            placeholder="Purcase date"
            onChange={(e) => setPurchaseDate(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="purchasePrice"
            id="purchasePrice"
            className="form-control"
            value={purchasePrice}
            placeholder="Purcase price"
            onChange={(e) => setPurchasePrice(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="arrivalDate"
            id="arrivalDate"
            className="form-control"
            value={arrivalDate}
            placeholder="Arrival date"
            onChange={(e) => setArrivalDate(e.target.value)}
          />
        </div>
        <div className="col-md-6 offset-md-3">
          <input
            type={"text"}
            name="productCategory"
            id="productCategory"
            className="form-control"
            value={productCategory}
            placeholder="Produto categoria"
            onChange={(e) => setProductCategory(e.target.value)}
          />
        </div>

        <div className="col-md-6 offset-md-3">
          <button
            type="submit"
            className="btn btn-success"
            onChange={(e) => this.handleSubmit(e)}
          >
            Salvar
          </button>
        </div>
      </form>
    </div>
  </div>
);
};
export default App;

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

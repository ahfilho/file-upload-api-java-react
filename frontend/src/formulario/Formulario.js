import './Form.css';

function Form(){
  
    //uma funçao que retorna o form
return(
   <div class="meuForm">
   
   <form>
    
    <div class="a">Brand</div>
    <input type="text" placeholder="brand"/>
      
    <div class="a">Model</div>   
    <input type="text" placeholder="model"/>
    <div class="a">Serial number</div>
    <input type="text" placeholder="serial number"/>
    <div class="a">Size</div>
    <input type="text" placeholder="size"/>
    <div class="a">Purchase price</div>
    <input type="text" placeholder="purchase price"/>
    <div class="a">Sale value</div>
    <input type="text" placeholder="sale value"/>
    <div class="a">Purchase date</div>
    <input type="text" placeholder="purchase date"/>
    <div class="a">Arrival date</div>
    <input type="text" placeholder="arrival date"/>
   <div class="botoes"> <input type="button" value="Cadastrar"/>
    <input type="button" value="Listar"/>
    <input type="button" value="A"/>
    <input type="button" value="B"/>
    <input type="button" value="C"/>
    <input type="button" value="D"/>
</div>

    </form> 
    </div>
)
}

export default Form;
import './Form.css';

function Form(){
  
    //uma funçao que retorna o form
return(
   <div class="meuForm">
   
   <form>
        <input type="button"class="btn btn-secondary" value="Início"/>
    <input type="button"class="btn btn-dark" value="Pesquisar"/>

    <div class="form-row">
    <div class=" col-md-6 offset-md-3">
      <input type="text" class="form-control" placeholder="Brand"/>
    </div>
     <div class=" col-md-6 offset-md-3">
            <input type="text" class="form-control" placeholder="Model"/>
    </div>
     <div class=" col-md-6 offset-md-3">
         <input type="text" class="form-control" placeholder="Serial number"/>
    </div>
     <div class=" col-md-6 offset-md-3">
       <input type="text" class="form-control" placeholder="Size"/>
    </div>
     <div class=" col-md-6 offset-md-3">
         <input type="text" class="form-control" placeholder="Purchase price"/>
    </div>
     <div class=" col-md-6 offset-md-3">
         <input type="text" class="form-control" placeholder="Sale value"/>
    </div>
     <div class=" col-md-6 offset-md-3">
        <input type="text" class="form-control" placeholder="Purchate date"/>
    </div>
     <div class=" col-md-6 offset-md-3">
         <input type="text" class="form-control" placeholder="Arrival date"/>
    </div>
     <div class="botao1"> <input type="submit" class="btn btn-success" value="Cadastrar"/>
    <input type="button"class="btn btn-primary" value="Listar"/>
    
</div>
    </div>
    </form> 
    </div>
)
}

export default Form;
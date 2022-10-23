import "./Form.css";

function Form({botao, eventoTeste}) {
  //uma funçao que retorna o form
  return (
    <form>
      <div className='meuForm'>
        <div className='form-row'>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='brand'
              className='form-control'
              placeholder='Brand'
            />
          </div>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='model'
              className='form-control'
              placeholder='Model'
            />
          </div>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='serialNumber'
              className='form-control'
              placeholder='Serial number'
            />
          </div>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='size'
              className='form-control'
              placeholder='Size'
            />
          </div>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='purchasePrice'
              className='form-control'
              placeholder='Purchase price'
            />
          </div>
          <div className=" col-md-6 offset-md-3">
            <input
              type='text'
              onChange={eventoTeste}
              name='saleValue'
              className='form-control'
              placeholder='Sale value'
            />
          </div>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='purchaseDate'
              className='form-control'
              placeholder='Purchate date'
            />
          </div>
          <div className='col-md-6 offset-md-3'>
            <input
              type='text'
              onChange={eventoTeste}
              name='arrivalDate'
              className='form-control'
              placeholder='Arrival date'
            />
          </div>
          <div className='botao'>
            {botao ? (
              <input
                type='button'
                className='btn btn-success'
                value='Cadastrar'
              />
            ) : (
              <div>
                <input
                  type='button'
                  className='btn btn-secondary'
                  value='Início'
                />
                <input
                  type='button'
                  className='btn btn-dark'
                  value='Pesquisar'
                />
                <input
                  type='button'
                  className='btn btn-primary'
                  value='Listar'
                />
              </div>
            )}
          </div>
        </div>
      </div>
    </form>
  );
}

export default Form;


function Listar({vetor }){

    return(
        <table className='table'>
            
            <thead>
<h4>Todos os produtos cadastrados
</h4>
<tr>
    <th>id</th>
    <th>Brand</th>
    <th>Model</th>
    <th>Serial Number</th>
    <th>Size</th>
    <th>Purchase price</th>
    <th>Purchase Date</th>
    <th>Arrival Date</th>
    <th>URL</th>
</tr>
    </thead>
    
     <tbody>
       {
        vetor.map((obj,indice) => (
             <tr key={indice}>
            <td>{indice+1}</td>
            <td>{obj.brand}</td>
            <td>{obj.model}</td>
            <td>{obj.serialNumber}</td>
            <td>{obj.size}</td>
            <td>{obj.purchasePrice}</td>
            <td>{obj.purchaseDate}</td>
            <td>{obj.arrivalDate}</td>
            <td a href="{obj.url}">{obj.url}</td>
      

            <td><button className="btn btn-success">Update</button></td>
            <td><button className="btn btn-danger">Delete</button></td>

        </tr>
        ))
       }
     </tbody>
        </table>
    )
}
export default Listar;
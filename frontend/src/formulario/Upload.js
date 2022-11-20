import { Component } from "react";
import axios from 'axios';






class Upload extends Component {


  state = {
    file: null
  }

  handleFile(e) {

let file = e.target.files[0]

    console.log(e.target.files, "$$$$");
    console.log(e.target.files[0], "$$$$");
    this.setState({img_file: file})
}

handleUpload(e){
console.log(this.state,"TESTE DE ESTADO ---- $$$$");

let file = this.state.file
let formdata = new FormData()

formdata.append('image',this.state.img_file);
formdata.append('name',"dinho");

axios({
  url: "http://localhost:9090/ssd/new",
  method: "POST",
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      },
  data: formdata,
}).then(
  (res) => {
    if (res.ok) {
      alert("Perfect! ");
    } else if (res.status == 401) {
      alert("Oops! ");
    }
  },
  function (e) {
    alert("Error submitting form!");
  }
);


}
render(){
  return (
    <div className="Upload">

      <form>
        <div className="">
          <input type="file" name="file" onChange={(e) => this.handleFile(e)} />

      
          </div>
      </form>

      {/* <button type="button" onClick={(e) => this.handleUpload(e)}> */}
        
      {/* </button> */}
    </div>
  );
}
}


export default Upload;
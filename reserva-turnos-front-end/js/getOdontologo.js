window.onload = () => {
  obtenerToken();

};


let myHeaders = new Headers();
myHeaders.append("Authorization", "Basic cmVzZXJ2YXR1cm5vczpyM3NlcnY0dHVyNW9z");
myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

let urlencoded = new URLSearchParams();
urlencoded.append("grant_type", "password");
urlencoded.append("username", "usertest1");
urlencoded.append("password", "12345");

let requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: urlencoded,
  redirect: 'follow'
};

function obtenerToken() {

  fetch("http://localhost:8080/oauth/token", requestOptions)
    .then(response => response.json())
    .then((data) => {
      cargarInformacionOdontologos(data);
    })

}

function cargarInformacionOdontologos(data) {

  const settings = {
    method: "GET",
    headers: {
      "Authorization": "Bearer " + data.access_token
    }
  };

  fetch("http://localhost:8080/odontologos", settings)

    .then((response) => response.json())
    .then((odontologo) => {

      const tablaOdontologos = document.getElementById("tablaOdontologos");

      odontologo.forEach((odontologo) => {
        tablaOdontologos.innerHTML += `
              <tr>
                  <td>${odontologo.id}</td>
                  <td>${odontologo.nombre}</td>
                  <td>${odontologo.apellido}</td>
                  <td>${odontologo.matricula}</td>
                  <td><button class="btn btn-primary" onClick="actualizar(${odontologo.id}, ${odontologo.matricula})">Editar✏️</button></td> 
                  <td><button class="btn btn-primary" onClick="eliminar(${odontologo.id})">Eliminar❌</button></td> 
              </tr>
          `;
      });
    });



}
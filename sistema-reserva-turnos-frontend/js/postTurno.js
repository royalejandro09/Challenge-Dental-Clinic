window.onload = () => {
    const formulario = document.getElementById("formularioTurno");

    formulario.addEventListener("submit", event => {
        event.preventDefault();
        obtenerToken();
    });
}


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
            agregarTurno(data);
        })

}

function agregarTurno(data) {
    const paciente = document.getElementById("paciente");
    const odontologo = document.getElementById("odontologo");
    const fechaHora = document.getElementById("fecha");

    let idO= parseInt(odontologo.value)
    let idP= parseInt(paciente.value)


    const formData = {
        odontologoDto:{
            id: idO
        },
        pacienteDto: {
            id: idP
        },
        fechaHora: fechaHora.value,
    };

    const settings = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + data.access_token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    };

    fetch("http://localhost:8080/turnos", settings)
      .then((response) => response.json())
      .then(() => alert("Se creó el Turno"))
      .catch(() => alert("No se pudo crear el Turno"))
      .finally(() => {
        paciente.value = "";
        odontologo.value = "";
        fechaHora.value = "";
      })
}
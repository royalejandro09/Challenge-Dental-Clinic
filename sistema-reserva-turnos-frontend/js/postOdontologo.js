window.onload = () => {

    const formulario = document.getElementById("formularioOdontologo");

    formulario.addEventListener("submit", event => {
        event.preventDefault();
        obtenerToken();
    });

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
            agregarOdontologo(data);
        })

}

function agregarOdontologo(data) {

    const nombre = document.getElementById("nombre");
    const apellido = document.getElementById("apellido");
    const matricula = document.getElementById("matricula");



    const formData = {
        nombre: nombre.value,
        apellido: apellido.value,
        matricula: matricula.value,
    };

    const settings = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + data.access_token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData),
    };

    fetch("http://localhost:8080/odontologos", settings)
        .then((response) => response.json())
        .then(() => alert("Se creó el odontólogo"))
        .catch(() => alert("No se pudo crear el odontólogo"))
        .finally(() => {
            nombre.value = "";
            apellido.value = "";
            matricula.value = "";
        })
}



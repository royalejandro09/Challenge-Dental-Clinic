window.onload = () => {
    const formulario = document.getElementById("formularioPaciente");

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
            agregarPaciente(data);
        })

}

function agregarPaciente(data) {
    const nombre = document.getElementById("nombre");
    const apellido = document.getElementById("apellido");
    const dni = document.getElementById("dni");
    const calle = document.getElementById("calle");
    const numero = document.getElementById("numero");
    const localidad = document.getElementById("localidad");
    const provincia = document.getElementById("provincia");

    const formData = {
        nombre: nombre.value,
        apellido: apellido.value,
        dni: dni.value,
        domicilioDto: {
            calle: calle.value,
            numero: numero.value,
            localidad: localidad.value,
            provincia: provincia.value
        }
    };

    const settings = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + data.access_token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    };

    fetch("http://localhost:8080/pacientes", settings)
        .then((response) => response.json())
        .then(() => alert("Se creó el paciente"))
        .catch(() => alert("No se pudo crear el paciente"))
        .finally(() => {
            nombre.value = "";
            apellido.value = "";
            dni.value = "";
            calle.value = "";
            numero.value = "";
            localidad.value = "";
            provincia.value = "";
        })
}
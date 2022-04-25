
let myHeaders2 = new Headers();
myHeaders2.append("Authorization", "Basic cmVzZXJ2YXR1cm5vczpyM3NlcnY0dHVyNW9z");
myHeaders2.append("Content-Type", "application/x-www-form-urlencoded");

let urlencoded2 = new URLSearchParams();
urlencoded2.append("grant_type", "password");
urlencoded2.append("username", "usertest1");
urlencoded2.append("password", "12345");

let requestOptions2 = {
    method: 'POST',
    headers: myHeaders2,
    body: urlencoded2,
    redirect: 'follow'
};



function eliminar(id) {

    fetch("http://localhost:8080/oauth/token", requestOptions2)
        .then(response => response.json())
        .then((data) => {
            eliminarOdontologo(id, data);

        })

}

function eliminarOdontologo(id, data) {
    const settings = {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + data.access_token
        }
    };

    fetch(`http://localhost:8080/odontologos/${id}`, settings);
    location.reload();
}

function actualizar(id, matricula) {
    
    fetch("http://localhost:8080/oauth/token", requestOptions2)
        .then(response => response.json())
        .then((data) => {
            actualizarOdontologo(id, matricula, data);

        })

}

function actualizarOdontologo(id, matricula, data) {

    mostrarFormulario(matricula);

    document.getElementById("actualizarOdontologoBtn").addEventListener("click", event => {
        
        event.preventDefault();

        const nombre = document.getElementById("nombre");
        const apellido = document.getElementById("apellido");
        const matricula = document.getElementById("matricula");

        const formData = {
            id: id,
            nombre: nombre.value,
            apellido: apellido.value,
            matricula: matricula.value,
        };


        const settings = {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + data.access_token,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetch('http://localhost:8080/odontologos', settings)
            .then((response) => response.json())
            .then(data => console.log( data))
            .then(() => alert("Se actualizó el odontólogo"))
            .finally(() => {
                ocultarFormulario();
                resetearCampos();
            })

        location.reload();
    });

    document.getElementById("resetearOdontologoBtn").addEventListener("click", event => {
        event.preventDefault();
        resetearCampos();
        ocultarFormulario();

    });
    function resetearCampos() {
        document.getElementById("nombre").value = "";
        document.getElementById("apellido").value = "";
        document.getElementById("matricula").value = "";
    }
}

function mostrarFormulario( matricula) {

    document.getElementById("actualizarOdontologo").classList.remove("display-none");
    document.getElementById("listado").classList.add("display-none");
    document.getElementById("matricula").value=matricula;

}

function ocultarFormulario() {
    document.getElementById("actualizarOdontologo").classList.add("display-none")
    document.getElementById("listado").classList.remove("display-none");
}

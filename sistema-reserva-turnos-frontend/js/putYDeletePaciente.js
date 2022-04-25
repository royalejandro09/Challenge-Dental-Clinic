

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
            eliminarPaciente(id, data);

        })

}

function eliminarPaciente(id, data) {

    const settings = {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + data.access_token
        }
    };

    fetch(`http://localhost:8080/pacientes/${id}`, settings)
    location.reload();

}

function actualizar(id) {
    
    fetch("http://localhost:8080/oauth/token", requestOptions2)
        .then(response => response.json())
        .then((data) => {
            actualizarPaciente(id, data);

        })

}

function actualizarPaciente(id, data) {

    mostrarFormulario();

    document.getElementById("actualizarPacienteBtn").addEventListener("click", event => {

        event.preventDefault();

        const nombre = document.getElementById("nombre");
        const apellido = document.getElementById("apellido");
        const dni = document.getElementById("dni");
        const calle = document.getElementById("calle");
        const numero = document.getElementById("numero");
        const localidad = document.getElementById("localidad");
        const provincia = document.getElementById("provincia");


        const formData = {
            id: id,
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
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + data.access_token
            },
            body: JSON.stringify(formData),
        };

        fetch(`http://localhost:8080/pacientes`, settings)
            .then((response) => response.json())
            .then(() => alert("Se actualizó el Paciente"))
            .finally(() => {
                ocultarFormulario();
                resetearCampos();
            })
            location.reload();
    });

    document.getElementById("resetearPacienteBtn").addEventListener("click", event => {
        event.preventDefault();
        resetearCampos();
        ocultarFormulario();
    });

    function resetearCampos() {
        document.getElementById("nombre").value = "";
        document.getElementById("apellido").value = "";
        document.getElementById("dni").value = "";
        document.getElementById("calle").value = "";
        document.getElementById("numero").value = "";
        document.getElementById("localidad").value = "";
        document.getElementById("provincia").value = "";
    }
}

function mostrarFormulario() {
    document.getElementById("actualizarPaciente").classList.remove("display-none");
    document.getElementById("listado").classList.add("display-none")
}

function ocultarFormulario() {
    document.getElementById("actualizarPaciente").classList.add("display-none")
    document.getElementById("listado").classList.remove("display-none");
}

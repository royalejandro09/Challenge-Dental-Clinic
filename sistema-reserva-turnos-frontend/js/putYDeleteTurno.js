

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
            eliminarTurno(id, data);

        })

}

function eliminarTurno(id, data) {

    const settings = {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + data.access_token
        }
    };

    fetch(`http://localhost:8080/turnos/${id}`, settings)
    location.reload();

}

function actualizar(id) {
    
    fetch("http://localhost:8080/oauth/token", requestOptions2)
        .then(response => response.json())
        .then((data) => {
            actualizarTurno(id, data);

        })

}

function actualizarTurno(id, data) {

    mostrarFormulario();

    document.getElementById("actualizarTurnoBtn").addEventListener("click", event => {

        event.preventDefault();

        const paciente = document.getElementById("paciente");
        const odontologo = document.getElementById("odontologo");
        const fechaHora = document.getElementById("fecha");

        let idO = parseInt(odontologo.value)
        let idP = parseInt(paciente.value)


        const formData = {
            id: id,
            odontologoDto: {
                id: idO
            },
            pacienteDto: {
                id: idP
            },
            fechaHora: fechaHora.value,
        };

        const settings = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + data.access_token
            },
            body: JSON.stringify(formData),
        };

        fetch(`http://localhost:8080/turnos`, settings)
            .then((response) => response.json())
            .then(() => alert("Se actualizó el Turno"))
            .finally(() => {
                ocultarFormulario();
                resetearCampos();
            })
        location.reload();
    });

    document.getElementById("resetearTurnoBtn").addEventListener("click", event => {
        event.preventDefault();
        resetearCampos();
        ocultarFormulario();
    });

    function resetearCampos() {
        document.getElementById("paciente").value = "";
        document.getElementById("odontologo").value = "";
        document.getElementById("fecha").value = "";
    }
}

function mostrarFormulario() {
    document.getElementById("actualizarTurno").classList.remove("display-none");
    document.getElementById("listado").classList.add("display-none")
}

function ocultarFormulario() {
    document.getElementById("actualizarTurno").classList.add("display-none")
    document.getElementById("listado").classList.remove("display-none");
}

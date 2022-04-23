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
            cargarInformacionTurno(data);
        })

}


function cargarInformacionTurno(data) {

    const settings = {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + data.access_token
        }
    };

    fetch("http://localhost:8080/turnos", settings)

        .then((response) => response.json())
        .then((turnos) => {

            const tablaTurnos = document.getElementById("tablaTurno");

            turnos.forEach((turno) => {
                tablaTurnos.innerHTML += `
                    <tr>
                        <td>${turno.id}</td>
                        <td>${turno.pacienteDto.id + " - " + turno.pacienteDto.nombre + " " + turno.pacienteDto.apellido}</td>
                        <td>${turno.odontologoDto.id + " - " + turno.odontologoDto.nombre + " " + turno.odontologoDto.apellido}</td>
                        <td>${turno.fechaHora}</td>
                        <td><button class="btn btn-primary" onClick="actualizar(${turno.id})">Editar✏️</button></td> 
                        <td><button class="btn btn-primary" onClick="eliminar(${turno.id})">Eliminar❌</button></td> 
                    </tr>
                `;
            });

        });

}
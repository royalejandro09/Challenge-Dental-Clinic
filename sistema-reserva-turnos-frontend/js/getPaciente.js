window.onload = () => {
    obtenerToken()

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
            cargarInformacionPacientes(data);
        })

}



function cargarInformacionPacientes(data) {

    const settings = {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + data.access_token
        }
    };

    fetch("http://localhost:8080/pacientes", settings)

        .then((response) => response.json())
        .then((pacientes) => {

            const tablaPacientes = document.getElementById("tablaPacientes");
        
            pacientes.forEach((paciente) => {
                tablaPacientes.innerHTML += `
                    <tr>
                        <td>${paciente.id}</td>
                        <td>${paciente.nombre}</td>
                        <td>${paciente.apellido}</td>
                        <td>${paciente.dni}</td>
                        <td>${paciente.fechaCreacion}</td>
                        <td>${paciente.domicilioDto.calle + " # " + paciente.domicilioDto.numero + " " + paciente.domicilioDto.localidad}</td>
                        <td><button class="btn btn-primary" onClick="actualizar(${paciente.id})">Editar✏️</button></td> 
                        <td><button class="btn btn-primary" onClick="eliminar(${paciente.id})">Eliminar❌</button></td> 
                    </tr>
                `;
            });

        });
}

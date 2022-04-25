package com.digitalhouse.reservaturnosaprgbt.Integrationjpatest;

import com.digitalhouse.reservaturnosaprgbt.entity.Domicilio;
import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import com.digitalhouse.reservaturnosaprgbt.entity.Turno;
import com.digitalhouse.reservaturnosaprgbt.repository.IOdontologoRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.IPacienteRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.ITurnoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TurnoJpaTest {
    /**
     * Injection Of Dependency
     */
    @Autowired
    ITurnoRepository turnoRepository;

    @Autowired
    IPacienteRepository pacienteRespository;

    @Autowired
    IOdontologoRepository odontologoRepository;

    Turno newTurno = null;

    @BeforeEach
    void setUp() {

        // Given - Odontologo
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setNombre("Pepito");
        odontologoEntity.setApellido("Fulano");
        odontologoEntity.setMatricula(12345);

        //When - Odontologo
        Odontologo newOdontologoEntity = odontologoRepository.save(odontologoEntity);

        // Given - Paciente
        Domicilio domicilioEntity = new Domicilio();
        domicilioEntity.setCalle("Calle");
        domicilioEntity.setNumero(1234);
        domicilioEntity.setLocalidad("Suba");
        domicilioEntity.setProvincia("Bogota");

        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setNombre("Pepe");
        pacienteEntity.setApellido("Florez");
        pacienteEntity.setDni("12345");
        pacienteEntity.setFechaDeCreacion(LocalDate.now());
        pacienteEntity.setDomicilio(domicilioEntity);

        //When - Paciente
        Paciente newPacienteEntity = pacienteRespository.save(pacienteEntity);

        // Given - Turno
        Turno turnoEntity = new Turno();
        turnoEntity.setOdontologo(odontologoEntity);
        turnoEntity.setPaciente(pacienteEntity);
        turnoEntity.setFechaHora(LocalDateTime.now());

        // When - Turno
        newTurno = turnoRepository.save(turnoEntity);

    }

    @Test
    @DisplayName("save turno on DB test")
    void saveTurnoTest() {

        // Given

        // When

        //Then
        assertNotNull(newTurno);
        assertEquals("Pepito", newTurno.getOdontologo().getNombre());
        assertEquals(12345, newTurno.getOdontologo().getMatricula());
        assertEquals("Pepe", newTurno.getPaciente().getNombre());
    }

    @Test
    @DisplayName("find all turno from DB test")
    void findAllTurnoTest() {

        // Given

        // When
        List<Turno> listTurnos = turnoRepository.findAll();

        // Then
        Assertions.assertThat(listTurnos).hasSize(1);
        assertNotNull(listTurnos.get(0));
        assertEquals("Pepito", listTurnos.get(0).getOdontologo().getNombre());
        assertEquals(12345, listTurnos.get(0).getOdontologo().getMatricula());
        assertEquals("Pepe", listTurnos.get(0).getPaciente().getNombre());
    }

    @Test
    @DisplayName("find turno by id from DB test")
    void findByIdPacienteTest() {

        // Given

        // When
        Optional<Turno> turnoEntity = turnoRepository.findById(newTurno.getId());

        // Then
        assertTrue(turnoEntity.isPresent());
        assertEquals("Pepito", turnoEntity.orElseThrow().getOdontologo().getNombre());
        assertEquals(12345, turnoEntity.orElseThrow().getOdontologo().getMatricula());
        assertEquals("Pepe", turnoEntity.orElseThrow().getPaciente().getNombre());
    }

    @Test
    @DisplayName("update existing pacient on DB test")
    void updatePacientTest() {

        // Given
        LocalDateTime updatedLocalDateTime = LocalDateTime.now();
        newTurno.setFechaHora(updatedLocalDateTime);

        //When
        Turno updatedTurnoEntity = turnoRepository.save(newTurno);

        //Then
        assertNotNull(updatedTurnoEntity);
        assertEquals("Pepito", updatedTurnoEntity.getOdontologo().getNombre());
        assertEquals(12345, updatedTurnoEntity.getOdontologo().getMatricula());
        assertEquals("Pepe", updatedTurnoEntity.getPaciente().getNombre());
        assertEquals(updatedLocalDateTime, updatedTurnoEntity.getFechaHora());
    }

    @Test
    @DisplayName("delete existing turno on DB test")
    void deleteTurnoTest() {

        // Given

        // When
        turnoRepository.delete(newTurno);

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            turnoRepository.findById(newTurno.getId()).orElseThrow();
        });
    }

}

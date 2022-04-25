package com.digitalhouse.reservaturnosaprgbt.Integrationjpatest;

import com.digitalhouse.reservaturnosaprgbt.entity.Domicilio;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import com.digitalhouse.reservaturnosaprgbt.repository.IPacienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PacienteJpaTest {

    /**
     * Inhjection Of Dependency
     */
    @Autowired
    IPacienteRepository pacienteRepository;

    Paciente newPacienteEntity = null;

    @BeforeEach
    void setUp() {

        // Given
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

        //When
        newPacienteEntity = pacienteRepository.save(pacienteEntity);
    }

    @Test
    @DisplayName("save pacient on DB test")
    void savePacienteTest() {

        // Given
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

        //When
        Paciente newPacientEntity = pacienteRepository.save(pacienteEntity);

        //Then
        assertNotNull(newPacientEntity);
        assertEquals("Pepe", newPacientEntity.getNombre());
        assertEquals("Florez", newPacientEntity.getApellido());
        assertEquals(12345, newPacientEntity.getDni());
    }

    @Test
    @DisplayName("find all pacient from DB test")
    void findAllPacienteTest() {

        // Given

        // When
        List<Paciente> listPacientes = pacienteRepository.findAll();

        // Then
        Assertions.assertThat(listPacientes).hasSize(1);
        assertNotNull(listPacientes.get(0));
        assertEquals("Pepe", listPacientes.get(0).getNombre());
        assertEquals("Florez", listPacientes.get(0).getApellido());
        assertEquals("12345", listPacientes.get(0).getDni());
    }

    @Test
    @DisplayName("find pacient by id from DB test")
    void findByIdPacienteTest() {

        // Given

        // When
        Optional<Paciente> pacienteEntity = pacienteRepository.findById(newPacienteEntity.getId());

        // Then
        assertTrue(pacienteEntity.isPresent());
        assertEquals("Pepe", pacienteEntity.orElseThrow().getNombre());
        assertEquals("Florez", pacienteEntity.orElseThrow().getApellido());
        assertEquals("12345", pacienteEntity.orElseThrow().getDni());
    }

    @Test
    @DisplayName("update existing pacient on DB test")
    void updatePacientTest() {

        // Given
        newPacienteEntity.setNombre("Pepito");
        newPacienteEntity.setApellido("Floriscienta");
        newPacienteEntity.setDni("99999");

        //When
        Paciente updatedPacientEntity = pacienteRepository.save(newPacienteEntity);

        //Then
        assertNotNull(updatedPacientEntity);
        assertEquals("Pepito", updatedPacientEntity.getNombre());
        assertEquals("Floriscienta", updatedPacientEntity.getApellido());
        assertEquals("99999", updatedPacientEntity.getDni());
    }

    @Test
    @DisplayName("delete existing pacient on DB test")
    void deletePacientTest() {

        // Given

        // When
        pacienteRepository.delete(newPacienteEntity);

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            pacienteRepository.findById(newPacienteEntity.getId()).orElseThrow();
        });
    }

}

package com.digitalhouse.reservaturnosaprgbt.Integrationjpatest;

import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import com.digitalhouse.reservaturnosaprgbt.repository.IOdontologoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class OdontologoJpaTest {

    /**
     * Injection Of Dependency
     */
    IOdontologoRepository odontologoRepository;

    @Test
    @DisplayName("Save Odontologo on DB test")
    void saveOdontologoTest() {

        //Given
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setNombre("Pedro");
        odontologoEntity.setApellido("Perez");
        odontologoEntity.setMatricula(12345);

        //When
        Odontologo newOdontEntity = odontologoRepository.save(odontologoEntity);

        //Then
        assertNotNull(newOdontEntity);
        assertEquals("Pedro", newOdontEntity.getNombre());
        assertEquals("Perez", newOdontEntity.getApellido());
        assertEquals(12345, newOdontEntity.getMatricula());
    }

    @Test
    @DisplayName("find all odontologo from DB test")
    void findAllOdontologoTest() {

        // Given

        // When
        List<Odontologo> listOdontologos = odontologoRepository.findAll();

        // Then
        Assertions.assertThat(listOdontologos).hasSize(1);
        assertNotNull(listOdontologos.get(0));
        assertEquals("Pepe", listOdontologos.get(0).getNombre());
        assertEquals("Florez", listOdontologos.get(0).getApellido());
        assertEquals(159, listOdontologos.get(0).getMatricula());
    }

    @Test
    @DisplayName("find odontologo by id from DB test")
    void findByIdOdontologoTest() {

        // Given

        //When
        Optional<Odontologo> odontologoEntity = odontologoRepository.findById(1);

        //Then
        assertTrue(odontologoEntity.isPresent());
        assertEquals("Pepe", odontologoEntity.orElseThrow().getNombre());
        assertEquals("Florez", odontologoEntity.orElseThrow().getApellido());
        assertEquals(159, odontologoEntity.orElseThrow().getMatricula());
    }

    @Test
    @DisplayName("update existing odontologo on DB test")
    void updateOdontologoTest() {

        // Given
        Optional<Odontologo> odontologoEntity = odontologoRepository.findById(1);
        Odontologo odontologoToUpdateEntity = new Odontologo();
        odontologoToUpdateEntity.setId(odontologoEntity.orElseThrow().getId());
        odontologoToUpdateEntity.setNombre("Pepa");
        odontologoToUpdateEntity.setApellido("Juarez");
        odontologoToUpdateEntity.setMatricula(6789);

        //When
        Odontologo updatedOdontologoEntity = odontologoRepository.save(odontologoToUpdateEntity);

        //Then
        assertNotNull(updatedOdontologoEntity);
        assertEquals("Pepa", updatedOdontologoEntity.getNombre());
        assertEquals("Juarez", updatedOdontologoEntity.getApellido());
        assertEquals(6789, updatedOdontologoEntity.getMatricula());
    }

    @Test
    @DisplayName("delete existing odontologo on DB test")
    void deleteOdontologoTest() {

        // Given
        Odontologo odontologoEntity = odontologoRepository.findById(1).orElse(null);

        // When
        odontologoRepository.delete(odontologoEntity);

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            odontologoRepository.findById(odontologoEntity.getId()).orElseThrow();
        });
    }

}

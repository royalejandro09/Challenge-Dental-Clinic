package com.digitalhouse.reservaturnosaprgbt.servcieImpl;

import com.digitalhouse.reservaturnosaprgbt.dto.DomicilioDto;
import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.dto.TurnoDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Domicilio;
import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import com.digitalhouse.reservaturnosaprgbt.entity.Turno;
import com.digitalhouse.reservaturnosaprgbt.mapper.TurnoMapper;
import com.digitalhouse.reservaturnosaprgbt.repository.ITurnoRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.service.impl.TurnoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TurnoServiceImplTest {

    @Mock
    TurnoMapper turnoMapper;

    @Mock
    ITurnoRepository turnoRepository;

    @InjectMocks
    TurnoServiceImpl turnoService;

    TurnoDto turnoDto = null;
    Turno turnoEntity = null;


    @BeforeEach
    void setUp() {

        //DTO
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Fulano");
        odontologoDto.setMatricula(12345);

        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle");
        domicilioDto.setNumero(1234);
        domicilioDto.setLocalidad("Suba");
        domicilioDto.setProvincia("Bogota");

        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setNombre("Avila");
        pacienteDto.setApellido("Gianlucha");
        pacienteDto.setDni("12345");
        pacienteDto.setFechaDeCreacion(LocalDate.now());
        pacienteDto.setDomicilioDto(domicilioDto);

        turnoDto = new TurnoDto();
        turnoDto.setOdontologoDto(odontologoDto);
        turnoDto.setPacienteDto(pacienteDto);
        turnoDto.setFechaHora(LocalDateTime.now());

        // Entity
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setNombre("Pepito");
        odontologoEntity.setApellido("Fulano");
        odontologoEntity.setMatricula(12345);

        Domicilio domicilioEntity = new Domicilio();
        domicilioEntity.setCalle("Calle La Esperanza");
        domicilioEntity.setNumero(127);
        domicilioEntity.setLocalidad("Suba");
        domicilioEntity.setProvincia("Bogota");

        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setNombre("Avila");
        pacienteEntity.setApellido("Gianlucha");
        pacienteEntity.setDni("12345");
        pacienteEntity.setFechaDeCreacion(LocalDate.now());
        pacienteEntity.setDomicilio(domicilioEntity);

        turnoEntity = new Turno();
        turnoEntity.setOdontologo(odontologoEntity);
        turnoEntity.setPaciente(pacienteEntity);
        turnoEntity.setFechaHora(LocalDateTime.now());

    }


    @Test
    @DisplayName("registrar turno on DB test")
    void registrarTurnoTest() throws Exception {

        //Given
        Mockito.when(turnoMapper.mapToEntity(turnoDto)).thenReturn(turnoEntity);

        Mockito.when(turnoRepository.save(turnoEntity)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return turnoEntity;
        });

        Mockito.when(turnoMapper.mapToDto(turnoEntity)).then(invocationOnMock -> {
            turnoDto.setId(1);
            return turnoDto;
        });

        // When
        TurnoDto turnoResponseDto = turnoService.save(turnoDto);

        //Then
        assertNotNull(turnoResponseDto);
        assertEquals(1, turnoResponseDto.getId());
        assertEquals("Pepito", turnoResponseDto.getOdontologoDto().getNombre());
        assertEquals("Avila", turnoResponseDto.getPacienteDto().getNombre());

    }

    @Test
    @DisplayName("modificar Turno on DB test")
    void modificarTurnoTest() throws Exception {

        // Given
        Mockito.when(turnoMapper.mapToEntity(turnoDto)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return turnoEntity;
        });

        Mockito.when(turnoRepository.save(turnoEntity)).then(invocationOnMock -> {

            turnoEntity.setId(1);
            turnoEntity.setOdontologo(turnoEntity.getOdontologo());
            turnoEntity.setPaciente(turnoEntity.getPaciente());

            return turnoEntity;
        });

        Mockito.when(turnoMapper.mapToDto(turnoEntity)).then(invocationOnMock -> {
            turnoDto.setId(1);
            turnoDto.setOdontologoDto(turnoDto.getOdontologoDto());
            turnoDto.setPacienteDto(turnoDto.getPacienteDto());
            return turnoDto;
        });

        // When
        TurnoDto turnoResponseDto = turnoService.save(turnoDto);

        // Then
        assertNotNull(turnoResponseDto);
        assertEquals(1, turnoResponseDto.getId());
        assertEquals("Pepito", turnoResponseDto.getOdontologoDto().getNombre());

    }

    @Test
    @DisplayName("listar turno on DB test")
    void listarTurnoTest() throws Exception {

        // Given
        turnoEntity.setId(1);
        List<Turno> turnoList = new ArrayList<>();
        turnoList.add(turnoEntity);

        Mockito.when(turnoRepository.findAll()).thenReturn(turnoList);

        Mockito.when(turnoMapper.mapToListDto(turnoList)).then(invocationOnMock -> {
            turnoDto.setId(1);
            List<TurnoDto> turnoDtoList = new ArrayList<>();
            turnoDtoList.add(turnoDto);
            return turnoDtoList;
        });

        // When
        List<TurnoDto> turnoDtoList = turnoService.findAll();

        // Then
        assertNotNull(turnoDtoList);
        assertEquals(1, turnoDtoList.size());
        assertEquals(1, turnoDtoList.get(0).getId());
        assertEquals("Fulano", turnoDtoList.get(0).getOdontologoDto().getApellido());
        assertEquals("Gianlucha", turnoDtoList.get(0).getPacienteDto().getApellido());
    }

    @Test
    @DisplayName("buscar turnos por Id on DB test")
    void buscarPorIdTurnosTest() throws Exception {

        // Given
        Mockito.when(turnoRepository.findById(1)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return Optional.of(turnoEntity);
        });

        Mockito.when(turnoMapper.mapToDto(turnoEntity)).then(invocationOnMock -> {
            turnoDto.setId(1);
            return turnoDto;
        });

        // When
        TurnoDto turnoResponseDto = turnoService.findById(1);

        // Then
        assertNotNull(turnoResponseDto);
        assertEquals(1, turnoResponseDto.getId());
        assertEquals("Fulano", turnoResponseDto.getOdontologoDto().getApellido());
        assertEquals("Gianlucha", turnoResponseDto.getPacienteDto().getApellido());

    }

    @Test
    @DisplayName("eliminar turnos on DB test")
    void eliminarTurnosTest() throws Exception {

        // Given
        Mockito.when(turnoRepository.findById(1)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return Optional.of(turnoEntity);
        });

        Mockito.doNothing().when(turnoRepository).deleteById(1);

        // When
        turnoService.delete(1);
        TurnoDto turnoDto = turnoService.findById(1);


        // Then
        assertNull(turnoDto);
        Mockito.verify(turnoRepository, Mockito.times(2)).findById(1);
    }
}

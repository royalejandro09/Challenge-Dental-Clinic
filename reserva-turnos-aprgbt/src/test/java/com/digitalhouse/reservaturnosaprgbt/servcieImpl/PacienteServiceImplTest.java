package com.digitalhouse.reservaturnosaprgbt.servcieImpl;

import com.digitalhouse.reservaturnosaprgbt.dto.DomicilioDto;
import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Domicilio;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import com.digitalhouse.reservaturnosaprgbt.mapper.PacienteMapper;
import com.digitalhouse.reservaturnosaprgbt.repository.IPacienteRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.service.impl.PacienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PacienteServiceImplTest {

    @Mock
    PacienteMapper pacienteMapper;

    @Mock
    IPacienteRepository pacienteRepository;

    @InjectMocks
    PacienteServiceImpl pacienteService;

    Paciente pacienteEntity = null;
    PacienteDto pacienteDto = null;

    @BeforeEach
    void setUp() {

        // DTO
        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle La Esperanza");
        domicilioDto.setNumero(127);
        domicilioDto.setLocalidad("Suba");
        domicilioDto.setProvincia("Bogota");

        pacienteDto = new PacienteDto();
        pacienteDto.setNombre("Avila");
        pacienteDto.setApellido("Gianlucha");
        pacienteDto.setDni("12345");
        pacienteDto.setFechaDeCreacion(LocalDate.now());
        pacienteDto.setDomicilioDto(domicilioDto);


        // Entity
        Domicilio domicilioEntity = new Domicilio();
        domicilioEntity.setCalle("Calle La Esperanza");
        domicilioEntity.setNumero(127);
        domicilioEntity.setLocalidad("Suba");
        domicilioEntity.setProvincia("Bogota");

        pacienteEntity = new Paciente();
        pacienteEntity.setNombre("Avila");
        pacienteEntity.setApellido("Gianlucha");
        pacienteEntity.setDni("12345");
        pacienteEntity.setFechaDeCreacion(LocalDate.now());
        pacienteEntity.setDomicilio(domicilioEntity);

    }

    @Test
    @DisplayName("registrar paciente on DB test")
    void registrarPacienteTest() throws Exception {

        // Given
        Mockito.when(pacienteMapper.mapToEntity(pacienteDto)).thenReturn(pacienteEntity);

        Mockito.when(pacienteRepository.save(pacienteEntity)).then(invocationOnMock -> {

            pacienteEntity.setId(1);
            return pacienteEntity;
        });

        Mockito.when(pacienteMapper.mapToDto(pacienteEntity)).then(invocationOnMock -> {
            pacienteDto.setId(1);
            return pacienteDto;
        });

        // When
        PacienteDto pacienteResponseDto = pacienteService.save(pacienteDto);

        // Then
        assertNotNull(pacienteResponseDto);
        assertEquals(1, pacienteResponseDto.getId());
        assertEquals("Avila", pacienteResponseDto.getNombre());
        assertEquals("Gianlucha", pacienteResponseDto.getApellido());
        assertEquals("12345", pacienteResponseDto.getDni());
    }

    @Test
    @DisplayName("modificar paciente on DB test")
    void modificarPacienteTest() throws Exception {

        // Given
        Mockito.when(pacienteMapper.mapToEntity(pacienteDto)).then(invocationOnMock -> {
            pacienteEntity.setId(1);
            return pacienteEntity;
        });

        Mockito.when(pacienteRepository.save(pacienteEntity)).then(invocationOnMock -> {

            pacienteEntity.setId(1);
            pacienteEntity.setNombre("Avila2");
            pacienteEntity.setApellido("Gianlucha2");
            pacienteEntity.setDni("56789");
            return pacienteEntity;
        });

        Mockito.when(pacienteMapper.mapToDto(pacienteEntity)).then(invocationOnMock -> {
            pacienteDto.setId(1);
            pacienteDto.setNombre("Avila2");
            pacienteDto.setApellido("Gianlucha2");
            pacienteDto.setDni("56789");
            return pacienteDto;
        });

        // When
        PacienteDto pacienteResponseDto = pacienteService.save(pacienteDto);

        // Then
        assertNotNull(pacienteResponseDto);
        assertEquals(1, pacienteResponseDto.getId());
        assertEquals("Avila2", pacienteResponseDto.getNombre());
        assertEquals("Gianlucha2", pacienteResponseDto.getApellido());
        assertEquals("56789", pacienteResponseDto.getDni());
    }

    @Test
    @DisplayName("listar paciente on DB test")
    void listarPacienteTest() throws Exception {

        // Given
        pacienteEntity.setId(1);
        List<Paciente> pacienteList = new ArrayList<>();
        pacienteList.add(pacienteEntity);

        Mockito.when(pacienteRepository.findAll()).thenReturn(pacienteList);

        Mockito.when(pacienteMapper.mapToListDto(pacienteList)).then(invocationOnMock -> {
            pacienteDto.setId(1);
            List<PacienteDto> pacienteDtoList = new ArrayList<>();
            pacienteDtoList.add(pacienteDto);
            return pacienteDtoList;
        });

        // When
        List<PacienteDto> pacienteDtoList = pacienteService.findAll();

        // Then
        assertNotNull(pacienteDtoList);
        assertEquals(1, pacienteDtoList.size());
        assertEquals(1, pacienteDtoList.get(0).getId());
        assertEquals("Avila", pacienteDtoList.get(0).getNombre());
        assertEquals("Gianlucha", pacienteDtoList.get(0).getApellido());
        assertEquals("12345", pacienteDtoList.get(0).getDni());
    }

    @Test
    @DisplayName("buscar paciente por Id on DB test")
    void buscarPorIdPacienteTest() throws Exception {

        // Given
        Mockito.when(pacienteRepository.findById(1)).then(invocationOnMock -> {
            pacienteEntity.setId(1);
            return Optional.of(pacienteEntity);
        });

        Mockito.when(pacienteMapper.mapToDto(pacienteEntity)).then(invocationOnMock -> {
            pacienteDto.setId(1);
            return pacienteDto;
        });

        // When
        PacienteDto pacienteResponseDto = pacienteService.findById(1);

        // Then
        assertNotNull(pacienteResponseDto);
        assertEquals(1, pacienteResponseDto.getId());
        assertEquals("Avila", pacienteResponseDto.getNombre());
        assertEquals("Gianlucha", pacienteResponseDto.getApellido());
        assertEquals("12345", pacienteResponseDto.getDni());
    }

    @Test
    @DisplayName("buscar paciente no existente por Id on DB test")
    void buscarPorIdPacienteNoExistenteTest() throws Exception {

        // Given
        Mockito.when(pacienteRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        // When
        PacienteDto pacDto = pacienteService.findById(1);

        // Then
        assertNull(pacDto);
        Mockito.verify(pacienteRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("eliminar paciente on DB test")
    void eliminarPacienteTest() throws Exception {

        // Given
        Mockito.when(pacienteRepository.findById(1)).then(invocationOnMock -> {
            pacienteEntity.setId(1);
            return Optional.of(pacienteEntity);
        });

        Mockito.doNothing().when(pacienteRepository).deleteById(1);

        // When
        pacienteService.delete(1);
        PacienteDto pacDto = pacienteService.findById(1);

        // Then
        assertNull(pacDto);
        Mockito.verify(pacienteRepository, Mockito.times(2)).findById(1);
    }
}

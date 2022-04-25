package com.digitalhouse.reservaturnosaprgbt.controller;


import com.digitalhouse.reservaturnosaprgbt.dto.DomicilioDto;
import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.dto.TurnoDto;
import com.digitalhouse.reservaturnosaprgbt.repository.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TurnoController.class)
class TurnoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ITurnoService service;

    ObjectMapper objectMapper;

    TurnoDto turnoDto = null;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        // DTO
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Pepe");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle La Esperanza");
        domicilioDto.setNumero(127);
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
    }

    @Disabled
    @Test
    @DisplayName("registrar turno on DB test")
    void registrarTurnoTest() throws Exception {
        // Given
        Mockito.when(service.save(ArgumentMatchers.any())).then(invocationOnMock -> {
            TurnoDto newTurnoDto = invocationOnMock.getArgument(0);
            newTurnoDto.setId(1);
            newTurnoDto.setOdontologoDto(turnoDto.getOdontologoDto());
            newTurnoDto.setPacienteDto(turnoDto.getPacienteDto());
            newTurnoDto.setFechaHora(turnoDto.getFechaHora());
            return newTurnoDto;
        });

        // When
        mvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .content(asJsonString(turnoDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.odontologoDto.nombre").value(turnoDto.getOdontologoDto().getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pacienteDto.nombre").value(turnoDto.getPacienteDto().getNombre()));

        Mockito.verify(service).save(ArgumentMatchers.any());
    }

    @Disabled
    @Test
    @DisplayName("listar turno on DB test")
    void listarTurnoTest() throws Exception {

        // Given
        Mockito.when(service.findAll()).then(invocationOnMock -> {
            List<TurnoDto> turnoDtoList = new ArrayList<>();

            TurnoDto turnoNewDto = new TurnoDto();
            turnoNewDto.setId(1);
            turnoNewDto.setOdontologoDto(turnoDto.getOdontologoDto());
            turnoNewDto.setPacienteDto(turnoDto.getPacienteDto());
            turnoNewDto.setFechaHora(turnoDto.getFechaHora());

            turnoDtoList.add(turnoNewDto);

            return turnoDtoList;
        });

        // When
        mvc.perform(MockMvcRequestBuilders.get("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].odontologoDto.nombre").value(turnoDto.getOdontologoDto().getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pacienteDto.nombre").value(turnoDto.getPacienteDto().getNombre()));

        Mockito.verify(service).findAll();
    }

    @Disabled
    @Test
    @DisplayName("buscar turnos por Id on DB test")
    void buscarPorIdTurnosTest() throws Exception {

        // Given
        Mockito.when(service.findById(1)).then(invocationOnMock -> {
            TurnoDto turnoNewDto = new TurnoDto();
            turnoNewDto.setId(1);
            turnoNewDto.setOdontologoDto(turnoDto.getOdontologoDto());
            turnoNewDto.setPacienteDto(turnoDto.getPacienteDto());
            turnoNewDto.setFechaHora(turnoDto.getFechaHora());
            return turnoNewDto;
        });

        // When
        mvc.perform(MockMvcRequestBuilders.get("/turnos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.odontologoDto.nombre").value(turnoDto.getOdontologoDto().getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pacienteDto.nombre").value(turnoDto.getPacienteDto().getNombre()));

        Mockito.verify(service).findById(1);
    }

    @Disabled
    @Test
    @DisplayName("modificar Turno on DB test")
    void modificarTurnoTest() throws Exception {

        OdontologoDto dto = new OdontologoDto();
        dto.setNombre("Fulanito");
        dto.setApellido("Firulais");
        dto.setMatricula(123);

        TurnoDto dto1 = new TurnoDto();
        dto1.setOdontologoDto(dto);

        // Given
        Mockito.when(service.save(ArgumentMatchers.any())).then(invocationOnMock -> {

            TurnoDto newTurnoDto = invocationOnMock.getArgument(0);
            newTurnoDto.setId(1);
            newTurnoDto.setOdontologoDto(dto1.getOdontologoDto());
            return newTurnoDto;
        });

        // When
        mvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .content(asJsonString(turnoDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.odontologoDto.nombre").value(dto1.getOdontologoDto().getNombre()));

        Mockito.verify(service).save(ArgumentMatchers.any());
    }

    @Disabled
    @Test
    @DisplayName("eliminar turno on DB test")
    void eliminarTurnoTest() throws Exception {

        // Given
        Mockito.doNothing().when(service).delete(1);

        // When
        mvc.perform(MockMvcRequestBuilders.delete("/turnos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(service).delete(1);
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
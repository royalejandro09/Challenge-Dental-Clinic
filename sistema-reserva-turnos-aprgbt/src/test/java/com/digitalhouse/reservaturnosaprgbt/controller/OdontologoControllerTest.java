package com.digitalhouse.reservaturnosaprgbt.controller;

import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IOdontologoService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(OdontologoController.class)
class OdontologoControllerTest {

    /**
     * Injection Of Dependency
     */
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IOdontologoService odontologoService;

    ObjectMapper objMapper;

    OdontologoDto odontologoDto = null;

    @Disabled
    @BeforeEach
    void setUp() {
        objMapper = new ObjectMapper();
        objMapper.findAndRegisterModules();

        //DTO
        odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Pepe");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);
    }

    @Disabled
    @Test
    @DisplayName("Registrar el Odontologo en la DB test")
    void registrarOodntologoTest() throws Exception {

        //Give
        when(odontologoService.save(ArgumentMatchers.any())).then(invocation -> {
            OdontologoDto newOdontologoDto = invocation.getArgument(0);
            newOdontologoDto.setId(1);
            newOdontologoDto.setNombre("Pepe");
            newOdontologoDto.setApellido("Perez");
            newOdontologoDto.setMatricula(1234);
            return newOdontologoDto;
        });

        //When
        mvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .content(asJsonString(odontologoDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Pepe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Perez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(12345));

        Mockito.verify(odontologoService).save(ArgumentMatchers.any());


    }

    @Disabled
    @Test
    @DisplayName("listar odontologo on DB test")
    void listarOdontologoTest() throws Exception {

        // Given
        Mockito.when(odontologoService.findAll()).then(invocationOnMock -> {
            List<OdontologoDto> odontologoDtoList = new ArrayList<>();

            OdontologoDto odontologoDto = new OdontologoDto();
            odontologoDto.setId(1);
            odontologoDto.setNombre("Pepe");
            odontologoDto.setApellido("Perez");
            odontologoDto.setMatricula(12345);

            odontologoDtoList.add(odontologoDto);

            return odontologoDtoList;
        });

        // When
        mvc.perform(MockMvcRequestBuilders.get("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )

                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Pepe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].apellido").value("Perez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].matricula").value(12345));

        Mockito.verify(odontologoService).findAll();
    }

    /**
     * Metodos propios
     */
    public String asJsonString(final Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
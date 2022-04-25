package com.digitalhouse.reservaturnosaprgbt.controller;

import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IPacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    /**
     * Injection Of Dependency
     * By Constructor
     */
    private final IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }



    /**
     * Guardar a un Odontologo en la BD.
     */
    @PostMapping()
    public ResponseEntity<PacienteDto> save(@Valid @RequestBody PacienteDto PacienteDto) throws Exception {
        log.info("Paciente RequestDto: {}", PacienteDto);

        PacienteDto pacienteResponseDto = pacienteService.save(PacienteDto);
        log.info("Paciente ResponseDto: {}", pacienteResponseDto);

        return new ResponseEntity<PacienteDto>(pacienteResponseDto, HttpStatus.CREATED);
    }


    /**
     * Obtener la lista de odontologs de la BD.
     */
    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<PacienteDto>> fiandAll() throws Exception {
        List<PacienteDto> listPacienteResponse = pacienteService.findAll();

        return ResponseEntity.ok(listPacienteResponse);
    }

    /**
     * Obtener un odontologo de la BD por Id.
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> findById(@PathVariable Integer id) throws Exception {
        PacienteDto pacienteDto = pacienteService.findById(id);

        return ResponseEntity.ok(pacienteDto);
    }

    /**
     * Actualizar informacion de un Odontologo en la base de datos.
     */
    @PutMapping()
    public ResponseEntity<PacienteDto> update(@Valid @RequestBody PacienteDto pacienteDto) throws Exception {
        log.info("Paciente RequestDto: {}", pacienteDto);

        PacienteDto pacienteResponseDto = pacienteService.save(pacienteDto);
        log.info("Paciente ResponseDto: {}", pacienteResponseDto);

        return new ResponseEntity<>(pacienteResponseDto, HttpStatus.OK);

    }

    /**
     * Eliminar un Odontologo de la BD.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

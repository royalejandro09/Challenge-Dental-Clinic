package com.digitalhouse.reservaturnosaprgbt.controller;

import com.digitalhouse.reservaturnosaprgbt.dto.TurnoDto;
import com.digitalhouse.reservaturnosaprgbt.repository.service.ITurnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    /**
     * Injection Of Dependency
     * By Constructor
     */
    private final ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }


    /**
     * Guardar a un Odontologo en la BD.
     */
    @PostMapping()
    public ResponseEntity<TurnoDto> save(@Valid @RequestBody TurnoDto turnoDto) throws Exception {
        log.info("Turno RequestDto: {}", turnoDto);

        TurnoDto turnoResponseDto = turnoService.save(turnoDto);
        log.info("Turno ResponseDto: {}", turnoResponseDto);

        return new ResponseEntity<TurnoDto>(turnoResponseDto, HttpStatus.CREATED);
    }


    /**
     * Obtener la lista de odontologs de la BD.
     */
    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<TurnoDto>> fiandAll() throws Exception {
        List<TurnoDto> listturnoResponse = turnoService.findAll();

        return ResponseEntity.ok(listturnoResponse);
    }

    /**
     * Obtener un odontologo de la BD por Id.
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> findById(@PathVariable Integer id) throws Exception {
        TurnoDto turnoDto = turnoService.findById(id);

        return ResponseEntity.ok(turnoDto);
    }

    /**
     * Actualizar informacion de un Odontologo en la base de datos.
     */
    @PutMapping()
    public ResponseEntity<TurnoDto> update(@Valid @RequestBody TurnoDto turnoDto) throws Exception {
        log.info("Turno RequestDto: {}", turnoDto);

        TurnoDto turnoResponseDto = turnoService.save(turnoDto);
        log.info("Turno ResponseDto: {}", turnoResponseDto);

        return new ResponseEntity<>(turnoResponseDto, HttpStatus.OK);

    }

    /**
     * Eliminar un Odontologo de la BD.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        turnoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

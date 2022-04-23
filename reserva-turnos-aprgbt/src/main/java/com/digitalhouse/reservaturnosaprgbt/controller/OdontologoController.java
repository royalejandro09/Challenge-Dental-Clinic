package com.digitalhouse.reservaturnosaprgbt.controller;

import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IOdontologoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    /**
     * Injection Of Dependency
     * By Constructor
     */
    private final IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    /**
     * Guardar a un Odontologo en la BD.
     */
    @PostMapping()
    public ResponseEntity<OdontologoDto> save(@Valid @RequestBody OdontologoDto odontologoDto) throws Exception {
        log.info("Odontologo RequestDto: {}", odontologoDto);

        OdontologoDto odontologoResponseDto = odontologoService.save(odontologoDto);
        log.info("Odontologo ResponseDto: {}", odontologoResponseDto);

        return new ResponseEntity<OdontologoDto>(odontologoResponseDto, HttpStatus.CREATED);
    }


    /**
     * Obtener la lista de odontologs de la BD.
     */
    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<OdontologoDto>> fiandAll() throws Exception {
        List<OdontologoDto> listOdontologoResponse = odontologoService.findAll();

        return ResponseEntity.ok(listOdontologoResponse);
    }

    /**
     * Obtener un odontologo de la BD por Id.
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> findById(@PathVariable Integer id) throws Exception {
        OdontologoDto odontologoDto = odontologoService.findById(id);

        return ResponseEntity.ok(odontologoDto);
    }

    /**
     * Actualizar informacion de un Odontologo en la base de datos.
     */
    @PutMapping()
    public ResponseEntity<OdontologoDto> update(@Valid @RequestBody OdontologoDto odontologoDto) throws Exception {
        log.info("Odontologo RequestDto: {}", odontologoDto);

        OdontologoDto odontologoResponseDto = odontologoService.save(odontologoDto);
        log.info("Odontologo ResponseDto: {}", odontologoResponseDto);

        return new ResponseEntity<>(odontologoResponseDto, HttpStatus.OK);

    }

    /**
     * Eliminar un Odontologo de la BD.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        odontologoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

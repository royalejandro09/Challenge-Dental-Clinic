package com.digitalhouse.reservaturnosaprgbt.repository.service.impl;

import com.digitalhouse.reservaturnosaprgbt.dto.TurnoDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Turno;
import com.digitalhouse.reservaturnosaprgbt.exception.ElementNotFoundException;
import com.digitalhouse.reservaturnosaprgbt.mapper.TurnoMapper;
import com.digitalhouse.reservaturnosaprgbt.repository.ITurnoRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.service.ITurnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TurnoServiceImpl implements ITurnoService {

    /**
     * Injection Of Dependency
     */
    private final TurnoMapper turnoMapper;
    private final ITurnoRepository turnoRepository;

    public TurnoServiceImpl(TurnoMapper turnoMapper, ITurnoRepository turnoRepository) {
        this.turnoMapper = turnoMapper;
        this.turnoRepository = turnoRepository;
    }

    /**
     * Implementacion de Metodos Crud.
     *
     * @param turnoDto
     * @return
     * @throws Exception
     */
    @Override
    public TurnoDto save(TurnoDto turnoDto) throws Exception {
        Turno turnoEntity = turnoMapper.mapToEntity(turnoDto);
        Turno turnoEntityRepo = turnoRepository.save(turnoEntity);

        return turnoMapper.mapToDto(turnoEntityRepo);
    }

    @Override
    public List<TurnoDto> findAll() throws Exception {
        List<Turno> listaTurnoEntity = turnoRepository.findAll();
        return turnoMapper.mapToListDto(listaTurnoEntity);
    }

    @Override
    public TurnoDto findById(Integer id) throws Exception {
        Turno turnoEntity = null;
        try {
            turnoEntity = turnoRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFoundException("Turno. Not Found on BD"));
        } catch (ElementNotFoundException e) {
            e.getMessage();
            log.error("Turno con id: {}. No existe en BD", id);
        }
        return turnoMapper.mapToDto(turnoEntity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Turno turnoEntity = turnoRepository.findById(id).get();
        turnoRepository.delete(turnoEntity);
    }
}

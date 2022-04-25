package com.digitalhouse.reservaturnosaprgbt.repository.service.impl;

import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import com.digitalhouse.reservaturnosaprgbt.exception.ElementNotFoundException;
import com.digitalhouse.reservaturnosaprgbt.mapper.PacienteMapper;
import com.digitalhouse.reservaturnosaprgbt.repository.IPacienteRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IPacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PacienteServiceImpl implements IPacienteService {

    /**
     * Injection of dependency by Constructor
     */
    private final IPacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteServiceImpl(IPacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    /**
     * Implementacion de metodos Crud
     *
     * @param pacienteDto
     * @return
     * @throws Exception
     */
    @Override
    public PacienteDto save(PacienteDto pacienteDto) throws Exception {
        Paciente pacienteEntity = pacienteMapper.mapToEntity(pacienteDto);
        Paciente pacienteEntityRepo = pacienteRepository.save(pacienteEntity);

        return pacienteMapper.mapToDto(pacienteEntityRepo);
    }

    /*@Override
    public PacienteDto update(PacienteDto pacienteDto) throws Exception {
        Paciente pacienteEntity = pacienteMapper.mapToEntity(pacienteDto);
        Paciente pacienteEntityRepo = pacienteRepository.save(pacienteEntity);

        return pacienteMapper.mapToDto(pacienteEntity);
    }*/

    @Override
    public List<PacienteDto> findAll() throws Exception {
        List<Paciente> listPacienteEntity = pacienteRepository.findAll();

        return pacienteMapper.mapToListDto(listPacienteEntity);
    }

    @Override
    public PacienteDto findById(Integer id) throws Exception {
        Paciente pacienteEntity = null;
        try {
            pacienteEntity = pacienteRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFoundException("Paciente. Not Found on BD"));
        } catch (ElementNotFoundException e) {
            e.getMessage();
            log.error("Paciente con id: {}. No existe en BD", id);
        }

        return pacienteMapper.mapToDto(pacienteEntity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Paciente pacienteEntity = pacienteRepository.findById(id).get();
        pacienteRepository.delete(pacienteEntity);
    }
}

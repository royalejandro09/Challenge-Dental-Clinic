package com.digitalhouse.reservaturnosaprgbt.mapper;

import com.digitalhouse.reservaturnosaprgbt.dto.DomicilioDto;
import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Domicilio;
import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PacienteMapper {

    /**
     * Instancia ModelMapper
     * Injection of Dependency
     */
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    DomicilioMapper domicilioMapper;


    /**
     * Entity ->  mapToDto
     */
    public PacienteDto mapToDto(Paciente pacienteEntity) {
        log.info("*** Paciente Entity {}", pacienteEntity);
        DomicilioDto domicilioDto = domicilioMapper.mapDTO(pacienteEntity.getDomicilio());
        PacienteDto pacienteDto = modelMapper.map(pacienteEntity, PacienteDto.class);
        pacienteDto.setDomicilioDto(domicilioDto);
        log.info("*** Paciente Dto {}", pacienteDto);

        return pacienteDto;
    }

    /**
     * Dto -> mapToEntity
     */
    public Paciente mapToEntity(PacienteDto pacienteDto) {
        log.info("*** Paciente Dto {}", pacienteDto);
        Domicilio domicilioEntity = domicilioMapper.mapEntity(pacienteDto.getDomicilioDto());
        Paciente pacienteEntity = modelMapper.map(pacienteDto, Paciente.class);
        pacienteEntity.setDomicilio(domicilioEntity);
        pacienteEntity.setFechaDeCreacion(LocalDate.now());
        log.info("*** Paciente Entity {}", pacienteEntity);

        return pacienteEntity;
    }

    /**
     * List Entity -> mapListDto
     */
    public List<PacienteDto> mapToListDto(List<Paciente> listPacienteEntity) {
        log.info("List<PacienteEntity>: {}", listPacienteEntity);

        List<PacienteDto> listPacienteDto = listPacienteEntity
                .stream()
                .map(pacienteEntity -> mapToDto(pacienteEntity))
                .collect(Collectors.toList());

        log.info("List<PacienteDto>: {}", listPacienteDto);
        return listPacienteDto;
    }

    /**
     * List Dto -> mapListEntity
     */
    public List<Paciente> mapToListEntity(List<PacienteDto> listPacienteDto) {
        log.info("List<PacienteDto>: {}", listPacienteDto);

        List<Paciente> listPacienteEntity = listPacienteDto
                .stream()
                .map(pacienteDto -> mapToEntity(pacienteDto))
                .collect(Collectors.toList());

        log.info("List<Paciente>: {}", listPacienteEntity);
        return listPacienteEntity;
    }


}

package com.digitalhouse.reservaturnosaprgbt.mapper;

import com.digitalhouse.reservaturnosaprgbt.dto.DomicilioDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Domicilio;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DomicilioMapper {

    /**
     * Instancia ModelMapper
     * Injection of Dependency
     */
    @Autowired
    ModelMapper modelMapper;

    /**
     * Entity ->  mapToDto
     */
    public DomicilioDto mapDTO(Domicilio domicilio) {
        log.info("*** Domicilio Entity {}", domicilio);
        DomicilioDto domicilioDto = modelMapper.map(domicilio, DomicilioDto.class);
        log.info("*** Domicilio Dto {}", domicilioDto);
        return domicilioDto;
    }

    /**
     * Dto -> mapToEntity
     */
    public Domicilio mapEntity(DomicilioDto domicilioDto) {
        log.info("*** Domicilio Dto: {}", domicilioDto);
        Domicilio domicilioEntity = modelMapper.map(domicilioDto, Domicilio.class);
        log.info("*** Domicilio Entity {}", domicilioEntity);
        return domicilioEntity;
    }

    /**
     * List Entity -> mapListDto
     */
    public List<DomicilioDto> mapListDto(List<Domicilio> listDomicilioEntity) {
        log.info("List<DomicilioEntity>: {}", listDomicilioEntity);

        List<DomicilioDto> listDomicilioDto = listDomicilioEntity.stream()
                .map(domEntity -> mapDTO(domEntity))
                .collect(Collectors.toList());

        log.info("List<DomicilioDto>: {}", listDomicilioDto);
        return listDomicilioDto;
    }

    /**
     * List Dto -> mapListEntity
     */
    public List<Domicilio> mapListEntity(List<DomicilioDto> listDomicilioDto) {
        log.info("List<DomicilioDto>: {}", listDomicilioDto);

        List<Domicilio> listDomicilioEntity = listDomicilioDto.stream()
                .map(domDto -> mapEntity(domDto))
                .collect(Collectors.toList());

        log.info("List<DomicilioEntity>: {}", listDomicilioEntity);
        return listDomicilioEntity;
    }
}

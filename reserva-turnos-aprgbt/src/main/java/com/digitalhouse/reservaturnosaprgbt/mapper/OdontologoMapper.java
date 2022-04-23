package com.digitalhouse.reservaturnosaprgbt.mapper;

import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OdontologoMapper {

    /**
     * Instancia ModelMapper
     * Injection of Dependency
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Entity ->  mapToDto
     */
    public OdontologoDto mapToDto(Odontologo odontologoEntity) {
        log.info("*** Odontologo Entity {}", odontologoEntity);
        OdontologoDto odontologoDto = modelMapper.map(odontologoEntity, OdontologoDto.class);
        log.info("*** Odontologo Dto {}", odontologoDto);

        return odontologoDto;
    }

    /**
     * Dto -> mapToEntity
     */
    public Odontologo mapToEntity(OdontologoDto odontologoDto) {
        log.info("*** Odontologo Dto {}", odontologoDto);
        Odontologo odontologoEntity = modelMapper.map(odontologoDto, Odontologo.class);
        log.info("*** Odontologo Entity {}", odontologoEntity);

        return odontologoEntity;
    }

    /**
     * List Entity -> mapListDto
     */
    public List<OdontologoDto> mapToListDto(List<Odontologo> listOdontologoEntity) {
        log.info("List<OdontologoEntity>: {}", listOdontologoEntity);

        List<OdontologoDto> listOdontologoDto = listOdontologoEntity
                .stream()
                .map(odoEntity -> mapToDto(odoEntity))
                .collect(Collectors.toList());

        log.info("List<OdontologoDto>: {}", listOdontologoDto);
        return listOdontologoDto;
    }

    /**
     * List Dto -> mapListEntity
     */
    public List<Odontologo> mapToListEntity(List<OdontologoDto> ListOdontologoDto) {
        log.info("List<ListOdontologoDto>: {}", ListOdontologoDto);

        List<Odontologo> OdontologoEntity = ListOdontologoDto
                .stream()
                .map(odoEntity -> mapToEntity(odoEntity))
                .collect(Collectors.toList());

        log.info("List<OdontologoEntity>: {}", OdontologoEntity);
        return OdontologoEntity;
    }

}

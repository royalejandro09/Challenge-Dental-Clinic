package com.digitalhouse.reservaturnosaprgbt.mapper;

import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.dto.PacienteDto;
import com.digitalhouse.reservaturnosaprgbt.dto.TurnoDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import com.digitalhouse.reservaturnosaprgbt.entity.Paciente;
import com.digitalhouse.reservaturnosaprgbt.entity.Turno;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IOdontologoService;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IPacienteService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TurnoMapper {

    /**
     * Instancia ModelMapper
     * Injection of Dependency
     */
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OdontologoMapper odontologoMapper;
    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    IPacienteService pacienteService;
    @Autowired
    PacienteMapper pacienteMapper;

    /**
     * Dto -> mapToEntity
     */
    public Turno mapToEntity(TurnoDto turnoDto) throws Exception {
        Turno turnoEntity = modelMapper.map(turnoDto, Turno.class);

        turnoEntity.setOdontologo(getOdontologoEntity(turnoDto.getOdontologoDto().getId()));
        turnoEntity.setPaciente(getPacienteEntity(turnoDto.getPacienteDto().getId()));

        return turnoEntity;
    }

    /**
     * Entity -> mapToDto
     */
    public TurnoDto mapToDto(Turno turnoEntity) {
        OdontologoDto odontologoDto = odontologoMapper.mapToDto(turnoEntity.getOdontologo());
        PacienteDto pacienteDto = pacienteMapper.mapToDto(turnoEntity.getPaciente());

        TurnoDto turnoDto = modelMapper.map(turnoEntity, TurnoDto.class);
        turnoDto.setOdontologoDto(odontologoDto);
        turnoDto.setPacienteDto(pacienteDto);

        return turnoDto;
    }

    /**
     * List<TurnoEntity> -> mapToList<TurnoDto>
     */
    public List<TurnoDto> mapToListDto(List<Turno> listTurnoEntity) {
        List<TurnoDto> listTurnoDto = listTurnoEntity.stream()
                .map(turnoEntity -> mapToDto(turnoEntity))
                .collect(Collectors.toList());

        return listTurnoDto;
    }


    /**
     * Metodos Propios de la clase
     */
//    Obtener un odontologo Entity
    private Odontologo getOdontologoEntity(Integer id) throws Exception {
        OdontologoDto odontologoDto = odontologoService.findById(id);

        return odontologoMapper.mapToEntity(odontologoDto);
    }

    //Obtener un Paciente Entity
    private Paciente getPacienteEntity(Integer id) throws Exception {
        PacienteDto pacienteDto = pacienteService.findById(id);

        return pacienteMapper.mapToEntity(pacienteDto);
    }

}

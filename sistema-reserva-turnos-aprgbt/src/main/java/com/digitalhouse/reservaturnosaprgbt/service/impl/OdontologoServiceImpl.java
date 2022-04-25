package com.digitalhouse.reservaturnosaprgbt.repository.service.impl;

import com.digitalhouse.reservaturnosaprgbt.dto.OdontologoDto;
import com.digitalhouse.reservaturnosaprgbt.entity.Odontologo;
import com.digitalhouse.reservaturnosaprgbt.exception.ElementNotFoundException;
import com.digitalhouse.reservaturnosaprgbt.mapper.OdontologoMapper;
import com.digitalhouse.reservaturnosaprgbt.repository.IOdontologoRepository;
import com.digitalhouse.reservaturnosaprgbt.repository.service.IOdontologoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OdontologoServiceImpl implements IOdontologoService {


    /**
     * Injection of dependency by Constructor
     */

    private final IOdontologoRepository odontologoRepository;
    private final OdontologoMapper odontologoMapper;


    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository, OdontologoMapper odontologoMapper) {
        this.odontologoRepository = odontologoRepository;
        this.odontologoMapper = odontologoMapper;
    }

    /**
     * Implementacion de metodos CRUD
     *
     * @param odontologoDto
     * @return
     * @throws Exception
     */
    @Override
    public OdontologoDto save(OdontologoDto odontologoDto) throws Exception {

        Odontologo odontologoEntity = odontologoMapper.mapToEntity(odontologoDto);
        Odontologo odontologoEntityRepository = odontologoRepository.save(odontologoEntity);

        return odontologoMapper.mapToDto(odontologoEntityRepository);
    }

    /*@Override
    public OdontologoDto update(OdontologoDto odontologoDto) throws Exception {

        Odontologo odontologoEntity = odontologoMapper.mapToEntity(odontologoDto);
        Odontologo odontologoEntityRepository = odontologoRepository.save(odontologoEntity);

        return odontologoMapper.mapToDto(odontologoEntityRepository);
    }*/

    @Override
    public List<OdontologoDto> findAll() throws Exception {
        List<Odontologo> listOdontologoEntity = odontologoRepository.findAll();

        return odontologoMapper.mapToListDto(listOdontologoEntity);
    }

    @Override
    public OdontologoDto findById(Integer id) throws Exception {
        Odontologo odontologoEntity = null;
        try {
            odontologoEntity = odontologoRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFoundException("Odontologo. Not Found on BD"));
        } catch (ElementNotFoundException e) {
            e.getMessage();
            log.error("Odontologo con id: {}. No existe en BD", id);
        }

        return odontologoMapper.mapToDto(odontologoEntity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Odontologo odontologoEntity = odontologoRepository.findById(id).get();
        odontologoRepository.delete(odontologoEntity);
    }
}

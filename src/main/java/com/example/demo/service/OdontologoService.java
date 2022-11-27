package com.example.demo.service;

import com.example.demo.entity.Odontologo;
import com.example.demo.repository.OdontologoRepository;
import com.example.demo.service.dto.OdontologoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private final OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    /*Listar, crear y eliminar*/


    @Override
    public void crearOdontologo(OdontologoDto odontologoDto) {
        Odontologo odontologo = new Odontologo();
        odontologo = mapper.convertValue(odontologoDto, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarOdontologo(Long id) {
         Optional<Odontologo> odontologo = odontologoRepository.findById(id);
         if(odontologo.isPresent()){
             //mapper.convertValue(odontologo, OdontologoDto.class);
             return odontologo;
         }
         return null;
    };



    /*@Override
    public OdontologoDto buscarOdontologo(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            return mapper.convertValue(odontologo, OdontologoDto.class);
        }
        return null;
    }*/

    @Override
    public void modificarOdontologo(Long id, OdontologoDto odontologoDto) {
        Optional<Odontologo> odontologo = buscarOdontologo(id);
        if (odontologo.isPresent()) {
            Odontologo odontologoModificado = mapper.convertValue(odontologoDto, Odontologo.class);
            odontologoRepository.save(odontologoModificado);
        }
    }

    @Override
    public void eliminarOdontologo(Long id) {
        Optional<Odontologo> odontologo = buscarOdontologo(id);
        if (odontologo.isPresent()) {
            odontologoRepository.deleteById(id);
        }
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }


}
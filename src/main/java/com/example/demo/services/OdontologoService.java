package com.example.demo.services;

import com.example.demo.entities.Odontologo;
import com.example.demo.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("odontologoServices")
public class OdontologoService implements IService<Odontologo>{

    public static Logger logger = Logger.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public boolean guardar(Odontologo odontologo) throws Exception {
        boolean response = false;
        try {
            odontologoRepository.save(odontologo);
            response = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return response;
    }

    @Override
    public boolean eliminar(Long id) {
        boolean response = false;
        try {
            odontologoRepository.deleteById(id);
            response = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return response;
    }

    @Override
    public Odontologo buscar(Long id) {
        Odontologo odontologo = null;
        if (odontologoRepository.findById(id).isPresent()) odontologo = odontologoRepository.findById(id).get();
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodo() {
        return odontologoRepository.findAll();
    }
}

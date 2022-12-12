package com.example.demo.services;

import com.example.demo.entities.Domicilio;
import com.example.demo.repository.DomicilioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DomicilioService implements IService<Domicilio> {

    private static Logger logger = Logger.getLogger(DomicilioService.class);
    private DomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public boolean guardar(Domicilio domicilio) throws Exception {
        boolean response = false;
        try {
            domicilioRepository.save(domicilio);
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
            domicilioRepository.deleteById(id);
            response = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return response;
    }

    @Override
    public Domicilio buscar(Long id) {
        return domicilioRepository.findById(id).get();
    }

    @Override
    public List<Domicilio> listarTodo() {
        return domicilioRepository.findAll();
    }
}

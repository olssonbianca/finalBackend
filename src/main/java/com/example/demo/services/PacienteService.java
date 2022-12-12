package com.example.demo.services;

import com.example.demo.entities.Paciente;
import com.example.demo.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pacienteServices")
public class PacienteService implements IService<Paciente>{

    private static Logger logger = Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public boolean guardar(Paciente paciente) throws Exception {
        boolean response = false;
        try {
            pacienteRepository.save(paciente);
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
            pacienteRepository.deleteById(id);
            response = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return response;
    }

    @Override
    public Paciente buscar(Long id) {
        Paciente paciente = null;
        if (pacienteRepository.findById(id).isPresent()) paciente = pacienteRepository.findById(id).get();
        return paciente;
    }

    @Override
    public List<Paciente> listarTodo() {
        return pacienteRepository.findAll();
    }


    public Paciente actualizar(Paciente paciente) throws Exception{
        Paciente actualizarPaciente = buscar(paciente.getId());
        if (actualizarPaciente!= null) {
            actualizarPaciente.setNombre(paciente.getNombre());
            actualizarPaciente.setApellido(paciente.getApellido());
            actualizarPaciente.setDni(paciente.getDni());
            actualizarPaciente.setFechaDeAlta(paciente.getFechaDeAlta());
            guardar(actualizarPaciente);
        }
        return paciente;
    }


}

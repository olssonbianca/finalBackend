package com.example.demo.services;

import com.example.demo.entities.Odontologo;
import com.example.demo.entities.Paciente;
import com.example.demo.entities.Turno;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements IService<Turno> {

    public static Logger logger = Logger.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private IService<Paciente> pacienteServices;
    private IService<Odontologo> odontologoServices;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Autowired
    //@Qualifier("paciente_services")
    public void setPacienteServices(IService<Paciente> pacienteServices) {
        this.pacienteServices = pacienteServices;
    }

    @Autowired
    //@Qualifier("odontologo_services")
    public void setOdontologoServices(IService<Odontologo> odontologoServices) {
        this.odontologoServices = odontologoServices;
    }

    @Override
    public boolean guardar(Turno turno) throws NotFoundException {
        boolean response = false;
        if (pacienteServices.buscar(turno.getPaciente().getId()) == null)
            throw new NotFoundException("No existe paciente en la base de datos");
        if (odontologoServices.buscar(turno.getOdontologo().getId()) == null)
            throw new NotFoundException("No existe odontologo en la base de datos");
        try {
            turnoRepository.save(turno);
            response = true;
        } catch (Exception e) {
            logger.error(e);
        }
        return response;
    }


    @Override
    public boolean eliminar(Long id) {
        boolean response = false;
        try {
            turnoRepository.deleteById(id);
            response = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return response;
    }

    @Override
    public Turno buscar(Long id) {
        Turno turno = null;
        if (turnoRepository.findById(id).isPresent()) turno = turnoRepository.findById(id).get();
        return turno;
    }

    @Override
    public List<Turno> listarTodo() {
        return turnoRepository.findAll();
    }
}

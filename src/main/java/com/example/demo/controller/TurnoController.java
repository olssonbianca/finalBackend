package com.example.demo.controller;

import com.example.demo.entities.Odontologo;
import com.example.demo.entities.Paciente;
import com.example.demo.entities.Turno;
import com.example.demo.entities.dto.TurnoDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/turno")

public class TurnoController {

    public Logger logger = Logger.getLogger(TurnoController.class);
    private IService<Turno> services;

    @Autowired
    @Qualifier("turnoServices")
    public void turnoServices(IService<Turno> services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<?> registar(@RequestBody Turno turno) throws Exception {
        ResponseEntity<?> response;
        Paciente pacienteAtender = turno.getPaciente();
        Odontologo odontologo = turno.getOdontologo();
        if (pacienteAtender.getId() == null) { response = ResponseEntity.badRequest().body("no se selecciono paciente"); }
        else if (odontologo.getId() == null) {  response = ResponseEntity.badRequest().body("no se selecciono paciente u odontologo"); }
        else if (turno.getFechaYHora().isBefore(LocalDateTime.now())) { response = ResponseEntity.badRequest().body("el turno debe ser posterior a la fecha y hora actual"); }
        else { response = ResponseEntity.ok(services.guardar(turno)); }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) throws Exception {
        Turno turno = services.buscar(id);
        if (turno == null) throw new NotFoundException("no existe turno con ese ID");
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        if (services.buscar(id) == null) throw new NotFoundException("no existe turno con ese ID");
        services.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<?> listarTodos() {
        List<Turno> listaTurnos = services.listarTodo();
        List<TurnoDto> listaRespuesta = new ArrayList<>();
        for (Turno turno:listaTurnos) {
            TurnoDto turnoDTO = new TurnoDto();
            turnoDTO.setFecha(turno.getFechaYHora().getDayOfMonth() + "/" + turno.getFechaYHora().getMonthValue() + "/" + turno.getFechaYHora().getYear());
            turnoDTO.setHora(turno.getFechaYHora().getHour() + ":" + turno.getFechaYHora().getMinute());
            turnoDTO.setOdontologo(turno.getOdontologo().getApellido() + ", " + turno.getOdontologo().getNombre());
            turnoDTO.setPaciente(turno.getPaciente().getApellido() + ", " + turno.getPaciente().getNombre());
            listaRespuesta.add(turnoDTO);
        }
        return ResponseEntity.ok(listaRespuesta);
    }

}

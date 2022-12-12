package com.example.demo.controller;

import com.example.demo.entities.Paciente;
import com.example.demo.services.IService;
import com.example.demo.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/paciente")

public class PacienteController {

    private IService<Paciente> services;

    @Autowired
    @Qualifier("pacienteServices")
    public void pacienteServices(IService<Paciente> services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<?> registar(@RequestBody Paciente p) throws Exception {
        return ResponseEntity.ok(services.guardar(p));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(services.buscar(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        services.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarRegistro(@RequestBody Paciente paciente) throws Exception {
        ResponseEntity<Paciente> response = ResponseEntity.notFound().build();
        if (paciente.getId() != null && services.buscar(paciente.getId()) != null) {
            response = ResponseEntity.ok(((PacienteService) services).actualizar(paciente));
        }
        return response;
    }

    @GetMapping("all")
    public ResponseEntity<?> listarTodo() {
        return ResponseEntity.ok(services.listarTodo());
    }

}

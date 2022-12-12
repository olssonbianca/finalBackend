package com.example.demo.controller;

import com.example.demo.entities.Odontologo;
import com.example.demo.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private IService<Odontologo> services;

    @Autowired
    public void odontologoServices(IService<Odontologo> services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<?> registar(@RequestBody Odontologo o) throws Exception {
        return ResponseEntity.ok(services.guardar(o));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(services.buscar(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return new ResponseEntity(services.eliminar(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("all")
    public ResponseEntity<?> listarTodo() {
        return ResponseEntity.ok(services.listarTodo());
    }
}

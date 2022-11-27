package com.example.demo.controller;


import com.example.demo.entity.Odontologo;
import com.example.demo.service.OdontologoService;
import com.example.demo.service.dto.OdontologoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/odontologo")

public class OdontologoController {
    private OdontologoService odontologoService;
    ObjectMapper mapper;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody OdontologoDto odontologoDto){
        Odontologo odontologo = odontologoService.crearOdontologo(odontologoDto);
        return ResponseEntity.ok(mapper.convertValue(odontologoDto, Odontologo.class));
        /*OdontologoService.crearOdontologo(odontologoDto);
        return (ResponseEntity<OdontologoDto>) ResponseEntity.status(HttpStatus.OK);*/
    }
}

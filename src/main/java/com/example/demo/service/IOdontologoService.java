package com.example.demo.service;

import com.example.demo.entity.Odontologo;
import com.example.demo.service.dto.OdontologoDto;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    /*Listar, agregar, buscar, eliminar, modificar*/

    void crearOdontologo(OdontologoDto odontologoDto);
    public Optional<Odontologo> buscarOdontologo(Long id);
    void modificarOdontologo(Long id, OdontologoDto odontologoDto);
    void eliminarOdontologo (Long id);

    List<Odontologo> listarOdontologos();

}

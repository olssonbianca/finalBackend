package com.example.demo.services;


import java.util.List;

public interface IService<T>{

    boolean guardar(T t) throws Exception;

    boolean eliminar(Long id);

    T buscar(Long id);

    List<T> listarTodo();
}


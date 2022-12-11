package com.example.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="domicilio")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle, localidad, provincia;
    private int numero;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name= "domicilio_id")
    private Set<Paciente> pacientes;

}

package com.example.demo.repository;

import com.example.demo.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository <Odontologo, Long>{
}

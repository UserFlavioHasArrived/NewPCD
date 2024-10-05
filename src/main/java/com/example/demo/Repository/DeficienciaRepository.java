package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Deficiencia;

@Repository
public interface DeficienciaRepository extends JpaRepository<Deficiencia, Long> {

    List<Deficiencia> findByAtivo(boolean ativo);
}

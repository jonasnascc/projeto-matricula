package br.edu.academico.jonas.nascimento.crudMatricula.dao;

import br.edu.academico.jonas.nascimento.crudMatricula.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}

package br.edu.academico.jonas.nascimento.crudMatricula.dao;

import br.edu.academico.jonas.nascimento.crudMatricula.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

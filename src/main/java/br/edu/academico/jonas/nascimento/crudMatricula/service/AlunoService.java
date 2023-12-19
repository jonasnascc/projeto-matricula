package br.edu.academico.jonas.nascimento.crudMatricula.service;


import br.edu.academico.jonas.nascimento.crudMatricula.dao.AlunoRepository;
import br.edu.academico.jonas.nascimento.crudMatricula.dto.AlunoDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Aluno;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Disciplina;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AlunoService {
    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno save(AlunoDTO dto){
        Aluno aluno = new Aluno(dto.nome(), dto.matricula(), dto.curso());

        return repository.save(aluno);
    }

    public Aluno getById(Long id) throws NotFoundException {
        return findAluno(id);
    }

    public List<Aluno> list(){
        return repository.findAll();
    }

    public Aluno update(Long id, AlunoDTO dto) throws NotFoundException {
        Aluno saved = findAluno(id);

        Aluno aluno = new Aluno(saved.getId(), dto.nome(), dto.matricula(), dto.curso());

        return repository.save(aluno);
    }

    public AlunoDTO delete(Long id) throws NotFoundException {
        Aluno aluno = findAluno(id);
        AlunoDTO dto = new AlunoDTO(aluno.getNome(), aluno.getMatrícula(), aluno.getCurso());
        repository.delete(aluno);

        return dto;
    }

    public Set<Disciplina> getDisciplinas(Long alunoId) throws NotFoundException {
        Aluno aluno = findAluno(alunoId);

        return aluno.getDisciplinas();
    }
    private Aluno findAluno(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado no banco de dados."));
    }
}

package br.edu.academico.jonas.nascimento.crudMatricula.service;

import br.edu.academico.jonas.nascimento.crudMatricula.dao.DisciplinaRepository;
import br.edu.academico.jonas.nascimento.crudMatricula.dto.AlunoDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.dto.DisciplinaDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Aluno;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Disciplina;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DisciplinaService {
    private final DisciplinaRepository repository;

    private final AlunoService alunoService;

    public Disciplina save(DisciplinaDTO dto){
        Disciplina disciplina = new Disciplina(dto.nome(), dto.cargaHoraria());

        return repository.save(disciplina);
    }

    public Disciplina getById(Long id) throws NotFoundException {
        return findDisciplina(id);
    }

    public List<Disciplina> list(){
        return repository.findAll();
    }

    public Disciplina update(Long id, DisciplinaDTO dto) throws NotFoundException {
        Disciplina saved = findDisciplina(id);

        Disciplina disciplina = new Disciplina(saved.getId(), dto.nome(), dto.cargaHoraria());

        return repository.save(disciplina);
    }

    public DisciplinaDTO delete(Long id) throws NotFoundException {
        Disciplina disciplina = findDisciplina(id);
        DisciplinaDTO dto = new DisciplinaDTO(disciplina.getNome(), disciplina.getCargaHoraria());
        repository.delete(disciplina);

        return dto;
    }

    public void addAluno(Long discId, Long alunoId) throws NotFoundException {
        Aluno aluno = alunoService.getById(alunoId);
        Disciplina disciplina = findDisciplina(discId);

        disciplina.getAlunos().add(aluno);

        repository.save(disciplina);
    }

    public Set<Aluno> getAlunos(Long discId) throws NotFoundException {
        Disciplina disciplina = findDisciplina(discId);

        return disciplina.getAlunos();
    }

    public AlunoDTO deleteAluno(Long discId, Long alunoId) throws NotFoundException {
        Aluno aluno = alunoService.getById(alunoId);
        Disciplina disciplina = findDisciplina(discId);

        if(disciplina.getAlunos().stream().noneMatch(al -> al.getId().equals(aluno.getId()))){
            throw new RuntimeException("Aluno não cadastrado na disciplina.");
        }

        disciplina.getAlunos().removeIf(al -> al.getId().equals(aluno.getId()));

        repository.save(disciplina);

        return new AlunoDTO(aluno.getNome(), aluno.getMatrícula(), aluno.getCurso());
    }

    private Disciplina findDisciplina(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Disciplina não encontrada no banco de dados."));
    }
}

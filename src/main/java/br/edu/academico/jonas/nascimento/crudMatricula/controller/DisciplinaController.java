package br.edu.academico.jonas.nascimento.crudMatricula.controller;

import br.edu.academico.jonas.nascimento.crudMatricula.dto.AlunoDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.dto.DisciplinaDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Aluno;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Disciplina;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.exceptions.NotFoundException;
import br.edu.academico.jonas.nascimento.crudMatricula.service.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/disciplinas")
@AllArgsConstructor
public class DisciplinaController {
    private final DisciplinaService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disciplina> getById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disciplina>> list(){
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody DisciplinaDTO dto) throws NotFoundException {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DisciplinaDTO> delete(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @PostMapping(value = "{discId}/alunos/{alunoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAluno(@PathVariable Long discId, @PathVariable Long alunoId) throws NotFoundException {
        service.addAluno(discId, alunoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "{discId}/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Aluno>> getAlunos(@PathVariable Long discId) throws NotFoundException {
        return new ResponseEntity<>(service.getAlunos(discId), HttpStatus.OK);
    }

    @DeleteMapping(value = "{discId}/alunos/{alunoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoDTO> deleteAluno(@PathVariable Long discId, @PathVariable Long alunoId) throws NotFoundException {
        return new ResponseEntity<>(service.deleteAluno(discId, alunoId), HttpStatus.OK);
    }
}

package br.edu.academico.jonas.nascimento.crudMatricula.controller;

import br.edu.academico.jonas.nascimento.crudMatricula.dto.AlunoDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.dto.DisciplinaDTO;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Aluno;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.Disciplina;
import br.edu.academico.jonas.nascimento.crudMatricula.entity.exceptions.NotFoundException;
import br.edu.academico.jonas.nascimento.crudMatricula.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/alunos")
@AllArgsConstructor
public class AlunoController {
    private final AlunoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> save(@RequestBody AlunoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> getById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Aluno>> list(){
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody AlunoDTO dto) throws NotFoundException {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoDTO> delete(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/disciplinas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Disciplina>> getDisciplinas(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(service.getDisciplinas(id), HttpStatus.OK);
    }
}

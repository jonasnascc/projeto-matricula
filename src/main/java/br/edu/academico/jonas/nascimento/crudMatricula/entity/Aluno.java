package br.edu.academico.jonas.nascimento.crudMatricula.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String matrícula;

    private String curso;

    @JsonIgnore
    @ManyToMany(mappedBy = "alunos")
    private Set<Disciplina> disciplinas;

    public Aluno(String nome, String matrícula, String curso) {
        this.nome = nome;
        this.matrícula = matrícula;
        this.curso = curso;
    }

    public Aluno(Long id, String nome, String matrícula, String curso) {
        this.id = id;
        this.nome = nome;
        this.matrícula = matrícula;
        this.curso = curso;
    }

    public void removeDisciplina(Long id){
        disciplinas.removeIf(disc -> disc.getId().equals(id));
    }

    @PreRemove
    public void removeFromDisciplinas(){
        disciplinas.forEach(disciplina -> {
            disciplina.getAlunos().removeIf(aluno -> aluno.getId().equals(this.id));
        });
        disciplinas = null;
    }


}

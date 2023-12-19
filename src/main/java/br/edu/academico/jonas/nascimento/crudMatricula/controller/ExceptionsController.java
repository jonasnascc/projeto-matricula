package br.edu.academico.jonas.nascimento.crudMatricula.controller;

import br.edu.academico.jonas.nascimento.crudMatricula.entity.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> notFound(Exception exception){
        return new ResponseEntity<>("Erro: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exception(){
        return new ResponseEntity<>("Ocorreu um erro ao tratar a sua requisição.", HttpStatus.BAD_REQUEST);
    }
}

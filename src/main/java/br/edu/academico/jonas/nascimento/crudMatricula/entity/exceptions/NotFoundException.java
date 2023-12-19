package br.edu.academico.jonas.nascimento.crudMatricula.entity.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }
}

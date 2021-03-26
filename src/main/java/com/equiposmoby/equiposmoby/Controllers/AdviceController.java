package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.respuestas.ErrorResponse;
import com.equiposmoby.equiposmoby.excepciones.AgendaNoCreadaException;
import com.equiposmoby.equiposmoby.excepciones.AgendaNoEncontradaException;
import com.equiposmoby.equiposmoby.excepciones.ReunionNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgendaNoEncontradaException.class)
    public ErrorResponse handleAgendaNoEncontradaException(AgendaNoEncontradaException ex) {

        return ErrorResponse.fromRunTimeException(ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgendaNoCreadaException.class)
    public ErrorResponse handleAgendaNoCreadaException(AgendaNoCreadaException ex) {

        return ErrorResponse.fromRunTimeException(ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReunionNoEncontradaException.class)
    public ErrorResponse handleReunionNoEncontradaException(ReunionNoEncontradaException ex) {

        return ErrorResponse.fromRunTimeException(ex);
    }

}

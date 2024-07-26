package com.fernandocanabarro.desafio_picpay.dtos.exceptions;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'",timezone = "GMT-3")
    private Instant timestamp;
    private Integer status;
    private String message;
    private String path;
}

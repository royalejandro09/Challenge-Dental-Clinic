package com.digitalhouse.reservaturnosaprgbt.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    /**
     * Atributos
     */
    private LocalDateTime fecha;
    private String mensaje;
    private String detalle;
}

package com.digitalhouse.reservaturnosaprgbt.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TurnoDto {

    /**
     * Atributos
     */
    private Integer id;

    @NotNull(message = "Atributo no puede ser Null")
    private LocalDateTime fechaHora;

    /**
     * Relacion TurnoDto - OdontologoDto
     */
    @NotNull(message = "Atributo no puede ser Null")
    private OdontologoDto odontologoDto;

    /**
     * Relacion TurnoDto - PacienteDto
     */
    @NotNull(message = "Atributo no puede ser Null")
    private PacienteDto pacienteDto;
}

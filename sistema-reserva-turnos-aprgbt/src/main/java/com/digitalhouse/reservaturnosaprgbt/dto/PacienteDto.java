package com.digitalhouse.reservaturnosaprgbt.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PacienteDto {

    /**
     * Atributos
     */
    private Integer id;

    @NotBlank(message = "Atributo no puede ser Null o vacio")
    @Size(min = 3, message = "Atributo debe tener minimo 3 caracteres")
    private String nombre;

    @NotBlank(message = "Atributo no puede ser Null o vacio")
    @Size(min = 3, message = "Atributo debe tener minimo 3 caracteres")
    private String apellido;

    @NotBlank(message = "Atributo no puede ser Null o vacio")
    @Size(min = 3, message = "Atributo debe tener minimo 3 caracteres")
    private String dni;

    private LocalDate fechaDeCreacion;

    /**
     * Relacion PacienteDto - DomicilioDto
     */
    @NotNull(message = "Atributo no puede ser Null")
    @Valid
    private DomicilioDto domicilioDto;
}



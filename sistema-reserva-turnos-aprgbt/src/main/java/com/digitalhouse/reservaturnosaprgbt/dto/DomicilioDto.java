package com.digitalhouse.reservaturnosaprgbt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DomicilioDto {

    /**
     * Atributos Dto
     */
    private Integer id;

    @NotBlank(message = "Atributo no puede ser Nul o Vacio")
    @Size(min = 3, message = "Atributo debe tener minimo 3 caracteres")
    private String calle;

    @NotNull(message = "Atributo no puede ser Null o Vacio")
    private int numero;

    @NotBlank(message = "Atributo no puede ser Nul o Vacio")
    @Size(min = 3, message = "Atributo debe tener minimo 3 caracteres")
    private String localidad;

    @NotBlank(message = "Atributo no puede ser Nul o Vacio")
    @Size(min = 3, message = "Atributo debe tener minimo 3 caracteres")
    private String provincia;
}

package com.digitalhouse.reservaturnosaprgbt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoDto {

    /**
     * Atributos
     */
    private Integer id;

    @NotBlank(message = "Atributo no puede ser Null o vacio")
    @Size(min = 3, message = "Atributo debe tener min 3 caracteres")
    private String nombre;

    @NotBlank(message = "Atributo no puede ser Null o vacio")
    @Size(min = 3, message = "Atributo debe tener min 3 caracteres")
    private String apellido;

    @NotNull(message = "Atributo no puede ser Null o vacio")
    private Integer matricula;


}



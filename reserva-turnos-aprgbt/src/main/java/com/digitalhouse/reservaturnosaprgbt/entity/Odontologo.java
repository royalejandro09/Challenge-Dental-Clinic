package com.digitalhouse.reservaturnosaprgbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Odontologos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Odontologo {

    /**
     * Atributos
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 60)
    private String apellido;

    @Column(nullable = false)
    private Integer matricula;

    /**
     * Relacion Odontologo - Turno
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "odontologo", fetch = FetchType.LAZY)
    private Set<Turno> turnos;


}

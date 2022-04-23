package com.digitalhouse.reservaturnosaprgbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Paciente {

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

    @Column(nullable = false, length = 20)
    private String dni;

    @Column(nullable = false)
    private LocalDate fechaDeCreacion;

    /**
     * Relacion Paciente - Domicilio
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    /**
     * Relacion Paciente - Turno
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Turno> turnos;


}

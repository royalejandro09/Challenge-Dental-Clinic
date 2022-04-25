package com.digitalhouse.reservaturnosaprgbt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Rol {

    /**
     * Atributos
     */
    @Id
    private Integer idRol;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;


}

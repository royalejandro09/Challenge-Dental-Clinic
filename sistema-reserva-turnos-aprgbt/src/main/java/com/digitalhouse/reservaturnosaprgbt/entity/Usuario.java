package com.digitalhouse.reservaturnosaprgbt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    /**
     * Atributos
     */
    @Id
    private Integer idUsuario;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Relacion Usuario -> Rol
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
    private List<Rol> roles;
}

package com.digitalhouse.reservaturnosaprgbt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Domicilio {

    /**
     * Atributos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String calle;

    @Column(nullable = false, length = 50)
    private int numero;

    @Column(nullable = false, length = 50)
    private String localidad;

    @Column(nullable = false, length = 50)
    private String provincia;


}

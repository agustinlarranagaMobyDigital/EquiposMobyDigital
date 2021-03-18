package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "integrantes")
public  class Integrante implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String apellido;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    private int experiencia;

    private boolean jefe;

    // Foreing Keys
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto")
    private Puesto puesto;
*/
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "integrante_x_lenguaje",
            joinColumns = @JoinColumn(name = "id_integrante"),
            inverseJoinColumns = @JoinColumn(name = "id_lenguaje"))
    private List<Lenguaje> lenguajes ;

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; */

}

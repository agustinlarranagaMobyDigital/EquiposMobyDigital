package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "integrantes")
public  class   Integrante implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_integrante")
    private int id;

    private String nombre;

    private String apellido;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    private int experiencia;

    private boolean jefe;

    // Foreing Keys

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto")
    private Puesto puesto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "integrante_x_lenguaje",
            joinColumns = @JoinColumn(name = "id_integrante"),
            inverseJoinColumns = @JoinColumn(name = "id_lenguaje"))
    private List <Lenguaje> lenguajes ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User usuario;

    /*public void setLenguajes(List <Lenguaje> lista){
        lenguajes = lista;
    }*/


}

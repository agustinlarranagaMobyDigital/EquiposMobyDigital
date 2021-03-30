package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private int experiencia;

    private boolean jefe;
    private boolean programador;

    // Foreing Keys

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    private boolean tieneEquipo;

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

    public <T> Integrante(int i, String agustin, String larra, LocalDate now, int i1, boolean b, Equipo equipo, boolean b1, Puesto puesto, List<T> asList, Agenda agenda, User user) {
    }

    public void setLenguajes(List <Lenguaje> lista){
        lenguajes = lista;
    }

    public boolean isJefe(){
        return jefe;
    }

    public boolean isProgramador(){
        return programador;
    }



}

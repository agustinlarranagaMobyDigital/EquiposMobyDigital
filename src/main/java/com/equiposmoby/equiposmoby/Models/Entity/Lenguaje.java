package com.equiposmoby.equiposmoby.Models.Entity;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lenguajes")
public class Lenguaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id_lenguaje")
    private int id;

    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "integrante_x_lenguaje",
            joinColumns = @JoinColumn(name = "id_lenguaje"),
            inverseJoinColumns = @JoinColumn(name = "id_integrante"))
    private List<Integrante> integrantes;


}
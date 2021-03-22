package com.equiposmoby.equiposmoby.Models.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String nombre;

    private Lider lider;

    @OneToMany
    private List<Integrante> arrayList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;
}

package com.equiposmoby.equiposmoby.Models.Entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column()
    private String nombre;
    private Lider lider;
    private ArrayList<Programador> arrayList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Cuenta cuenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Agenda agenda;
}

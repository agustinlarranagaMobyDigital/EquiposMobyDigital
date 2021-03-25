package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "puestos")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
}

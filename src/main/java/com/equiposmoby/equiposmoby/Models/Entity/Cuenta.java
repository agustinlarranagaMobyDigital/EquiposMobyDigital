package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Cuentas")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id_cuenta;

    @Column
    private String nombre;

    @Column
    private Double valor;
}

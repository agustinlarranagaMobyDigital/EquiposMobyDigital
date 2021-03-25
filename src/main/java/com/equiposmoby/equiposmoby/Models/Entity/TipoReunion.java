package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data //Crea los getters and setter, equals, toString.
@AllArgsConstructor /// Crea el constructor con todos los atributos
@NoArgsConstructor  /// Contructor vacio
@Entity
@Table(name = "tipo_reunion")
public class TipoReunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo_reunion")
    private Integer idTipoReunion;

    @Column
    private String nombre;

}

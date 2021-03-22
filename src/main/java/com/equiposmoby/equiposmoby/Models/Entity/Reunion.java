package com.equiposmoby.equiposmoby.Models.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data //Crea los getters and setter, equals, toString.
@AllArgsConstructor /// Crea el constructor con todos los atributos
@NoArgsConstructor  /// Contructor vacio
@Entity
@Table(name = "reuniones")
public class Reunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha")
    private Date fecha;


    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "hora_inicial")
    private Date horaInicial;


    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "hora_final")
    private Date horaFinal;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_reunion")
    private List <TipoReunion> listaTipoReunion;



}


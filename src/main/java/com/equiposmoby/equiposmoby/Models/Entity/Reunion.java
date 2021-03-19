package com.equiposmoby.equiposmoby.Models.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


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
/*
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicial")
    private LocalDateTime horaInicial;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_final")
    private LocalDateTime horaFinal;


    @JoinColumn(name = "id_tipo_reunion")
    @ManyToOne
    private TipoReunion id_tipoReunion;
*/

}


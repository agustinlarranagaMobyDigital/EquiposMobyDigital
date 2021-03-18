package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicial")
    private DateTimeFormatter horaInicial;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_final")
    private DateTimeFormatter horaFinal;


    @JoinColumn(name = "id_tipo_reunion")
    @ManyToOne
    private TipoReunion id_tipoReunion;


}

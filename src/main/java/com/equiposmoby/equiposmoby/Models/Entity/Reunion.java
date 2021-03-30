package com.equiposmoby.equiposmoby.Models.Entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Builder
@Data //Crea los getters and setter, equals, toString.
@AllArgsConstructor /// Crea el constructor con todos los atributos
@NoArgsConstructor  /// Contructor vacio
@Entity
@Table(name = "reuniones")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Reunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reunion")
    private Integer idReunion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;


    @Column(name = "hora_inicial")
    private LocalTime horaInicial;


    @Column(name = "hora_final")
    private LocalTime horaFinal;


    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "id_tipo_reunion")
    private TipoReunion tipoReunion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "AGENDAXREUNION",
            joinColumns = @JoinColumn(name = "id_reunion"),
            inverseJoinColumns = @JoinColumn(name = "id_agenda"))
    private List<Agenda> agenda;



}


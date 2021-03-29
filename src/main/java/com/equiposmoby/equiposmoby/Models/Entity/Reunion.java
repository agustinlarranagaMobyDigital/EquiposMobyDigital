package com.equiposmoby.equiposmoby.Models.Entity;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Data //Crea los getters and setter, equals, toString.
@AllArgsConstructor /// Crea el constructor con todos los atributos
@NoArgsConstructor  /// Contructor vacio
@Entity
@Table(name = "reuniones")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Reunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reunion")
    private Integer idReunion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha")
    private LocalDate fecha;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "hora_inicial")
    private LocalTime horaInicial;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "hora_final")
    private LocalTime horaFinal;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_reunion")
    private List<TipoReunion> listaTipoReunion;
}
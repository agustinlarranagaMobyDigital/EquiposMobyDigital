package com.equiposmoby.equiposmoby.Models.Entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Builder
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

    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha")
    private Date fecha;


    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "hora_inicial")
    private Date horaInicial;


    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "hora_final")
    private Date horaFinal;


    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "id_tipo_reunion")
    private TipoReunion tipoReunion;



}


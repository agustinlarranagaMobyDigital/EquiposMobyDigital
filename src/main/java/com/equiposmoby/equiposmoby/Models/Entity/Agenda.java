package com.equiposmoby.equiposmoby.Models.Entity;

import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "agendas")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Agenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Agenda")
    private Integer idAgenda;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AGENDAXREUNION",
            joinColumns = @JoinColumn(name = "id_agenda"),
            inverseJoinColumns = @JoinColumn(name = "id_reunion"))
    private List<Reunion> reuniones = new ArrayList<>();

}

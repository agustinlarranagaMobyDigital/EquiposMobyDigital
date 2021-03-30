package com.equiposmoby.equiposmoby.Models.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "equipos")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nombre;

    @OneToMany
    private List<Integrante> arrayList;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    private boolean completo;

    public Equipo (int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public List<Integrante> getProgramadores(){
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getPuesto().getNombre().equals("lider")){
                arrayList.remove(i);
                break;
            }
        }
        return arrayList;
    }

    public Integrante getLider(){
        Integrante lider = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getPuesto().getNombre().equals("lider")){
                lider = arrayList.get(i);
                break;
            }
        }
        return lider;
    }
}

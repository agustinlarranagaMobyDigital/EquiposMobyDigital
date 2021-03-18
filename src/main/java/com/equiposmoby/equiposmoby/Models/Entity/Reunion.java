package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data //Crea los getters and setter, equals, toString.
@AllArgsConstructor /// Crea el constructor con todos los atributos
@NoArgsConstructor  /// Contructor vacio
@Entity
@Table(name = "reuniones")
public class Reunion implements Serializable {

    /** Es una clave primaria */

    ///Falta colum

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date fecha;         /** CAMBIAR  por localDateTime*/

    @Temporal(TemporalType.TIME)

    @Column /**Crea la columna de la base de datos. */

    private DateTimeFormatter horaInicial;
    @Temporal(TemporalType.TIME)
    private DateTimeFormatter horaFinal;

    /** Es una foreing key */
    @ManyToOne
    ///En vez de colum va JoinColum.
    private int id_tipo_reunion; ///Tipo reunion.




}

package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Capacitaciones extends Reunion {

    private List<Programador> programadores;
}

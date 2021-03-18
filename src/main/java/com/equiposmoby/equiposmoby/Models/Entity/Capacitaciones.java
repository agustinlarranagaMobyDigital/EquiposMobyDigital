package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Capacitaciones extends Reunion {

    private List<Programador> programadores;
}

package com.equiposmoby.equiposmoby.Models.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programador extends Integrante{

    private List<String> tecnologias;

}

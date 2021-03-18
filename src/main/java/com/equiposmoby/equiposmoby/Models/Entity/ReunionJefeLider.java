package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReunionJefeLider extends Reunion{

    private Lider lider;

    private Jefe jefe;
}

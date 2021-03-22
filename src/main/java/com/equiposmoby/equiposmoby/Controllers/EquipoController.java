package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EquipoController {

    @Autowired
    private EquipoServiceIMP equipoServiceIMP;
}

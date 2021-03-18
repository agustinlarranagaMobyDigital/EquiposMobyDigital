package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ReunionController {

    @Autowired
    private IDao reunionDao;

    @RequestMapping(value = "/reuniones") // Si esta vacio, implicitamente es GET
    public String listar(Model model){
        model.addAttribute("titulo","Listado de las reuniones");
        model.addAttribute("reuniones", reunionDao.traerTodas());
        return "reuniones";
    }
}

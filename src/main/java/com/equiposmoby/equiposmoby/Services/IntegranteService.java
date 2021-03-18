package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IntegranteRepository;
import com.equiposmoby.equiposmoby.Models.Entity.Programador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IntegranteService {

    @Autowired
    private IntegranteRepository integranteDAO;

    public void addProgramador(Programador programador){
        integranteDAO.addProgramador(programador);
    }


}

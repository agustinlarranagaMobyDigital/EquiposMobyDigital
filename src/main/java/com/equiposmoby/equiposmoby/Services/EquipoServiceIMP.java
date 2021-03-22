package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import com.equiposmoby.equiposmoby.Models.Entity.Lider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceIMP implements IEquipoService{

    @Autowired
    @Qualifier("equipoDAO")
    private IDao equipoDAO;

    @Override
    public List<Equipo> traerTodas() {
        return equipoDAO.traerTodas();
    }

    @Override
    public void agregar(Equipo equipo) {
        equipoDAO.agregar(equipo);
    }

    @Override
    public void eliminar(Equipo equipo) {
        equipoDAO.eliminar(equipo);
    }


    @Override
    public Equipo buscar(String nombre) {
            Equipo equipoBuscado = (Equipo) equipoDAO.buscar(nombre);
            if(!equipoBuscado.getNombre().isEmpty()){
                if(equipoBuscado != null){
                    return equipoBuscado;
                }
            }
            return new Equipo();
    }


}

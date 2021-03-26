package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceIMP implements IEquipoService{

    @Autowired
    @Qualifier("equipoDAO")
    private IDao equipoDAO;

    @Autowired
    private IntegranteService integranteService;

    @Override
    public List<Equipo> traerTodas() {

         List<Equipo> lista = equipoDAO.traerTodas();
         Integer idEquipo = 0 ;

         for (int i = 0; i < lista.size(); i++) {
             idEquipo = lista.get(i).getId();
             List<Integrante> listaIntegrantes = integranteService.getIntegrantesByIdEquipo(idEquipo);
             lista.get(i).setArrayList(listaIntegrantes);
             System.out.println("idEquipo = " + idEquipo);
             System.out.println("listaIntegrantes.toString() = " + listaIntegrantes.toString());
             System.out.println("lista.get(i).getArrayList().toString() = " + lista.get(i).getArrayList().toString());

         }
         
        return lista;
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

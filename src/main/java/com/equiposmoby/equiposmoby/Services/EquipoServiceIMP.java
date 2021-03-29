package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipoServiceIMP implements IEquipoService{

    @Autowired
    @Qualifier("equipoDAO")
    private IDao equipoDAO;

    @Autowired
    @Qualifier("agendaDao")
    private IDao agendaDao;

    @Autowired
    @Qualifier("cuentaDaoJPA")
    private IDao cuentaDao;

    @Autowired
    private IntegranteService integranteService;

    @Autowired
    private CuentaService cuentaService;




    // ----------------------------------------------------- METODOS DE INTERFACE
    @Override
    public List<Equipo> traerTodas() {

        List<Equipo> listaEquipos = equipoDAO.traerTodas();

        for (int i = 0; i < listaEquipos.size(); i++) {
            this.agregarIntegrantesAEquipo(listaEquipos.get(i));
        }


        return listaEquipos;
    }

    @Override
    public void agregar(Equipo equipo) {
        equipoDAO.agregar(equipo);
    }

    @Override
    public void eliminar(Equipo equipo) {

        integranteService.borrarEquipoDeTodosLosIntegrantes(equipo);
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


    public Equipo getById (Integer id){

        Equipo equipo = (Equipo) equipoDAO.getById(id);
        this.agregarIntegrantesAEquipo(equipo);
        return equipo;
    }



    // ------------------------------------------------------------------- METODOS INTERNOS
        private void agregarIntegrantesAEquipo(  Equipo equipo){

            Integer idEquipo = 0 ;
            idEquipo = equipo.getId();
            List<Integrante> listaIntegrantes = integranteService.getIntegrantesByIdEquipo(idEquipo);
            equipo.setArrayList(listaIntegrantes);
        }

        private void agregarCuentasAListaEquipos(List<Equipo> listaEquipos){

            Integer idEquipo = 0 ;

            for (int i = 0; i < listaEquipos.size(); i++) {
                idEquipo = listaEquipos.get(i).getId();
               // Cuenta cuenta= cuentaService.getByIdEquipo(idEquipo);
              //  listaEquipos.get(i).setCuenta(cuenta);
            }
        }

        public void eliminarIntegrante(){

            // borrar integrante de equipo
            // borrar equipo de integrante

        }

    // ------------------------------------------------- METODOS FORANEOS

    public Cuenta getCuentaByID(Integer id){
        Cuenta resultado = null;
        if(id > 0) {
            List<Cuenta> lista = cuentaDao.traerTodas();
            for (Cuenta cuenta : lista) {
                if (id == cuenta.getId_cuenta()) {
                    resultado = cuenta;
                    break;
                }
            }
        }
        return resultado;
    }


    public boolean agregarAgenda(Equipo equipo){

        if (equipo != null){
            Agenda agenda = Agenda.builder()
                    .reuniones(new ArrayList<Reunion>())
                    .build();

            equipo.setAgenda(agenda);

            agendaDao.agregar(agenda);
            return true;
        }
        return false;
    }


}

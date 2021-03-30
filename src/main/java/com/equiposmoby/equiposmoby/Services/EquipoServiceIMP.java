package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipoServiceIMP {

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

    @Autowired
    private ValidacionesService validacionesService;


    // ----------------------------------------------------- METODOS DE INTERFACE
    public List<Equipo> traerTodas() {

        List<Equipo> listaEquipos = equipoDAO.traerTodas();

        for (int i = 0; i < listaEquipos.size(); i++) {
            this.agregarListaIntegrantesAEquipo(listaEquipos.get(i));
        }


        return listaEquipos;
    }

    public boolean agregar(Equipo equipo) {

            equipo.setCompleto(false);
            equipoDAO.agregar(equipo);
            return true;
    }

    public void editar(Equipo equipo) {
        equipoDAO.agregar(equipo);
    }

    public void eliminar(Equipo equipo) {

        integranteService.borrarEquipoDeTodosLosIntegrantes(equipo);
        equipoDAO.eliminar(equipo);
    }

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
        this.agregarListaIntegrantesAEquipo(equipo);
        return equipo;
    }



    // ------------------------------------------------------------------- METODOS INTERNOS

    public void validarNuevoIntegrante(Integrante integrante,Equipo equipo ,Map<String, String> errores){

        boolean isCompleto = isPuestoCompleto(integrante.getPuesto().getNombre(),equipo);
        if (isCompleto){
            errores.put("puestoCompleto","No hay mas vacantes en este equipo para el puesto "+integrante.getPuesto().getNombre()+" esta completo");
        }
    }

    private boolean isPuestoCompleto(String puesto,Equipo equipo){
        int vacantes = 0;
        if(puesto.equals("lider")){vacantes = 1;}
        if(puesto.equals("tester")){vacantes = 2;}
        if(puesto.equals("backend")){vacantes = 3;}
        if(puesto.equals("frontend")){vacantes = 4;}

        int cantidad = 0;
        for (int i = 0; i < equipo.getArrayList().size(); i++) {
            if(equipo.getArrayList().get(i).getPuesto().getNombre().equals(puesto)){
                cantidad++;
            }
        }
        System.out.println("cantidad = " + cantidad);
        System.out.println("vacantes = " + vacantes);

        //cuenta cuantos integrantes tengo en este puesto
        // si las vacantes estan llenas tira true
        if(vacantes==cantidad){
            return true;
        }else{
            return false;
        }
    }
        private void agregarListaIntegrantesAEquipo(  Equipo equipo){

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

        public boolean agregarIntegrante(Map<String, String> errores,Integer idIntegrante, Integer idEquipo) {

            Integrante integrante = integranteService.getById(idIntegrante);
            Equipo equipo = this.getById(idEquipo);

            boolean isOk = validacionesService.validarNuevoIntegranteAEquipo(errores,integrante,equipo.getArrayList());
            if(isOk){
                integranteService.asignarEquipo(integrante,equipo);
                checkEquipoCompleto(equipo);
            }

            return isOk;
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

    public void checkEquipoCompleto(Equipo equipo){

        int lider = 0;
        int back = 0;
        int front = 0;
        int tester = 0;

        for (int i = 0; i < equipo.getArrayList().size(); i++) {
            if(equipo.getArrayList().get(i).getPuesto().equals("lider")){
                lider++;
            }
            if(equipo.getArrayList().get(i).getPuesto().equals("frontend")){
                back++;
            }
            if(equipo.getArrayList().get(i).getPuesto().equals("backend")){
                front++;
            }
            if(equipo.getArrayList().get(i).getPuesto().equals("tester")){
                tester++;
            }
        }

        if(lider==1 && back==3 && front==4 && tester==2){
            if(equipo.isCompleto() == false){
                equipo.setCompleto(true);
                editar(equipo);
            }
        }else{
            equipo.setCompleto(false);
        }
    }

}

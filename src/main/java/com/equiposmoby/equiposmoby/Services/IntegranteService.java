package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.AgendaDAO;
import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Daos.IntegranteDAO;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.PushBuilder;
import java.util.*;


@Service
public class IntegranteService extends ValidacionesService{

    // ------------------------------------------------------------------------ INYECCIONES

    @Autowired
    private EquipoServiceIMP equipoService;

    @Autowired
    @Qualifier("agendaDao")
    private IDao agendaDao;

    @Autowired
    @Qualifier("integranteDAO")
    private IntegranteDAO integranteDAO;

    @Autowired
    @Qualifier("lenguajeDAO")
    private IDao lenguajeDAO;

    @Autowired
    @Qualifier("puestoDAO")
    private IDao puestoDAO;

    @Autowired
    @Qualifier("equipoDAO")
    private IDao equipoDAO;

    @Autowired
    @Qualifier("usuarioDAO")
    private IDao userDao;

    // ------------------------------------------------------------------------ METODOS INTERNOS

    public boolean add(Integrante integrante){

        if(validarIntegrante(integrante)){
            integranteDAO.agregar(integrante);
            return true;
        }
        return false;

    }

    public Integrante agregarAgenda(Integrante integrante){

        Agenda agenda = Agenda.builder()
                .reuniones(new ArrayList<Reunion>())
                .build();

        integrante.setAgenda(agenda);

        agendaDao.agregar(agenda);
        return integrante;

    }
    
    public void editar(Integrante integrante){
        integranteDAO.agregar(integrante);
    }

    public List<Integrante> listar(){
        return integranteDAO.traerTodas();
    }

    public boolean eliminar(Integer id){
        if(id > 0){
            Integrante integrante = getById(id);
            if(integrante != null){
                integranteDAO.eliminar(integrante);
                userDao.eliminar(integrante.getUsuario());
                return true;
            }
        }
        return false;
    }

    public Integrante getById (Integer id){
        if(id > 0){
            return (Integrante) integranteDAO.getById(id);
        }
        return null;
    }

    // ------------------------------------------------------------------------ METODOS EXTERNOS

    public List<Lenguaje> getLenguajes() {
        return lenguajeDAO.traerTodas();
    }

    public Lenguaje getLenguajeByID(Integer id){
        Lenguaje resultado = new Lenguaje();
        List<Lenguaje> lista = getLenguajes();
        if(!lista.isEmpty()){
            for (Lenguaje lenguaje: lista){
                if(id == lenguaje.getId()){
                    resultado = lenguaje;
                    break;
                }
            }
        }

        return resultado;
    }

    public List<Puesto> getPuestos() {
        return puestoDAO.traerTodas();
    }

    public Puesto getPuestoByID(Integer id){
        Puesto resultado = null;
        if(id > 0) {
            List<Puesto> lista = puestoDAO.traerTodas();
            for (Puesto puesto : lista) {
                if (id == puesto.getId()) {
                    resultado = puesto;
                    break;
                }
            }
        }
        return resultado;
    }

    public List<Integrante> getIntegrantesByIdEquipo(Integer idEquipo){ return integranteDAO.getByIdEquipo(idEquipo); }

    public Equipo getEquipoByID (Integer id){
        Equipo resultado = null;

        if(id > 0){
            resultado = (Equipo) equipoDAO.getById(id);
        }
        return resultado;
    }

    public List<Equipo> getEquipos() {
        return equipoDAO.traerTodas();
    }

    public List<Integrante> getOrderIntegrante(){

        ArrayList<Integrante> integrantes = obtenerLideres();

        integrantes.addAll(obtenerProgramadoresBKN());
        integrantes.addAll(obtenerProgramadoresFTN());
        integrantes.addAll(obtenerProgramadoresTester());

        return integrantes;
    }

    public ArrayList<Integrante> obtenerLideres(){
        ArrayList<Integrante> lideres = new ArrayList<>();

        List<Integrante> integrantes = integranteDAO.traerTodas();
        for (Integrante nuevo : integrantes){
            if (nuevo.getPuesto().getNombre().equals("lider")){
                lideres.add(nuevo);
            }
        }
        return lideres;
    }

    public ArrayList<Integrante> obtenerProgramadoresBKN(){
        ArrayList<Integrante> programadores = new ArrayList<>();

        List<Integrante> integrantes = integranteDAO.traerTodas();
        for (Integrante nuevo : integrantes){
            if (nuevo.getPuesto().getNombre().equals("backend")){
                programadores.add(nuevo);
            }
        }
        return programadores;
    }

    public ArrayList<Integrante> obtenerProgramadoresFTN(){
        ArrayList<Integrante> programadores = new ArrayList<>();

        List<Integrante> integrantes = integranteDAO.traerTodas();
        for (Integrante nuevo : integrantes){
            if (nuevo.getPuesto().getNombre().equals("frontend")){
                programadores.add(nuevo);
            }
        }
        return programadores;
    }

    public ArrayList<Integrante> obtenerProgramadoresTester(){
        ArrayList<Integrante> programadores = new ArrayList<>();

        List<Integrante> integrantes = integranteDAO.traerTodas();
        for (Integrante nuevo : integrantes){
            if (nuevo.getPuesto().getNombre().equals("tester")){
                programadores.add(nuevo);
            }
        }
        return programadores;
    }

    public void asignarEquipo(Integer idIntegrante, Integer idEquipo){
        Integrante integrante = getById(idIntegrante);
        Equipo equipo = equipoService.getById(idEquipo);
        if(equipo != null){
            integrante.setEquipo(equipo);
            integrante.setTieneEquipo(true);
            this.editar(integrante);
        }
    }

    public void quitarEquipo(Integer idIntegrante){
        Integrante integrante = getById(idIntegrante);
        integrante.setEquipo(null);
        integrante.setTieneEquipo(false);
        this.editar(integrante);
    }
}

package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class IntegranteService extends ValidacionesService{

    // ------------------------------------------------------------------------ INYECCIONES

    @Autowired
    @Qualifier("agendaDao")
    private  IDao agendaDao;

    @Autowired
    @Qualifier("integranteDAO")
    private IDao integranteDAO;

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
        Lenguaje resultado = null;
        List<Lenguaje> lista = getLenguajes();
        for (Lenguaje lenguaje: lista){
            if(id == lenguaje.getId()){
                resultado = lenguaje;
                break;
            }
        }
        return resultado;
    }

    public List<Puesto> getPuestos() {
        return puestoDAO.traerTodas();
    }

    public Puesto getPuestoByID(Integer id){
        Puesto resultado = null;
        List<Puesto> lista = puestoDAO.traerTodas();
        for (Puesto puesto: lista){
            if(id == puesto.getId()){
                resultado = puesto;
                break;
            }
        }
        return resultado;
    }

    public Equipo getEquipoByID (Integer id){
        Equipo resultado = null;
        List<Equipo> lista = equipoDAO.traerTodas();
        for (Equipo equipo: lista){
            if(id.equals(equipo.getId())){
                resultado = equipo;
                break;
            }
        }
        return resultado;
    }

    public List<Equipo> getEquipos() {
        return equipoDAO.traerTodas();
    }


    public ArrayList<Integrante> getOrderIntegrante(){

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
}

package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.PushBuilder;
import java.util.*;


@Service
public class IntegranteService {

    // ------------------------------------------------------------------------ INYECCIONES

   /* @Autowired
    private AgendaService agendaService; */

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

    public void add(Integrante integrante){
        agregarAgenda(integrante);
        integranteDAO.agregar(integrante);
    }

    public boolean agregarAgenda(Integrante integrante){
        //boolean todook = agendaService.agregarAgendaIntegrante(integrante);
        //return todook;
        return true;
    }

    public List<Integrante> listar(){
        return integranteDAO.traerTodas();
    }

    public void eliminar(Integer id){
        Integrante integrante = getById(id);
        integranteDAO.eliminar(integrante);
<<<<<<< HEAD
        userDao.eliminar(userDao.buscar(integrante.getUsuario().getEmail()));
=======
>>>>>>> 8c4cb718bbe3b7f112ce0261fac776c164c6e96f
    }

    public Integrante getById (Integer id){
        return (Integrante) integranteDAO.getById(id);
    }
<<<<<<< HEAD


    // ------------------------------------------------------------------------ METODOS EXTERNOS
=======
    // ------------------------------------------------------------------------ METODOS PARA FOREING CLASES
>>>>>>> 8c4cb718bbe3b7f112ce0261fac776c164c6e96f

    public List<Lenguaje> getLenguajes() {
        return lenguajeDAO.traerTodas();
    }

    public Lenguaje getLenguajeByID(Integer id){
        Lenguaje resultado = null;
<<<<<<< HEAD
=======

>>>>>>> 8c4cb718bbe3b7f112ce0261fac776c164c6e96f
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
<<<<<<< HEAD


    public Equipo getEquipoByID (Integer id){
        Equipo resultado = null;
        List<Equipo> lista = equipoDAO.traerTodas();
=======
/*
    public Equipo getEquipoByID (Integer id){
        Equipo resultado = null;
        List<Puesto> lista = equipoDAO.traerTodas();
>>>>>>> 8c4cb718bbe3b7f112ce0261fac776c164c6e96f
        for (Equipo equipo: lista){
            if(id == equipo.getId()){
                resultado = equipo;
                break;
            }
        }
        return resultado;
    }

<<<<<<< HEAD
    public List<Equipo> getEquipos() {
        return equipoDAO.traerTodas();
    }

=======
    public List<Equipo> getEquipo() {
        return equipoDAO.traerTodas();
    }
    */
>>>>>>> 8c4cb718bbe3b7f112ce0261fac776c164c6e96f

    public Map<String , String> crearUsuario(BindingResult result ,String email, String pass){

        User userNew = new User();
        userNew.setEmail(email);
        userNew.setPassword(pass);

        Map <String , String> error = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            error.put(
                    err.getField(),"el campo "
                            .concat(err.getField())
                            .concat(" ")
                            .concat(Objects.requireNonNull(err.getDefaultMessage())));});

        User buscado = (User) userDao.buscar(userNew.getEmail());
        if(error.isEmpty()){
            if(buscado == null){
                userDao.agregar(userNew);
            }else {
                error.put("creado" , "este usuario ya existe");
            }
        }
        return error;
    }

    public User getUltimoUserByEmail(String email){
        return  (User) userDao.buscar(email);
    }

<<<<<<< HEAD
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
=======

>>>>>>> 8c4cb718bbe3b7f112ce0261fac776c164c6e96f
}

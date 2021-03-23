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
public class IntegranteService {

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

    public void add(Integrante integrante){
        integranteDAO.agregar(integrante);
        agregarAgenda(integrante);
    }

    public boolean agregarAgenda(Integrante integrante){

        Agenda agenda = Agenda.builder()
                .reuniones(new ArrayList<Reunion>())
                .build();
        integrante.setAgenda(agenda);

        agendaDao.agregar(agenda);
        return true;
    }

    public List<Integrante> listar(){
        return integranteDAO.traerTodas();
    }

    public void eliminar(Integer id){
        Integrante integrante = getById(id);

        integranteDAO.eliminar(integrante);
        userDao.eliminar(userDao.buscar(integrante.getUsuario().getEmail()));
    }

    public Integrante getById (Integer id){
        return (Integrante) integranteDAO.getById(id);
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
            if(id == equipo.getId()){
                resultado = equipo;
                break;
            }
        }
        return resultado;
    }

    public List<Equipo> getEquipos() {
        return equipoDAO.traerTodas();
    }


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

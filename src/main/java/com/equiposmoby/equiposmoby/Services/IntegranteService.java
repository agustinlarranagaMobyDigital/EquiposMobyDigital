package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.AgendaDAO;
import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Daos.IntegranteDAO;
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

    public void add(Integrante integrante){
        integranteDAO.agregar(integrante);
    }

    public void editar(Integrante integrante){
        integranteDAO.agregar(integrante);
    }

    public boolean agregarAgenda(Integrante integrante){

        if (integrante != null){
            Agenda agenda = Agenda.builder()
                    .reuniones(new ArrayList<Reunion>())
                    .build();

            integrante.setAgenda(agenda);

            agendaDao.agregar(agenda);
            return true;
        }
        return false;
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

    public Equipo getEquipoByID (Integer id){
        Equipo resultado = null;

        if(id > 0){
            resultado = equipoService.getById(id);
        }
        return resultado;
    }

    public List<Equipo> getEquipos() {
        return equipoDAO.traerTodas();
    }


    public List<Integrante> getIntegrantesByIdEquipo(Integer idEquipo){ return integranteDAO.getByIdEquipo(idEquipo); }

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

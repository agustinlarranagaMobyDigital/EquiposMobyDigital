package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.PushBuilder;
import java.util.*;

@Service
public class IntegranteService {

    // ------------------------------------------------------------------------ INYECCIONES

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
    @Qualifier("usuarioDAO")
    private IDao userDao;

    // ------------------------------------------------------------------------ METODOS DE INTEGRANTE

    public void add(Integrante integrante){
        integranteDAO.agregar(integrante);
    }

    public List<Integrante> listar(){
        return integranteDAO.traerTodas();
    }

    public void eliminar(Integer id){
        Integrante integrante = getById(id);
        integranteDAO.eliminar(integrante);
    }

    public Integrante getById (Integer id){
        return (Integrante) integranteDAO.getById(id);
    }
    // ------------------------------------------------------------------------ METODOS PARA FOREING CLASES

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
/*
    public Equipo getEquipoByID (Integer id){
        Equipo resultado = null;
        List<Puesto> lista = equipoDAO.traerTodas();
        for (Equipo equipo: lista){
            if(id == equipo.getId()){
                resultado = equipo;
                break;
            }
        }
        return resultado;
    }

    public List<Equipo> getEquipo() {
        return equipoDAO.traerTodas();
    }
    */

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


}

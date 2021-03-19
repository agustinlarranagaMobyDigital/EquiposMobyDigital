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

@Service
public class IntegranteService {

    @Autowired
    @Qualifier("integranteDAO")
    private IDao integranteDAO;



    public void add(Integrante integrante){
        integranteDAO.agregar(integrante);
    }

    @Autowired
    @Qualifier("lenguajeDAO")
    private IDao lenguajeDAO;

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

    @Autowired
    @Qualifier("puestoDAO")
    private IDao puestoDAO;

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



    @Autowired
    @Qualifier("usuarioDAO")
    private IDao userDao;
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

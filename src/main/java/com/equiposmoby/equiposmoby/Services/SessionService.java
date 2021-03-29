package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SessionService {


    @Autowired
    @Qualifier("usuarioDAO")
    private IDao userDao;


    public String sesionIniciada(HttpSession session , String vista){

        if(vista.equals("login")){
            return session.getAttribute("usuario") != null ?  "redirect:/app" : vista;
        }
        else {
            return session.getAttribute("usuario") == null ?  "redirect:/login" : vista;
        }
    }

    public Map<String , String> crearSesion(User usuario, HttpServletRequest request){

        ///this.createJefe();
        System.out.println("email:" + usuario.getEmail());
        System.out.println("password:" + usuario.getPassword());
        Map<String , String> errores = new HashMap<>();
        if (usuario.getEmail().isEmpty()){
            errores.put("email", "No puede estar vacio");
        }
        if(usuario.getPassword().isEmpty()) {
            errores.put("password", "No puede estar vacio");
        }
        User logUser = null;
        if(errores.isEmpty()){
            logUser = (User) this.userDao.buscar(usuario.getEmail());
            if(logUser == null){
                errores.put("incorrecto" , "el usuario no existe");
            }
        }


        if(errores.isEmpty() && !usuario.getPassword().isEmpty()){
            if (logUser.getPassword().equals(usuario.getPassword())){
               request.getSession().setAttribute("usuario", logUser);
            }else{
                errores.put("incorrecto" , "contraseña incorrecta");
            }
        }

        return errores;
    }

    public Map<String , String> crearUsuario(BindingResult result ,User userNew){
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
/*
    private void createJefe() throws ParseException {


        User nuevo = new User("Jefe@mobydigital.com" , "12345");
        User logUser = (User) userDao.buscar(nuevo.getEmail());

        if (logUser == null){
            userDao.agregar(nuevo);

            logUser = (User) userDao.buscar(nuevo.getEmail());
            Jefe nuevoJefe = new Jefe();
            nuevoJefe.setUsuario(logUser);
           /* integranteDAO.agregar(nuevoJefe);*/
        //}
    //}

}

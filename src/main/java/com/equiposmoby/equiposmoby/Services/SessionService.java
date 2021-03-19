package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Daos.UserDao;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SessionService {


    @Autowired
    @Qualifier("usuarioDAO")
    private IDao userDao;

    public String sesionIniciada(HttpSession session , String vista){

        return session.getAttribute("usuario") != null ?  "redirect:/app" : vista;
    }

    public Map<String , String> crearSesion(String email , String password , HttpServletRequest request){

        Map<String , String> errores = new HashMap<>();

        if (email.isEmpty()){
            errores.put("email", "No puede estar vacio");
        }
        if(password.isEmpty()){
            errores.put("password" , "No puede estar vacio");
        }
        User logUser = (User) userDao.buscar(email);
        if(logUser == null){
            errores.put("incorrecto" , "el usuario no existe");
        }

        if(errores.isEmpty() && !password.isEmpty()){
            if (logUser.getPassword().equals(password)){
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


}

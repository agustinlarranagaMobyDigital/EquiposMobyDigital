package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioServiceIMP implements IUsuarioServices{
    @Autowired
    @Qualifier("usuarioDAO")
    private IDao userDAO;

    public List<User> traerTodas(){
        return userDAO.traerTodas();
    }

    public boolean agregar(User usuario){

        User buscado = (User) userDAO.buscar(usuario.getEmail());
        if(buscado == null){
            if(usuario.getPassword() != null && usuario.getEmail() != null){
                userDAO.agregar(usuario);
                return true;
            }
        }
        return false;
    }


    public User buscar(String email, String password){

        if(email != null && password != null) {
            User buscado = (User) userDAO.buscar(email);

            if (buscado != null) {
                if (buscado.getPassword().equals(password)) {
                    return buscado;
                }

            }
        }

        return null;
    }


    @Override
    public void crearUsuario(String email, String pass, HashMap<String, String> errores) {


        if(email.isEmpty()){
            errores.put("email", "Campo email vacio");
        }
        if(pass.isEmpty()){
            errores.put("password", "Campo password vacio");
        }

        if (errores.isEmpty()) {

            if (!email.isEmpty() && !pass.isEmpty()) {
                User userNew = new User();
                userNew.setEmail(email);
                userNew.setPassword(pass);
                User buscado = (User) userDAO.buscar(email);
                if (buscado == null) {
                    userDAO.agregar(userNew);
                } else {
                    errores.put("creado", "este usuario ya existe");
                }

            }else{
                errores.put("test", "nombre y apellido vacio");
            }

        }

    }

    public User getUsuarioByEmail(String email){
        return (User) userDAO.buscar(email);
    }
}

package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceIMP implements IUsuarioServices{
    @Autowired
    @Qualifier("usuarioDAO")
    private IDao userDAO;

    public List<User> traerTodas(){
        return userDAO.traerTodas();
    }
    public void agregar(User usuario){
        userDAO.agregar(usuario);
    }

    @Override
    public void eliminar(User usuario) {
        userDAO.eliminar(usuario);
    }

    public User buscar(String email, String password){
        User buscado = (User) userDAO.buscar(email);
        if(!buscado.getEmail().isEmpty()){
            if (buscado.getPassword().equals(password)){
                return buscado;
            }
        }
        return new User();
    }
}

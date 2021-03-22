package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.CuentaDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements ICuentaService{

    @Autowired
    private CuentaDAO cuentaDAO;

    @Override
    public List<Cuenta> traerTodas() {
        return cuentaDAO.traerTodas();
    }

    @Override
    public void agregar(Cuenta cuenta) {
        cuentaDAO.agregar(cuenta);
    }

    @Override
    public void eliminar(Cuenta cuenta) {
        cuentaDAO.eliminar(cuenta);
    }

    @Override
    public Cuenta buscar(String txt) {
        return cuentaDAO.buscar(txt);
    }
}

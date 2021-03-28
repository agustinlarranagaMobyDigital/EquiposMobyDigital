package com.equiposmoby.equiposmoby.Models.Editors;

import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;


@Component
public class CuentaPropiertieEditor extends PropertyEditorSupport {

    @Autowired
    private EquipoServiceIMP equipoServiceIMP;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {

        if (idString != null && idString.length() > 0) {
            try {
                Integer id = Integer.parseInt(idString);
                this.setValue(equipoServiceIMP.getCuentaByID(id));
            } catch (NumberFormatException e) {
                setValue(null);
            }
        } else {
            setValue(null);
        }

    }

}
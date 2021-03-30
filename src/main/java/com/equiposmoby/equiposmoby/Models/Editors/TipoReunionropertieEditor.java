package com.equiposmoby.equiposmoby.Models.Editors;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import com.equiposmoby.equiposmoby.Services.ReunionService;
import com.equiposmoby.equiposmoby.Services.TipoReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TipoReunionropertieEditor extends PropertyEditorSupport {

    @Autowired
    private TipoReunionService tipoReunion;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {

        if (idString != null && idString.length() > 0){
            try {
                Integer id = Integer.parseInt(idString);
                TipoReunion resultado = tipoReunion.getById(id);
                if(resultado != null){
                    this.setValue(resultado);
                }

            }catch (NumberFormatException e){
                setValue(null);
            }
        }else{
            setValue(null);
        }

    }
}

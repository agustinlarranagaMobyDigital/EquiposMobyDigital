package com.equiposmoby.equiposmoby.Models.Editors;

import com.equiposmoby.equiposmoby.Services.IntegranteService;
import com.equiposmoby.equiposmoby.Services.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TipoReunionropertieEditor extends PropertyEditorSupport {

    @Autowired
    private ReunionService reunionService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {

        if (idString != null && idString.length() > 0){
            try {
                Integer id = Integer.parseInt(idString);
                this.setValue(reunionService.getTipoReunionByID(id));
            }catch (NumberFormatException e){
                setValue(null);
            }
        }else{
            setValue(null);
        }

    }
}

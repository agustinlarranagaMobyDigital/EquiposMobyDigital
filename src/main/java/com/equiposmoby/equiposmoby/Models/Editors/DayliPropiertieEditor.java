package com.equiposmoby.equiposmoby.Models.Editors;
/*
import com.equiposmoby.equiposmoby.Models.Entity.Daily;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class DayliPropiertieEditor {

    @Autowired
    private Daily daily;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {

        if (idString != null && idString.length() > 0) {
            try {
                Integer id = Integer.parseInt(idString);
                this.setValue(daily.getIdReunion());
            } catch (NumberFormatException e) {
                setValue(null);
            }
        } else {
            setValue(null);
        }

    }
}
*/
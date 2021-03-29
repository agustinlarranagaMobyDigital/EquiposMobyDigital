package com.equiposmoby.equiposmoby.Services;

import java.util.Date;

public class ReunionValidaciones {

    public boolean checkHora(Date fecha){
        if(fecha != null){
            if(new Date().getDate() < fecha.getDate()){
                return true;
            }
        }
        return false;
    }




}

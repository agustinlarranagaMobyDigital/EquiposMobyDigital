package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDao<T,t>{
    /**Metodos de Reunion */

    List<T> traerTodas();
    void agregar(T t);
    void delete(T t);
    void guardar();

    /**TipoReunion*/


}

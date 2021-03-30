package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Daos.TipoReunionDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReunionService extends ValidacionesService implements IReunionServices {

    @Autowired
    private ReunionDaoImple reunionDAO;

    @Autowired
    private TipoReunionDAO tipoReunionDAO;

    @Override
    public List<Reunion> traerTodas() {
        return reunionDAO.traerTodas();
    }

    @Override
    public boolean agregar(Reunion reunion) {
        reunionDAO.agregar(reunion);
        return false;
    }

    @Override
    public boolean eliminar(Reunion reunion) {
        boolean verificarEliminar = revisarDiaActual(reunion.getFecha());
        reunionDAO.eliminar(reunion);
        return verificarEliminar;
    }

    @Override
    public Reunion buscar(String txt) {
        return reunionDAO.buscar(txt);
    }

    public Reunion agregarReunion(Reunion reunion){

        Reunion reunionNew = Reunion.builder()
                .idReunion(reunion.getIdReunion())
                .fecha(reunion.getFecha())
                .horaInicial(reunion.getHoraInicial())
                .horaFinal(reunion.getHoraFinal())
                .tipoReunion(reunion.getTipoReunion())
                .build();
        agregar(reunionNew);
        return reunionNew;
    }

    public Reunion getById(Integer id) {
        if(id > 0){
            Reunion verificarBuscar = reunionDAO.getById(id);
            return verificarBuscar;
        }
        return null;

    }

    public TipoReunion getTipoReunionByID(Integer id) {
        TipoReunion resultado = null;
        if (id > 0) {
            List<TipoReunion> lista = tipoReunionDAO.traerTodas();
            for (TipoReunion tipoReunion : lista) {
                if (id.equals(tipoReunion.getIdTipoReunion())) {
                    resultado = tipoReunion;
                    break;
                }
            }
        }
        return resultado;
    }
    ///---------------------------------------------------------------------

    public boolean revisarDiaAnteriorOActual(LocalDate fecha){
        if(fecha != null){
            if(LocalDate.now().isBefore (fecha) || LocalDate.now().isEqual(fecha)){
                return true;
            }
        }
        return false;
    }

    public boolean revisarDiaActual(LocalDate fecha){
        if(fecha != null){
            if(LocalDate.now().isBefore (fecha)){
                return true;
            }
        }
        return false;
    }

    public boolean revisarGetID(Integer id){
        int i=0;
        if(traerTodas().size() != 0){
            while(i < traerTodas().size()){
                if(traerTodas().get(i).getIdReunion().equals(id)){
                    return true;
                }
                else{
                    i++;
                }
            }
        }
        return false;
    }

    public ArrayList<Reunion> obtenerSegunTipoIntegrante(String tipo){

        if(tipo.equals("lider")){
            return obtenerReunionesLider();
        }
        else{
            return obtenerReunionesProgramador();
        }

    }

    public ArrayList<Reunion> obtenerReunionesProgramador(){
        List<Reunion> reuniones = reunionDAO.traerTodas();
        ArrayList<Reunion> reunionesCorrespondientes = new ArrayList<>();
        for (Reunion reunion : reuniones) {
            if (reunion.getTipoReunion().getNombre().equals("Dayli") ||reunion.getTipoReunion().getNombre().equals("Capacitacion")){
                reunionesCorrespondientes.add(reunion);
            }
        }
        return reunionesCorrespondientes;
    }

    public ArrayList<Reunion> obtenerReunionesLider(){
        List<Reunion> reuniones = reunionDAO.traerTodas();
        ArrayList<Reunion> reunionesCorrespondientes = new ArrayList<>();
        for (Reunion reunion : reuniones) {
            if (reunion.getTipoReunion().getNombre().equals("Dayli") ||reunion.getTipoReunion().getNombre().equals("Formal")){
                reunionesCorrespondientes.add(reunion);
            }
        }
        return reunionesCorrespondientes;
    }

}

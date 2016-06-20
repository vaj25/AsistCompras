package gr23.sv.ues.fia.asistcompras.Entidades;

import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

/**
 * Created by Moisés on 15/06/2016.
 */
public class Lista {
    private int idLista;
    private String nombreLista;


    public Lista() {
    }

    public Lista(int idLista, String nombreLista, String descripcionLista) {
        this.idLista = idLista;
        this.nombreLista = nombreLista;

    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }


    @Override
    public String toString(){

        return ""+nombreLista;
    }
}

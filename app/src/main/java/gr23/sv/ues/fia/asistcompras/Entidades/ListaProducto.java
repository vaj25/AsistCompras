package gr23.sv.ues.fia.asistcompras.Entidades;

import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

/**
 * Created by MC on 17/06/2016.
 */
public class ListaProducto {
    private Integer idProductoLista;
    private String descripcion;
    private Integer idLista;
    private boolean checked;


    public ListaProducto() {
    }

    public ListaProducto(Integer idProductoLista, String descripcion, Integer idLista, boolean checked) {
        this.idProductoLista = idProductoLista;
        this.descripcion = descripcion;
        this.idLista = idLista;
        this.checked = checked;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public Integer getIdProductoLista() {
        return idProductoLista;
    }

    public void setIdProductoLista(Integer idProductoLista) {
        this.idProductoLista = idProductoLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
    @Override
    public String toString(){


        return ""+descripcion;
    }
}

package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class Articulo {
    private int idArticulo;
    private String descripcionArticulo;
    private String nombreArticulo;

    public Articulo() {
    }

    public Articulo(int idArticulo, String descripcionArticulo, String nombreArticulo) {
        this.idArticulo = idArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.nombreArticulo = nombreArticulo;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    @Override
    public String toString() {
        return nombreArticulo;
    }
}

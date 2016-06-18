package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class DetalleArticulo {
    private int idDetalleArticulo;
    private int cantidad;
    private double precio;
    private int idArticulo;
    private int idOferta;

    public DetalleArticulo() {
    }

    public DetalleArticulo(int idDetalleArticulo, int cantidad, double precio, int idArticulo, int idOferta) {
        this.idDetalleArticulo = idDetalleArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idArticulo = idArticulo;
        this.idOferta = idOferta;
    }

    public int getIdDetalleArticulo() {
        return idDetalleArticulo;
    }

    public void setIdDetalleArticulo(int idDetalleArticulo) {
        this.idDetalleArticulo = idDetalleArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String toString() {
        return String.valueOf(idDetalleArticulo)+"-";
    }
}

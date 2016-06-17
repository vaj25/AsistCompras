package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class detalleArticulo {
    private int idDetalleArticulo;
    private int cantidad;
    private double precio;
    private int idArticulo;
    private int idOferta;
    private double latitud;
    private double longitud;

    public detalleArticulo() {
    }

    public detalleArticulo(int idDetalleArticulo, int cantidad, double precio, int idArticulo, int idOferta, double latitud, double longitud) {
        this.idDetalleArticulo = idDetalleArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idArticulo = idArticulo;
        this.idOferta = idOferta;

        this.latitud = latitud;
        this.longitud = longitud;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    @Override

    public String toString() {
        return String.valueOf(idDetalleArticulo)+"-";
    }
}

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
    private int idLista;
    private int idUnidadMedida;
    private double latitud;
    private double longitud;

    public DetalleArticulo() {
    }

    public DetalleArticulo(int idDetalleArticulo, int cantidad, double precio, int idArticulo, int idOferta, int idLista, int idUnidadMedida, double latitud, double longitud) {
        this.idDetalleArticulo = idDetalleArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idArticulo = idArticulo;
        this.idOferta = idOferta;
        this.idLista = idLista;
        this.idUnidadMedida = idUnidadMedida;
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

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
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

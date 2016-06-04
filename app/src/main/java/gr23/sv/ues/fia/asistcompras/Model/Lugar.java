package gr23.sv.ues.fia.asistcompras.Model;

/**
 * Created by FAMILY on 04/06/2016.
 */
public class Lugar {
    private double latitud;
    private double longitud;
    private String nombre;
    private String Descripcion;
    private String image;

    public Lugar() {
    }

    public Lugar(double latitud, double longitud, String nombre, String descripcion, String image) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        Descripcion = descripcion;
        this.image = image;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

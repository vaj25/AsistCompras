package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class Oferta {
    private int idOferta;
    private String nombre;
    private String descripcion;
    private String foto;
    private String video;
    private String nombrelugar;

    public Oferta(int idOferta, String nombre, String descripcion, String foto, String video, String nombrelugar) {
        this.idOferta = idOferta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.video = video;
        this.nombrelugar = nombrelugar;
    }

    public Oferta() {
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String isFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String isVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getNombrelugar() {
        return nombrelugar;
    }

    public void setNombrelugar(String nombrelugar) {
        this.nombrelugar = nombrelugar;
    }
}

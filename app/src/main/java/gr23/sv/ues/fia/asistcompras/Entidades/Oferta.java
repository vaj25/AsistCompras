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

    public Oferta() {
    }

    public Oferta(int idOferta,String nombre,String descripcion, String foto, String video) {
        this.idOferta = idOferta;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.foto = foto;
        this.video = video;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
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

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){return nombre;}
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public String getDescripcion(){return  descripcion; }
}

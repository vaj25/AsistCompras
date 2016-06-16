package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class Oferta {
    private int idOferta;
    private String foto;
    private String video;

    public Oferta() {
    }

    public Oferta(int idOferta, String foto, String video) {
        this.idOferta = idOferta;
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
}

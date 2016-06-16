package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class Oferta {
    private int idOferta;
    private boolean foto;
    private boolean video;

    public Oferta() {
    }

    public Oferta(int idOferta, boolean foto, boolean video) {
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

    public boolean isFoto() {
        return foto;
    }

    public void setFoto(boolean foto) {
        this.foto = foto;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }
}

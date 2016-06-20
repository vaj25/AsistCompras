package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Karla Villavicencio on 16/06/2016.
 */
public class listaImagen {
    private String idImagen;
    private String textoEncima;
    private String textoDebajo;

    public listaImagen (String idImagen, String textoEncima, String textoDebajo) {
        this.idImagen = idImagen;
        this.textoEncima = textoEncima;
        this.textoDebajo = textoDebajo;
    }

    public String get_textoEncima() {
        return textoEncima;
    }

    public String get_textoDebajo() {
        return textoDebajo;
    }

    public String get_idImagen() {
        return idImagen;
    }
}


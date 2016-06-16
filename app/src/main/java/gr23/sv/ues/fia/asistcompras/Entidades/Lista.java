package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class Lista {
    private int idLista;
    private String nombreLista;
    private String descripcionLista;

    public Lista() {
    }

    public Lista(int idLista, String nombreLista, String descripcionLista) {
        this.idLista = idLista;
        this.nombreLista = nombreLista;
        this.descripcionLista = descripcionLista;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getDescripcionLista() {
        return descripcionLista;
    }

    public void setDescripcionLista(String descripcionLista) {
        this.descripcionLista = descripcionLista;
    }
}

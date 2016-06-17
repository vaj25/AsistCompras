package gr23.sv.ues.fia.asistcompras.Entidades;

/**
 * Created by Moisés on 15/06/2016.
 */
public class unidadMedida {
    private int idUnidadMedida;
    private String nombreUM;
    private String descripcionUM;

    public unidadMedida() {
    }

    public unidadMedida(int idUnidadMedida, String nombreUM, String descripcionUM) {
        this.idUnidadMedida = idUnidadMedida;
        this.nombreUM = nombreUM;
        this.descripcionUM = descripcionUM;
    }

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getNombreUM() {
        return nombreUM;
    }

    public void setNombreUM(String nombreUM) {
        this.nombreUM = nombreUM;
    }

    public String getDescripcionUM() {
        return descripcionUM;
    }

    public void setDescripcionUM(String descripcionUM) {
        this.descripcionUM = descripcionUM;
    }
}

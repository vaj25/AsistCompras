package gr23.sv.ues.fia.asistcompras.Entidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Karla Villavicencio on 17/06/2016.
 */
public abstract class listaImagenAdapter extends BaseAdapter {
    private ArrayList<?> ofertas;
    private int R_layout_IdView;
    private Context contexto;

    public listaImagenAdapter(Context contexto, int R_layout_IdView, ArrayList<?> ofertas) {
        super();
        this.contexto = contexto;
        this.ofertas = ofertas;
        this.R_layout_IdView = R_layout_IdView;
    }


    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onOferta (ofertas.get(posicion), view);
        return view;
    }


    public int getCount() {
        return ofertas.size();
    }


    public Object getItem(int posicion) {
        return ofertas.get(posicion);
    }


    public long getItemId(int posicion) {
        return posicion;
    }

    /** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * parametro ofertas La oferta que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public abstract void onOferta (Object entrada, View view);

}

package gr23.sv.ues.fia.asistcompras;

/**
 * Created by MC on 19/06/2016.
 */

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.TextView;

import gr23.sv.ues.fia.asistcompras.Entidades.Lista;


public class AdapterLista extends ArrayAdapter<Lista>
{
    private LayoutInflater layoutInflater;

    public AdapterLista(Context context, List<Lista> objects)
    {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.objetos_lista, null);

            holder.setTexto((TextView) convertView.findViewById(R.id.textoLista));

            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }

        Lista fila = getItem(position);
        holder.getTexto().setText(fila.getNombreLista());

        return convertView;
    }

    static class Holder
    {
        TextView texto;


        public TextView getTexto()
        {

            return texto;
        }

        public void setTexto(TextView texto)

        {
            this.texto = texto;
        }



    }
}

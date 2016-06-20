package gr23.sv.ues.fia.asistcompras;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.TextView;

import gr23.sv.ues.fia.asistcompras.Entidades.ListaProducto;


public class AdapterListaProducto extends ArrayAdapter<ListaProducto>
{
    private LayoutInflater layoutInflater;

    public AdapterListaProducto(Context context, List<ListaProducto> objects)
    {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // holder pattern
        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.objetos_lista_producto, null);

            holder.setTexto((TextView) convertView.findViewById(R.id.textoListaProducto));

            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }

        ListaProducto fila = getItem(position);
        holder.getTexto().setText(fila.getDescripcion());

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

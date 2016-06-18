package gr23.sv.ues.fia.asistcompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gr23.sv.ues.fia.asistcompras.Entidades.listaImagen;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagenAdapter;

public class OfertaConsultarActivity extends AppCompatActivity {
private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_consultar);
        ArrayList<listaImagen> datos = new ArrayList<listaImagen>();
        datos.add(new listaImagen(R.drawable.image,"Oferta de All Star","todos los estilos a mitad de precio, por liquidacion de la tienda  "));
        datos.add(new listaImagen(R.drawable.wii,"grandes descuentos para el Wii", "50% de descuento al comprar una consola al contado, INCREIBLEMENTE BARATUUUUS "));
        datos.add(new listaImagen(R.drawable.guitarra,"Guitarras Electricas","¿QUIERES INICIAR TU PROPIA BANDA? Entonces debes aproveche estos super descuento en guitarras electricas en la tienda MEGADEATH SHOP "));
        lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new listaImagenAdapter(this, R.layout.oferta, datos){
            @Override
            public void onOferta(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((listaImagen) entrada).get_textoEncima());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((listaImagen) entrada).get_textoDebajo());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((listaImagen) entrada).get_idImagen());
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                listaImagen elegido = (listaImagen) pariente.getItemAtPosition(posicion);
                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
                Toast toast = Toast.makeText(OfertaConsultarActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}


package gr23.sv.ues.fia.asistcompras;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gr23.sv.ues.fia.asistcompras.Entidades.Oferta;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagen;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagenAdapter;

public class OfertaConsultarActivity extends AppCompatActivity implements SensorEventListener {

    private ListView lista;

    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    SensorManager mSensorManager;
    Sensor mSensorAcc;
    private long mShakeTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_consultar);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            detectShake(event);
        }
    }

    private void detectShake(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - mShakeTime) > SHAKE_WAIT_TIME_MS) {
            mShakeTime = now;

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            // Change background color if gForce exceeds threshold;
            // otherwise, reset the color
            if (gForce > SHAKE_THRESHOLD) {
                Intent inte = new Intent(OfertaConsultarActivity.this, NuevaOfertaActivity.class);
                startActivity(inte);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorAcc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static class ListaActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista);
        }
    }
}


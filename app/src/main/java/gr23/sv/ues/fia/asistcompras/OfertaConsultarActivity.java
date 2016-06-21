package gr23.sv.ues.fia.asistcompras;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gr23.sv.ues.fia.asistcompras.Entidades.Oferta;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagen;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagenAdapter;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class OfertaConsultarActivity extends AppCompatActivity implements SensorEventListener {

    private ListView lista;
    List<Oferta> ofertaList;
    ControlDB helper;
    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    SensorManager mSensorManager;
    Sensor mSensorAcc;
    private long mShakeTime = 0;

    //------- var menu lateral -------------
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    //--------------------fin var menu lateral------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_consultar);
        helper = new ControlDB(this);
        lista = (ListView) findViewById(R.id.ListView_listado);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ArrayList<listaImagen> datos = new ArrayList<listaImagen>();
        helper.abrir();
        ofertaList=helper.consultarOferta();
        helper.cerrar();
        for(Oferta oferta : ofertaList){
            datos.add(new listaImagen(oferta.isFoto(),oferta.getNombre(),oferta.getDescripcion()));
        }


        // datos.add(new listaImagen(R.drawable.image,"Oferta de All Star","todos los estilos a mitad de precio, por liquidacion de la tienda  "));
        //datos.add(new listaImagen(R.drawable.wii,"grandes descuentos para el Wii", "50% de descuento al comprar una consola al contado, INCREIBLEMENTE BARATUUUUS "));
        //datos.add(new listaImagen(R.drawable.guitarra,"Guitarras Electricas","¿QUIERES INICIAR TU PROPIA BANDA? Entonces debes aproveche estos super descuento en guitarras electricas en la tienda MEGADEATH SHOP "));

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
                    if (imagen_entrada != null){
                        File photo = new File(Environment.getExternalStorageDirectory()+"/Image",
                                ((listaImagen) entrada).get_idImagen());
                        imagen_entrada.setImageURI(Uri.fromFile(photo));
                    }
                    /*    File photo = new
                                File(Environment.getExternalStorageDirectory(), );
                    file = Uri.fromFile(photo);
                        imagen_entrada.setImageResource(((listaImagen) entrada).get_idImagen());
                   */
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




       //------------------------------------------------------------------------

        //------------------------------------menu lateral-------------------------------------------------------

        appbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                Intent inte = new Intent(OfertaConsultarActivity.this, NuevaOfertaActivity.class);
                                startActivity(inte);
                                //fragment = new Fragment1();
                                // fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                Intent inte2 = new Intent(OfertaConsultarActivity.this, OfertaConsultarActivity.class);
                                startActivity(inte2);
                                break;
                            case R.id.menu_opcion_1:
                                Intent inte4 = new Intent(OfertaConsultarActivity.this, LugarInsertarActivity.class);
                                startActivity(inte4);
                                break;
                            case R.id.menu_opcion_2:
                                Intent inte5 = new Intent(OfertaConsultarActivity.this, LugarConsultarActivity.class);
                                startActivity(inte5);
                                break;
                            case R.id.menu_opcion_3:
                                Intent inte6 = new Intent(OfertaConsultarActivity.this, MapsActivity.class);
                                startActivity(inte6);
                                break;
                            case R.id.menu_opcion_4:
                                Intent inte7 = new Intent(OfertaConsultarActivity.this, ListaActivity.class);
                                startActivity(inte7);
                                break;
                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


        //----------------------------------fin menu lateral---------------------------------------




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

    //-------------------------------parte de menu lateral--------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //...
        }

        return super.onOptionsItemSelected(item);
    }

//----------------------------------fin parte de menu lateral--------------------------------------------

    public static class ListaActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista);
        }
    }
}


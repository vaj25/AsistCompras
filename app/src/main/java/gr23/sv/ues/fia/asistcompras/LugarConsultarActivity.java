package gr23.sv.ues.fia.asistcompras;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gr23.sv.ues.fia.asistcompras.Entidades.Image;
import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Entidades.Social;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagen;
import gr23.sv.ues.fia.asistcompras.Entidades.listaImagenAdapter;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;


public class LugarConsultarActivity extends ActionBarActivity implements SensorEventListener {

    private List<Lugar> listLugar;
    private ListView listView;
    private ControlDB helper;
    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    SensorManager mSensorManager;
    Sensor mSensorAcc;
    private long mShakeTime = 0;
    private ShareActionProvider myShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_consultar);
        helper = new ControlDB(this);
        listView = (ListView) findViewById(R.id.listLugar);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        helper.abrir();
        //helper.llenarBD();
        listLugar = helper.consultarAllLugar();
        helper.cerrar();

        ArrayList<listaImagen> datos = new ArrayList<listaImagen>();

        for(Lugar lugar : listLugar){
            datos.add(new listaImagen(lugar.getImage(),lugar.getNombre(),lugar.getDescripcion()));
        }

        listView.setAdapter(new listaImagenAdapter(this, R.layout.oferta, datos) {
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
                }
            }
        });
        /*
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listLugar);
        adaptador.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
        */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compartir, menu);
        MenuItem shareOpt = menu.findItem(R.id.menu_item_share);
        //Inicializamos nuestro ShareActionProvider
        myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareOpt);
        //Creamos nuestro sharer Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, "mensaje de prueba");
        myShareActionProvider.setShareIntent(i);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Iterator iterador = listLugar.listIterator();
        int count = 0;
        double latitud=0.0;
        double longitud=0.0;

        switch (item.getItemId()) {
            case R.id.delete:
                while (iterador.hasNext()) {
                    Lugar lugar = (Lugar) iterador.next();
                    if (count == info.position) {
                        latitud = lugar.getLatitud();
                        longitud = lugar.getLongitud();
                    }
                    count++;
                }
                int eliminadas;
                Lugar lug = new Lugar();
                lug.setLatitud(latitud);
                lug.setLongitud(longitud);
                helper.abrir();
                eliminadas = helper.eliminar(lug);
                helper.cerrar();
                if (eliminadas>0){
                    Toast.makeText(this,"Se elimino " + eliminadas +" Lugar", Toast.LENGTH_SHORT).show();
                    this.returnHome();
                }
                else{
                    Toast.makeText(this,"Error al eliminar", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.compartir:
            case R.id.blue:
            case R.id.wifi:
            case R.id.social:
                Social social=new Social();
                social.share(this,"prueba","prueba");
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),LugarConsultarActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
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
                Intent inte = new Intent(LugarConsultarActivity.this, LugarInsertarActivity.class);
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

}

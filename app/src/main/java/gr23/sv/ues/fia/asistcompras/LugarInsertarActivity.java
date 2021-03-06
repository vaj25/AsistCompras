package gr23.sv.ues.fia.asistcompras;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import gr23.sv.ues.fia.asistcompras.Entidades.Image;
import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class LugarInsertarActivity extends AppCompatActivity {

    private TextInputLayout nombre;
    private TextInputLayout descripcion;
    public LocationManager locationManager;
    private ImageView image;
    ListView lvn;
    ListView lvd;
    ArrayAdapter<String> adaptador;
    static final int checkNombre = 1111;
    static final int checkDescripcion = 1112;
    final int FOTOGRAFIA = 654;
    Uri file;
    private double latitud;
    private double longitud;
    private String fotoFile;
    private ControlDB helper;

    //------- var menu lateral -------------
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    //--------------------fin var menu lateral------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_insertar);

        nombre = (TextInputLayout) findViewById(R.id.til_nombre);

        descripcion = (TextInputLayout) findViewById(R.id.til_descripcion);
        image = (ImageView) findViewById(R.id.mainimage);
        lvn = (ListView) findViewById(R.id.lVoiceNombre);
        lvd = (ListView) findViewById(R.id.lVoiceDescripcion);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        helper = new ControlDB(this);

        Button botonAceptar = (Button) findViewById(R.id.btnAceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });

        Button botonCancelar = (Button) findViewById(R.id.btnCancelar);
        botonCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent inte = new Intent(LugarInsertarActivity.this, LugarConsultarActivity.class);
                startActivity(inte);
            }
        });

        Button btTomarFoto = (Button) findViewById(R.id.bttomarfoto);
        btTomarFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tomarFoto();
            }
        });

        Button btnTsNombre = (Button) findViewById(R.id.bts_nombre);
        btnTsNombre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Si entramos a dar clic en el boton
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent. EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent. LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent. EXTRA_PROMPT, "Hable ahora ");
                startActivityForResult(i, checkNombre);
            }
        });

        Button btnTsDescripcion = (Button) findViewById(R.id.bts_descripcion);
        btnTsDescripcion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Si entramos a dar clic en el boton
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent. EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent. LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent. EXTRA_PROMPT, "Hable ahora ");
                startActivityForResult(i, checkDescripcion);
            }
        });

        lvn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) adaptador.getItem(position);
                nombre.getEditText().setText(text);
                adaptador.clear();
            }
        });

        lvd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) adaptador.getItem(position);
                descripcion.getEditText().setText(text);
                adaptador.clear();
            }
        });

        if(savedInstanceState != null){
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState
                        .getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }


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
                                    Intent inte = new Intent(LugarInsertarActivity.this, NuevaOfertaActivity.class);
                                    startActivity(inte);
                                    //fragment = new Fragment1();
                                    // fragmentTransaction = true;
                                    break;
                                case R.id.menu_seccion_2:
                                    Intent inte2 = new Intent(LugarInsertarActivity.this, OfertaConsultarActivity.class);
                                    startActivity(inte2);
                                    break;
                                case R.id.menu_opcion_1:
                                    Intent inte4 = new Intent(LugarInsertarActivity.this, LugarInsertarActivity.class);
                                    startActivity(inte4);
                                    break;
                                case R.id.menu_opcion_2:
                                    Intent inte5 = new Intent(LugarInsertarActivity.this, LugarConsultarActivity.class);
                                    startActivity(inte5);
                                    break;
                                case R.id.menu_opcion_3:
                                    Intent inte6 = new Intent(LugarInsertarActivity.this, MapsActivity.class);
                                    startActivity(inte6);
                                    break;
                                case R.id.menu_opcion_4:
                                    Intent inte7 = new Intent(LugarInsertarActivity.this, ListaActivity.class);
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


    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);
    }

    private void tomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /*
         * Crea una imagen en la carpeta Image
         */
        fotoFile = String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg";
        String file_path = Environment.getExternalStorageDirectory()+"/Image";

        //verifica la existencia de la carpeta
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File photo = new File(Environment.getExternalStorageDirectory()+"/Image" ,fotoFile);
        file = Uri.fromFile(photo);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent, FOTOGRAFIA);
    }
    ////////////////////////////////////////////////////PRUEBA DEL MENU/////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_prueba_oferta, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nuevaOferta:
                Intent inte = new Intent(LugarInsertarActivity.this, NuevaOfertaActivity.class);
                startActivity(inte);
                return true;
            case R.id.consultarOferta:
                Intent inte2= new Intent(LugarInsertarActivity.this,OfertaConsultarActivity.class);
                startActivity(inte2);
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onActivityResult( int RequestCode, int ResultCode, Intent intent) {
        //camara
        if (RequestCode == FOTOGRAFIA){
            if(ResultCode == RESULT_OK){
                image.setImageURI(file);
            }
            else{
                Toast.makeText(getApplicationContext(), "Fotografia No tomada", Toast.LENGTH_SHORT).show();
            }
        }

        /*Speech
        * la varable chech__ es un identificador
        * para saber que tipo de accion hacer
        */
        if (RequestCode==checkNombre ){
            if(ResultCode==RESULT_OK){
                ArrayList<String> results =
                        intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                adaptador = new
                        ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results);
                lvn.setAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "Audio No tomado", Toast.LENGTH_SHORT).show();
            }

        } else if(RequestCode==checkDescripcion){
            if(ResultCode==RESULT_OK){
            ArrayList<String> results =
                    intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            adaptador = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results);
            lvd.setAdapter(adaptador);
            }else {
                Toast.makeText(getApplicationContext(), "Audio No tomado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ingresar() {
        String regInsertados;
        String nombreLugar = nombre.getEditText().getText().toString();
        String descripcionLugar = descripcion.getEditText().getText().toString();

        Lugar lugar = new Lugar(latitud, longitud, nombreLugar, descripcionLugar, fotoFile);
        helper.abrir();
        regInsertados = helper.insertar(lugar);
        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitud = location.getLongitude();
            latitud = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

}

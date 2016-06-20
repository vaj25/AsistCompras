package gr23.sv.ues.fia.asistcompras;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Entidades.Oferta;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class NuevaOfertaActivity extends AppCompatActivity {
    private TextInputLayout nombreOferta;
    private TextInputLayout descripcionOferta;
    public LocationManager locationManager;
    private ImageView imageOferta;
    ListView lvn;
    ListView lvd;
    ArrayAdapter<String> adaptador;
    static final int checkNombre = 1111;
    static final int checkDescripcion = 1112;
    final int FOTOGRAFIA = 654;
    Uri file;
   // private double latitud;
    //private double longitud;
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
        setContentView(R.layout.activity_nueva_oferta);
//        getSupportActionBar().setTitle("Nueva Oferta");

        nombreOferta = (TextInputLayout) findViewById(R.id.til_nombre_oferta);
        descripcionOferta = (TextInputLayout) findViewById(R.id.til_descripcion_oferta);
        imageOferta = (ImageView) findViewById(R.id.imagenOferta);
        lvn = (ListView) findViewById(R.id.lVoiceNombreOferta);
        lvd = (ListView) findViewById(R.id.lVoiceDescripcionOferta);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        helper = new ControlDB(this);

        Button botonAceptar = (Button) findViewById(R.id.btnAceptarOferta);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });

        Button botonCancelar = (Button) findViewById(R.id.btnCancelarOferta);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(NuevaOfertaActivity.this, OfertaConsultarActivity.class);
                startActivity(inte);
            }
        });

        Button btTomarFoto = (Button) findViewById(R.id.bttomarfotoOferta);
        btTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });

        Button btnTsNombre = (Button) findViewById(R.id.bts_nombre_oferta);
        btnTsNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si entramos a dar clic en el boton
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora ");
                startActivityForResult(i, checkNombre);
            }
        });

        Button btnTsDescripcion = (Button) findViewById(R.id.bts_descripcion_oferta);
        btnTsDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si entramos a dar clic en el boton
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora ");
                startActivityForResult(i, checkDescripcion);
            }
        });
        lvn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) adaptador.getItem(position);
                nombreOferta.getEditText().setText(text);
                adaptador.clear();
            }
        });

        lvd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) adaptador.getItem(position);
                descripcionOferta.getEditText().setText(text);
                adaptador.clear();
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                imageOferta.setImageURI(Uri.parse(savedInstanceState
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
                                Intent inte = new Intent(NuevaOfertaActivity.this, NuevaOfertaActivity.class);
                                startActivity(inte);
                                //fragment = new Fragment1();
                                // fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                Intent inte2 = new Intent(NuevaOfertaActivity.this, OfertaConsultarActivity.class);
                                startActivity(inte2);
                                break;
                            case R.id.menu_opcion_1:
                                Intent inte4 = new Intent(NuevaOfertaActivity.this, LugarInsertarActivity.class);
                                startActivity(inte4);
                                break;
                            case R.id.menu_opcion_2:
                                Intent inte5 = new Intent(NuevaOfertaActivity.this, LugarConsultarActivity.class);
                                startActivity(inte5);
                                break;
                            case R.id.menu_opcion_3:
                                Intent inte6 = new Intent(NuevaOfertaActivity.this, MapsActivity.class);
                                startActivity(inte6);
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
    }/////////////////fin onCreate

    public void onSaveInstanceState(Bundle bundle) {
        if (file != null) {
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

        /*
        * codigo para mostrar una imagen en el imageView
        * File photo = new  File(Environment.getExternalStorageDirectory()+"/Imagen" ,"imagen.jpg");
        * file = Uri.fromFile(photo);
        * image.setImageURI(file);
        * */
    }

    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        //camara
        if (RequestCode == FOTOGRAFIA) {
            if (ResultCode == RESULT_OK) {
                imageOferta.setImageURI(file);
            } else {
                Toast.makeText(getApplicationContext(), "Fotografia No tomada", Toast.LENGTH_SHORT).show();
            }
        }

        /*Speech
        * la varable chech__ es un identificador
        * para saber que tipo de accion hacer
        */
        if (RequestCode == checkNombre) {
            if (ResultCode == RESULT_OK) {
                ArrayList<String> results =
                        intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                adaptador = new
                        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
                lvn.setAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "Audio No tomado", Toast.LENGTH_SHORT).show();
            }

        } else if (RequestCode == checkDescripcion) {
            if (ResultCode == RESULT_OK) {
                ArrayList<String> results =
                        intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                adaptador = new
                        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
                lvd.setAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "Audio No tomado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ingresar() {
        String regInsertados;
        int id=helper.contarRegistros("oferta","idoferta");
        String nombreOf = nombreOferta.getEditText().getText().toString();
        String descripcionOf = descripcionOferta.getEditText().getText().toString();

        Oferta oferta = new Oferta(id, nombreOf, descripcionOf, fotoFile, "");
        helper.abrir();
        regInsertados = helper.insertar(oferta);
        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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

}

    /*LocationListener locationListener = new LocationListener() {
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //
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
}*/

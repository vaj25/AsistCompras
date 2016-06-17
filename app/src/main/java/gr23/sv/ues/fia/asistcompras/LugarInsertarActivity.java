package gr23.sv.ues.fia.asistcompras;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class LugarInsertarActivity extends Activity {

    private TextInputLayout nombre;
    private TextInputLayout descripcion;
    public LocationManager locationManager;
    private ImageView image;
    final int FOTOGRAFIA = 654;
    Uri file;
    private double latitud;
    private double longitud;
    private String fotoFile;
    private ControlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_insertar);

        nombre = (TextInputLayout) findViewById(R.id.til_nombre);
        descripcion = (TextInputLayout) findViewById(R.id.til_descripcion);
        image = (ImageView) findViewById(R.id.mainimage);
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

        if(savedInstanceState != null){
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState
                        .getString("Foto")));
                file = Uri. parse(savedInstanceState.getString("Foto"));
            }
        }
    }

        public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);
    }

    private void tomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fotoFile = String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg";
        File photo = new
                File(Environment.getExternalStorageDirectory(),fotoFile);
        file = Uri.fromFile(photo);
        intent.putExtra(MediaStore. EXTRA_OUTPUT, file);
        startActivityForResult(intent, FOTOGRAFIA);
    }

    @Override
    public void onActivityResult( int RequestCode, int ResultCode, Intent intent) {
        if (RequestCode == FOTOGRAFIA){
            if(ResultCode == RESULT_OK){
                image.setImageURI(file);
            }
            else{
                Toast.makeText(getApplicationContext(), "fotografia No tomada", Toast.LENGTH_SHORT).show();
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

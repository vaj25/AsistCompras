package gr23.sv.ues.fia.asistcompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NuevaOfertaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_oferta);
        getSupportActionBar().setTitle("Nueva Oferta");
    }
}

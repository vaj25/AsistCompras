package gr23.sv.ues.fia.asistcompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OfertaConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_consultar);
    }

    public static class ListaActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista);
        }
    }
}

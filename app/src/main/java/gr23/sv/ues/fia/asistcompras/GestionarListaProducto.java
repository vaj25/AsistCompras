package gr23.sv.ues.fia.asistcompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gr23.sv.ues.fia.asistcompras.Entidades.Lista;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class GestionarListaProducto extends AppCompatActivity {
    ControlDB helper;
    //Button botonEliminar;
    Button botonActualizar;
    EditText nombreLP;
    TextView t;
    int va=0;
    String para="";
    String param2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new ControlDB(this);
        setContentView(R.layout.activity_gestionar_lista_producto);
        //botonEliminar=(Button)findViewById(R.id.eliminarListaProducto);
        botonActualizar=(Button)findViewById(R.id.BtnEditarListaProducto);;
        nombreLP=(EditText)findViewById(R.id.editarListaProducto);
        t=(TextView)findViewById(R.id.te);
        Bundle bundle = getIntent().getExtras();
        para = bundle.getString("activityActualP");
        param2=bundle.getString("ActivityLista");
        helper.abrir();
        va= helper.extraerListaProductoNombre(para);
        helper.cerrar();
        //nombreLP.setText(String.valueOf(va));
        t.setText(para);
      }

    public void actualizarListaProduct(View v){
    String mensaje;
        if(nombreLP.getText().toString().equals("")){
            mensaje="Inserte un nombre";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
        else {
            String nombre = nombreLP.getText().toString();

            Lista lis = new Lista();
            lis.setNombreLista(nombre);
            helper.abrir();
            mensaje = helper.actualizarListaProducto(va,nombre);
            helper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

            nombreLP.setText("");
            Bundle bundle = getIntent().getExtras();
            String activity = bundle.getString("activityActualP");
            helper.abrir();
            va= helper.extraerListaProductoNombre(activity);
            mensaje = helper.actualizarListaProducto(va,nombre);
            helper.cerrar();



            Intent atras = new Intent(this, ListaProductoActivity.class);
            atras.putExtra("activityActual", param2);
            startActivity(atras);
        }

    }

    public void eliminarListaprodut(View v){
        String regEliminadas;

        helper.abrir();
        regEliminadas=helper.eliminarListaProducto(va);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

        Intent atras = new Intent(this, ListaProductoActivity.class);
        atras.putExtra("activityActual", param2);
        startActivity(atras);

    }



}

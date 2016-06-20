package gr23.sv.ues.fia.asistcompras;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gr23.sv.ues.fia.asistcompras.Entidades.Lista;
import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class ListaActivity extends AppCompatActivity {
    private ListView li;
    private List<Lista> lista;
    int activityac;
    int posicion;
    String nombre;
    Lista list;
    ControlDB helper;
    EditText editAgregar;
    TextView TextList;
    int total=0;
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        helper = new ControlDB(this);

        TextList=(TextView)findViewById(R.id.textView2);
        editAgregar = (EditText) findViewById(R.id.TextLista);
        lista = new ArrayList<Lista>();
        li = (ListView) findViewById(R.id.lista);
        helper.abrir();
        lista = helper.consultarLista();
        helper.cerrar();
        li.setAdapter(new AdapterLista(this, lista));
        li=(ListView)findViewById(R.id.lista);
        helper.abrir();
        total=helper.obtenerTotalIdLista();

        helper.cerrar();

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
               String nombre=(String)li.getItemAtPosition(position).toString();



                Intent modify_intent = new Intent(getApplicationContext(), ListaProductoActivity.class);
                modify_intent.putExtra("activityActual", nombre);

                startActivity(modify_intent);
            }
        });



        }
/*
    public void onItemClick(AdapterView<?> customerAdapter, View footer, int selectedInt, long selectedLong) {
        nombre=(String)li.getItemAtPosition(selectedInt).toString();
        //posicion=helper.extraerListaNombre(nombre);
        //Toast.makeText(ListaActivity.this,String.valueOf(posicion), Toast.LENGTH_SHORT).show();

    }
    public void lanzar(View view) {
       // li.getSelectedItem().toString();

        int v=li.getSelectedItemPosition();
        Iterator iter=lista.listIterator();
        int count=0;
        String selec="";
        while(iter.hasNext()){
            Lista l=(Lista)iter.next();
            if(count==v){

                selec=l.getNombreLista();
            }
            count++;
        }


//        nombre=(String)li.getItemAtPosition(li.getSelectedItemPosition()).toString();
        Intent i = new Intent(this, ListaProductoActivity.class );
        i.putExtra("activityActual",selec);
        startActivity(i);


 public void lanzar(View view) {

 Intent i = new Intent(this, ListaProductoActivity.class );
        i.putExtra("activityActual",selec);
        startActivity(i);


    }

  */
    public void insertarLi(View v){
        String mensaje;

        if(editAgregar.getText().toString().equals("")){
            mensaje="Inserte un nombre";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
        else {


            String nombre = editAgregar.getText().toString();

            Lista lis = new Lista();
            lis.setNombreLista(nombre);
            helper.abrir();
            mensaje = helper.insertarLista(lis);
            helper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

            lista=new ArrayList<Lista>();
            li=(ListView)findViewById(R.id.lista);
            helper.abrir();
            lista=helper.consultarLista();
            helper.cerrar();
            li.setAdapter(new AdapterLista(this, lista));

            editAgregar.setText("");
        }

    }



}
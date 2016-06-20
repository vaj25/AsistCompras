package gr23.sv.ues.fia.asistcompras;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import gr23.sv.ues.fia.asistcompras.Entidades.Lista;
import gr23.sv.ues.fia.asistcompras.Entidades.ListaProducto;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class ListaProductoActivity extends AppCompatActivity {
    List<ListaProducto> listaPro;
    int idActual=0;
    private ListView listView;
    TextView text;
    String activityActual="";
    ListaProducto listPro;
    ControlDB helper;
    EditText editAgregarLP;


    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);
        helper = new ControlDB(this);

        editAgregarLP = (EditText) findViewById(R.id.editLiPro);
        text=(TextView) findViewById(R.id.texto);


        Bundle bundle = getIntent().getExtras();
        activityActual = bundle.getString("activityActual");
        helper.abrir();
        idActual=helper.extraerListaNombre(activityActual);
        //String ac=String.valueOf(activityActual);
        //text.setText(String.valueOf(idActual));
        text.setText(activityActual);

        helper.cerrar();



        listaPro=new ArrayList<ListaProducto>();
        listView = (ListView) findViewById(R.id.list);
        helper.abrir();
        listaPro = helper.consultarListaProductoId(idActual);

        helper.cerrar();
        listView.setAdapter(new AdapterListaProducto(this, listaPro));

        listView=(ListView)findViewById(R.id.list);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String nom=(String)listView.getItemAtPosition(position).toString();


                Intent modify_intent = new Intent(getApplicationContext(), GestionarListaProducto.class);
                modify_intent.putExtra("activityActualP", nom);
                modify_intent.putExtra("ActivityLista",activityActual);

                startActivity(modify_intent);
            }
        });
    }


    public void insertarListaPro(View v){
        String mensaje;

        if(editAgregarLP.getText().toString().equals("")){
            mensaje="Inserte datos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
        else {

            String nombre = editAgregarLP.getText().toString();
            ListaProducto lis = new ListaProducto();
            lis.setDescripcion(nombre);
            lis.setIdLista(idActual);
            helper.abrir();
            mensaje = helper.insertarListaProducto(lis);
            helper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();



            listaPro=new ArrayList<ListaProducto>();
            listView=(ListView)findViewById(R.id.list);
            helper.abrir();
            listaPro=helper.consultarListaProductoId(idActual);
            helper.cerrar();
            listView.setAdapter(new AdapterListaProducto(this, listaPro));

            listView=(ListView)findViewById(R.id.list);

            editAgregarLP.setText("");
        }

    }



/*

    public void eliminarListaProducto(View v){




        ListaProducto alumno=new ListaProducto();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String regEliminadas;
                int ida=listaPro.get(position).getIdProductoLista();
                helper.abrir();
                helper.eliminarListaProducto(ida);
                helper.cerrar();
                //Toast.makeText(ListaProductoActivity.this,listaPro.get(position).getIdProductoLista(), Toast.LENGTH_SHORT).show();
                helper.abrir();
                regEliminadas=helper.eliminarListaProducto(idActual);
                helper.cerrar();
            }
        });



        listaPro=new ArrayList<ListaProducto>();
        listView=(ListView)findViewById(R.id.list);


        helper.abrir();
        listaPro=helper.consultarListaProductoId(idActual);
        helper.cerrar();


        listView.setAdapter(new AdapterListaProducto(this, listaPro));

        listView=(ListView)findViewById(R.id.list);
        //Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

*/

}

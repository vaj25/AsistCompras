package gr23.sv.ues.fia.asistcompras;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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


    //------- var menu lateral -------------
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    //--------------------fin var menu lateral------------------------


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
                                Intent inte = new Intent(ListaProductoActivity.this, NuevaOfertaActivity.class);
                                startActivity(inte);
                                //fragment = new Fragment1();
                                // fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                Intent inte2 = new Intent(ListaProductoActivity.this, OfertaConsultarActivity.class);
                                startActivity(inte2);
                                break;
                            case R.id.menu_opcion_1:
                                Intent inte4 = new Intent(ListaProductoActivity.this, LugarInsertarActivity.class);
                                startActivity(inte4);
                                break;
                            case R.id.menu_opcion_2:
                                Intent inte5 = new Intent(ListaProductoActivity.this, LugarConsultarActivity.class);
                                startActivity(inte5);
                                break;
                            case R.id.menu_opcion_3:
                                Intent inte6 = new Intent(ListaProductoActivity.this, MapsActivity.class);
                                startActivity(inte6);
                                break;
                            case R.id.menu_opcion_4:
                                Intent inte7 = new Intent(ListaProductoActivity.this, ListaActivity.class);
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

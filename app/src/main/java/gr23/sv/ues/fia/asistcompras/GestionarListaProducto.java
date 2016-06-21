package gr23.sv.ues.fia.asistcompras;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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


    //------- var menu lateral -------------
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    //--------------------fin var menu lateral------------------------

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
                                Intent inte = new Intent(GestionarListaProducto.this, NuevaOfertaActivity.class);
                                startActivity(inte);
                                //fragment = new Fragment1();
                                // fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                Intent inte2 = new Intent(GestionarListaProducto.this, OfertaConsultarActivity.class);
                                startActivity(inte2);
                                break;
                            case R.id.menu_opcion_1:
                                Intent inte4 = new Intent(GestionarListaProducto.this, LugarInsertarActivity.class);
                                startActivity(inte4);
                                break;
                            case R.id.menu_opcion_2:
                                Intent inte5 = new Intent(GestionarListaProducto.this, LugarConsultarActivity.class);
                                startActivity(inte5);
                                break;
                            case R.id.menu_opcion_3:
                                Intent inte6 = new Intent(GestionarListaProducto.this, MapsActivity.class);
                                startActivity(inte6);
                                break;
                            case R.id.menu_opcion_4:
                                Intent inte7 = new Intent(GestionarListaProducto.this, ListaActivity.class);
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

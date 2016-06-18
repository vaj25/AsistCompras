package gr23.sv.ues.fia.asistcompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;
import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;

public class LugarConsultarActivity extends Activity {

    private List<Lugar> listLugar;
    private ListView listView;

    private ControlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_consultar);
        helper = new ControlDB(this);
        listView = (ListView) findViewById(R.id.listLugar);
        helper.abrir();
        helper.llenarBD();
        listLugar = helper.consultarAllLugar();
        helper.cerrar();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listLugar);
        adaptador.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Iterator iterador = listLugar.listIterator();
        int count = 0;
        double latitud=0.0;
        double longitud=0.0;

        switch (item.getItemId()) {
            case R.id.delete:
                while (iterador.hasNext()) {
                    Lugar lugar = (Lugar) iterador.next();
                    if (count == info.position) {
                        latitud = lugar.getLatitud();
                        longitud = lugar.getLongitud();
                    }
                    count++;
                }
                int eliminadas;
                Lugar lug = new Lugar();
                lug.setLatitud(latitud);
                lug.setLongitud(longitud);
                helper.abrir();
                eliminadas = helper.eliminar(lug);
                helper.cerrar();
                if (eliminadas>0){
                    Toast.makeText(this,"Se elimino " + eliminadas +" Lugar", Toast.LENGTH_SHORT).show();
                    this.returnHome();
                }
                else{
                    Toast.makeText(this,"Error al eliminar", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),LugarConsultarActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}

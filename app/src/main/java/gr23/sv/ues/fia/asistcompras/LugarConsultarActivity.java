package gr23.sv.ues.fia.asistcompras;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        listLugar = helper.consultarAllLugar();
        helper.cerrar();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listLugar);
        adaptador.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(adaptador);
    }
}

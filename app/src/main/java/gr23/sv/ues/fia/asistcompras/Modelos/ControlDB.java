package gr23.sv.ues.fia.asistcompras.Modelos;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;

/**
 * Created by FAMILY on 05/06/2016.
 */

public class ControlDB {

    private final String[] camposLugar = {"latitud", "longitud", "nombre", "descripcion", "imagen"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDB (Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase(); //?Abrir BD con permisos R/W
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    /*
    * metodos CRUD
    * insertar Lugar
    */
    public String insertar(Lugar lugar){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("latitud", lugar.getLatitud());
        lgr.put("longitud", lugar.getLongitud());
        lgr.put("nombre", lugar.getNombre());
        lgr.put("descripcion", lugar.getDescripcion());
        lgr.put("imagen", lugar.getImage());
        contador = db.insert("lugar", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    /*
    * consultar lugar
    */
    public List consultarAllLugar(){
        abrir();
        List<Lugar> lista= new ArrayList<>();
        Cursor cur = db.rawQuery("select * from lugar",null );
        while(cur.moveToNext()){
            Lugar lugar = new Lugar();
            lugar.setNombre(cur.getString(2));
            lugar.setDescripcion(cur.getString(3));
            lista.add(lugar);
        }
        cur.close();
        db.close();
        return lista;
    }

    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {

        return false;
    }
}

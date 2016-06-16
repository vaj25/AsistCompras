package gr23.sv.ues.fia.asistcompras.Modelos;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import gr23.sv.ues.fia.asistcompras.Entidades.Articulo;
import gr23.sv.ues.fia.asistcompras.Entidades.DetalleArticulo;
import gr23.sv.ues.fia.asistcompras.Entidades.Lista;
import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Entidades.Oferta;
import gr23.sv.ues.fia.asistcompras.Entidades.UnidadMedida;

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

    public String insertar(Articulo articulo){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("idarticulo", articulo.getIdArticulo());
        lgr.put("descripcionarticulo", articulo.getDescripcionArticulo());
        lgr.put("nombrearticulo", articulo.getNombreArticulo());
        contador = db.insert("articulo", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }
   /* public List consultarAllArticulo(){
        abrir();
        List<Articulo> lista= new ArrayList<>();
        Cursor cur = db.rawQuery("select * from articulo",null );
        while(cur.moveToNext()){
            Articulo articulo = new Articulo();
            articulo.setNombreArticulo(cur.getString(1));
            articulo.setDescripcionArticulo(cur.getString(2));
            lista.add(articulo);
        }
        cur.close();
        db.close();
        return lista;
    }*/
    public String insertar(DetalleArticulo detart){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("iddetallearticulo", detart.getIdDetalleArticulo());
        lgr.put("cantidad", detart.getCantidad());
        lgr.put("precio", detart.getPrecio());
        lgr.put("idoferta", detart.getIdOferta());
        lgr.put("idarticulo", detart.getIdArticulo());
        lgr.put("idlista", detart.getIdLista());
        lgr.put("idunidadmedida", detart.getIdUnidadMedida());
        lgr.put("latitud", detart.getLatitud());
        lgr.put("longitud", detart.getLongitud());
        contador = db.insert("detallearticulo", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(Oferta oferta){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("idoferta", oferta.getIdOferta());
        lgr.put("foto", oferta.isFoto());
        lgr.put("video", oferta.isVideo());
        contador = db.insert("oferta", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(Lista lista){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("idlista", lista.getIdLista());
        lgr.put("nombrelista", lista.getNombreLista());
        lgr.put("descripcionlista", lista.getDescripcionLista());
        contador = db.insert("lista", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(UnidadMedida um){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("idunidadmedida", um.getIdUnidadMedida());
        lgr.put("nombreum", um.getNombreUM());
        lgr.put("descripcionum", um.getDescripcionUM());
        contador = db.insert("unidadmedida", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }
    public List consultarAllDetalleArticulo(){
        abrir();
        List<ArrayList> lista= new ArrayList<>();
        Cursor cur = db.rawQuery("select iddetallearticulo,nombrearticulo,descripcionarticulo,foto,video,precio from detallearticulo as d inner join articulo as a on d.idarticulo=a.idarticulo inner join oferta as o on d.idoferta=o.idoferta",null );
        while(cur.moveToNext()){
            ArrayList array = new ArrayList();
            array.add(cur.getString(0));
            array.add(cur.getString(1));
            lista.add(array);
        }
        cur.close();
        db.close();
        return lista;
    }

    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {

        return false;
    }
}

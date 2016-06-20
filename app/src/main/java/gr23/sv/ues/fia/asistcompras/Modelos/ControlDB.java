package gr23.sv.ues.fia.asistcompras.Modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import gr23.sv.ues.fia.asistcompras.Entidades.Articulo;
import gr23.sv.ues.fia.asistcompras.Entidades.ListaProducto;
import gr23.sv.ues.fia.asistcompras.Entidades.detalleArticulo;
import gr23.sv.ues.fia.asistcompras.Entidades.Lista;
import gr23.sv.ues.fia.asistcompras.Entidades.Lugar;
import gr23.sv.ues.fia.asistcompras.Entidades.Oferta;
//import gr23.sv.ues.fia.asistcompras.Entidades.UnidadMedida;

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
            lugar.setLatitud(cur.getDouble(0));
            lugar.setLongitud(cur.getDouble(1));
            lugar.setNombre(cur.getString(2));
            lugar.setDescripcion(cur.getString(3));
            lugar.setImage(cur.getString(4));
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

    public String insertar(detalleArticulo detart){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("iddetallearticulo", detart.getIdDetalleArticulo());
        lgr.put("cantidad", detart.getCantidad());
        lgr.put("precio", detart.getPrecio());
        lgr.put("idoferta", detart.getIdOferta());
        lgr.put("idarticulo", detart.getIdArticulo());
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
        lgr.put("nombre",oferta.getNombre());
        lgr.put("descripcion",oferta.getDescripcion());
        lgr.put("foto", oferta.isFoto());
        lgr.put("video", oferta.isVideo());
        lgr.put("nombrelugar",oferta.getNombrelugar());

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

    public List consultarOferta(){
        abrir();
       List<Oferta> ofertaList=new ArrayList<>();
        Cursor cur = db.rawQuery("select * from oferta",null );
        while(cur.moveToNext()){
            Oferta oferta=new Oferta();
            oferta.setIdOferta(cur.getInt(0));
            oferta.setNombre(cur.getString(1));
            oferta.setDescripcion(cur.getString(2));
            oferta.setFoto(cur.getString(3));
            oferta.setVideo(cur.getString(4));
            ofertaList.add(oferta);
        }
        cur.close();
        db.close();
        return ofertaList;
    }
    public String insertarLista(Lista lista){
        String regInsertados="Registro Insertado Nº= ";
        long contador = 0;
        ContentValues lgr = new ContentValues();

        lgr.put("nombrelista", lista.getNombreLista());

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

    public String eliminarLista(Lista lis){
        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("lista", "idlista='"+lis.getIdLista()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminarListaProducto(int lisPro){
        String regAfectados="Eliminado";
        int contador=0;

        if (verificarIntegridad(lisPro,2)) {
            contador+=db.delete("listaproducto", "idlistaproducto='"+lisPro+"'", null);
        }
        regAfectados="Eliminado";
        return regAfectados;
    }

    public List consultarLista(){
        abrir();
        List<Lista> li=new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT * FROM lista ORDER BY idlista DESC",null);
        while(cursor.moveToNext()){
            Lista list = new Lista();
            list.setIdLista(cursor.getInt(0));
            list.setNombreLista(cursor.getString(1));


            li.add(list);
        }
        cursor.close();
        db.close();
        return li;
    }

    public List consultarListaProductoId(int id){
        abrir();
        List<ListaProducto> ac=new ArrayList<>();
        String[] nom={String.valueOf(id)};
        String[] campos={"descripcion"};
        Cursor cursor=db.query("listaproducto", campos, "idlista = ?", nom, null, null, null);
        while(cursor.moveToNext()){
            ListaProducto l=new ListaProducto();
            l.setDescripcion(cursor.getString(0));

            ac.add(l);

        }
        cursor.close();
        db.close();
        return ac;

    }

    public int extraerListaNombre(String nombreL){
        abrir();
        int ac=0;
        String[] nom={nombreL};
        String[] campos={"idlista"};
        Cursor cursor=db.query("lista", campos, "nombrelista = ?", nom, null, null, null);
        while(cursor.moveToNext()){
            ac=cursor.getInt(0);

        }
        cursor.close();
        db.close();
        return ac;

    }
    public int extraerListaProductoNombre(String nombreL){
        abrir();
        int ac=0;
        String[] nom={nombreL};
        String[] campos={"idlistaproducto"};
        Cursor cursor=db.query("listaproducto", campos, "descripcion = ?", nom, null, null, null);
        while(cursor.moveToNext()){
            ac=cursor.getInt(0);

        }
        cursor.close();
        db.close();
        return ac;

    }


    public List consultarListaProducto(){
        abrir();
        List<ListaProducto> li=new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT * FROM listaproducto ORDER BY idlistaproducto DESC",null);
        while(cursor.moveToNext()){
            ListaProducto list = new ListaProducto();
            list.setIdProductoLista(cursor.getInt(0));
            list.setIdLista(cursor.getInt(1));
            list.setDescripcion(cursor.getString(2));


            li.add(list);
        }
        cursor.close();
        db.close();
        return li;
    }

    public int obtenerTotalIdLista(){
        abrir();
        int tota=0;
        Cursor cursor=db.rawQuery("SELECT * FROM lista",null);
        tota=cursor.getCount();
        cursor.close();
        db.close();
        return tota;
    }

    public String actualizarListaProducto(int ac, String nuevo){
        String[] id = {String.valueOf(ac)};

        if(verificarIntegridad(ac, 2)){


            ContentValues cv = new ContentValues();
            cv.put("descripcion", nuevo);

            db.update("listaproducto", cv, "idlistaproducto = ?", id);
            return "Actualizado ";
        }else{
            return "Registro no existe";
        }}



    public String insertarListaProducto(ListaProducto listpro){
        String regInsertados="";
        long contador = 0;
        ContentValues lgr = new ContentValues();
        lgr.put("idlistaproducto", listpro.getIdProductoLista());
        lgr.put("descripcion", listpro.getDescripcion());
        lgr.put("idlista", listpro.getIdLista());

        contador = db.insert("listaproducto", null, lgr);
        if(contador == -1 || contador == 0)
        {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados="Agregado";
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
            array.add(cur.getString(2));
            array.add(cur.getString(3));
            array.add(cur.getString(4));
            lista.add(array);
        }
        cur.close();
        db.close();
        return lista;
    }
    public int eliminar(Lugar lugar){
        String[] id = {String.valueOf(lugar.getLatitud()), String.valueOf(lugar.getLongitud())};
        ContentValues cv = new ContentValues();
        cv.put("latitud", String.valueOf(0.0));
        cv.put("longitud", String.valueOf(0.0));
        int contador=0;
        String where="latitud='"+lugar.getLatitud()+"'";
        where=where+" AND longitud='"+lugar.getLongitud()+"'";
        contador+=db.delete("lugar", where, null);
        return contador;
    }

    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //Verificar si existe Lugar en DetalleArticulo (Eliminar Lugar)
                Lugar lugar = (Lugar) dato;
                String[] id1 = {String.valueOf(lugar.getLatitud()), String.valueOf(lugar.getLongitud())};
                abrir();
                Cursor cursor1 = db.query("detallearticulo", null, "latitud = ? AND longitud = ?", id1, null, null, null);
                if (cursor1.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 2:
                //Verificar que exista ListaProducto
                {
                    int LiPro = (int)dato;
                    String[] id = {String.valueOf(dato)};
                    abrir();
                    Cursor cursor = db.query("listaproducto", null, "idlistaproducto = ?", id, null, null, null);
                    if(cursor.moveToFirst()){
                        //Se encontro Alumno
                        return true;
                    }
                    return false;


                }
            default:
                return false;
        }
}
    //by Moisés Herrera
    public int contarRegistros(String tabla,String id) {
        Integer aux=0;
        String[] campos = new String[]{id};
        abrir();
        int contador=0;
        Cursor c = db.query(tabla, campos, null, null, null, null,null);
        c.moveToLast();
        aux=c.getInt(0)+1;
        return aux;
    }
    public String llenarBD(){
        final double [] VLlatitud={6596.3,785451.3,4545.6,784.3};
        final double[] VLlongitud={7845.6,4512.3,7889.2,452.3};
        final String[] VLnombre={"SuperSelectos","DespensaFAM","Metrocentro","La Bendicion"};
        final String[] VLdescripcion={"ofertas","rebajas","al dos por uno","grandes precios"};
        final String[] VLimage={"prueba1.jpg","prueba2.jpg","prueba3.jpg","prueba4.jpg"};

        final Integer[] VLiId={1,2,3,4};
        final String[] VLiNombreLista={"de compras","para la fiesta","cumpleaños","cena del jueves"};

        final Integer[] VLPId={1,2,3,4,5,6,7,8,9,10,11};
        final Integer[] VLPIdLista={1,1,1,1,2,3,3,4,4,4,2};
        final String[] VLPdesc={"piñata","pastel","pan dej caja","arroz","carne","huezo","churros","mucha fruta","oferta de rinso","azucar","una pata"};





        abrir();
        db.execSQL("DELETE FROM oferta");
        db.execSQL("DELETE FROM lista");
        db.execSQL("DELETE FROM listaproducto");


        Lugar lugar = new Lugar();
        for(int i=0;i<4;i++){
            lugar.setLatitud(VLlatitud[i]);
            lugar.setLongitud(VLlongitud[i]);
            lugar.setNombre(VLnombre[i]);
            lugar.setDescripcion(VLdescripcion[i]);
            lugar.setImage(VLimage[i]);
            insertar(lugar);
        }

        Lista lista = new Lista();
        for(int i=0;i<4;i++){
            lista.setIdLista(VLiId[i]);
            lista.setNombreLista(VLiNombreLista[i]);

            insertarLista(lista);

        }

        ListaProducto listaP = new ListaProducto();
        for(int i=0;i<11;i++){
            //listaP.setIdProductoLista(VLiId[i]);
            listaP.setIdLista(VLPIdLista[i]);
            listaP.setDescripcion(VLPdesc[i]);
            insertarListaProducto(listaP);

        }
        cerrar();
        return "Guardo Correctamente";
    }
}


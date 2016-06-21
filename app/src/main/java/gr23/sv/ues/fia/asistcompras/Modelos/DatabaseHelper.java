package gr23.sv.ues.fia.asistcompras.Modelos;

/**
 * Created by FAMILY on 05/06/2016.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BASE_DATOS = "asistComprasv20.s3db";
    private static final int VERSION = 2;
    private ControlDB helper;
    DatabaseHelper (Context context) {
        super(context,BASE_DATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE lugar (" +
                    "latitud FLOAT NOT NULL," +
                    "longitud FLOAT NOT NULL," +
                    "nombre CHAR(30) NOT NULL," +
                    "descripcion CHAR(60)," +
                    "imagen CHAR(20)," +
                    "constraint PK_LUGAR primary key (latitud, longitud));");
            db.execSQL("CREATE TABLE articulo (" +
                    "idarticulo INTEGER NOT NULL," +
                    "descripcionarticulo varchar(100)," +
                    "nombrearticulo CHAR(50)," +
                    "constraint PK_ARTICULO primary key (idarticulo));");
            db.execSQL("CREATE TABLE oferta (" +
                    "idoferta INTEGER NOT NULL," +
                    "nombre VARCHAR(40)," +
                    "descripcion VARCHAR(100)," +
                    "foto CHAR(20)," +
                    "video CHAR(20)," +
                    "nombreLugar CHAR(30)," +
                    "constraint PK_OFERTA primary key (idoferta));");
            db.execSQL("CREATE TABLE lista (" +
                    "idlista INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "nombrelista VARCHAR(25));");
            db.execSQL("CREATE TABLE listaproducto (" +
                    "idlistaproducto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "idlista INTEGER," +
                    "descripcion VARCHAR(100));");
            db.execSQL("CREATE TABLE detallearticulo (" +
                    "iddetallearticulo INTEGER NOT NULL," +
                    "cantidad INTEGER," +
                    "precio FLOAT," +
                    "idarticulo INTEGER NOT NULL," +
                    "idoferta INTEGER NOT NULL," +
                    "constraint PK_DETART primary key (iddetallearticulo));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

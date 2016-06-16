package gr23.sv.ues.fia.asistcompras.Modelos;

/**
 * Created by FAMILY on 05/06/2016.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BASE_DATOS = "asistCompras.s3db";
    private static final int VERSION = 1;
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
                    "descripcionArticulo varchar(100)," +
                    "nombreArticulo CHAR(50)," +
                    "constraint PK_ARTICULO primary key (idarticulo));");
            db.execSQL("CREATE TABLE oferta (" +
                    "idoferta INTEGER NOT NULL," +
                    "foto boolean," +
                    "video boolean," +
                    "constraint PK_OFERTA primary key (idoferta));");
            db.execSQL("CREATE TABLE lista (" +
                    "idlista INTEGER NOT NULL," +
                    "nombreLista VARCHAR(25)," +
                    "descripcionlista VARCHAR(100)," +
                    "constraint PK_LISTA primary key (idlista));");
            db.execSQL("CREATE TABLE unidadmedida (" +
                    "idunidadmedida INTEGER NOT NULL," +
                    "nombreum VARCHAR(25)," +
                    "descripcion VARCHAR(100)," +
                    "constraint PK_UM primary key (idunidadmedida);");
            db.execSQL("CREATE TABLE detallearticulo (" +
                    "iddetallearticulo INTEGER NOT NULL," +
                    "cantidad INTEGER," +
                    "precio FLOAT," +
                    "latitud FLOAT NOT NULL," +
                    "longitud FLOAT NOT NULL," +
                    "idarticulo INTEGER NOT NULL," +
                    "idoferta INTEGER NOT NULL," +
                    "idlista INTEGER NOT NULL," +
                    "idunidadmedida INTEGER NOT NULL," +
                    "constraint PK_UM primary key (iddetallearticulo);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

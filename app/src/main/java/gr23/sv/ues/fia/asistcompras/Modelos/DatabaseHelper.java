package gr23.sv.ues.fia.asistcompras.Modelos;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FAMILY on 05/06/2016.
 */
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
                    "constraint PK_LUGAR primary key (latitud, longitud));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

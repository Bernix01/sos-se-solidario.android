package paralelo1.poo.sossesolidario.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by roche on 21/08/2016.
 */
public class CADbHelper extends SQLiteOpenHelper {

    private static int version = 4;
    private static String name = "CADb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public CADbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE CENTROS_DE_ACOPIO(" +
                " _id INTEGER PRIMARY KEY," +
                " ca_latitud TEXT, " +
                " ca_longitud TEXT, " +
                " ca_nombre TEXT NOT NULL, " +
                " ca_direccion TEXT, " +
                " ca_facebook TEXT," +
                " ca_twitter TEXT," +
                " ca_descripcion TEXT," +
                " ca_necesidades TEXT)" );

        db.execSQL( "CREATE UNIQUE INDEX CA_nombre ON CENTROS_DE_ACOPIO(ca_nombre ASC)" );

        Log.i(this.getClass().toString(), "Tabla CENTROS_DE_ACOPIO creada");

    /*
     * Insertamos datos iniciales
     */
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(1,'Santander')");
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(2,'BBVA')");
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(3,'La Caixa')");
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(4,'Cajamar')");
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(5,'Bankia')");
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(6,'Banco Sabadell')");
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(7,'Banco Popular')");

        //Log.i(this.getClass().toString(), "Datos iniciales HIPOTECA insertados");

        //Log.i(this.getClass().toString(), "Base de datos creada");

        // Aplicamos las sucesivas actualizaciones
        //upgrade_2(db);
        // upgrade_3(db);
        //upgrade_4(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Actualización a versión 2
        if (oldVersion < 2)
        {
            upgrade_2(db);
        }
        // Actualización a versión 3
        if (oldVersion < 3)
        {
            upgrade_3(db);
        }
        // Actualización a versión 4
        if (oldVersion < 4)
        {
            upgrade_4(db);
        }
    }

    private void upgrade_2(SQLiteDatabase db)
    {
        //
        // Upgrade versión 2: definir algunos datos de ejemplo
        //
        db.execSQL( "UPDATE CENTRO_DE_ACOPIO SET ca_nombre = 'CENTRO DE ACOPIO LUZ DE ESPERANZA'," +
                                " WHERE _id = 1");

        Log.i(this.getClass().toString(), "Actualización versión 2 finalizada");
    }

    private void upgrade_3(SQLiteDatabase db)
    {
        //
        // Upgrade versión 3: Incluir pasivo_sn
        //

    }

    private void upgrade_4(SQLiteDatabase db)
    {
        //
        // Upgrade versión 4: Incluir la clasificación SITUACION para las hipotecas
        //

    }

}

package paralelo1.poo.sossesolidario.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import paralelo1.poo.sossesolidario.objects.CA;

/**
 * Created by roche on 21/08/2016.
 */
public class CADbAdapter {

    public static final String C_TABLA = "CENTROS DE ACOPIO";
    public static final String C_COLUMNA_ID	= "_id";
    public static final String C_COLUMNA_LATITUD = "ca_latitud";
    public static final String C_COLUMNA_LONGITUD = "ca_longitud";
    public static final String C_COLUMNA_NOMBRE = "ca_nombre";
    public static final String C_COLUMNA_DIRECCION = "ca_direccion";
    public static final String C_COLUMNA_FACEBOOK = "ca_facebook";
    public static final String C_COLUMNA_TWITTER = "ca_twitter";
    public static final String C_COLUMNA_DESCRIPCION = "ca_descripcion";
    public static final String C_COLUMNA_NECESIDADES = "ca_necesidades";

    public Context getContexto() {
        return contexto;
    }

    private Context contexto;

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    private CADbHelper dbHelper;
    private SQLiteDatabase db;

    private String[] columnas = new String[]{
            C_COLUMNA_ID,
            C_COLUMNA_LATITUD,
            C_COLUMNA_LONGITUD,
            C_COLUMNA_NOMBRE,
            C_COLUMNA_DIRECCION,
            C_COLUMNA_FACEBOOK,
            C_COLUMNA_TWITTER,
            C_COLUMNA_DESCRIPCION,
            C_COLUMNA_NECESIDADES } ;


    public CADbAdapter(Context context)
    {
        this.contexto = context;
    }

    public CADbAdapter abrir() throws SQLException
    {
        dbHelper = new CADbHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        dbHelper.close();
    }
    public Cursor getCursor(String filtro) throws SQLException {

        if (db == null)
            abrir();

        Cursor c = db.query(true, C_TABLA, columnas, filtro, null, null, null, null, null);

        return c;
    }
    public Cursor getRegistro(long id) throws SQLException
    {
        if (db == null)
            abrir();

        Cursor c = db.query( true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public long insert(ContentValues reg)
    {
        if (db == null)
            abrir();

        return db.insert(C_TABLA, null, reg);
    }
    public long delete(long id)
    {
        if (db == null)
            abrir();

        return db.delete(C_TABLA, "_id=" + id, null);
    }
    public long update(ContentValues reg)
    {
        long result = 0;

        if (db == null)
            abrir();

        if (reg.containsKey(C_COLUMNA_ID))
        {

            long id = reg.getAsLong(C_COLUMNA_ID);

            reg.remove(C_COLUMNA_ID);


            result = db.update(C_TABLA, reg, "_id=" + id, null);
        }
        return result;
    }
    public boolean exists(long id) throws SQLException
    {
        boolean exists ;

        if (db == null)
            abrir();

        Cursor c = db.query( true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id, null, null, null, null, null);

        exists = (c.getCount() > 0);

        c.close();

        return exists;
    }

    public ArrayList<CA> getCA(String filtro)
    {
        ArrayList<CA> cas = new ArrayList<CA>();

        if (db == null)
            abrir();

        Cursor c = db.query(true, C_TABLA, columnas, filtro, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            cas.add(CA.cursorToHipoteca(contexto, c));
        }

        c.close();

        return cas;
    }
}
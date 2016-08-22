package paralelo1.poo.sossesolidario.objects;

/**
 * Created by roche on 14/08/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.strongloop.android.loopback.Model;

import java.util.LinkedList;
import java.util.List;

import paralelo1.poo.sossesolidario.utils.CADbAdapter;

public class CA extends Model implements Parcelable {
    public static final Creator<CA> CREATOR = new Creator<CA>() {
        @Override
        public CA createFromParcel(Parcel in) {
            return new CA(in);
        }

        @Override
        public CA[] newArray(int size) {
            return new CA[size];
        }
    };
    private int ide;
    private long latitud;
    private long longitud;
    private String nombre;
    private String direccion;
    private String fb;
    private String twitter;
    private List<Necesidad> necesidades;
    private String descripcion;

    public CA(int ide, long latitud, long longitud, String nombre, String direccion, String fb, String twitter) {
        this.ide = ide;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fb = fb;
        this.twitter = twitter;
        this.necesidades = new LinkedList<>();
    }

    public CA(int ide, long latitud, long longitud, String nombre, String direccion, String fb, String twitter, List<Necesidad> necesidades) {
        this.ide = ide;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fb = fb;
        this.twitter = twitter;
        this.necesidades = necesidades;
    }

    public CA(Parcel in) {
        ide = in.readInt();
        latitud = in.readLong();
        longitud = in.readLong();
        nombre = in.readString();
        direccion = in.readString();
        fb = in.readString();
        twitter = in.readString();
        descripcion = in.readString();
    }

    public CA(){}

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public List<Necesidad> getNecesidades() {
        return necesidades;
    }

    public void setNecesidades(List<Necesidad> necesidades) {
        this.necesidades = necesidades;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(latitud);
        parcel.writeLong(longitud);
        parcel.writeString(nombre);
        parcel.writeString(direccion);
        parcel.writeString(fb);
        parcel.writeString(twitter);
        parcel.writeString(descripcion);
    }
    public static CA cursorToHipoteca(Context context, Cursor c)
    {
        CA ca = null;

        if (c != null)
        {
            ca = new CA();

            ca.setIde(c.getInt(c.getColumnIndex(CADbAdapter.C_COLUMNA_ID)));
            ca.setNombre(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_NOMBRE)));
            ca.setLatitud(c.getLong(c.getColumnIndex(CADbAdapter.C_COLUMNA_LATITUD)));
            ca.setLongitud(c.getLong(c.getColumnIndex(CADbAdapter.C_COLUMNA_LONGITUD)));
            ca.setDireccion(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_DIRECCION)));
            ca.setFb(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_FACEBOOK)));
            ca.setTwitter(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_TWITTER)));
            ca.setDescripcion(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_DESCRIPCION)));
            //ca.setNecesidades(c.get(c.getColumnIndex(CADbAdapter.C_COLUMNA_NECESIDADES)));
        }

        return ca;
    }
}

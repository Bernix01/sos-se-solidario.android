package paralelo1.poo.sossesolidario.objects;

/**
 * Created by roche on 14/08/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.strongloop.android.loopback.Model;

import java.util.HashMap;
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
    private double latitud;
    private double longitud;
    private String nombre;
    private String direccion;
    private String fb;
    private String tw;
    private HashMap<String, Double> ubicacion;
    private List<Necesidad> necesidades;
    private String dscr;

    public CA(){

    }

    public CA(long latitud, long longitud, String nombre, String direccion, String fb, String tw) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fb = fb;
        this.tw = tw;
        this.necesidades = new LinkedList<>();
    }

    public CA(long latitud, long longitud, String nombre, String direccion, String fb, String tw, List<Necesidad> necesidades) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fb = fb;
        this.tw = tw;
        this.necesidades = necesidades;
    }

    protected CA(Parcel in) {
        latitud = in.readDouble();
        longitud = in.readDouble();
        nombre = in.readString();
        direccion = in.readString();
        fb = in.readString();
        tw = in.readString();
        dscr = in.readString();
    }

    public static CA cursorToHipoteca(Context context, Cursor c) {
        CA ca = null;

        if (c != null) {
            ca = new CA();

            ca.setNombre(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_NOMBRE)));
            ca.setLatitud(c.getLong(c.getColumnIndex(CADbAdapter.C_COLUMNA_LATITUD)));
            ca.setLongitud(c.getLong(c.getColumnIndex(CADbAdapter.C_COLUMNA_LONGITUD)));
            ca.setDireccion(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_DIRECCION)));
            ca.setFb(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_FACEBOOK)));
            ca.setTw(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_TWITTER)));
            ca.setDscr(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_DESCRIPCION)));
            //ca.setNecesidades(c.get(c.getColumnIndex(CADbAdapter.C_COLUMNA_NECESIDADES)));
        }

        return ca;
    }

    public HashMap<String, Double> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(HashMap<String, Double> ubicacion) {
        this.ubicacion = ubicacion;
        this.latitud = this.ubicacion.get("lat");
        this.longitud = this.ubicacion.get("long");
    }

    public String getDscr() {
        return dscr;
    }

    public void setDscr(String dscr) {
        this.dscr = dscr;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
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

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw;
    }

    public List<Necesidad> getNecesidades() {
        return necesidades;
    }

    public void setNecesidades(List<Necesidad> necesidades) {
        this.necesidades = necesidades;
    }

    @Override
    public String toString() {
        return "CA{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fb='" + fb + '\'' +
                ", tw='" + tw + '\'' +
                ", ubicacion=" + ubicacion +
                ", necesidades=" + necesidades +
                ", dscr='" + dscr + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitud);
        parcel.writeDouble(longitud);
        parcel.writeString(nombre);
        parcel.writeString(direccion);
        parcel.writeString(fb);
        parcel.writeString(tw);
        parcel.writeString(dscr);
    }

    public LatLng getPos() {
        return new LatLng(this.latitud, this.longitud);
    }
}

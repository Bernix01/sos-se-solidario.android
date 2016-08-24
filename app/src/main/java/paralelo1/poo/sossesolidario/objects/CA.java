package paralelo1.poo.sossesolidario.objects;

/**
 * Created by roche on 14/08/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import paralelo1.poo.sossesolidario.server.Rest;
import paralelo1.poo.sossesolidario.utils.CADbAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CA implements Parcelable {

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
    private String nombre;
    private String direccion;
    private String fb;
    private String tw;
    private int id;
    private HashMap<String, Double> ubicacion;
    private String dscr;

    public CA(){
        this.ubicacion = new HashMap<>();
        this.id = Math.abs(new Random(System.currentTimeMillis()).nextInt());
        ubicacion.put("lat", 0d);
        ubicacion.put("lng", 0d);

    }



    protected CA(Parcel in) {
        ubicacion = new HashMap<>();
        ubicacion.put("lat", in.readDouble());
        ubicacion.put("lng", in.readDouble());
        nombre = in.readString();
        direccion = in.readString();
        fb = in.readString();
        tw = in.readString();
        dscr = in.readString();
        id = in.readInt();
    }

    public static CA cursorToHipoteca(Context context, Cursor c) {
        CA ca = null;

        if (c != null) {
            ca = new CA();

            ca.setNombre(c.getString(c.getColumnIndex(CADbAdapter.C_COLUMNA_NOMBRE)));
            ca.setLatitud(c.getDouble(c.getColumnIndex(CADbAdapter.C_COLUMNA_LATITUD)));
            ca.setLongitud(c.getDouble(c.getColumnIndex(CADbAdapter.C_COLUMNA_LONGITUD)));
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
    }

    public int getId() {
        return id;
    }

    public String getDscr() {
        return dscr;
    }

    public void setDscr(String dscr) {
        this.dscr = dscr;
    }

    public double getLatitud() {
        return this.ubicacion.get("lat");
    }

    public void setLatitud(double latitud) {
        this.ubicacion.put("lat", latitud);
    }

    public double getlngitud() {
        return this.ubicacion.get("lng");
    }

    public void setLongitud(double lngitud) {
        this.ubicacion.put("lng", lngitud);
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

    public void getNecesidades(Callback<List<Necesidad>> cb) {
        Rest.get().service().getCaNecesidades(this.id).enqueue(cb);
    }



    public void delete()
    {
        // borramos el registro
        final CA self = this;
        Rest.get().service().deleteCA(this.id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

//                CADbAdapter dbAdapter = new CADbAdapter(self.getContexto());
//                dbAdapter.delete(self.getId());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void save(Callback<CA> cb) {
        Log.e("asdad", this.id + "");
        Rest.get().service().updateCA(this).enqueue(cb);
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(ubicacion.get("lat"));
        parcel.writeDouble(ubicacion.get("lng"));
        parcel.writeString(nombre);
        parcel.writeString(direccion);
        parcel.writeString(fb);
        parcel.writeString(tw);
        parcel.writeString(dscr);
        parcel.writeInt(id);
    }

    public LatLng getPos() {
        return new LatLng(this.ubicacion.get("lat"), this.ubicacion.get("lng"));
    }


}

package paralelo1.poo.sossesolidario.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

import paralelo1.poo.sossesolidario.server.Rest;
import retrofit2.Callback;

/**
 * Created by roche on 14/08/2016.
 */
public class Necesidad implements Parcelable {

    public static final Creator<Necesidad> CREATOR = new Creator<Necesidad>() {
        @Override
        public Necesidad createFromParcel(Parcel in) {
            return new Necesidad(in);
        }

        @Override
        public Necesidad[] newArray(int size) {
            return new Necesidad[size];
        }
    };

    private int cantidad;
    private String nombre;
    private String tipo;
    private int id;
    private int fk_necesidad_ca;

    public Necesidad(){

        this.id = Math.abs(new Random(System.currentTimeMillis()).nextInt());

    }

    protected Necesidad(Parcel in) {
        cantidad = in.readInt();
        nombre = in.readString();
        tipo = in.readString();
        id = in.readInt();
        fk_necesidad_ca = in.readInt();
    }

    public int getFk_necesidad_ca() {
        return fk_necesidad_ca;
    }

    public void setFk_necesidad_ca(int fk_necesidad_ca) {
        this.fk_necesidad_ca = fk_necesidad_ca;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cantidad);
        parcel.writeString(nombre);
        parcel.writeString(tipo);
        parcel.writeInt(id);
        parcel.writeInt(fk_necesidad_ca);

    }

    public void save(Callback<Necesidad> callback) {
        Rest.get().service().addNecesidad(this.getId()).enqueue(callback);
    }

    public void delete(Callback<Void> callback) {
        Rest.get().service().deleteNecesidad(this.id).enqueue(callback);
    }

    enum tipos_necesidad {
        aseo, ropa, viveres
    }

}

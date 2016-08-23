package paralelo1.poo.sossesolidario.objects;

import android.os.Parcel;
import android.os.Parcelable;

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
    private tipos_necesidad tipo;
    private int cantidad;
    private String nombre;

    public Necesidad(){

    }

    public Necesidad(tipos_necesidad tipo, int cantidad, String nombre) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    protected Necesidad(Parcel in) {
        cantidad = in.readInt();
        nombre = in.readString();
    }

    public tipos_necesidad getTipo() {
        return tipo;
    }

    public void setTipo(tipos_necesidad tipo) {
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
    }

    static enum tipos_necesidad{
        aseo,ropa,viveres;
    }

}

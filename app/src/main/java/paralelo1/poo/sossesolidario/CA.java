package paralelo1.poo.sossesolidario;

/**
 * Created by roche on 14/08/2016.
 */

import java.util.LinkedList;
import java.util.List;
public class CA {
    private long latitud;
    private long longitud;
    private String nombre;
    private String direccion;
    private String fb;
    private String twitter;
    private List<Necesidad> necesidades;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
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

    public CA(long latitud, long longitud, String nombre, String direccion, String fb, String twitter) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fb = fb;
        this.twitter = twitter;
        this.necesidades = new LinkedList<>();
    }

    public CA(long latitud, long longitud, String nombre, String direccion, String fb, String twitter, List<Necesidad> necesidades) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fb = fb;
        this.twitter = twitter;
        this.necesidades = necesidades;
    }
}

package paralelo1.poo.sossesolidario.objects;

/**
 * Created by whugo on 14/8/2016.
 */
public class Usuario {
    private String nombre;
    private String correo;
    private String contraseña;
    private boolean esardministrador;

    public Usuario(String nombre, String correo, String contraseña, boolean esadministrador) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.esardministrador=esadministrador;
    }

    public boolean isEsardministrador() {
        return esardministrador;
    }

    public void setEsardministrador(boolean esardministrador) {
        this.esardministrador = esardministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

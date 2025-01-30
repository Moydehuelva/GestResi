
package Clases;

/**
 *
 * @author moyde
 */
public class Usuario {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String personaContacto;
    private String telefono;
    private String observaciones;

    public Usuario() {
    }

    public Usuario(int codigo, String nombre, String apellidos, String personaContacto, String telefono, String observaciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.personaContacto = personaContacto;
        this.telefono = telefono;
        this.observaciones = observaciones;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

  
}

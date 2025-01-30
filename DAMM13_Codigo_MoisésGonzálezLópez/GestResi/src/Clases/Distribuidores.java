
package Clases;

/**
 *
 * @author moyde
 */
public class Distribuidores {
    private int codigo;
    private String cif;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;

    public Distribuidores() {
    }

    public Distribuidores(int codigo, String cif, String nombre, String telefono, String direccion, String correo) {
        this.codigo = codigo;
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }
    
    public int getCodigo() {
    return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    
    
}

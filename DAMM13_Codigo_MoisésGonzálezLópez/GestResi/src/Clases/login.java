
package Clases;

/**
 *
 * @author moyde
 */
public class login {
    private int codigo;
    private String nombre;
    private String correo;
    private String pass;
    private String privilegios;

    public login() {
    }

    public login(int codigo, String nombre, String correo, String pass, String privilegios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.privilegios = privilegios;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }
    
    


}

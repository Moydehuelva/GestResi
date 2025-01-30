
package Clases;

/**
 *
 * @author moyde
 */
public class Turno {
    private int codigo;
    private String usuario;
    private String empleado;
    private String feha;

    public Turno() {
    }

    public Turno(int codigo, String usuario, String empleado) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.empleado = empleado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getFeha() {
        return feha;
    }

    public void setFeha(String feha) {
        this.feha = feha;
    }
    
    
}


package Clases;

/**
 *
 * @author moyde
 */
public class Cuidados {
    private int codigo;
    private String descripcion;

    public Cuidados() {
    }

    public Cuidados(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}


package Clases;

import java.util.Date;

/**
 *
 * @author moyde
 */
public class Almacen {
    private int codigo;
    private String producto;
    private String caducidad;
    private String distribuidor;
    private int cantidad;

    public Almacen() {
    }



    public Almacen(int codigo, String producto, String caducidad, String distribuidor, int cantidad) {
        this.codigo = codigo;
        this.producto = producto;
        this.caducidad = caducidad;
        this.distribuidor = distribuidor;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getproducto() {
        return producto;
    }

    public void setproducto(String producto) {
        this.producto = producto;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}

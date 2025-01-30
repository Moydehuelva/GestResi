package Clases;

/**
 *
 * @author moyde
 */
public class DetallesTurno {

    private int id;
    private String descripcion;
    private String medida;
    private String hora;
    private int cantidad;
    private int id_turno;
    private String producto;
    private String codigoProducto;
    private String codigoCuidado;

    public DetallesTurno() {
    }

    public DetallesTurno(int id, String descripcion, String medida, String hora, int cantidad, int id_turno, String producto, String codigoProducto, String codigoCuidado) {
        this.id = id;
        this.descripcion = descripcion;
        this.medida = medida;
        this.hora = hora;
        this.cantidad = cantidad;
        this.id_turno = id_turno;
        this.producto = producto;
        this.codigoProducto = codigoProducto;
        this.codigoCuidado = codigoCuidado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoCuidado() {
        return codigoCuidado;
    }

    public void setCodigoCuidado(String codigoCuidado) {
        this.codigoCuidado = codigoCuidado;
    }

}

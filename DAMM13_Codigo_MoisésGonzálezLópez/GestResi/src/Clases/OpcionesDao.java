
package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;

/**
 *
 * @author moyde
 */
public class OpcionesDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    public boolean ActualizarEmpresa(Opciones op) {
        String sql = "UPDATE opciones SET nombre=?, cif=?, direccion=?, telefono=?, correo_electronico=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, op.getNombre());
            ps.setString(2, op.getCif());
            ps.setString(3, op.getDireccion());
            ps.setString(4, op.getTelefono());
            ps.setString(5, op.getCorreo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally{
            try {
                if (cn != null){
                    cn.cerrarConnection();
                }              
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    public Opciones DatosEmpresa() {
        Opciones opc = new Opciones();
        String sql = "SELECT * FROM opciones";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                opc.setNombre(rs.getString("nombre"));
                opc.setCif(rs.getString("cif"));
                opc.setDireccion(rs.getString("direccion"));
                opc.setTelefono(rs.getString("telefono"));
                opc.setCorreo(rs.getString("correo_electronico"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                if (cn != null){
                    cn.cerrarConnection();
                }              
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return opc;
    }
}

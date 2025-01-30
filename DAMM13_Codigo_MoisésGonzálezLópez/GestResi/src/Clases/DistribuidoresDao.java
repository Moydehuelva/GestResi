
package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author moyde
 */
public class DistribuidoresDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
     public boolean RegistrarDistribuidor(Distribuidores di){
         String sql = "INSERT INTO distribuidores (cif, nombre, telefono, direccion, correo) VALUES (?, ?, ?, ?, ?)";
         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             ps.setString(1, di.getCif());
             ps.setString(2, di.getNombre());
             ps.setString(3, di.getTelefono());
             ps.setString(4, di.getDireccion());
             ps.setString(5, di.getCorreo());
             ps.execute();
             return true;
         } catch (SQLException e) {
             System.out.println(e.toString());
             return false;
         }finally{
            try {
                if (cn != null){
                    cn.cerrarConnection();
                }              
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
     }
     
     public List RelacionarDistribuidores(){
         List<Distribuidores> RelDistr = new ArrayList();
         String sql = "SELECT * FROM distribuidores";
         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
             while (rs.next()) { 
                 Distribuidores di = new Distribuidores();
                 di.setCodigo(rs.getInt("codigo"));
                 di.setCif(rs.getString("cif"));
                 di.setNombre(rs.getString("nombre"));
                 di.setTelefono(rs.getString("telefono"));
                 di.setDireccion(rs.getString("direccion"));
                 di.setCorreo(rs.getString("correo"));
                 RelDistr.add(di);
             }
         } catch (SQLException e) {
             System.out.println(e.toString());
         }
         return RelDistr;
     }
     
     public boolean EliminarDistribuidor(int id){
         String sql = "DELETE FROM distribuidores WHERE codigo = ?";
         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             ps.setInt(1, id);
             ps.execute();
             return true;
         } catch (SQLException e) {
             System.out.println(e.toString());
             return false;
         }finally{
             try {
                 con.close();
             } catch (SQLException e) {
                 System.out.println(e.toString());
             }
         }
     }
     
     public boolean ActualizarDistribuidor(Distribuidores di){
         String sql = "UPDATE distribuidores SET cif=?, nombre=?, telefono=?, direccion=?, correo=? WHERE codigo=?";
         try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, di.getCif());
            ps.setString(2, di.getNombre());
            ps.setString(3, di.getTelefono());
            ps.setString(4, di.getDireccion());
            ps.setString(5, di.getCorreo());
            ps.setInt(6, di.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                if (cn != null){
                    cn.cerrarConnection();
                }              
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
     }
}

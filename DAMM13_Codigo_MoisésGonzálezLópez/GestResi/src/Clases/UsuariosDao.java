
package Clases;
import com.mycompany.sqlite.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author moyde
 */
public class UsuariosDao {
    Conexion cn = new Conexion ();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarUsuario(Usuario us){
        String sql = "INSERT INTO usuarios (nombre, apellidos, persona_contacto, telefono_contacto, observaciones) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getApellidos());
            ps.setString(3, us.getPersonaContacto());
            ps.setString(4, us.getTelefono());
            ps.setString(5, us.getObservaciones());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
         finally{
            try {
                if (cn != null){
                    cn.cerrarConnection();
                }              
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        
    }
    
    public List RelacionUsuarios(){
        List<Usuario> RelacionUs = new ArrayList();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setCodigo(rs.getInt("codigo"));
                us.setNombre(rs.getString("nombre"));
                us.setApellidos(rs.getString("apellidos"));
                us.setPersonaContacto(rs.getString("persona_contacto"));
                us.setTelefono(rs.getString("telefono_contacto"));
                us.setObservaciones(rs.getString("observaciones"));
                RelacionUs.add(us);
                
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
        return RelacionUs;
    }
    public boolean EliminarUsuario(int codigo){
        String sql = "DELETE FROM usuarios WHERE codigo = ?";
        try {
        	con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ActualizarUsuario(Usuario us){
        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, persona_contacto=?, telefono_contacto=?, observaciones=? WHERE codigo=?";
        try {
        	con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getApellidos());
            ps.setString(3, us.getPersonaContacto());
            ps.setString(4, us.getTelefono());
            ps.setString(5, us.getObservaciones());
            ps.setInt(6, us.getCodigo());
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
    public Usuario EncontrarUsuarioId(int ide){
        Usuario us = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE codigo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ide);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setCodigo(rs.getInt("codigo"));
                us.setNombre(rs.getString("nombre"));
                us.setApellidos(rs.getString("apellidos"));
                us.setPersonaContacto(rs.getString("persona_contacto"));
                us.setTelefono(rs.getString("telefono_contacto"));
                us.setObservaciones(rs.getString("observaciones"));
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
        return us;
    }
    public Usuario EncontrarUsuarioNom(String nom){
        Usuario us = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setCodigo(rs.getInt("codigo"));
                us.setNombre(rs.getString("nombre"));
                us.setApellidos(rs.getString("apellidos"));
                us.setPersonaContacto(rs.getString("persona_contacto"));
                us.setTelefono(rs.getString("telefono_contacto"));
                us.setObservaciones(rs.getString("observaciones"));
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
        return us;
    }
}

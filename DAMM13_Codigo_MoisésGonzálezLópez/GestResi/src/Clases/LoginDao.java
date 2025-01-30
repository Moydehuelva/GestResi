package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moyde
 */
public class LoginDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public login log(String correo, String pass) { //llamo a la clase login
        login l = new login();
        String sql = "SELECT * FROM empleados WHERE correo = ? AND pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();//nos devuelve un resultSet que capturamos 
            if (rs.next()) {
                l.setCodigo(rs.getInt("codigo"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                l.setPrivilegios(rs.getString("privilegios"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (cn != null) {
                    cn.cerrarConnection();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return l;
    }

    public boolean Registrar(login reg) {
        String sql = "INSERT INTO empleados (nombre, correo, pass, privilegios) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getPrivilegios());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.cerrarConnection();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List RelacionarTrabajadores() {
        List<login> Lista = new ArrayList();
        String sql = "SELECT * FROM empleados";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                login lg = new login();
                lg.setCodigo(rs.getInt("codigo"));
                lg.setNombre(rs.getString("nombre"));
                lg.setCorreo(rs.getString("correo"));
                lg.setPass(rs.getString("pass"));
                lg.setPrivilegios(rs.getString("privilegios"));
                Lista.add(lg);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (cn != null) {
                    cn.cerrarConnection();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return Lista;
    }

    public boolean EliminarEmpleado(int id) {
        String sql = "DELETE FROM Empleados WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

}

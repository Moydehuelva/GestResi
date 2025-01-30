package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;
import javax.swing.JComboBox;

/**
 *
 * @author moyde
 */
public class DetallesturnoDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public void Productos(JComboBox producto) {//este metodo es para que aparezcan los productos en el jcombobox
        String sql = "SELECT producto FROM almacen";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto.addItem(rs.getString("producto"));
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

    }

    public void Cuidado(JComboBox descripcion) {//este metodo es para que aparezcan los productos en el jcombobox
        String sql = "SELECT descripcion FROM cuidados";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                descripcion.addItem(rs.getString("descripcion"));
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
    }

    public void Usuari(JComboBox nombre) {//este metodo es para que aparezcan los productos en el jcombobox
        String sql = "SELECT nombre FROM usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombre.addItem(rs.getString("nombre"));
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
    }
}

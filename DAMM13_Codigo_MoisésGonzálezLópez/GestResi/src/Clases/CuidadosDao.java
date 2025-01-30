package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moyde
 */
public class CuidadosDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarCuidado(Cuidados cui) {
        String sql = "INSERT INTO  cuidados (descripcion) VALUES(?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cui.getDescripcion());
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

    public List RelacionCuidados() {
        List<Cuidados> RelacionCui = new ArrayList();
        String sql = "SELECT * FROM cuidados";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuidados cui = new Cuidados();
                cui.setCodigo(rs.getInt("codigo"));
                cui.setDescripcion(rs.getString("descripcion"));
                RelacionCui.add(cui);

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
        return RelacionCui;
    }

    public boolean EliminarCuidado(int codigo) {
        String sql = "DELETE FROM cuidados WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
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

    public boolean ActualizarCuidado(Cuidados cui) {
        String sql = "UPDATE cuidados SET descripcion=? WHERE codigo=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cui.getDescripcion());
            ps.setInt(2, cui.getCodigo());
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

    public Cuidados EncontrarPorCodigoCui(String cod) {
        Cuidados cui = new Cuidados();
        String sql = "SELECT * FROM cuidados WHERE codigo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                cui.setCodigo(rs.getInt("codigo"));
                cui.setDescripcion(rs.getString("descripcion"));
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
        return cui;
    }

    public Cuidados EncontrarPorDescripCui(String des) {
        Cuidados cui = new Cuidados();
        String sql = "SELECT * FROM cuidados WHERE descripcion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, des);
            rs = ps.executeQuery();
            if (rs.next()) {
                cui.setCodigo(rs.getInt("codigo"));
                cui.setDescripcion(rs.getString("descripcion"));
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
        return cui;
    }
}

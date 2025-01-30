package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author moyde
 */
public class AlmacenDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProducto(Almacen al) {
        String sql = "INSERT INTO  almacen (producto, caducidad, distribuidor, cantidad) VALUES(?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, al.getproducto());
            ps.setString(2, al.getCaducidad());
            ps.setString(3, al.getDistribuidor());
            ps.setInt(4, al.getCantidad());
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

    public void ListarDistribuidores(JComboBox distribuidor) {//este metodo es para que aparezcan los distribuidores en el jcombobox
        String sql = "SELECT nombre FROM distribuidores";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                distribuidor.addItem(rs.getString("nombre"));
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

    public List RelacionAlmacen() {
        List<Almacen> RelacionAl = new ArrayList();
        String sql = "SELECT * FROM almacen";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Almacen al = new Almacen();
                al.setCodigo(rs.getInt("codigo"));
                al.setproducto(rs.getString("producto"));
                al.setCaducidad(rs.getString("caducidad"));
                al.setDistribuidor(rs.getString("distribuidor"));
                al.setCantidad(rs.getInt("cantidad"));
                RelacionAl.add(al);

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
        return RelacionAl;
    }

    public boolean EliminarAlmacen(int codigo) {
        String sql = "DELETE FROM almacen WHERE codigo = ?";
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

    public boolean ActualizarAlmacen(Almacen al) {
        String sql = "UPDATE almacen SET producto=?, caducidad=?, distribuidor=?, cantidad=? WHERE codigo=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, al.getproducto());
            ps.setString(2, al.getCaducidad());
            ps.setString(3, al.getDistribuidor());
            ps.setInt(4, al.getCantidad());
            ps.setInt(5, al.getCodigo());
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

    public Almacen EncontrarPorCodigo(int cod) {
        Almacen proCui = new Almacen();
        String sql = "SELECT * FROM almacen WHERE codigo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                proCui.setCodigo(rs.getInt("codigo"));
                proCui.setproducto(rs.getString("producto"));
                proCui.setCaducidad(rs.getString("caducidad"));
                proCui.setDistribuidor(rs.getString("distribuidor"));
                proCui.setCantidad(rs.getInt("cantidad"));
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
        return proCui;
    }

    public Almacen EncontrarPorNombre(String des) {
        Almacen proCui = new Almacen();
        String sql = "SELECT * FROM almacen WHERE producto = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, des);
            rs = ps.executeQuery();
            if (rs.next()) {
                proCui.setCodigo(rs.getInt("codigo"));
                proCui.setproducto(rs.getString("producto"));
                proCui.setCaducidad(rs.getString("caducidad"));
                proCui.setDistribuidor(rs.getString("distribuidor"));
                proCui.setCantidad(rs.getInt("cantidad"));
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
        return proCui;
    }

   /* public Almacen EncontrarPorCodigo(String codPr) {
        Almacen proCui = new Almacen();
        String sql = "SELECT * FROM almacen WHERE codigo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            String cod = null;
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                proCui.setCodigo(rs.getInt("codigo"));
                proCui.setproducto(rs.getString("producto"));
                proCui.setCaducidad(rs.getString("caducidad"));
                proCui.setDistribuidor(rs.getString("distribuidor"));
                proCui.setCantidad(rs.getInt("cantidad"));
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
        return proCui;
    }*/
}

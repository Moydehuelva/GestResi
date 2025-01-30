package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author moyde
 */
public class TurnoDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public int idTurno() {
        int codTur = 0;
        String sql = "SELECT MAX(codigo) FROM turno";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                codTur = rs.getInt(1);
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
        return codTur;
    }

    public int RegistrarTurno(Turno t) {
        String sql = "INSERT INTO turno ( usuario, empleado) VALUES (?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getUsuario());
            ps.setString(2, t.getEmpleado());
            ps.execute();
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
        return resp;
    }

    public int RegistrarDetallesPro(DetallesTurno dt) {
        String sql = "INSERT INTO detalle_turnopro (codigo_producto, producto, cantidad, id_turno) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dt.getCodigoProducto());
            ps.setString(2, dt.getProducto());
            ps.setInt(3, dt.getCantidad());
            ps.setInt(4, dt.getId_turno());

            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return resp;
    }

    public int RegistrarDetallesCui(DetallesTurno dt) {
        String sql = "INSERT INTO detalle_turnocui (codigo, descripcion, medida, hora, id_turno) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dt.getCodigoCuidado());
            ps.setString(2, dt.getDescripcion());
            ps.setString(3, dt.getMedida());
            ps.setString(4, dt.getHora());
            ps.setInt(5, dt.getId_turno());

            ps.execute();
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
        return resp;
    }

    public boolean ActualizarAlmacen(int cant, int cod) {
        String sql = "UPDATE almacen SET cantidad = ? WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, cod);
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

    public List RelacionTurnos() {
        List<Turno> RelacionTurno = new ArrayList();
        String sql = "SELECT * FROM turno";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turno tur = new Turno();
                tur.setCodigo(rs.getInt("codigo"));
                tur.setUsuario(rs.getString("usuario"));
                tur.setEmpleado(rs.getString("empleado"));
                tur.setFeha(rs.getString("fecha"));
                RelacionTurno.add(tur);

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
        return RelacionTurno;
    }

}

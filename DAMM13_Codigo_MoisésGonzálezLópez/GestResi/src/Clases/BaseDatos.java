package Clases;

import com.mycompany.sqlite.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author moyde
 */
public class BaseDatos {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public void CrearAlmacen() {
        String sql = "CREATE TABLE IF NOT EXISTS almacen ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "producto text,"
                + "caducidad text,"
                + "distribuidor text,"
                + "cantidad integer"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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
    }

    public void CrearArchivo() {
        String sql = "CREATE TABLE IF NOT EXISTS archivo ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "usuario text,"
                + "trabajador text,"
                + "fecha text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearCuidados() {
        String sql = "CREATE TABLE IF NOT EXISTS cuidados ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "descripcion text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearDetalleTurCui() {
        String sql = "CREATE TABLE IF NOT EXISTS detalle_turnocui ("
                + "id integer primary key AUTOINCREMENT,"
                + "codigo integer,"
                + "descripcion text,"
                + "medida text,"
                + "hora text,"
                + "id_turno integer"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearDetalleTurPro() {
        String sql = "CREATE TABLE IF NOT EXISTS detalle_turpro ("
                + "id integer primary key AUTOINCREMENT,"
                + "codigo_producto integer"
                + "producto text,"
                + "cantidad integer,"
                + "id_turno integer"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearDistribuidores() {
        String sql = "CREATE TABLE IF NOT EXISTS distribuidores ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "cif text,"
                + "nombre text,"
                + "telefono text,"
                + "direccion text,"
                + "correo text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearEmpleados() {
        String sql = "CREATE TABLE IF NOT EXISTS empleados ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "nombre text,"
                + "correo text,"
                + "pass text,"
                + "privilegios text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void InsertarAdmin() {
        String sql = "INSERT INTO empleados (nombre, correo, pass, privilegios) "
                + "SELECT 'Admin', 'admin', 'admin', 'Administrador' "
                + "WHERE NOT EXISTS (SELECT * FROM empleados)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }
    
    public void VerificarAdmin() {
    	String sql = "SELECT MIN(codigo) FROM empleados";
    	int cod = 0;

    	try {
    		con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
    	    cod = rs.getInt(1);
    	} catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}finally {
    		try {
                if (cn != null) {
                    cn.cerrarConnection();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
    	}

    	// Mostrar mensaje si se creó "admin"
    	if (cod == 1) {
    	    JOptionPane.showMessageDialog(null, "IMPORTANTE: \n Primer EMPLEADO CREADO \n Correo: admin \n Password: admin \n\n "
    	    		+ "CREE UN NUEVO USUARIO ADMINISTRADOR TRAS EL PRIMER INICIO"
    	    		+ "\n TRAS ELLO ELIMINE EL EMPLEADO CON CODIGO 1 "
    	    		+ "PARA QUE DEJE DE SALIR ESTE MENSAJE");
    	}
    	try {
            if (cn != null) {
                cn.cerrarConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
	
    	 
    }

    public void CrearOpciones() {
        String sql = "CREATE TABLE IF NOT EXISTS opciones ("
                + "nombre text,"
                + "cif text,"
                + "direccion text,"
                + "telefono text,"
                + "correo_electronico text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void InsertarOpciones() {
        String sql = "INSERT INTO opciones (nombre, cif, direccion, telefono, correo_electronico) "
                + "SELECT 'Nombre', 'CIF', 'Direccion', 'Telefono', 'correo@correo.es' "
                + "WHERE NOT EXISTS (SELECT * FROM opciones)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearTurno() {
        String sql = "CREATE TABLE IF NOT EXISTS turno ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "usuario text,"
                + "empleado text,"
                + "fecha text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }

    public void CrearUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "codigo integer primary key AUTOINCREMENT,"
                + "nombre text,"
                + "apellidos text,"
                + "persona_contacto text,"
                + "telefono_contacto text,"
                + "observaciones text"
                + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
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

    }
}

package Interfaz;

import javax.swing.*;
import Informes.GenerarExcel;
import Clases.Almacen;
import Clases.AlmacenDao;
import Clases.DetallesTurno;
import Clases.Distribuidores;
import Clases.DistribuidoresDao;
import Clases.Desplegable;
import Clases.Usuario;
import Clases.UsuariosDao;
import Clases.Cuidados;
import Clases.CuidadosDao;
import Clases.DetallesturnoDao;
import Clases.LoginDao;
import Clases.Opciones;
import Clases.OpcionesDao;
import Clases.Turno;
import Clases.TurnoDao;
import Clases.Verificaciones;
import Clases.login;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author moyde
 */
public class Sistema extends javax.swing.JFrame {

    login log = new login();
    LoginDao logDao = new LoginDao();
    Usuario us = new Usuario();
    UsuariosDao usuari = new UsuariosDao();
    Distribuidores di = new Distribuidores();
    DistribuidoresDao disDao = new DistribuidoresDao();
    Almacen al = new Almacen();
    AlmacenDao alDao = new AlmacenDao();
    Cuidados cui = new Cuidados();
    CuidadosDao cuiDao = new CuidadosDao();
    Turno tur = new Turno();
    TurnoDao turDao = new TurnoDao();
    Opciones opci = new Opciones();
    OpcionesDao opciDao = new OpcionesDao();
    DetallesTurno det = new DetallesTurno();
    DetallesturnoDao detDao = new DetallesturnoDao();
    Verificaciones ver = new Verificaciones();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel temp = new DefaultTableModel();
    DefaultTableModel tempo = new DefaultTableModel();
    int item;
    int itempo;

    /**
     * Creates new form Sistema
     */
    public Sistema() {
        initComponents();
        alDao.ListarDistribuidores(comboDistAlmacen);
        AutoCompleteDecorator.decorate(comboDistAlmacen);
        detDao.Productos(jcomboProductoTurno);
        AutoCompleteDecorator.decorate(jcomboProductoTurno);
        detDao.Cuidado(jcomboDescripcionTurno);
        AutoCompleteDecorator.decorate(jcomboDescripcionTurno);
        detDao.Usuari(jComboNombreTurno);
        AutoCompleteDecorator.decorate(jComboNombreTurno);
        personaTurno.setVisible(false);
        telefonoTurno.setVisible(false);
        codigoUsuario.setVisible(false);
        codigoDistribuidor.setVisible(false);
        codigoAlmacen.setVisible(false);
        codigoCuidado.setVisible(false);
        codigoArchivo.setVisible(false);
        txtCodigoEmpleados.setVisible(false);

        RelacionarDatos();

        this.setLocationRelativeTo(null);
    }

    public Sistema(login priv) {
        initComponents();
        alDao.ListarDistribuidores(comboDistAlmacen);
        AutoCompleteDecorator.decorate(comboDistAlmacen);
        detDao.Productos(jcomboProductoTurno);
        AutoCompleteDecorator.decorate(jcomboProductoTurno);
        detDao.Cuidado(jcomboDescripcionTurno);
        AutoCompleteDecorator.decorate(jcomboDescripcionTurno);
        detDao.Usuari(jComboNombreTurno);
        AutoCompleteDecorator.decorate(jComboNombreTurno);

        personaTurno.setVisible(false);
        telefonoTurno.setVisible(false);
        codigoUsuario.setVisible(false);
        codigoDistribuidor.setVisible(false);
        codigoAlmacen.setVisible(false);
        codigoCuidado.setVisible(false);
        codigoArchivo.setVisible(false);
        txtCodigoEmpleados.setVisible(false);
        RelacionarDatos();

        this.setLocationRelativeTo(null);
        if (priv.getPrivilegios().equals("Administrador")) {
            empleadoMenu.setText(priv.getNombre());
        } else {
            botonDistribuidoresMenu.setEnabled(false);
            botonAlmacenMenu.setEnabled(false);
            botonOpcionesMenu.setEnabled(false);
            botonCuidadosMenu.setEnabled(false);
            botonUsuariosMenu.setEnabled(false);
            botonEmpleadosMenu.setEnabled(false);
            empleadoMenu.setText(priv.getNombre());

        }
        JOptionPane.showMessageDialog(null, "Bienvenido " + priv.getNombre());
    }

    public void RelacionUsuario() {
        List<Usuario> RelacionUs = usuari.RelacionUsuarios();
        modelo = (DefaultTableModel) tablaUsuarios.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < RelacionUs.size(); i++) {
            ob[0] = RelacionUs.get(i).getCodigo();
            ob[1] = RelacionUs.get(i).getNombre();
            ob[2] = RelacionUs.get(i).getApellidos();
            ob[3] = RelacionUs.get(i).getPersonaContacto();
            ob[4] = RelacionUs.get(i).getTelefono();
            ob[5] = RelacionUs.get(i).getObservaciones();
            modelo.addRow(ob);

        }
        tablaUsuarios.setModel(modelo);
    }

    public void RelacionarTrabajadores() {
        List<login> Listar = logDao.RelacionarTrabajadores();
        modelo = (DefaultTableModel) tablaTrabajadores.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getCodigo();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getCorreo();
            ob[3] = Listar.get(i).getPass();
            ob[4] = Listar.get(i).getPrivilegios();
            modelo.addRow(ob);
        }
        tablaTrabajadores.setModel(modelo);

    }

    public void RelacionarDistribuidores() {
        List<Distribuidores> RelacionDi = disDao.RelacionarDistribuidores();
        modelo = (DefaultTableModel) tablaDistribuidores.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < RelacionDi.size(); i++) {
            ob[0] = RelacionDi.get(i).getCodigo();
            ob[1] = RelacionDi.get(i).getCif();
            ob[2] = RelacionDi.get(i).getNombre();
            ob[3] = RelacionDi.get(i).getTelefono();
            ob[4] = RelacionDi.get(i).getDireccion();
            ob[5] = RelacionDi.get(i).getCorreo();
            modelo.addRow(ob);

        }
        tablaDistribuidores.setModel(modelo);
    }

    public void RelacionarAlmacen() {
        List<Almacen> RelacionAl = alDao.RelacionAlmacen();
        modelo = (DefaultTableModel) tablaAlmacen.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < RelacionAl.size(); i++) {
            ob[0] = RelacionAl.get(i).getCodigo();
            ob[1] = RelacionAl.get(i).getproducto();
            ob[2] = RelacionAl.get(i).getCaducidad();
            ob[3] = RelacionAl.get(i).getDistribuidor();
            ob[4] = RelacionAl.get(i).getCantidad();
            modelo.addRow(ob);

        }
        tablaAlmacen.setModel(modelo);
    }

    public void RelacionarCuidados() {
        List<Cuidados> RelacionCui = cuiDao.RelacionCuidados();
        modelo = (DefaultTableModel) tablaCuidados.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < RelacionCui.size(); i++) {
            ob[0] = RelacionCui.get(i).getCodigo();
            ob[1] = RelacionCui.get(i).getDescripcion();

            modelo.addRow(ob);

        }
        tablaCuidados.setModel(modelo);
    }

    public void RelacionarDatos() {
        opci = opciDao.DatosEmpresa();
        NombreOpciones.setText("" + opci.getNombre());
        cifOpciones.setText("" + opci.getCif());
        direccionOpciones.setText("" + opci.getDireccion());
        telefonoOpciones.setText("" + opci.getTelefono());
        correoOpciones.setText("" + opci.getCorreo());
    }

    public void Relacionarturnos() {
        List<Turno> RelacionTur = turDao.RelacionTurnos();
        modelo = (DefaultTableModel) tablaArchivo.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < RelacionTur.size(); i++) {
            ob[0] = RelacionTur.get(i).getCodigo();
            ob[1] = RelacionTur.get(i).getUsuario();
            ob[2] = RelacionTur.get(i).getEmpleado();
            ob[3] = RelacionTur.get(i).getFeha();
            modelo.addRow(ob);

        }
        tablaArchivo.setModel(modelo);
    }

    public void limpiarTabla() {//creamos el metodo para que no se reproduzcan las rows en la tabla al darle al boton
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = --i;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonGastoMenu = new javax.swing.JButton();
        botonUsuariosMenu = new javax.swing.JButton();
        botonDistribuidoresMenu = new javax.swing.JButton();
        botonAlmacenMenu = new javax.swing.JButton();
        botonArchivoMenu = new javax.swing.JButton();
        botonOpcionesMenu = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botonCuidadosMenu = new javax.swing.JButton();
        empleadoMenu = new javax.swing.JTextField();
        botonEmpleadosMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pestanas = new javax.swing.JTabbedPane();
        pestanaTurno = new javax.swing.JPanel();
        codUsuaTurno = new javax.swing.JTextField();
        disponibleProdTurno = new javax.swing.JTextField();
        codProTurno = new javax.swing.JTextField();
        cantidadProdTurno = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        personaTurno = new javax.swing.JTextField();
        telefonoTurno = new javax.swing.JTextField();
        apellidosUsuarioTurno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProdTurno = new javax.swing.JTable();
        botonEliminarProTurno = new javax.swing.JButton();
        botonProIntroTurno = new javax.swing.JButton();
        botonGuardarTurno = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        codigoCuiTurno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        MedidaCuiTurno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        horaCuiTurno = new javax.swing.JTextField();
        botonIntCuidadoTurno = new javax.swing.JButton();
        botonEliminarCuiTurno = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCuiTurno = new javax.swing.JTable();
        turnocuinombre = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jcomboProductoTurno = new javax.swing.JComboBox<>();
        jcomboDescripcionTurno = new javax.swing.JComboBox<>();
        jComboNombreTurno = new javax.swing.JComboBox<>();
        pestanaUsuarios = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JTextField();
        apellidosUsuario = new javax.swing.JTextField();
        persUsuario = new javax.swing.JTextField();
        telefonoUsuario = new javax.swing.JTextField();
        observacionesUsuario = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        botonNuevoUsuario = new javax.swing.JButton();
        botonEliminarUsuario = new javax.swing.JButton();
        botonActualizarUsuario = new javax.swing.JButton();
        botonGuardarUsuario = new javax.swing.JButton();
        codigoUsuario = new javax.swing.JTextField();
        pestanaDistribuidores = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        direccionDistribuidor = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nombreDistribuidor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        telefonoDistribuidor = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cifDistribuidor = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        correoDistribuidor = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaDistribuidores = new javax.swing.JTable();
        codigoDistribuidor = new javax.swing.JTextField();
        pestanaAlmacen = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        productoAlmacen = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        caducidadAlmacen = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cantidadAlmacen = new javax.swing.JTextField();
        botonNuevoAlmacen = new javax.swing.JButton();
        botonEliminarAlmacen = new javax.swing.JButton();
        botonActualizarAlmacen = new javax.swing.JButton();
        botonAgregarAlmacen = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAlmacen = new javax.swing.JTable();
        comboDistAlmacen = new javax.swing.JComboBox<>();
        BotonExcelAlmacen = new javax.swing.JButton();
        codigoAlmacen = new javax.swing.JTextField();
        pestanaCuidados = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        descripcionCuidado = new javax.swing.JTextField();
        botonEliminarcuidado = new javax.swing.JButton();
        botonActualizarCuidado = new javax.swing.JButton();
        botonNuevoCuidado = new javax.swing.JButton();
        botonGuardarCuidado1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaCuidados = new javax.swing.JTable();
        codigoCuidado = new javax.swing.JTextField();
        pestanaArchivo = new javax.swing.JPanel();
        pdfArchivo = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaArchivo = new javax.swing.JTable();
        codigoArchivo = new javax.swing.JTextField();
        pestanaOpciones = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        NombreOpciones = new javax.swing.JTextField();
        direccionOpciones = new javax.swing.JTextField();
        cifOpciones = new javax.swing.JTextField();
        telefonoOpciones = new javax.swing.JTextField();
        correoOpciones = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        botonActualizarOpciones = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        codigoEmpleados = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtCorreoRegis = new javax.swing.JTextField();
        txtPassRegis = new javax.swing.JPasswordField();
        botonRegistrar = new javax.swing.JButton();
        txtNombreRegis = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jComboPrivi = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaTrabajadores = new javax.swing.JTable();
        txtCodigoEmpleados = new javax.swing.JTextField();
        botonEliminarEmpleado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        botonGastoMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonGastoMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonGastoMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonGastoMenu.setText("Turno");
        botonGastoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGastoMenuActionPerformed(evt);
            }
        });

        botonUsuariosMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonUsuariosMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonUsuariosMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonUsuariosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/usuarios.png"))); // NOI18N
        botonUsuariosMenu.setText("Usuarios");
        botonUsuariosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonUsuariosMenuActionPerformed(evt);
            }
        });

        botonDistribuidoresMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonDistribuidoresMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonDistribuidoresMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonDistribuidoresMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/distribuidor.png"))); // NOI18N
        botonDistribuidoresMenu.setText("Distribuidores");
        botonDistribuidoresMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDistribuidoresMenuActionPerformed(evt);
            }
        });

        botonAlmacenMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonAlmacenMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonAlmacenMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonAlmacenMenu.setText("Almacén");
        botonAlmacenMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAlmacenMenuMouseClicked(evt);
            }
        });
        botonAlmacenMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAlmacenMenuActionPerformed(evt);
            }
        });

        botonArchivoMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonArchivoMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonArchivoMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonArchivoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/archivo.png"))); // NOI18N
        botonArchivoMenu.setText("Archivo");
        botonArchivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonArchivoMenuActionPerformed(evt);
            }
        });

        botonOpcionesMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonOpcionesMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonOpcionesMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonOpcionesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/engranaje.png"))); // NOI18N
        botonOpcionesMenu.setText("Opciones");
        botonOpcionesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcionesMenuActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoGest.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );

        botonCuidadosMenu.setBackground(new java.awt.Color(255, 255, 255));
        botonCuidadosMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonCuidadosMenu.setForeground(new java.awt.Color(0, 0, 153));
        botonCuidadosMenu.setText("Cuidados");
        botonCuidadosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCuidadosMenuActionPerformed(evt);
            }
        });

        empleadoMenu.setEditable(false);
        empleadoMenu.setBackground(new java.awt.Color(255, 255, 255));
        empleadoMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        empleadoMenu.setForeground(new java.awt.Color(0, 0, 153));
        empleadoMenu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        empleadoMenu.setBorder(null);

        botonEmpleadosMenu.setBackground(new java.awt.Color(0, 0, 153));
        botonEmpleadosMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonEmpleadosMenu.setForeground(new java.awt.Color(255, 255, 255));
        botonEmpleadosMenu.setText("Empleados");
        botonEmpleadosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpleadosMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(botonDistribuidoresMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonCuidadosMenu)
                                    .addComponent(botonAlmacenMenu)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(botonGastoMenu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(botonUsuariosMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(botonOpcionesMenu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(botonArchivoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(empleadoMenu)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonEmpleadosMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(empleadoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonGastoMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonUsuariosMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonDistribuidoresMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonAlmacenMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCuidadosMenu)
                .addGap(18, 18, 18)
                .addComponent(botonArchivoMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonOpcionesMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(122, 122, 122)
                .addComponent(botonEmpleadosMenu)
                .addGap(34, 34, 34))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 830));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/decoracion.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 720, 220));

        pestanas.setBackground(new java.awt.Color(255, 255, 255));
        pestanas.setForeground(new java.awt.Color(0, 0, 153));
        pestanas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pestanas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        pestanas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pestanasMouseClicked(evt);
            }
        });

        pestanaTurno.setBackground(new java.awt.Color(255, 255, 255));
        pestanaTurno.setForeground(new java.awt.Color(0, 0, 153));

        codUsuaTurno.setBackground(new java.awt.Color(255, 255, 255));
        codUsuaTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        codUsuaTurno.setForeground(new java.awt.Color(0, 0, 153));
        codUsuaTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codUsuaTurnoActionPerformed(evt);
            }
        });
        codUsuaTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codUsuaTurnoKeyTyped(evt);
            }
        });

        disponibleProdTurno.setBackground(new java.awt.Color(255, 255, 255));
        disponibleProdTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        disponibleProdTurno.setForeground(new java.awt.Color(0, 0, 153));

        codProTurno.setBackground(new java.awt.Color(255, 255, 255));
        codProTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        codProTurno.setForeground(new java.awt.Color(0, 0, 153));
        codProTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codProTurnoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codProTurnoKeyTyped(evt);
            }
        });

        cantidadProdTurno.setBackground(new java.awt.Color(255, 255, 255));
        cantidadProdTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cantidadProdTurno.setForeground(new java.awt.Color(0, 0, 153));
        cantidadProdTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadProdTurnoKeyTyped(evt);
            }
        });

        jTextField6.setText("jTextField5");

        personaTurno.setBackground(new java.awt.Color(255, 255, 255));
        personaTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        personaTurno.setForeground(new java.awt.Color(0, 0, 153));

        telefonoTurno.setBackground(new java.awt.Color(255, 255, 255));
        telefonoTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        telefonoTurno.setForeground(new java.awt.Color(0, 0, 153));
        telefonoTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoTurnoActionPerformed(evt);
            }
        });

        apellidosUsuarioTurno.setBackground(new java.awt.Color(255, 255, 255));
        apellidosUsuarioTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        apellidosUsuarioTurno.setForeground(new java.awt.Color(0, 0, 153));

        tablaProdTurno.setBackground(new java.awt.Color(255, 255, 255));
        tablaProdTurno.setForeground(new java.awt.Color(0, 0, 153));
        tablaProdTurno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Cantidad", "Disponible"
            }
        ));
        jScrollPane1.setViewportView(tablaProdTurno);
        if (tablaProdTurno.getColumnModel().getColumnCount() > 0) {
            tablaProdTurno.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaProdTurno.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaProdTurno.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablaProdTurno.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        botonEliminarProTurno.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarProTurno.setForeground(new java.awt.Color(0, 0, 153));
        botonEliminarProTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        botonEliminarProTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarProTurnoActionPerformed(evt);
            }
        });

        botonProIntroTurno.setBackground(new java.awt.Color(255, 255, 255));
        botonProIntroTurno.setForeground(new java.awt.Color(0, 0, 153));
        botonProIntroTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/flechaAbajo.png"))); // NOI18N
        botonProIntroTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonProIntroTurnoActionPerformed(evt);
            }
        });

        botonGuardarTurno.setBackground(new java.awt.Color(0, 0, 153));
        botonGuardarTurno.setForeground(new java.awt.Color(255, 255, 255));
        botonGuardarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        botonGuardarTurno.setText("Guardar todo");
        botonGuardarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarTurnoActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Codigo");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Nombre");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Cantidad");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Disponible");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("Cod. Usuario");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("Nombre");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("Apellidos");

        codigoCuiTurno.setBackground(new java.awt.Color(255, 255, 255));
        codigoCuiTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        codigoCuiTurno.setForeground(new java.awt.Color(0, 0, 153));
        codigoCuiTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoCuiTurnoActionPerformed(evt);
            }
        });
        codigoCuiTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoCuiTurnoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigoCuiTurnoKeyTyped(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setText("Código");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 153));
        jLabel11.setText("Desccripción");

        MedidaCuiTurno.setBackground(new java.awt.Color(255, 255, 255));
        MedidaCuiTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        MedidaCuiTurno.setForeground(new java.awt.Color(0, 0, 153));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 153));
        jLabel12.setText("Medida");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 153));
        jLabel13.setText("Hora");

        horaCuiTurno.setBackground(new java.awt.Color(255, 255, 255));
        horaCuiTurno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        horaCuiTurno.setForeground(new java.awt.Color(0, 0, 153));

        botonIntCuidadoTurno.setBackground(new java.awt.Color(255, 255, 255));
        botonIntCuidadoTurno.setForeground(new java.awt.Color(0, 0, 153));
        botonIntCuidadoTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/flechaAbajo.png"))); // NOI18N
        botonIntCuidadoTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIntCuidadoTurnoActionPerformed(evt);
            }
        });

        botonEliminarCuiTurno.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarCuiTurno.setForeground(new java.awt.Color(0, 0, 153));
        botonEliminarCuiTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        botonEliminarCuiTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarCuiTurnoActionPerformed(evt);
            }
        });

        tablaCuiTurno.setBackground(new java.awt.Color(255, 255, 255));
        tablaCuiTurno.setForeground(new java.awt.Color(0, 0, 153));
        tablaCuiTurno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Medida", "Hora"
            }
        ));
        jScrollPane2.setViewportView(tablaCuiTurno);
        if (tablaCuiTurno.getColumnModel().getColumnCount() > 0) {
            tablaCuiTurno.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaCuiTurno.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaCuiTurno.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablaCuiTurno.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        turnocuinombre.setBackground(new java.awt.Color(255, 255, 255));
        turnocuinombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        turnocuinombre.setForeground(new java.awt.Color(0, 0, 153));
        turnocuinombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turnocuinombre.setText("Cuidados");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 153));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Productos");

        jcomboProductoTurno.setBackground(new java.awt.Color(255, 255, 255));
        jcomboProductoTurno.setForeground(new java.awt.Color(0, 0, 153));
        jcomboProductoTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboProductoTurnoActionPerformed(evt);
            }
        });
        jcomboProductoTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcomboProductoTurnoKeyPressed(evt);
            }
        });

        jcomboDescripcionTurno.setBackground(new java.awt.Color(255, 255, 255));
        jcomboDescripcionTurno.setForeground(new java.awt.Color(0, 0, 153));
        jcomboDescripcionTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboDescripcionTurnoActionPerformed(evt);
            }
        });

        jComboNombreTurno.setBackground(new java.awt.Color(255, 255, 255));
        jComboNombreTurno.setForeground(new java.awt.Color(0, 0, 153));
        jComboNombreTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNombreTurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestanaTurnoLayout = new javax.swing.GroupLayout(pestanaTurno);
        pestanaTurno.setLayout(pestanaTurnoLayout);
        pestanaTurnoLayout.setHorizontalGroup(
            pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaTurnoLayout.createSequentialGroup()
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codProTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jcomboProductoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cantidadProdTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(disponibleProdTurno)
                            .addComponent(jLabel6))
                        .addGap(60, 60, 60)
                        .addComponent(botonEliminarProTurno))
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(187, 187, 187)
                        .addComponent(botonProIntroTurno)))
                .addGap(136, 136, 136))
            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(92, 92, 92))
                            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                                .addComponent(codUsuaTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboNombreTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                                .addComponent(apellidosUsuarioTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(botonGuardarTurno))
                            .addComponent(jLabel9)))
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(personaTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(telefonoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaTurnoLayout.createSequentialGroup()
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigoCuiTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jcomboDescripcionTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(MedidaCuiTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(horaCuiTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEliminarCuiTurno))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaTurnoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(turnocuinombre)
                        .addGap(202, 202, 202)
                        .addComponent(botonIntCuidadoTurno)))
                .addGap(117, 117, 117))
        );
        pestanaTurnoLayout.setVerticalGroup(
            pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addComponent(botonProIntroTurno)
                        .addGap(18, 18, 18)
                        .addComponent(botonEliminarProTurno)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaTurnoLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaTurnoLayout.createSequentialGroup()
                                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codProTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcomboProductoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pestanaTurnoLayout.createSequentialGroup()
                                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cantidadProdTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(disponibleProdTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28)))
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonIntCuidadoTurno)
                    .addComponent(turnocuinombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonEliminarCuiTurno)
                    .addGroup(pestanaTurnoLayout.createSequentialGroup()
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horaCuiTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MedidaCuiTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoCuiTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcomboDescripcionTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codUsuaTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidosUsuarioTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonGuardarTurno)
                    .addComponent(jComboNombreTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pestanaTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personaTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pestanas.addTab("Turno", pestanaTurno);

        pestanaUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        pestanaUsuarios.setForeground(new java.awt.Color(0, 0, 153));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 153));
        jLabel17.setText("Nombre");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 153));
        jLabel18.setText("Apellidos");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setText("Persona de Contacto");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 153));
        jLabel20.setText("Teléfono de contacto");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 153));
        jLabel21.setText("Observaciones");

        nombreUsuario.setBackground(new java.awt.Color(255, 255, 255));
        nombreUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nombreUsuario.setForeground(new java.awt.Color(0, 0, 153));

        apellidosUsuario.setBackground(new java.awt.Color(255, 255, 255));
        apellidosUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        apellidosUsuario.setForeground(new java.awt.Color(0, 0, 153));

        persUsuario.setBackground(new java.awt.Color(255, 255, 255));
        persUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        persUsuario.setForeground(new java.awt.Color(0, 0, 153));
        persUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                persUsuarioActionPerformed(evt);
            }
        });

        telefonoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        telefonoUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        telefonoUsuario.setForeground(new java.awt.Color(0, 0, 153));

        observacionesUsuario.setBackground(new java.awt.Color(255, 255, 255));
        observacionesUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        observacionesUsuario.setForeground(new java.awt.Color(0, 0, 153));

        tablaUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setForeground(new java.awt.Color(0, 0, 153));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Apellidos", "Persona cont.", "Teléfono", "Observaciones"
            }
        ));
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaUsuarios);
        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(10);
            tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(10);
            tablaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        botonNuevoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevoUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonNuevoUsuario.setForeground(new java.awt.Color(0, 0, 153));
        botonNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar persona.png"))); // NOI18N
        botonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoUsuarioActionPerformed(evt);
            }
        });

        botonEliminarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonEliminarUsuario.setForeground(new java.awt.Color(0, 0, 153));
        botonEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        botonEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarUsuarioActionPerformed(evt);
            }
        });

        botonActualizarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        botonActualizarUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonActualizarUsuario.setForeground(new java.awt.Color(0, 0, 153));
        botonActualizarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        botonActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarUsuarioActionPerformed(evt);
            }
        });

        botonGuardarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        botonGuardarUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonGuardarUsuario.setForeground(new java.awt.Color(0, 0, 153));
        botonGuardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        botonGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestanaUsuariosLayout = new javax.swing.GroupLayout(pestanaUsuarios);
        pestanaUsuarios.setLayout(pestanaUsuariosLayout);
        pestanaUsuariosLayout.setHorizontalGroup(
            pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(108, 108, 108)
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                                .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                                .addComponent(botonGuardarUsuario))
                            .addComponent(botonActualizarUsuario))
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(botonEliminarUsuario))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaUsuariosLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(botonNuevoUsuario)))
                        .addGap(49, 49, 49))
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(27, 27, 27)
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(telefonoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(persUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaUsuariosLayout.createSequentialGroup()
                        .addComponent(observacionesUsuario)
                        .addContainerGap())
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codigoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
            .addComponent(jScrollPane3)
        );
        pestanaUsuariosLayout.setVerticalGroup(
            pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(botonGuardarUsuario)
                                    .addComponent(botonEliminarUsuario)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaUsuariosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)))
                        .addGap(26, 26, 26)
                        .addComponent(botonNuevoUsuario))
                    .addComponent(botonActualizarUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellidosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addGap(18, 18, 18)
                .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(persUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(telefonoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pestanaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(observacionesUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(codigoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaUsuariosLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pestanas.addTab("usuarios", pestanaUsuarios);

        pestanaDistribuidores.setBackground(new java.awt.Color(255, 255, 255));
        pestanaDistribuidores.setForeground(new java.awt.Color(0, 0, 153));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 153));
        jLabel22.setText("Nombre");

        direccionDistribuidor.setBackground(new java.awt.Color(255, 255, 255));
        direccionDistribuidor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        direccionDistribuidor.setForeground(new java.awt.Color(0, 0, 153));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 153));
        jLabel23.setText("Correo");

        nombreDistribuidor.setBackground(new java.awt.Color(255, 255, 255));
        nombreDistribuidor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nombreDistribuidor.setForeground(new java.awt.Color(0, 0, 153));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 153));
        jLabel24.setText("CIF");

        telefonoDistribuidor.setBackground(new java.awt.Color(255, 255, 255));
        telefonoDistribuidor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        telefonoDistribuidor.setForeground(new java.awt.Color(0, 0, 153));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 153));
        jLabel25.setText("Teléfono ");

        cifDistribuidor.setBackground(new java.awt.Color(255, 255, 255));
        cifDistribuidor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cifDistribuidor.setForeground(new java.awt.Color(0, 0, 153));

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 153));
        jLabel26.setText("Dirección");

        correoDistribuidor.setBackground(new java.awt.Color(255, 255, 255));
        correoDistribuidor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        correoDistribuidor.setForeground(new java.awt.Color(0, 0, 153));
        correoDistribuidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoDistribuidorActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 153));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar persona.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 153));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 153));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 153));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        tablaDistribuidores.setBackground(new java.awt.Color(255, 255, 255));
        tablaDistribuidores.setForeground(new java.awt.Color(0, 0, 153));
        tablaDistribuidores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "CIF", "Nombre", "Teléfono", "Dirección", "Correo"
            }
        ));
        tablaDistribuidores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDistribuidoresMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaDistribuidores);
        if (tablaDistribuidores.getColumnModel().getColumnCount() > 0) {
            tablaDistribuidores.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaDistribuidores.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaDistribuidores.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaDistribuidores.getColumnModel().getColumn(3).setPreferredWidth(10);
            tablaDistribuidores.getColumnModel().getColumn(4).setPreferredWidth(100);
            tablaDistribuidores.getColumnModel().getColumn(5).setPreferredWidth(40);
        }

        javax.swing.GroupLayout pestanaDistribuidoresLayout = new javax.swing.GroupLayout(pestanaDistribuidores);
        pestanaDistribuidores.setLayout(pestanaDistribuidoresLayout);
        pestanaDistribuidoresLayout.setHorizontalGroup(
            pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel22)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23))
                .addGap(75, 75, 75)
                .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nombreDistribuidor)
                            .addComponent(telefonoDistribuidor)
                            .addComponent(cifDistribuidor))
                        .addGap(166, 166, 166)
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(50, 50, 50)
                                .addComponent(jButton6))
                            .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5)))
                        .addGap(19, 19, 19))
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addComponent(direccionDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addComponent(correoDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codigoDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        pestanaDistribuidoresLayout.setVerticalGroup(
            pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton6)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pestanaDistribuidoresLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cifDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))))
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaDistribuidoresLayout.createSequentialGroup()
                                .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nombreDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addGap(10, 10, 10))))
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)))
                .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(codigoDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaDistribuidoresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefonoDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(direccionDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pestanaDistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(correoDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pestanas.addTab("distribuidores", pestanaDistribuidores);

        pestanaAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        pestanaAlmacen.setForeground(new java.awt.Color(0, 0, 153));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 153));
        jLabel27.setText("Producto");

        productoAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        productoAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        productoAlmacen.setForeground(new java.awt.Color(0, 0, 153));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 153));
        jLabel28.setText("Caducidad/Receta");

        caducidadAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        caducidadAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        caducidadAlmacen.setForeground(new java.awt.Color(0, 0, 153));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 153));
        jLabel29.setText("Distribuidor");

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 153));
        jLabel35.setText("Cantidad");

        cantidadAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        cantidadAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cantidadAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        cantidadAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadAlmacenKeyTyped(evt);
            }
        });

        botonNuevoAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevoAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonNuevoAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        botonNuevoAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        botonNuevoAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoAlmacenActionPerformed(evt);
            }
        });

        botonEliminarAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonEliminarAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        botonEliminarAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        botonEliminarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarAlmacenActionPerformed(evt);
            }
        });

        botonActualizarAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        botonActualizarAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonActualizarAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        botonActualizarAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        botonActualizarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarAlmacenActionPerformed(evt);
            }
        });

        botonAgregarAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        botonAgregarAlmacen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonAgregarAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        botonAgregarAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        botonAgregarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarAlmacenActionPerformed(evt);
            }
        });

        tablaAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        tablaAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        tablaAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Producto", "Caducidad", "Distribuidor", "Cantidad"
            }
        ));
        tablaAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlmacenMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaAlmacen);
        if (tablaAlmacen.getColumnModel().getColumnCount() > 0) {
            tablaAlmacen.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaAlmacen.getColumnModel().getColumn(1).setPreferredWidth(30);
            tablaAlmacen.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaAlmacen.getColumnModel().getColumn(3).setPreferredWidth(20);
            tablaAlmacen.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        comboDistAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        comboDistAlmacen.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        comboDistAlmacen.setForeground(new java.awt.Color(0, 0, 153));
        comboDistAlmacen.setMaximumRowCount(10);
        comboDistAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDistAlmacenActionPerformed(evt);
            }
        });

        BotonExcelAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        BotonExcelAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        BotonExcelAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonExcelAlmacenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestanaAlmacenLayout = new javax.swing.GroupLayout(pestanaAlmacen);
        pestanaAlmacen.setLayout(pestanaAlmacenLayout);
        pestanaAlmacenLayout.setHorizontalGroup(
            pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel29))
                        .addGap(57, 57, 57)
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidadAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDistAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productoAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(caducidadAlmacen))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaAlmacenLayout.createSequentialGroup()
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                                .addComponent(botonActualizarAlmacen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonNuevoAlmacen))
                            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                                .addComponent(botonAgregarAlmacen)
                                .addGap(45, 45, 45)
                                .addComponent(botonEliminarAlmacen)))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaAlmacenLayout.createSequentialGroup()
                        .addComponent(BotonExcelAlmacen)
                        .addGap(74, 74, 74))))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(codigoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pestanaAlmacenLayout.setVerticalGroup(
            pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(productoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(caducidadAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(botonAgregarAlmacen)
                                    .addComponent(botonEliminarAlmacen))))
                        .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pestanaAlmacenLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(comboDistAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaAlmacenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonNuevoAlmacen))))
                    .addComponent(botonActualizarAlmacen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(cantidadAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BotonExcelAlmacen))
                .addGap(16, 16, 16)
                .addComponent(codigoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pestanas.addTab("almacen", pestanaAlmacen);

        pestanaCuidados.setBackground(new java.awt.Color(255, 255, 255));
        pestanaCuidados.setForeground(new java.awt.Color(0, 0, 153));

        jLabel41.setBackground(new java.awt.Color(255, 255, 255));
        jLabel41.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 153));
        jLabel41.setText("Descripción ");

        descripcionCuidado.setBackground(new java.awt.Color(255, 255, 255));
        descripcionCuidado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        descripcionCuidado.setForeground(new java.awt.Color(0, 0, 153));
        descripcionCuidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionCuidadoActionPerformed(evt);
            }
        });

        botonEliminarcuidado.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarcuidado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        botonEliminarcuidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarcuidadoActionPerformed(evt);
            }
        });

        botonActualizarCuidado.setBackground(new java.awt.Color(255, 255, 255));
        botonActualizarCuidado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        botonActualizarCuidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarCuidadoActionPerformed(evt);
            }
        });

        botonNuevoCuidado.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevoCuidado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        botonNuevoCuidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoCuidadoActionPerformed(evt);
            }
        });

        botonGuardarCuidado1.setBackground(new java.awt.Color(255, 255, 255));
        botonGuardarCuidado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/CUIDADOS.png"))); // NOI18N
        botonGuardarCuidado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarCuidado1ActionPerformed(evt);
            }
        });

        tablaCuidados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cuidado"
            }
        ));
        tablaCuidados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuidadosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaCuidados);
        if (tablaCuidados.getColumnModel().getColumnCount() > 0) {
            tablaCuidados.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaCuidados.getColumnModel().getColumn(1).setPreferredWidth(90);
        }

        javax.swing.GroupLayout pestanaCuidadosLayout = new javax.swing.GroupLayout(pestanaCuidados);
        pestanaCuidados.setLayout(pestanaCuidadosLayout);
        pestanaCuidadosLayout.setHorizontalGroup(
            pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaCuidadosLayout.createSequentialGroup()
                .addContainerGap(502, Short.MAX_VALUE)
                .addGroup(pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                        .addComponent(botonActualizarCuidado)
                        .addGap(36, 36, 36)
                        .addComponent(botonNuevoCuidado))
                    .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                        .addComponent(botonGuardarCuidado1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(botonEliminarcuidado)))
                .addGap(44, 44, 44))
            .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                .addGroup(pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcionCuidado, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(codigoCuidado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pestanaCuidadosLayout.setVerticalGroup(
            pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                .addGroup(pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(descripcionCuidado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(codigoCuidado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))
                    .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonGuardarCuidado1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEliminarcuidado))
                        .addGroup(pestanaCuidadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestanaCuidadosLayout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(botonNuevoCuidado))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaCuidadosLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(botonActualizarCuidado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pestanas.addTab("Cuidados", pestanaCuidados);

        pestanaArchivo.setBackground(new java.awt.Color(255, 255, 255));
        pestanaArchivo.setForeground(new java.awt.Color(0, 0, 153));

        pdfArchivo.setBackground(new java.awt.Color(255, 255, 255));
        pdfArchivo.setForeground(new java.awt.Color(0, 0, 153));
        pdfArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        pdfArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfArchivoActionPerformed(evt);
            }
        });

        tablaArchivo.setBackground(new java.awt.Color(255, 255, 255));
        tablaArchivo.setForeground(new java.awt.Color(0, 0, 153));
        tablaArchivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Usuario", "Empleado", "Fecha"
            }
        ));
        tablaArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArchivoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaArchivo);

        javax.swing.GroupLayout pestanaArchivoLayout = new javax.swing.GroupLayout(pestanaArchivo);
        pestanaArchivo.setLayout(pestanaArchivoLayout);
        pestanaArchivoLayout.setHorizontalGroup(
            pestanaArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(pestanaArchivoLayout.createSequentialGroup()
                .addGroup(pestanaArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaArchivoLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(pdfArchivo))
                    .addGroup(pestanaArchivoLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(codigoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pestanaArchivoLayout.setVerticalGroup(
            pestanaArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaArchivoLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(pdfArchivo)
                .addGap(24, 24, 24)
                .addComponent(codigoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pestanas.addTab("Archivo", new javax.swing.ImageIcon(getClass().getResource("/Img/archivo.png")), pestanaArchivo); // NOI18N

        pestanaOpciones.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 153));
        jLabel30.setText("NOMBRE");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 153));
        jLabel31.setText("DIRECCIÓN");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 153));
        jLabel32.setText("CIF");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 153));
        jLabel33.setText("TELÉFONO");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 153));
        jLabel34.setText("CORREO ELECTRONICO");

        NombreOpciones.setBackground(new java.awt.Color(255, 255, 255));
        NombreOpciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        NombreOpciones.setForeground(new java.awt.Color(0, 0, 153));
        NombreOpciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        direccionOpciones.setBackground(new java.awt.Color(255, 255, 255));
        direccionOpciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        direccionOpciones.setForeground(new java.awt.Color(0, 0, 153));
        direccionOpciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cifOpciones.setBackground(new java.awt.Color(255, 255, 255));
        cifOpciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cifOpciones.setForeground(new java.awt.Color(0, 0, 153));
        cifOpciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        telefonoOpciones.setBackground(new java.awt.Color(255, 255, 255));
        telefonoOpciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        telefonoOpciones.setForeground(new java.awt.Color(0, 0, 153));
        telefonoOpciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        correoOpciones.setBackground(new java.awt.Color(255, 255, 255));
        correoOpciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        correoOpciones.setForeground(new java.awt.Color(0, 0, 153));
        correoOpciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(0, 0, 153));
        jTextField8.setText("Datos Residencia");

        botonActualizarOpciones.setBackground(new java.awt.Color(255, 255, 255));
        botonActualizarOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        botonActualizarOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestanaOpcionesLayout = new javax.swing.GroupLayout(pestanaOpciones);
        pestanaOpciones.setLayout(pestanaOpcionesLayout);
        pestanaOpcionesLayout.setHorizontalGroup(
            pestanaOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestanaOpcionesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(telefonoOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
            .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                .addGroup(pestanaOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(botonActualizarOpciones))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel34))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(correoOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel33))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(direccionOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel31))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel32))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(cifOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel30))
                    .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(NombreOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pestanaOpcionesLayout.setVerticalGroup(
            pestanaOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestanaOpcionesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NombreOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(cifOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addComponent(direccionOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(telefonoOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addGap(32, 32, 32)
                .addComponent(correoOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonActualizarOpciones)
                .addGap(52, 52, 52))
        );

        pestanas.addTab("Opciones", new javax.swing.ImageIcon(getClass().getResource("/Img/engranaje.png")), pestanaOpciones); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        codigoEmpleados.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/candado.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 153));
        jLabel16.setText("Correo electronico");

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 153));
        jLabel36.setText("Password");

        txtCorreoRegis.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreoRegis.setForeground(new java.awt.Color(0, 0, 153));

        txtPassRegis.setBackground(new java.awt.Color(255, 255, 255));
        txtPassRegis.setForeground(new java.awt.Color(0, 0, 153));

        botonRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        botonRegistrar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        botonRegistrar.setForeground(new java.awt.Color(0, 0, 153));
        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        txtNombreRegis.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreRegis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNombreRegis.setForeground(new java.awt.Color(0, 0, 153));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 153));
        jLabel37.setText("Nombre");

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 153));
        jLabel38.setText("Privilegios");

        jComboPrivi.setBackground(new java.awt.Color(255, 255, 255));
        jComboPrivi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboPrivi.setForeground(new java.awt.Color(0, 0, 153));
        jComboPrivi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Empleado" }));
        jComboPrivi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPriviActionPerformed(evt);
            }
        });

        tablaTrabajadores.setBackground(new java.awt.Color(255, 255, 255));
        tablaTrabajadores.setForeground(new java.awt.Color(0, 0, 153));
        tablaTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Correo", "PassWord", "Privilegios "
            }
        ));
        tablaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTrabajadoresMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaTrabajadores);
        if (tablaTrabajadores.getColumnModel().getColumnCount() > 0) {
            tablaTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaTrabajadores.getColumnModel().getColumn(1).setPreferredWidth(40);
            tablaTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaTrabajadores.getColumnModel().getColumn(3).setPreferredWidth(10);
            tablaTrabajadores.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        txtCodigoEmpleados.setEditable(false);

        botonEliminarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar rojo.png"))); // NOI18N
        botonEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout codigoEmpleadosLayout = new javax.swing.GroupLayout(codigoEmpleados);
        codigoEmpleados.setLayout(codigoEmpleadosLayout);
        codigoEmpleadosLayout.setHorizontalGroup(
            codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36)
                    .addComponent(txtPassRegis)
                    .addComponent(txtCorreoRegis)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel16)
                    .addComponent(txtNombreRegis)
                    .addComponent(jComboPrivi, 0, 232, Short.MAX_VALUE))
                .addGroup(codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodigoEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel14)
                        .addGap(150, 150, 150))
                    .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(botonEliminarEmpleado)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        codigoEmpleadosLayout.setVerticalGroup(
            codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(txtCorreoRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addComponent(txtPassRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37))
                    .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCodigoEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(codigoEmpleadosLayout.createSequentialGroup()
                        .addComponent(txtNombreRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addGroup(codigoEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboPrivi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(botonEliminarEmpleado, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(codigoEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 681, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(codigoEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pestanas.addTab("tab8", jPanel2);

        getContentPane().add(pestanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 710, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGastoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGastoMenuActionPerformed
        // TODO add your handling code here:
        pestanas.setSelectedIndex(0);
    }//GEN-LAST:event_botonGastoMenuActionPerformed

    private void botonUsuariosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonUsuariosMenuActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        RelacionUsuario();
        pestanas.setSelectedIndex(1);

    }//GEN-LAST:event_botonUsuariosMenuActionPerformed

    private void botonDistribuidoresMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDistribuidoresMenuActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        RelacionarDistribuidores();
        pestanas.setSelectedIndex(2);


    }//GEN-LAST:event_botonDistribuidoresMenuActionPerformed

    private void botonAlmacenMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAlmacenMenuActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        RelacionarAlmacen();
        pestanas.setSelectedIndex(3);


    }//GEN-LAST:event_botonAlmacenMenuActionPerformed

    private void botonArchivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonArchivoMenuActionPerformed
        // TODO add your handling code here:
        pestanas.setSelectedIndex(5);
        limpiarTabla();
        Relacionarturnos();
    }//GEN-LAST:event_botonArchivoMenuActionPerformed

    private void descripcionCuidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripcionCuidadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descripcionCuidadoActionPerformed

    private void botonEliminarcuidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarcuidadoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(codigoCuidado.getText())) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el cuidado?");
            if (confirmacion == 0) {
                int codigo = Integer.parseInt(codigoCuidado.getText());
                cuiDao.EliminarCuidado(codigo);
                limpiarTabla();
                RelacionarCuidados();
                LimpiarCuidados();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cuidado de la Tabla");
        }
    }//GEN-LAST:event_botonEliminarcuidadoActionPerformed

    private void botonActualizarCuidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarCuidadoActionPerformed
        // TODO add your handling code here:
    	if ("".equals(codigoCuidado.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un Cuidado de la Tabla");

        } else {

            if ("".equals(descripcionCuidado.getText())) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");

            } else {
                cui.setDescripcion(descripcionCuidado.getText());
                cui.setCodigo(Integer.parseInt(codigoCuidado.getText()));
                cuiDao.ActualizarCuidado(cui);
                JOptionPane.showMessageDialog(null, "Cuidado Actualizado");
                limpiarTabla();
               
                RelacionarCuidados();
                 LimpiarCuidados();
            }
        }
    }//GEN-LAST:event_botonActualizarCuidadoActionPerformed

    private void botonNuevoCuidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoCuidadoActionPerformed
        // TODO add your handling code here:

        LimpiarCuidados();

    }//GEN-LAST:event_botonNuevoCuidadoActionPerformed

    private void botonGuardarCuidado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCuidado1ActionPerformed
        // TODO add your handling code here:
        if (!"".equals(descripcionCuidado.getText())) {

            cui.setDescripcion(descripcionCuidado.getText());
            cuiDao.RegistrarCuidado(cui);
            JOptionPane.showMessageDialog(null, "Nuevo Cuidado Guardado");

            limpiarTabla();
            RelacionarCuidados();
            LimpiarCuidados();

        } else {
            JOptionPane.showMessageDialog(null, "No Dejes Campos Vacios");
        }

    }//GEN-LAST:event_botonGuardarCuidado1ActionPerformed

    private void pestanasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestanasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pestanasMouseClicked

    private void codUsuaTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codUsuaTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codUsuaTurnoActionPerformed

    private void telefonoTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoTurnoActionPerformed

    private void correoDistribuidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoDistribuidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoDistribuidorActionPerformed

    private void BotonExcelAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonExcelAlmacenActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            GenerarExcel.Inventario();
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonExcelAlmacenActionPerformed

    private void botonCuidadosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCuidadosMenuActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        LimpiarCuidados();
        RelacionarCuidados();
        pestanas.setSelectedIndex(4);
    }//GEN-LAST:event_botonCuidadosMenuActionPerformed

    private void botonOpcionesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcionesMenuActionPerformed
        // TODO add your handling code here:
        pestanas.setSelectedIndex(6);
    }//GEN-LAST:event_botonOpcionesMenuActionPerformed

    private void botonGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarUsuarioActionPerformed
        // TODO add your handling code here:
        if (!"".equals(nombreUsuario.getText()) || !"".equals(apellidosUsuario.getText()) || !"".equals(persUsuario.getText()) || !"".equals(telefonoUsuario.getText()) || !"".equals(observacionesUsuario.getText())) {
            us.setNombre(nombreUsuario.getText());
            us.setApellidos(apellidosUsuario.getText());
            us.setPersonaContacto(persUsuario.getText());
            us.setTelefono(telefonoUsuario.getText());
            us.setObservaciones(observacionesUsuario.getText());
            usuari.RegistrarUsuario(us);
            limpiarTabla();
            LimpiarUsuario();
            RelacionUsuario();
            JOptionPane.showMessageDialog(null, "Usuatio Guardado");
        } else {
            JOptionPane.showMessageDialog(null, "Revisa Rellenar todo el Formulario");
        }
    }//GEN-LAST:event_botonGuardarUsuarioActionPerformed

    private void botonActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarUsuarioActionPerformed
        // TODO add your handling code here:
        if ("".equals(codigoUsuario.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un Usuario de la Tabla");

        } else {
            if ("".equals(nombreUsuario.getText()) || "".equals(apellidosUsuario.getText()) || "".equals(persUsuario.getText()) || "".equals(telefonoUsuario.getText()) || "".equals(observacionesUsuario.getText())) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");

            } else {
                us.setNombre(nombreUsuario.getText());
                us.setApellidos(apellidosUsuario.getText());
                us.setPersonaContacto(persUsuario.getText());
                us.setTelefono(telefonoUsuario.getText());
                us.setObservaciones(observacionesUsuario.getText());
                us.setCodigo(Integer.parseInt(codigoUsuario.getText()));
                usuari.ActualizarUsuario(us);
                JOptionPane.showMessageDialog(null, "Usuario Actualizado");
                limpiarTabla();
                LimpiarUsuario();
                RelacionUsuario();
            }
        }
    }//GEN-LAST:event_botonActualizarUsuarioActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if ("".equals(cifDistribuidor.getText()) || "".equals(nombreDistribuidor.getText()) || "".equals(telefonoDistribuidor.getText()) || "".equals(direccionDistribuidor.getText()) || "".equals(correoDistribuidor.getText())) {
            JOptionPane.showMessageDialog(null, "No Dejes Campos Vacios");
        } else {
            di.setCif(cifDistribuidor.getText());
            di.setNombre(nombreDistribuidor.getText());
            di.setTelefono(telefonoDistribuidor.getText());
            di.setDireccion(direccionDistribuidor.getText());
            di.setCorreo(correoDistribuidor.getText());
            disDao.RegistrarDistribuidor(di);
            JOptionPane.showMessageDialog(null, "Distribuidor Guardado");
            limpiarTabla();
            RelacionarDistribuidores();
            LimpiarDistribuidor();

        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        // TODO add your handling code here:
        int fila = tablaUsuarios.rowAtPoint(evt.getPoint());
        codigoUsuario.setText(tablaUsuarios.getValueAt(fila, 0).toString());
        nombreUsuario.setText(tablaUsuarios.getValueAt(fila, 1).toString());
        apellidosUsuario.setText(tablaUsuarios.getValueAt(fila, 2).toString());
        persUsuario.setText(tablaUsuarios.getValueAt(fila, 3).toString());
        telefonoUsuario.setText(tablaUsuarios.getValueAt(fila, 4).toString());
        observacionesUsuario.setText(tablaUsuarios.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void botonEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarUsuarioActionPerformed
        // TODO add your handling code here:
        if (!"".equals(codigoUsuario.getText())) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el Usuario?");
            if (confirmacion == 0) {
                int id = Integer.parseInt(codigoUsuario.getText());
                usuari.EliminarUsuario(id);
                limpiarTabla();
                LimpiarUsuario();
                RelacionUsuario();
            }
        }
    }//GEN-LAST:event_botonEliminarUsuarioActionPerformed

    private void botonNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoUsuarioActionPerformed
        // TODO add your handling code here:
    	LimpiarUsuario();
    }//GEN-LAST:event_botonNuevoUsuarioActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (!"".equals(codigoDistribuidor.getText())) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el Distribuidor?");
            if (confirmacion == 0) {
                int id = Integer.parseInt(codigoDistribuidor.getText());
                disDao.EliminarDistribuidor(id);
                limpiarTabla();
                RelacionarDistribuidores();
                LimpiarDistribuidor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Distribuidor de la Tabla");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if ("".equals(codigoDistribuidor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un Distribuidor de la Tabla");

        } else {
            if ("".equals(cifDistribuidor.getText()) || "".equals(nombreDistribuidor.getText()) || "".equals(telefonoDistribuidor.getText()) || "".equals(direccionDistribuidor.getText()) || "".equals(correoDistribuidor.getText())) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");

            } else {
                di.setCif(cifDistribuidor.getText());
                di.setNombre(nombreDistribuidor.getText());
                di.setTelefono(telefonoDistribuidor.getText());
                di.setDireccion(direccionDistribuidor.getText());
                di.setCorreo(correoDistribuidor.getText());
                di.setCif(cifDistribuidor.getText());
                di.setCodigo(Integer.parseInt(codigoDistribuidor.getText()));
                disDao.ActualizarDistribuidor(di);
                JOptionPane.showMessageDialog(null, "Distribuidor Actualizado");
                limpiarTabla();
                LimpiarDistribuidor();
                RelacionarDistribuidores();
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        LimpiarDistribuidor();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void botonAgregarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarAlmacenActionPerformed
        // TODO add your handling code here:
        if (!"".equals(productoAlmacen.getText()) || !"".equals(caducidadAlmacen.getText()) || !"".equals(comboDistAlmacen.getSelectedItem()) || !"".equals(cantidadAlmacen.getText())) {

            al.setproducto(productoAlmacen.getText());
            al.setCaducidad(caducidadAlmacen.getText());
            al.setDistribuidor(comboDistAlmacen.getSelectedItem().toString());
            al.setCantidad(Integer.parseInt(cantidadAlmacen.getText()));
            alDao.RegistrarProducto(al);
            JOptionPane.showMessageDialog(null, "Producto Guardado");

            limpiarTabla();
            RelacionarAlmacen();
            LimpiarAlmacen();

        } else {
            JOptionPane.showMessageDialog(null, "No Dejes Campos Vacios");
        }

    }//GEN-LAST:event_botonAgregarAlmacenActionPerformed

    private void botonEliminarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarAlmacenActionPerformed
        // TODO add your handling code here:
        if (!"".equals(codigoAlmacen.getText())) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el produco?");
            if (confirmacion == 0) {
                int codigo = Integer.parseInt(codigoAlmacen.getText());
                alDao.EliminarAlmacen(codigo);
                limpiarTabla();
                RelacionarAlmacen();
                LimpiarAlmacen();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la Tabla");
        }

    }//GEN-LAST:event_botonEliminarAlmacenActionPerformed

    private void botonActualizarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarAlmacenActionPerformed
        // TODO add your handling code here:
        if ("".equals(codigoAlmacen.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un Producto de la Tabla");

        } else {

            if ("".equals(productoAlmacen.getText()) || "".equals(caducidadAlmacen.getText()) || "".equals(comboDistAlmacen.getSelectedItem()) || "".equals(cantidadAlmacen.getText())) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");

            } else {
                al.setproducto(productoAlmacen.getText());
                al.setCaducidad(caducidadAlmacen.getText());
                al.setDistribuidor(comboDistAlmacen.getSelectedItem().toString());
                al.setCantidad(Integer.parseInt(cantidadAlmacen.getText()));
                al.setCodigo(Integer.parseInt(codigoAlmacen.getText()));
                alDao.ActualizarAlmacen(al);
                JOptionPane.showMessageDialog(null, "Producto Actualizado");
                limpiarTabla();
                LimpiarAlmacen();
                RelacionarAlmacen();
            }
        }
    }//GEN-LAST:event_botonActualizarAlmacenActionPerformed

    private void botonNuevoAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoAlmacenActionPerformed
        // TODO add your handling code here:
        LimpiarAlmacen();
    }//GEN-LAST:event_botonNuevoAlmacenActionPerformed

    private void comboDistAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDistAlmacenActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_comboDistAlmacenActionPerformed

    private void tablaDistribuidoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDistribuidoresMouseClicked
        // TODO add your handling code here:
        int fila = tablaDistribuidores.rowAtPoint(evt.getPoint());
        codigoDistribuidor.setText(tablaDistribuidores.getValueAt(fila, 0).toString());
        cifDistribuidor.setText(tablaDistribuidores.getValueAt(fila, 1).toString());
        nombreDistribuidor.setText(tablaDistribuidores.getValueAt(fila, 2).toString());
        telefonoDistribuidor.setText(tablaDistribuidores.getValueAt(fila, 3).toString());
        direccionDistribuidor.setText(tablaDistribuidores.getValueAt(fila, 4).toString());
        correoDistribuidor.setText(tablaDistribuidores.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tablaDistribuidoresMouseClicked

    private void tablaAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlmacenMouseClicked
        // TODO add your handling code here:
        int fila = tablaAlmacen.rowAtPoint(evt.getPoint());

        codigoAlmacen.setText(tablaAlmacen.getValueAt(fila, 0).toString());
        productoAlmacen.setText(tablaAlmacen.getValueAt(fila, 1).toString());
        caducidadAlmacen.setText(tablaAlmacen.getValueAt(fila, 2).toString());
        comboDistAlmacen.setSelectedItem(tablaAlmacen.getValueAt(fila, 3).toString());
        cantidadAlmacen.setText(tablaAlmacen.getValueAt(fila, 4).toString());

    }//GEN-LAST:event_tablaAlmacenMouseClicked

    private void botonProIntroTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonProIntroTurnoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(cantidadProdTurno.getText())) {
            int codipr = Integer.parseInt(codProTurno.getText());
            String nomPro = (String) jcomboProductoTurno.getSelectedItem();
            int cant = Integer.parseInt(cantidadProdTurno.getText());
            int disp = Integer.parseInt(disponibleProdTurno.getText());
            if (disp >= cant) {
                item = item + 1;
                temp = (DefaultTableModel) tablaProdTurno.getModel();

                ArrayList listaPr = new ArrayList();
                listaPr.add(item);
                listaPr.add(codipr);
                listaPr.add(nomPro);
                listaPr.add(cant);
                listaPr.add(disp);
                Object[] O = new Object[4];
                O[0] = listaPr.get(1);
                O[1] = listaPr.get(2);
                O[2] = listaPr.get(3);
                O[3] = listaPr.get(4);
                temp.addRow(O);
                tablaProdTurno.setModel(temp);
                LimpiarProdTurno();
                codProTurno.requestFocus();

            } else {
                JOptionPane.showMessageDialog(null, "No disponible suficiente en el Almacén");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
        }
    }//GEN-LAST:event_botonProIntroTurnoActionPerformed

    private void codProTurnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codProTurnoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(codProTurno.getText())) {
                int codPr = Integer.parseInt(codProTurno.getText());
                al = alDao.EncontrarPorCodigo(codPr);
                if (al.getproducto() != null) {
                    jcomboProductoTurno.setSelectedItem(al.getproducto());
                    //nombreProdTurno.setText("" + al.getproducto());
                    disponibleProdTurno.setText("" + al.getCantidad());
                    cantidadProdTurno.requestFocus();//pone el cursor en cantidad para introducirla

                } else {
                    LimpiarProdTurno();
                    codProTurno.requestFocus();
                    JOptionPane.showMessageDialog(null, "No Hay Codigo asociado");
                }
            } else {
                codProTurno.requestFocus();
                JOptionPane.showMessageDialog(null, "Introduce un codigo");
            }

        }
    }//GEN-LAST:event_codProTurnoKeyPressed

    private void codigoCuiTurnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoCuiTurnoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(codigoCuiTurno.getText())) {
                String codCui = codigoCuiTurno.getText();
                cui = cuiDao.EncontrarPorCodigoCui(codCui);
                if (cui.getDescripcion() != null) {
                    jcomboDescripcionTurno.setSelectedItem("" + cui.getDescripcion());
                    MedidaCuiTurno.requestFocus();//pone el cursor en medida para introducirla

                } else {
                    LimpiarCuiTurno();
                    codigoCuiTurno.requestFocus();
                    JOptionPane.showMessageDialog(null, "No Hay Codigo asociado");
                }
            } else {
                codigoCuiTurno.requestFocus();
                JOptionPane.showMessageDialog(null, "Introduce un codigo");
            }

        }
    }//GEN-LAST:event_codigoCuiTurnoKeyPressed

    private void codigoCuiTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoCuiTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoCuiTurnoActionPerformed

    private void botonIntCuidadoTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIntCuidadoTurnoActionPerformed
        // TODO add your handling code here:

        if (!"".equals(codigoCuiTurno.getText()) || !"".equals(jcomboDescripcionTurno.getSelectedItem()) || !"".equals(horaCuiTurno.getText())) {
            int codicui = Integer.parseInt(codigoCuiTurno.getText());
            String desCui = (String) jcomboDescripcionTurno.getSelectedItem();
            String med = MedidaCuiTurno.getText();
            String hor = horaCuiTurno.getText();
            itempo = itempo + 1;
            tempo = (DefaultTableModel) tablaCuiTurno.getModel();
            for (int i = 0; i < tablaCuiTurno.getRowCount(); i++) {
            }
            ArrayList listaCui = new ArrayList();
            listaCui.add(itempo);
            listaCui.add(codicui);
            listaCui.add(desCui);
            listaCui.add(med);
            listaCui.add(hor);
            Object[] O = new Object[4];
            O[0] = listaCui.get(1);
            O[1] = listaCui.get(2);
            O[2] = listaCui.get(3);
            O[3] = listaCui.get(4);
            tempo.addRow(O);
            tablaCuiTurno.setModel(tempo);
            LimpiarCuiTurno();
            codigoCuiTurno.requestFocus();

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Datos");
        }

    }//GEN-LAST:event_botonIntCuidadoTurnoActionPerformed

    private void botonEliminarProTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarProTurnoActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) tablaProdTurno.getModel();
        modelo.removeRow(tablaProdTurno.getSelectedRow());
    }//GEN-LAST:event_botonEliminarProTurnoActionPerformed

    private void botonEliminarCuiTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarCuiTurnoActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) tablaCuiTurno.getModel();
        modelo.removeRow(tablaCuiTurno.getSelectedRow());
    }//GEN-LAST:event_botonEliminarCuiTurnoActionPerformed

    private void botonAlmacenMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAlmacenMenuMouseClicked
        // TODO add your handling code here:
        comboDistAlmacen.removeAllItems();
        PonerDistribuidores();
    }//GEN-LAST:event_botonAlmacenMenuMouseClicked

    private void jcomboProductoTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboProductoTurnoActionPerformed
        // TODO add your handling code here:
        //AutoCompleteDecorator.decorate(jcomboProductoTurno);
        jcomboProductoTurno.addActionListener((ActionEvent e) -> {
            String selectedElement = (String) jcomboProductoTurno.getSelectedItem();
            al = alDao.EncontrarPorNombre(selectedElement);

            // AquÃ­ puede recuperar la informaciÃ³n relacionada con el elemento seleccionado
            // y mostrarla en los campos de texto correspondientes
            if (selectedElement != null) {
                codProTurno.setText("" + al.getCodigo());
                disponibleProdTurno.setText("" + al.getCantidad());

            }
        });

    }//GEN-LAST:event_jcomboProductoTurnoActionPerformed

    private void jcomboProductoTurnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcomboProductoTurnoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jcomboProductoTurnoKeyPressed

    private void jcomboDescripcionTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboDescripcionTurnoActionPerformed
        // TODO add your handling code here:
        //AutoCompleteDecorator.decorate(jcomboDescripcionTurno);
        jcomboDescripcionTurno.addActionListener((ActionEvent e) -> {
            String selectedElement = (String) jcomboDescripcionTurno.getSelectedItem();
            cui = cuiDao.EncontrarPorDescripCui(selectedElement);

            // Aquí puede recuperar la información relacionada con el elemento seleccionado
            // y mostrarla en los campos de texto correspondientes
            if (selectedElement != null) {
                codigoCuiTurno.setText("" + cui.getCodigo());

            }
        });
    }//GEN-LAST:event_jcomboDescripcionTurnoActionPerformed

    private void jComboNombreTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNombreTurnoActionPerformed
        // TODO add your handling code here:
        AutoCompleteDecorator.decorate(jComboNombreTurno);
        jComboNombreTurno.addActionListener((ActionEvent e) -> {
            String selectedElement = (String) jComboNombreTurno.getSelectedItem();
            us = usuari.EncontrarUsuarioNom(selectedElement);

            // Aquí puede recuperar la información relacionada con el elemento seleccionado
            // y mostrarla en los campos de texto correspondientes
            if (selectedElement != null) {
                codUsuaTurno.setText("" + us.getCodigo());
                apellidosUsuarioTurno.setText("" + us.getApellidos());
                personaTurno.setText("" + us.getPersonaContacto());
                telefonoTurno.setText("" + us.getTelefono());

            }
        });
    }//GEN-LAST:event_jComboNombreTurnoActionPerformed

    private void botonGuardarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarTurnoActionPerformed
        // TODO add your handling code here:

        if (tablaProdTurno.getRowCount() == 0 || tablaCuiTurno.getRowCount() == 0 || "".equals(apellidosUsuarioTurno.getText())) {
            JOptionPane.showMessageDialog(null, "Debe introducir datos para guardar turno");

        } else {

            RegistrarTurno();
            RegistrarDetalle();
            ActualizarAlm();
            pdf();
            LimpiarTablasTurnoPro();
            LimpiarTablasTurnoCui();
            apellidosUsuarioTurno.setText("");
            codUsuaTurno.setText("");

            JOptionPane.showMessageDialog(null, "Datos turno Guardado");

        }
    }//GEN-LAST:event_botonGuardarTurnoActionPerformed

    private void persUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_persUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_persUsuarioActionPerformed

    private void codProTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codProTurnoKeyTyped
        // TODO add your handling code here:
        ver.ComprobarNum(evt);
    }//GEN-LAST:event_codProTurnoKeyTyped

    private void codigoCuiTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoCuiTurnoKeyTyped
        // TODO add your handling code here:
        ver.ComprobarNum(evt);
    }//GEN-LAST:event_codigoCuiTurnoKeyTyped

    private void cantidadProdTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadProdTurnoKeyTyped
        // TODO add your handling code here:
        ver.ComprobarNum(evt);
    }//GEN-LAST:event_cantidadProdTurnoKeyTyped

    private void codUsuaTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codUsuaTurnoKeyTyped
        // TODO add your handling code here:
        ver.ComprobarNum(evt);
    }//GEN-LAST:event_codUsuaTurnoKeyTyped

    private void cantidadAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadAlmacenKeyTyped
        // TODO add your handling code here:
        ver.ComprobarNum(evt);
    }//GEN-LAST:event_cantidadAlmacenKeyTyped

    private void botonActualizarOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarOpcionesActionPerformed
        // TODO add your handling code here:
        if ("".equals(NombreOpciones.getText()) || "".equals(cifOpciones.getText()) || "".equals(direccionOpciones.getText()) || "".equals(telefonoOpciones.getText()) || "".equals(correoOpciones.getText())) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");

        } else {
            opci.setNombre(NombreOpciones.getText());
            opci.setCif(cifOpciones.getText());
            opci.setDireccion(direccionOpciones.getText());
            opci.setTelefono(telefonoOpciones.getText());
            opci.setCorreo(correoOpciones.getText());
            opciDao.ActualizarEmpresa(opci);
            JOptionPane.showMessageDialog(null, "Datos de Empresa Actualizados");
            RelacionarDatos();
        }
    }//GEN-LAST:event_botonActualizarOpcionesActionPerformed

    private void tablaArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArchivoMouseClicked
        // TODO add your handling code here:
        int linea = tablaArchivo.rowAtPoint(evt.getPoint());
        codigoArchivo.setText(tablaArchivo.getValueAt(linea, 0).toString());

    }//GEN-LAST:event_tablaArchivoMouseClicked

    private void pdfArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfArchivoActionPerformed
        try {
            // TODO add your handling code here:
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + "turno" + codigoArchivo.getText().toString() + ".pdf");
            //File file = new File("src/pdf/turno"+codigoArchivo.getText().toString()+".pdf");
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pdfArchivoActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        // TODO add your handling code here:
        if (txtCorreoRegis.getText().equals("") || txtNombreRegis.getText().equals("") || txtPassRegis.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
        } else {
            String correo = txtCorreoRegis.getText();
            String pass = String.valueOf(txtPassRegis.getPassword());
            String nom = txtNombreRegis.getText();
            String privilegio = jComboPrivi.getSelectedItem().toString();
            log.setNombre(nom);
            log.setCorreo(correo);
            log.setPass(pass);
            log.setPrivilegios(privilegio);
            logDao.Registrar(log);
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
            limpiarTabla();
            RelacionarTrabajadores();

        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void jComboPriviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPriviActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPriviActionPerformed

    private void botonEmpleadosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpleadosMenuActionPerformed
        // TODO add your handling code here:
        pestanas.setSelectedIndex(7);
        RelacionarTrabajadores();
    }//GEN-LAST:event_botonEmpleadosMenuActionPerformed

    private void tablaCuidadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuidadosMouseClicked
        // TODO add your handling code here:
        int fila = tablaCuidados.rowAtPoint(evt.getPoint());
        codigoCuidado.setText(tablaCuidados.getValueAt(fila, 0).toString());
        descripcionCuidado.setText(tablaCuidados.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_tablaCuidadosMouseClicked

    private void tablaTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabajadoresMouseClicked
        // TODO add your handling code here:
        int fila = tablaTrabajadores.rowAtPoint(evt.getPoint());
        txtCodigoEmpleados.setText(tablaTrabajadores.getValueAt(fila, 0).toString());
        txtNombreRegis.setText(tablaTrabajadores.getValueAt(fila, 1).toString());
        txtCorreoRegis.setText(tablaTrabajadores.getValueAt(fila, 2).toString());
        txtPassRegis.setText(tablaTrabajadores.getValueAt(fila, 3).toString());
        jComboPrivi.setSelectedItem(tablaTrabajadores.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tablaTrabajadoresMouseClicked

    private void botonEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarEmpleadoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtCodigoEmpleados.getText())) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el Empleado?");
            if (confirmacion == 0) {
                int id = Integer.parseInt(txtCodigoEmpleados.getText());
                logDao.EliminarEmpleado(id);
                limpiarTabla();
                LimpiarEmpleado();
                RelacionarTrabajadores();
            }
        }

    }//GEN-LAST:event_botonEliminarEmpleadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonExcelAlmacen;
    private javax.swing.JTextField MedidaCuiTurno;
    private javax.swing.JTextField NombreOpciones;
    private javax.swing.JTextField apellidosUsuario;
    private javax.swing.JTextField apellidosUsuarioTurno;
    private javax.swing.JButton botonActualizarAlmacen;
    private javax.swing.JButton botonActualizarCuidado;
    private javax.swing.JButton botonActualizarOpciones;
    private javax.swing.JButton botonActualizarUsuario;
    private javax.swing.JButton botonAgregarAlmacen;
    private javax.swing.JButton botonAlmacenMenu;
    private javax.swing.JButton botonArchivoMenu;
    private javax.swing.JButton botonCuidadosMenu;
    private javax.swing.JButton botonDistribuidoresMenu;
    private javax.swing.JButton botonEliminarAlmacen;
    private javax.swing.JButton botonEliminarCuiTurno;
    private javax.swing.JButton botonEliminarEmpleado;
    private javax.swing.JButton botonEliminarProTurno;
    private javax.swing.JButton botonEliminarUsuario;
    private javax.swing.JButton botonEliminarcuidado;
    private javax.swing.JButton botonEmpleadosMenu;
    private javax.swing.JButton botonGastoMenu;
    private javax.swing.JButton botonGuardarCuidado1;
    private javax.swing.JButton botonGuardarTurno;
    private javax.swing.JButton botonGuardarUsuario;
    private javax.swing.JButton botonIntCuidadoTurno;
    private javax.swing.JButton botonNuevoAlmacen;
    private javax.swing.JButton botonNuevoCuidado;
    private javax.swing.JButton botonNuevoUsuario;
    private javax.swing.JButton botonOpcionesMenu;
    private javax.swing.JButton botonProIntroTurno;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonUsuariosMenu;
    private javax.swing.JTextField caducidadAlmacen;
    private javax.swing.JTextField cantidadAlmacen;
    private javax.swing.JTextField cantidadProdTurno;
    private javax.swing.JTextField cifDistribuidor;
    private javax.swing.JTextField cifOpciones;
    private javax.swing.JTextField codProTurno;
    private javax.swing.JTextField codUsuaTurno;
    private javax.swing.JTextField codigoAlmacen;
    private javax.swing.JTextField codigoArchivo;
    private javax.swing.JTextField codigoCuiTurno;
    private javax.swing.JTextField codigoCuidado;
    private javax.swing.JTextField codigoDistribuidor;
    private javax.swing.JPanel codigoEmpleados;
    private javax.swing.JTextField codigoUsuario;
    private javax.swing.JComboBox<String> comboDistAlmacen;
    private javax.swing.JTextField correoDistribuidor;
    private javax.swing.JTextField correoOpciones;
    private javax.swing.JTextField descripcionCuidado;
    private javax.swing.JTextField direccionDistribuidor;
    private javax.swing.JTextField direccionOpciones;
    private javax.swing.JTextField disponibleProdTurno;
    private javax.swing.JTextField empleadoMenu;
    private javax.swing.JTextField horaCuiTurno;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboNombreTurno;
    private javax.swing.JComboBox<String> jComboPrivi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JComboBox<String> jcomboDescripcionTurno;
    private javax.swing.JComboBox<String> jcomboProductoTurno;
    private javax.swing.JTextField nombreDistribuidor;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JTextField observacionesUsuario;
    private javax.swing.JButton pdfArchivo;
    private javax.swing.JTextField persUsuario;
    private javax.swing.JTextField personaTurno;
    private javax.swing.JPanel pestanaAlmacen;
    private javax.swing.JPanel pestanaArchivo;
    private javax.swing.JPanel pestanaCuidados;
    private javax.swing.JPanel pestanaDistribuidores;
    private javax.swing.JPanel pestanaOpciones;
    private javax.swing.JPanel pestanaTurno;
    private javax.swing.JPanel pestanaUsuarios;
    private javax.swing.JTabbedPane pestanas;
    private javax.swing.JTextField productoAlmacen;
    private javax.swing.JTable tablaAlmacen;
    private javax.swing.JTable tablaArchivo;
    private javax.swing.JTable tablaCuiTurno;
    private javax.swing.JTable tablaCuidados;
    private javax.swing.JTable tablaDistribuidores;
    private javax.swing.JTable tablaProdTurno;
    private javax.swing.JTable tablaTrabajadores;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField telefonoDistribuidor;
    private javax.swing.JTextField telefonoOpciones;
    private javax.swing.JTextField telefonoTurno;
    private javax.swing.JTextField telefonoUsuario;
    private javax.swing.JLabel turnocuinombre;
    private javax.swing.JTextField txtCodigoEmpleados;
    private javax.swing.JTextField txtCorreoRegis;
    private javax.swing.JTextField txtNombreRegis;
    private javax.swing.JPasswordField txtPassRegis;
    // End of variables declaration//GEN-END:variables

    private void LimpiarUsuario() {
        nombreUsuario.setText("");
        apellidosUsuario.setText("");
        persUsuario.setText("");
        telefonoUsuario.setText("");
        observacionesUsuario.setText("");

    }

    private void LimpiarDistribuidor() {
        codigoDistribuidor.setText("");
        cifDistribuidor.setText("");
        nombreDistribuidor.setText("");
        telefonoDistribuidor.setText("");
        direccionDistribuidor.setText("");
        correoDistribuidor.setText("");

    }

    private void LimpiarAlmacen() {
        codigoAlmacen.setText("");
        productoAlmacen.setText("");
        caducidadAlmacen.setText("");
        comboDistAlmacen.setSelectedItem(null);
        cantidadAlmacen.setText("");
    }

    private void LimpiarEmpleado() {
        txtNombreRegis.setText("");
        txtCorreoRegis.setText("");
        txtPassRegis.setText("");
        txtCodigoEmpleados.setText("");
        jComboPrivi.setSelectedItem("");

    }

    private void PonerDistribuidores() {
        List<Distribuidores> lista = disDao.RelacionarDistribuidores();
        for (int i = 0; i < lista.size(); i++) {
            int codigo = lista.get(i).getCodigo();
            String nombre = lista.get(i).getNombre();
            comboDistAlmacen.addItem(nombre);
        }
    }

    private void PonerProductos() {
        List<Almacen> lista = alDao.RelacionAlmacen();
        for (int i = 0; i < lista.size(); i++) {
            int codigo = lista.get(i).getCodigo();
            String nombre = lista.get(i).getproducto();
            comboDistAlmacen.addItem(nombre);
        }
    }

    private void LimpiarCuidados() {
        codigoCuidado.setText("");
        descripcionCuidado.setText("");
    }

    private void LimpiarProdTurno() {
        codProTurno.setText("");

        cantidadProdTurno.setText("");
        disponibleProdTurno.setText("");

    }

    private void LimpiarCuiTurno() {
        codigoCuiTurno.setText("");
        //jcomboDescripcionTurno.setSelectedItem("");
        MedidaCuiTurno.setText("");
        horaCuiTurno.setText("");

    }

    private void RegistrarTurno() {
        String usuario = (String) jComboNombreTurno.getSelectedItem();
        String empleado = empleadoMenu.getText();
        tur.setUsuario(usuario);
        tur.setEmpleado(empleado);
        turDao.RegistrarTurno(tur);
    }

    private void RegistrarDetalle() {
        int id = turDao.idTurno();
        for (int i = 0; i < tablaProdTurno.getRowCount(); i++) {
            String codp = tablaProdTurno.getValueAt(i, 0).toString();
            String pro = tablaProdTurno.getValueAt(i, 1).toString();
            int cant = Integer.parseInt(tablaProdTurno.getValueAt(i, 2).toString());
            det.setCodigoProducto(codp);
            det.setProducto(pro);
            det.setCantidad(cant);
            det.setId_turno(id);
            turDao.RegistrarDetallesPro(det);

        }
        for (int i = 0; i < tablaCuiTurno.getRowCount(); i++) {
            String codC = tablaCuiTurno.getValueAt(i, 0).toString();
            String desc = tablaCuiTurno.getValueAt(i, 1).toString();
            String med = tablaCuiTurno.getValueAt(i, 2).toString();
            String hor = tablaCuiTurno.getValueAt(i, 3).toString();
            det.setCodigoCuidado(codC);
            det.setDescripcion(desc);
            det.setMedida(med);
            det.setHora(hor);
            det.setId_turno(id);
            turDao.RegistrarDetallesCui(det);

        }
    }

    private void ActualizarAlm() {
        for (int i = 0; i < tablaProdTurno.getRowCount(); i++) {
            int cod = (int) tablaProdTurno.getValueAt(i, 0);
            int cant = (int) tablaProdTurno.getValueAt(i, 2);
            al = alDao.EncontrarPorCodigo(cod);
            int cantActual = al.getCantidad() - cant;
            turDao.ActualizarAlmacen(cantActual, cod);
        }
    }

    private void LimpiarTablasTurnoPro() {
        temp = (DefaultTableModel) tablaProdTurno.getModel();
        int linea = tablaProdTurno.getRowCount();
        for (int i = 0; i < linea; i++) {
            temp.removeRow(0);
        }

    }

    private void LimpiarTablasTurnoCui() {
        tempo = (DefaultTableModel) tablaCuiTurno.getModel();
        int linea = tablaCuiTurno.getRowCount();
        for (int i = 0; i < linea; i++) {
            tempo.removeRow(0);
        }
    }

    private void pdf() {
        try {
            int id = turDao.idTurno();
            FileOutputStream archivo;
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + "turno" + id + ".pdf");
            //File file = new File("src/pdf/turno"+id+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            //Image img = Image.getInstance("src/img/logogestpdf.png");

            Paragraph fech = new Paragraph();
            Font negrita = new Font(Font.FontFamily.COURIER, 12, Font.BOLD, BaseColor.BLUE);
            fech.add(Chunk.NEWLINE);
            Date date = new Date();
            fech.add("Turno: " + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");
            PdfPTable Cabecera = new PdfPTable(3);
            Cabecera.setWidthPercentage(100);
            Cabecera.getDefaultCell().setBorder(0);
            float[] ColumnaCabecera = new float[]{30f, 70f, 40f};
            Cabecera.setWidths(ColumnaCabecera);
            Cabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

            //Cabecera.addCell(img);
            String nombre = NombreOpciones.getText();
            String nif = cifOpciones.getText();
            String direccion = direccionOpciones.getText();
            String telefono = telefonoOpciones.getText();
            String correo = correoOpciones.getText();

            Cabecera.addCell("");
            Cabecera.addCell("Nombre: " + nombre + "\nNIF: " + nif + "\nDirección: " + direccion + "\nTeléfono: " + telefono + "\nCorreo: " + correo);
            Cabecera.addCell(fech);
            doc.add(Cabecera);

            Paragraph usu = new Paragraph();
            usu.add(Chunk.NEWLINE);
            usu.add("Datos Usuario" + "\n\n");
            doc.add(usu);

            //Datos Usuario
            PdfPTable tablausu = new PdfPTable(5);
            tablausu.setWidthPercentage(100);
            tablausu.getDefaultCell().setBorder(0);
            float[] Columnausu = new float[]{20f, 30f, 60f, 30f, 20f};
            tablausu.setWidths(Columnausu);
            tablausu.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell usu1 = new PdfPCell(new Phrase("Código", negrita));
            PdfPCell usu2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell usu3 = new PdfPCell(new Phrase("Apellidos", negrita));
            PdfPCell usu4 = new PdfPCell(new Phrase("Contacto", negrita));
            PdfPCell usu5 = new PdfPCell(new Phrase("Teléfono", negrita));
            usu1.setBorder(0);
            usu2.setBorder(0);
            usu3.setBorder(0);
            usu4.setBorder(0);
            usu5.setBorder(0);
            tablausu.addCell(usu1);
            tablausu.addCell(usu2);
            tablausu.addCell(usu3);
            tablausu.addCell(usu4);
            tablausu.addCell(usu5);
            tablausu.addCell(codUsuaTurno.getText());
            tablausu.addCell((String) jComboNombreTurno.getSelectedItem());
            tablausu.addCell(apellidosUsuarioTurno.getText());
            tablausu.addCell(personaTurno.getText());
            tablausu.addCell(telefonoTurno.getText());

            doc.add(tablausu);

            //Datos Productos
            PdfPTable tablapro = new PdfPTable(3);
            tablapro.setWidthPercentage(100);
            tablapro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{20f, 100f, 30f};
            tablapro.setWidths(Columnapro);
            tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Código", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Producto", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Cantidad", negrita));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pro2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pro3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            for (int i = 0; i < tablaProdTurno.getRowCount(); i++) {
                String codigo = tablaProdTurno.getValueAt(i, 0).toString();
                String prod = tablaProdTurno.getValueAt(i, 1).toString();
                String cant = tablaProdTurno.getValueAt(i, 2).toString();

                tablapro.addCell(codigo);
                tablapro.addCell(prod);
                tablapro.addCell(cant);

            }

            doc.add(tablapro);

            //Datos Cuidados
            PdfPTable tablacui = new PdfPTable(4);
            tablacui.setWidthPercentage(100);
            tablacui.getDefaultCell().setBorder(0);
            float[] Columnacui = new float[]{20f, 50f, 30f, 30f};
            tablacui.setWidths(Columnacui);
            tablacui.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cui1 = new PdfPCell(new Phrase("Código", negrita));
            PdfPCell cui2 = new PdfPCell(new Phrase("Cuidado", negrita));
            PdfPCell cui3 = new PdfPCell(new Phrase("Medida", negrita));
            PdfPCell cui4 = new PdfPCell(new Phrase("Hora", negrita));
            cui1.setBorder(0);
            cui2.setBorder(0);
            cui3.setBorder(0);
            cui4.setBorder(0);
            cui1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cui2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cui3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cui4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablacui.addCell(cui1);
            tablacui.addCell(cui2);
            tablacui.addCell(cui3);
            tablacui.addCell(cui4);
            for (int i = 0; i < tablaCuiTurno.getRowCount(); i++) {
                String cod = tablaCuiTurno.getValueAt(i, 0).toString();
                String des = tablaCuiTurno.getValueAt(i, 1).toString();
                String med = tablaCuiTurno.getValueAt(i, 2).toString();
                String hor = tablaCuiTurno.getValueAt(i, 3).toString();

                tablacui.addCell(cod);
                tablacui.addCell(des);
                tablacui.addCell(med);
                tablacui.addCell(hor);

            }

            doc.add(tablacui);

            Paragraph trab = new Paragraph();
            trab.add(Chunk.NEWLINE);
            trab.add("\n\nTrabajador encargado: " + "\n\n" + empleadoMenu.getText());
            trab.setAlignment(Element.ALIGN_CENTER);
            doc.add(trab);

            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }

}

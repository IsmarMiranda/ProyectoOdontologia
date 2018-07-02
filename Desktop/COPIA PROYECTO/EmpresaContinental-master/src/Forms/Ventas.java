/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.*;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public final class Ventas extends javax.swing.JPanel {

    String ruta = "C:\\Users\\ismar\\Desktop\\FIRMA";
    PanelDibujo archivo = new PanelDibujo();
    double Valortratamiento = 0;
    int NoTratamiento = 0;
    int fila, columna;

    public class prueba extends JFrame implements ActionListener {

        JButton boton;
        JLabel texto;

        public prueba() {
            NCanvas n = new NCanvas();
            add(n);
            //  setLayout(null);
            //  boton = new JButton("SALIR");
            //  boton.setBounds(100, 150, 100, 30);
            //  boton.addActionListener(this);
            //  add(boton);
            // texto = new JLabel("Presione el botón para salir.");
            //  texto.setBounds(50, 50, 220, 40);
            //  add(texto);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    FichaClinica ficha = new FichaClinica();
    TableRowSorter trs;
    public DefaultTableModel modeloPacientes = new DefaultTableModel();       ///CREAMOS EL DEFAULT MODELO HACE REFERENCIA AL JTABLE
    public DefaultTableModel modelo1Tratamientos = new DefaultTableModel();
    public DefaultTableModel modelo2Sesiones = new DefaultTableModel();
    public DefaultTableModel modelo3Facturas = new DefaultTableModel();

    String diabetes, alergias, altres, presion, corazon, herpes, trastornos, otros;
    boolean dia = false, ale = false, alt = false, pres = false, cora = false, her = false, tras = false, otro = false;
    public int orden, valor;
    public String pieza, tratamiento;

    public void mostrar_Pacientes() {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT id,Nombre,Direccion, Telefono, Edad, Sexo, Identificacion,Nit FROM Paciente";

        modeloPacientes.addColumn("No. Expediente");
        modeloPacientes.addColumn("Nombre");
        modeloPacientes.addColumn("Direccion");
        modeloPacientes.addColumn("Telefono");
        modeloPacientes.addColumn("Edad");
        modeloPacientes.addColumn("Sexo");
        modeloPacientes.addColumn("No. Identificacion");
        modeloPacientes.addColumn("NIT");

        String[] datos = new String[8];
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);

                modeloPacientes.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int ultimoID() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        int x = 0;
        String sql = "SELECT MAX(id) AS ultimo FROM Paciente";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            x = rs.getInt("ultimo");
        }
        return x + 1;
    }

    public int ultimoControl() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        int x = 0;
        String sql = "SELECT MAX(id) AS ultimo FROM Control";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            x = rs.getInt("ultimo");
        }
        return x;
    }

    public int ultimoTratamiento() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        int x = 0;
        String sql = "SELECT MAX(id) AS ultimo FROM Paciente";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            x = rs.getInt("ultimo");
        }
        return x;
    }

    Conexion con = new Conexion();
    Connection cn = con.ConectarBaseDatos();
    String sexo;

    /**
     * Creates new form Ventas
     */
    //DECLARACION DE VARIABLES 
    public Ventas() throws SQLException {
        initComponents();
        
        //recuperarFacturas();
        // recuperarFacturas();
        juntos.add(jCheckBox1);
        juntos.add(jCheckBox2);
        jCheckBox1.setSelected(true);
        //CUANDO LE DA CLICK AL JTABLE TRATAMIENTOS
        jTableTratamientos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    try {
                        Valortratamiento = Double.parseDouble(jTableTratamientos.getValueAt(jTableTratamientos.getSelectedRow(), 3).toString());
                        jLabel33.setText(jLabel29.getText());
                        jLabel30.setText(txtPaciente.getText());
                        jLabel35.setText(jTableTratamientos.getValueAt(jTableTratamientos.getSelectedRow(), 0).toString());
                        limpiarTablaSesiones();
                        recuperarSesiones(Integer.parseInt(jLabel29.getText()), Integer.parseInt(jTableTratamientos.getValueAt(jTableTratamientos.getSelectedRow(), 0).toString()));
                        CrearFilas1();
                        System.out.println("EL PACIENTE SELECIONO EL TRATAMIENTO" + jTableTratamientos.getValueAt(jTableTratamientos.getSelectedRow(), 0).toString());
                        CrearFilas1();
                        //   CrearFilas1();
                        indiceConsulta.setSelectedIndex(1);
                    } catch (Exception e) {
                    }

                }

            }
        });

        //controla toda la informacion del paciente cuando da dos clicks en el jtable pacientes;
        jTablePacientes.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    int paciente = Integer.parseInt(jTablePacientes.getValueAt(jTablePacientes.getSelectedRow(), 0).toString());
                    //System.out.println("" + jTable5.getValueAt(jTable5.getSelectedRow(), 0));
                    limpiarTablaTratamientos();
                    recuperarPaciente(paciente);
                    recuperarHistoriaMedica(paciente);
                    recuperarEnfermedades(paciente);
                    recuperarHistoriaOdontologica(paciente);
                    //limpiarTablaTratamientos();
                    // limpiarTablaEnfermedades();
                    recuperarTratamientos(paciente);
                    CrearFilas();
                    btnGuardarCambios.setEnabled(false);
                    btnActualizar.setEnabled(true);
                    btnTratamiento.setEnabled(true);
                    indiceConsulta.setSelectedIndex(0);
                }
            }
        });
        txtPaciente.setEnabled(false);
        txtIdentificacion.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEdad.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        txtEnfermo.setEnabled(false);
        txtTx.setEnabled(false);
        txtNit.setEnabled(false);
        cbEmbarazada.setEnabled(false);
        txtMedicamento.setEnabled(false);
        txtAlergias.setEnabled(false);
        txtVisita.setEnabled(false);
        txtTratRecibido.setEnabled(false);
        txtComplicacion.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardarCambios.setEnabled(false);
        btnTratamiento.setEnabled(false);
        jTablePacientes.setModel(modeloPacientes);
        modelo1Tratamientos.addColumn("Orden");
        modelo1Tratamientos.addColumn("Pieza");
        modelo1Tratamientos.addColumn("Tratamiento");
        modelo1Tratamientos.addColumn("valor");
        jTableTratamientos.setModel(modelo1Tratamientos);
        modelo2Sesiones.addColumn("FECHA");
        modelo2Sesiones.addColumn("PIEZA");
        modelo2Sesiones.addColumn("TRATAMIENTO");
        modelo2Sesiones.addColumn("VALOR");
        modelo2Sesiones.addColumn("ABONO");
        modelo2Sesiones.addColumn("SALDO");
        modelo2Sesiones.addColumn("FIRMA");
        jTableSesiones.setModel(modelo2Sesiones);
        modelo3Facturas.addColumn("No. Factura");
        modelo3Facturas.addColumn("Fecha");
        modelo3Facturas.addColumn("Nombre");
        modelo3Facturas.addColumn("Nit");
        modelo3Facturas.addColumn("Fecha Inicio");
        modelo3Facturas.addColumn("Fecha Fin");
        modelo3Facturas.addColumn("Telefono");
        modelo3Facturas.addColumn("Orden");
        modelo3Facturas.addColumn("Pieza");
        modelo3Facturas.addColumn("Tratamiento");
        modelo3Facturas.addColumn("Valor");
        modelo3Facturas.addColumn("Tipo");
        jtableFactuas.setModel(modelo3Facturas);
        recuperarFacturas();
//        limpiarTablaEnfermedades();

        mostrar_Pacientes();
        jTableEnfermedades.setValueAt(Boolean.FALSE, 0, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 1, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 3, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 4, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 5, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 6, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 7, 2);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo1 = new javax.swing.ButtonGroup();
        PopMenu = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        juntos = new javax.swing.ButtonGroup();
        indiceConsulta = new javax.swing.JTabbedPane();
        pnlVentas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEnfermo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTx = new javax.swing.JTextField();
        txtMedicamento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAlergias = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEnfermedades = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtVisita = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtComplicacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnGuardarCambios = new javax.swing.JButton();
        btnTratamiento = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbEmbarazada = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        btnActualizar = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnObtener = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableTratamientos = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        txtTratRecibido = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtNit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        pnlConsultas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSesiones = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        btnSesion = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        pnlPacientes = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablePacientes = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableFactuas = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableDeudores = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        Eliminar.setText("Eliminar Producto");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        PopMenu.add(Eliminar);

        setBackground(new java.awt.Color(36, 41, 46));
        setLayout(new java.awt.GridLayout(1, 0));

        indiceConsulta.setForeground(new java.awt.Color(255, 255, 255));
        indiceConsulta.setToolTipText("");
        indiceConsulta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        indiceConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                click(evt);
            }
        });

        pnlVentas.setBackground(new java.awt.Color(36, 41, 46));
        pnlVentas.setLayout(null);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel5.setBackground(new java.awt.Color(51, 153, 255));
        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("NOMBRE DEL PACIENTE: ");

        txtPaciente.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPacienteActionPerformed(evt);
            }
        });
        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 153, 255));
        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("NO. DE INDENTIFICACION:");

        txtIdentificacion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtIdentificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtDireccion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setBackground(new java.awt.Color(51, 153, 255));
        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("DIRECCION:");

        jLabel6.setBackground(new java.awt.Color(51, 153, 255));
        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("TELEFONO:");

        txtTelefono.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setBackground(new java.awt.Color(51, 153, 255));
        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("EDAD:");

        txtEdad.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 255));
        jLabel13.setText("HISTORIA MEDICA");

        jLabel12.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 255));
        jLabel12.setText("¿HA ESTADO ENFERMO EN LOS ULTIMOS 6 MESES? SI NO ¿POR QUE? ");

        txtEnfermo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtEnfermo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("TX.");

        txtMedicamento.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtMedicamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 255));
        jLabel9.setText("¿TOMA ACTUALMENTE ALGUN MEDICAMENTO? SI NO ¿CUAL?");

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 255));
        jLabel10.setText("¿ES ALERGICO A ALGUN MEDICAMENTO?");

        txtAlergias.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtAlergias.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 255));
        jLabel11.setText("¿ESTA EMBARAZADA?");

        jTableEnfermedades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Diabetes", null, null},
                { new Integer(2), "Alergias", null, null},
                { new Integer(3), "Alteraciones Respiratorias", null, null},
                { new Integer(4), "Presion Arterial alta o baja",  new Boolean(false), null},
                { new Integer(5), "Enfermedades del corazon", null, null},
                { new Integer(6), "Herpes", null, null},
                { new Integer(7), "Trastornos sanguineos", null, null},
                { new Integer(8), "Alguna otra enfermedad grave",  new Boolean(false), null}
            },
            new String [] {
                "No.", "ENFERMEDAD", "X", "OBSERVACIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableEnfermedades.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTableEnfermedades);
        if (jTableEnfermedades.getColumnModel().getColumnCount() > 0) {
            jTableEnfermedades.getColumnModel().getColumn(0).setResizable(false);
            jTableEnfermedades.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableEnfermedades.getColumnModel().getColumn(1).setResizable(false);
            jTableEnfermedades.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTableEnfermedades.getColumnModel().getColumn(2).setResizable(false);
            jTableEnfermedades.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTableEnfermedades.getColumnModel().getColumn(3).setResizable(false);
            jTableEnfermedades.getColumnModel().getColumn(3).setPreferredWidth(770);
        }

        jLabel17.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 255));
        jLabel17.setText("HISTORIA ODONTOLOGICA ANTERIOR");

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 255));
        jLabel18.setText("¿ULTIMA VISITA AL ODONTOLOGO?");

        txtVisita.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtVisita.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 255));
        jLabel15.setText("¿ALGUNA COMPLICACION ODONTOLOGICA?");

        txtComplicacion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtComplicacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("PLAN DE TRATAMIENTO");

        btnGuardarCambios.setText("GUARDAR CAMBIOS");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnTratamiento.setText("GUARDAR NUEVO TRATAMIENTO");
        btnTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTratamientoActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 255));
        jLabel23.setText("Acepto que los datos proporcionados son verídicos y me comprometo a reportar cualquier cambio.");

        jLabel20.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 255));
        jLabel20.setText("Además de estar de acuerdo con el plan de tratamiento.");

        jLabel21.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 255));
        jLabel21.setText("Me comprometo a hacer los pagos de tratamiento conforme se vayan realizando los tratamientos.");

        jLabel14.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 255));
        jLabel14.setText("Cualquier cambio en el tratamiento ó convenio con el pago se anotará en la parte de anexos de la ficha, donde serán firmadas por el profesional y el paciente.");

        jLabel22.setText("jLabel22");

        jLabel19.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 255));
        jLabel19.setText("FIRMA DEL PACIENTE(o encargado)");

        jCheckBox1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 51, 204));
        jCheckBox1.setText("Hombre");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(0, 51, 204));
        jCheckBox2.setText("Mujer");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });

        btnActualizar.setText("Actualizar Registros");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 204));
        jLabel26.setText("No. Expediente");

        jLabel29.setText("...............");

        btnObtener.setText("Obtener");
        btnObtener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObtenerActionPerformed(evt);
            }
        });

        jTableTratamientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ORDEN", "PIEZA", "TRATAMIENTO", "VALOR"
            }
        ));
        jTableTratamientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTratamientosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableTratamientosMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(jTableTratamientos);
        if (jTableTratamientos.getColumnModel().getColumnCount() > 0) {
            jTableTratamientos.getColumnModel().getColumn(0).setResizable(false);
            jTableTratamientos.getColumnModel().getColumn(0).setPreferredWidth(4);
            jTableTratamientos.getColumnModel().getColumn(1).setResizable(false);
            jTableTratamientos.getColumnModel().getColumn(1).setPreferredWidth(130);
            jTableTratamientos.getColumnModel().getColumn(2).setResizable(false);
            jTableTratamientos.getColumnModel().getColumn(2).setPreferredWidth(725);
            jTableTratamientos.getColumnModel().getColumn(3).setResizable(false);
            jTableTratamientos.getColumnModel().getColumn(3).setPreferredWidth(130);
        }

        jLabel36.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 255));
        jLabel36.setText("¿TRATAMIENTO RECIBIDO?");

        jButton2.setText("FACTURAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtNit.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtNit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setBackground(new java.awt.Color(51, 153, 255));
        jLabel16.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("NIT");

        jButton3.setText("Desbloquar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("FIRMA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(430, 430, 430)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(270, 270, 270)
                                        .addComponent(txtVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(4, 4, 4)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTratRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtComplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(860, 860, 860)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(465, 465, 465)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(535, 535, 535)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)
                                .addComponent(btnTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(300, 300, 300)
                                        .addComponent(txtAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(102, 102, 102)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cbEmbarazada))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(482, 482, 482)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtEnfermo, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtTx, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnObtener)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(190, 190, 190)
                                                .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(89, 89, 89))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGap(120, 120, 120)
                                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel4)
                                        .addGap(7, 7, 7)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jLabel26))))
                .addGap(2205, 2205, 2205))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel26)
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEnfermo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(cbEmbarazada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTratRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtComplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)))
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnObtener)
                        .addGap(132, 132, 132)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))))
        );

        jScrollPane1.setViewportView(jPanel3);

        pnlVentas.add(jScrollPane1);
        jScrollPane1.setBounds(-10, 0, 1440, 750);

        indiceConsulta.addTab("Ficha Clinica", pnlVentas);

        pnlConsultas.setBackground(new java.awt.Color(36, 41, 46));
        pnlConsultas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("REGISTRO DIARIO DE TRATAMIENTOS");

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 204));
        jLabel24.setText("En el siguiente cuadro se llevará el registro de los tratamientos donde el paciente podrá firmar al estar conforme con los pagos y el tratamiento.");

        jTableSesiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "FECHA", "PIEZA", "TRATAMIENTO", "VALOR", "ABONO", "SALDO", "FIRMA"
            }
        ));
        jTableSesiones.setRowHeight(80);
        jTableSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSesionesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSesionesMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTableSesiones);
        if (jTableSesiones.getColumnModel().getColumnCount() > 0) {
            jTableSesiones.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTableSesiones.getColumnModel().getColumn(1).setPreferredWidth(75);
            jTableSesiones.getColumnModel().getColumn(2).setPreferredWidth(500);
            jTableSesiones.getColumnModel().getColumn(3).setPreferredWidth(70);
            jTableSesiones.getColumnModel().getColumn(4).setPreferredWidth(70);
            jTableSesiones.getColumnModel().getColumn(5).setPreferredWidth(70);
            jTableSesiones.getColumnModel().getColumn(6).setMinWidth(80);
            jTableSesiones.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane8.setViewportView(jTextArea2);

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 204));
        jLabel28.setText("ANEXO");

        btnSesion.setText("GUARDAR SESION");
        btnSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesionActionPerformed(evt);
            }
        });

        jLabel30.setText("---------------------");

        jLabel31.setText("PACIENTE:");

        jLabel32.setText("No. Expediente");

        jLabel33.setText("------------");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel34.setText("No. Orden Tratamiento");

        jLabel35.setText("-------");

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(543, 543, 543)
                        .addComponent(jLabel1)
                        .addGap(323, 323, 323)
                        .addComponent(jButton7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1005, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel32)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel34)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(955, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(btnSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(623, 623, 623)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton7))
                .addGap(9, 9, 9)
                .addComponent(jLabel24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jButton1)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jScrollPane4.setViewportView(jPanel2);

        pnlConsultas.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 760));

        indiceConsulta.addTab("Seguir Tratamiento", pnlConsultas);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setForeground(new java.awt.Color(51, 51, 51));

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 51, 204));
        jLabel25.setText("PACIENTES");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTablePacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No. Expediente", "Nombre", "Dirección", "Teléfono", "Edad", "Sexo", "No. Identificacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePacientes.setRowHeight(40);
        jTablePacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePacientesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTablePacientes);
        if (jTablePacientes.getColumnModel().getColumnCount() > 0) {
            jTablePacientes.getColumnModel().getColumn(0).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(0).setPreferredWidth(55);
            jTablePacientes.getColumnModel().getColumn(1).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTablePacientes.getColumnModel().getColumn(2).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTablePacientes.getColumnModel().getColumn(3).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTablePacientes.getColumnModel().getColumn(4).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(4).setPreferredWidth(25);
            jTablePacientes.getColumnModel().getColumn(5).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTablePacientes.getColumnModel().getColumn(6).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 204));
        jLabel27.setText("Buscar:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(510, 560, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 580, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1196, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 705, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(645, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlPacientesLayout = new javax.swing.GroupLayout(pnlPacientes);
        pnlPacientes.setLayout(pnlPacientesLayout);
        pnlPacientesLayout.setHorizontalGroup(
            pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPacientesLayout.setVerticalGroup(
            pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPacientesLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        indiceConsulta.addTab("Pacientes", pnlPacientes);

        jPanel5.setLayout(null);

        jtableFactuas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No. Factura", "Fecha", "Nombre", "Nit", "fecha Inicio", "fecha fin", "telefono", "Orden", "Pieza", "Tratamiento", "Valor", "Tipo"
            }
        ));
        jtableFactuas.setRowHeight(40);
        jScrollPane2.setViewportView(jtableFactuas);

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(30, 260, 1292, 370);

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 204));
        jLabel37.setText("FACTURAS");
        jPanel5.add(jLabel37);
        jLabel37.setBounds(570, 40, 210, 50);

        jCheckBox3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(0, 51, 204));
        jCheckBox3.setText("Contado");
        jPanel5.add(jCheckBox3);
        jCheckBox3.setBounds(300, 192, 120, 40);

        jCheckBox4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(0, 51, 204));
        jCheckBox4.setText("Cotizaciones");
        jPanel5.add(jCheckBox4);
        jCheckBox4.setBounds(540, 190, 200, 40);

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 204));
        jLabel38.setText("Visualizar Facturas por tipo:");
        jPanel5.add(jLabel38);
        jLabel38.setBounds(40, 200, 270, 24);

        jCheckBox5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCheckBox5.setForeground(new java.awt.Color(0, 51, 204));
        jCheckBox5.setText("Credito");
        jPanel5.add(jCheckBox5);
        jCheckBox5.setBounds(430, 190, 200, 40);

        jButton5.setText("ACTUALIZAR REGISTROS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(450, 680, 430, 25);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        indiceConsulta.addTab("Facturas", jPanel1);

        jTableDeudores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableDeudores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "No. Expediente", "Nombre", "Cantidad"
            }
        ));
        jTableDeudores.setName(""); // NOI18N
        jTableDeudores.setRowHeight(50);
        jScrollPane7.setViewportView(jTableDeudores);

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 204));
        jLabel39.setText("PACIENTES CON SALDOS PENDIENTES");

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jButton8)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addGap(66, 66, 66)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );

        indiceConsulta.addTab("Pacientes deudores", jPanel6);

        add(indiceConsulta);
    }// </editor-fold>//GEN-END:initComponents

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed

    }//GEN-LAST:event_EliminarActionPerformed

    private void click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_click

    }//GEN-LAST:event_click

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // FILTRO DEL JTABLE
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextField1.getText(), 1));
            }
        });

        trs = new TableRowSorter(modeloPacientes);
        jTablePacientes.setRowSorter(trs);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        recuperarSesiones(1, 2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionActionPerformed
        int filaSelec = jTableSesiones.getSelectedRow();
        if (filaSelec >= 0) {
            ficha.insertarSesion(jTableSesiones.getValueAt(filaSelec, 0).toString(), jTableSesiones.getValueAt(filaSelec, 1).toString(), jTableSesiones.getValueAt(filaSelec, 2).toString(), Double.parseDouble(jTableSesiones.getValueAt(filaSelec, 3).toString()), Double.parseDouble(jTableSesiones.getValueAt(filaSelec, 4).toString()), Double.parseDouble(jTableSesiones.getValueAt(filaSelec, 5).toString()), Integer.parseInt(jLabel33.getText()), Integer.parseInt(jLabel35.getText()));
            JOptionPane.showMessageDialog(null, "TRATAMIENTO INSERTADO CORRECTAMENTE");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una casilla.");
        }
    }//GEN-LAST:event_btnSesionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int filaSelec = jTableTratamientos.getSelectedRow();
        if (filaSelec >= 0) {

            Facturas cos = null;
            try {
                cos = new Facturas();
            } catch (SQLException ex) {
                Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            }
            cos.setVisible(true);
            cos.txtPaciente.setText(txtPaciente.getText());
            cos.txtDireccion.setText(txtDireccion.getText());
            cos.txtNit.setText(txtNit.getText());
            cos.txtTelefono1.setText(txtTelefono.getText());
            cos.jTable1.setValueAt(jTableTratamientos.getValueAt(filaSelec, 0), 0, 0);
            cos.jTable1.setValueAt(jTableTratamientos.getValueAt(filaSelec, 1), 0, 1);
            cos.jTable1.setValueAt(jTableTratamientos.getValueAt(filaSelec, 2), 0, 2);
            cos.jTable1.setValueAt(jTableTratamientos.getValueAt(filaSelec, 3), 0, 3);
            cos.jLabel10.setText(jLabel29.getText());

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una casilla.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableTratamientosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTratamientosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableTratamientosMousePressed

    private void jTableTratamientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTratamientosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableTratamientosMouseClicked

    private void btnObtenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObtenerActionPerformed
        try {
            ImageIcon fot = new ImageIcon("C:\\Users\\ismar\\Desktop\\FIRMA9.png");
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel22.getWidth(), jLabel22.getHeight(), Image.SCALE_DEFAULT));
            jLabel22.setIcon(icono);
            repaint();
            jLabel29.setText(String.valueOf(ultimoID()));
            txtPaciente.setEnabled(true);
            txtIdentificacion.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtTelefono.setEnabled(true);
            txtEdad.setEnabled(true);
            jCheckBox1.setEnabled(true);
            jCheckBox2.setEnabled(true);
            txtEnfermo.setEnabled(true);
            txtTx.setEnabled(true);
            txtNit.setEnabled(true);
            cbEmbarazada.setEnabled(true);
            txtMedicamento.setEnabled(true);
            txtAlergias.setEnabled(true);
            txtVisita.setEnabled(true);
            txtTratRecibido.setEnabled(true);
            txtComplicacion.setEnabled(true);
            btnActualizar.setEnabled(true);
            btnGuardarCambios.setEnabled(true);
            btnTratamiento.setEnabled(true);
            CrearFilas();

        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnObtenerActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (jCheckBox1.isSelected()) {
            sexo = "M";
        }
        if (jCheckBox2.isSelected()) {
            sexo = "F";
        }
        ficha.ModificarPaciente(txtPaciente.getText(), txtDireccion.getText(), txtTelefono.getText(), Integer.parseInt(txtEdad.getText()), sexo, txtIdentificacion.getText(), txtNit.getText(), Integer.parseInt(jLabel29.getText()));
        ficha.ModificarHistoriaMed(txtEnfermo.getText(), txtTx.getText(), txtMedicamento.getText(), txtAlergias.getText(), cbEmbarazada.isSelected(), Integer.parseInt(jLabel29.getText()), Integer.parseInt(jLabel29.getText()));
        dia = Boolean.valueOf(jTableEnfermedades.getValueAt(0, 2).toString());
        System.out.println("Dia: " + dia);
        if (dia == true) {
            //VERIFICAMOS SI EL CHECKBOX ESTA ACTIVO

            diabetes = String.valueOf(jTableEnfermedades.getValueAt(0, 3));                             //OBTENEMOS LO QUE ESTE ESCRITO EN LA DESCRIPCION
            if ("".equals(diabetes)) {
                diabetes = ".";                                                                              //SI LA VARIABLE ES NULA ENTONCES ASIGNAMOS ALGO
            }
        }
        ale = Boolean.valueOf(jTableEnfermedades.getValueAt(1, 2).toString());
        System.out.println("ale: " + ale);
        if (ale == true) {

            alergias = String.valueOf(jTableEnfermedades.getValueAt(1, 3));
            if ("".equals(alergias)) {
                alergias = ".";
            }
        }
        alt = Boolean.valueOf(jTableEnfermedades.getValueAt(2, 2).toString());
        System.out.println("altraciones: " + alt);
        if (alt == true) {

            altres = String.valueOf(jTableEnfermedades.getValueAt(2, 3));
            if ("".equals(altres)) {
                altres = ".";
            }
        }
        pres = Boolean.valueOf(jTableEnfermedades.getValueAt(3, 2).toString());
        System.out.println("presion: " + pres);
        if (pres == true) {
            presion = String.valueOf(jTableEnfermedades.getValueAt(3, 3));
            if ("".equals(presion)) {
                presion = ".";
            }
        }
        cora = Boolean.valueOf(jTableEnfermedades.getValueAt(4, 2).toString());
        System.out.println("corazon: " + cora);
        if (cora == true) {
            corazon = String.valueOf(jTableEnfermedades.getValueAt(3, 3));
            if ("".equals(corazon)) {
                corazon = ".";
            }
        }
        her = Boolean.valueOf(jTableEnfermedades.getValueAt(5, 2).toString());
        System.out.println("herpes: " + her);
        if (her == true) {
            herpes = String.valueOf(jTableEnfermedades.getValueAt(5, 3));
            if ("".equals(herpes)) {
                herpes = ".";
            }
        }
        tras = Boolean.valueOf(jTableEnfermedades.getValueAt(6, 2).toString());
        System.out.println("tastornos: " + tras);
        if (tras == true) {
            trastornos = String.valueOf(jTableEnfermedades.getValueAt(6, 3));
            if ("".equals(trastornos)) {
                trastornos = ".";
            }
        }
        otro = Boolean.valueOf(jTableEnfermedades.getValueAt(7, 2).toString());
        System.out.println("otros: " + otro);
        if (otro == true) {
            otros = String.valueOf(jTableEnfermedades.getValueAt(7, 3));
            if ("".equals(otros)) {
                otros = ".";
            }
        }

        ficha.modificarEnfermedades(diabetes, alergias, altres, presion, corazon, herpes, trastornos, otros, Integer.parseInt(jLabel29.getText()), Integer.parseInt(jLabel29.getText()));
        ficha.ModificarHistoriaOdontologica(txtVisita.getText(), txtTratRecibido.getText(), txtComplicacion.getText(), Integer.parseInt(jLabel29.getText()), Integer.parseInt(jLabel29.getText()));
        JOptionPane.showMessageDialog(null, "PACIENTE INSERTADO CORRECTAMENTE");
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked

    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked

    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void btnTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTratamientoActionPerformed
        int filaSelec = jTableTratamientos.getSelectedRow();
        if (filaSelec >= 0) {
            ficha.insertarTratamiento(txtPaciente.getText(), "0", Integer.parseInt(jTableTratamientos.getValueAt(filaSelec, 0).toString()), jTableTratamientos.getValueAt(filaSelec, 1).toString(), jTableTratamientos.getValueAt(filaSelec, 2).toString(), Integer.parseInt(jTableTratamientos.getValueAt(filaSelec, 3).toString()), Integer.parseInt(jLabel29.getText()));
            JOptionPane.showMessageDialog(null, "TRATAMIENTO INSERTADO CORRECTAMENTE");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una casilla.");
        }
    }//GEN-LAST:event_btnTratamientoActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed

        try {
            if (jCheckBox1.isSelected()) {
                sexo = "M";
            }
            if (jCheckBox2.isSelected()) {
                sexo = "F";
            }
            //INSERTAMOS LA INFORMACION EN LA BASE DE DATOS
            ficha.insertarPaciente(txtPaciente.getText(), txtDireccion.getText(), txtTelefono.getText(), Integer.parseInt(txtEdad.getText()), sexo, txtIdentificacion.getText(), txtNit.getText());
            ficha.insertarHistoriaMed(txtEnfermo.getText(), txtTx.getText(), txtMedicamento.getText(), txtAlergias.getText(), cbEmbarazada.isSelected(), Integer.parseInt(jLabel29.getText()));
            //VERIFICAMOS SI LOS CHECKBOX ESTAN ACTIVOS
            dia = Boolean.valueOf(jTableEnfermedades.getValueAt(0, 2).toString());
            System.out.println("Dia: " + dia);
            if (dia == true) {
                //VERIFICAMOS SI EL CHECKBOX ESTA ACTIVO

                diabetes = String.valueOf(jTableEnfermedades.getValueAt(0, 3));                             //OBTENEMOS LO QUE ESTE ESCRITO EN LA DESCRIPCION
                if ("".equals(diabetes)) {
                    diabetes = ".";                                                                              //SI LA VARIABLE ES NULA ENTONCES ASIGNAMOS ALGO
                }
            }
            ale = Boolean.valueOf(jTableEnfermedades.getValueAt(1, 2).toString());
            System.out.println("ale: " + ale);
            if (ale == true) {

                alergias = String.valueOf(jTableEnfermedades.getValueAt(1, 3));
                if ("".equals(alergias)) {
                    alergias = ".";
                }
            }
            alt = Boolean.valueOf(jTableEnfermedades.getValueAt(2, 2).toString());
            System.out.println("altraciones: " + alt);
            if (alt == true) {

                altres = String.valueOf(jTableEnfermedades.getValueAt(2, 3));
                if ("".equals(altres)) {
                    altres = ".";
                }
            }
            pres = Boolean.valueOf(jTableEnfermedades.getValueAt(3, 2).toString());
            System.out.println("presion: " + pres);
            if (pres == true) {
                presion = String.valueOf(jTableEnfermedades.getValueAt(3, 3));
                if ("".equals(presion)) {
                    presion = ".";
                }
            }
            cora = Boolean.valueOf(jTableEnfermedades.getValueAt(4, 2).toString());
            System.out.println("corazon: " + cora);
            if (cora == true) {
                corazon = String.valueOf(jTableEnfermedades.getValueAt(3, 3));
                if (corazon == null) {
                    corazon = ".";
                }
            }
            her = Boolean.valueOf(jTableEnfermedades.getValueAt(5, 2).toString());
            System.out.println("herpes: " + her);
            if (her == true) {
                herpes = String.valueOf(jTableEnfermedades.getValueAt(5, 3));
                if ("".equals(herpes)) {
                    herpes = ".";
                }
            }
            tras = Boolean.valueOf(jTableEnfermedades.getValueAt(6, 2).toString());
            System.out.println("tastornos: " + tras);
            if (tras == true) {
                trastornos = String.valueOf(jTableEnfermedades.getValueAt(6, 3));
                if ("".equals(trastornos)) {
                    trastornos = ".";
                }
            }
            otro = Boolean.valueOf(jTableEnfermedades.getValueAt(7, 2).toString());
            System.out.println("otros: " + otro);
            if (otro == true) {
                otros = String.valueOf(jTableEnfermedades.getValueAt(7, 3));
                if ("".equals(otros)) {
                    otros = ".";
                }
            }

            ficha.insertarEnfermedades(diabetes, alergias, altres, presion, corazon, herpes, trastornos, otros, Integer.parseInt(jLabel29.getText()));
            ficha.insertarHistoriaOdontologica(txtVisita.getText(), txtTratRecibido.getText(), txtComplicacion.getText(), Integer.parseInt(jLabel29.getText()));
            String[] datos = new String[8];
            datos[0] = jLabel29.getText();
            datos[1] = txtPaciente.getText();
            datos[2] = txtDireccion.getText();
            datos[3] = txtTelefono.getText();
            datos[4] = txtEdad.getText();
            datos[5] = sexo;
            datos[6] = txtIdentificacion.getText();
            datos[7] = txtNit.getText();
            modeloPacientes.addRow(datos);
            // mostrar_Pacientes();
            JOptionPane.showMessageDialog(null, "PACIENTE INGRESADO CORRECTAMENTE");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ALGUNOS DATOS SON INCOMPATIBLES");
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void txtPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyTyped
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                try {
                    txtComplicacion.setText(String.valueOf(ultimoID()));
                    // jLabel29.setText(String.valueOf(ultimoID()));
                } catch (SQLException ex) {
                    Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        trs = new TableRowSorter(modeloPacientes);
        jTableSesiones.setRowSorter(trs);
    }//GEN-LAST:event_txtPacienteKeyTyped

    private void txtPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        txtPaciente.setEnabled(true);
        txtNit.setEnabled(true);
        txtIdentificacion.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtEdad.setEnabled(true);
        jCheckBox1.setEnabled(true);
        jCheckBox2.setEnabled(true);
        txtEnfermo.setEnabled(true);
        txtTx.setEnabled(true);
        cbEmbarazada.setEnabled(true);
        txtMedicamento.setEnabled(true);
        txtAlergias.setEnabled(true);
        txtVisita.setEnabled(true);
        txtTratRecibido.setEnabled(true);
        txtComplicacion.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnGuardarCambios.setEnabled(true);
        btnTratamiento.setEnabled(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTablePacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePacientesMouseClicked

    }//GEN-LAST:event_jTablePacientesMouseClicked

    private void jTableSesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSesionesMouseClicked

    }//GEN-LAST:event_jTableSesionesMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        GUI_Principal programaPrincipal = new GUI_Principal();
        programaPrincipal.setVisible(true);
        try {
            ruta = ruta + ultimoControl() + ".png";
            System.out.println("LA RUTA SIGUIENTE ES:   " + ruta);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTableSesionesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSesionesMouseReleased

        int filaX = jTableSesiones.getSelectedRow();
        int columnaX = jTableSesiones.getSelectedColumn();
        fila = filaX;
        columna = columnaX;

        if (columna == 3 && columna >= 0) {
            jTableSesiones.setValueAt(Valortratamiento, fila, columna);
        }
        if (columnaX == 5 && filaX == 0) {

            double saldo = 0;
            double abono = 0;
            double restante = 0;
            try {

                abono = Double.parseDouble(jTableSesiones.getValueAt(jTableSesiones.getSelectedRow(), columnaX - 1).toString());
                saldo = Valortratamiento - abono;
                jTableSesiones.setValueAt(saldo, filaX, columnaX);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Seleccione la casilla correcta");
            }

        }
        if (columnaX == 5 && filaX > 0) {

            double saldo = 0;
            double abono = 0;
            double restante = 0;
            try {

                abono = Double.parseDouble(jTableSesiones.getValueAt(jTableSesiones.getSelectedRow(), columnaX - 1).toString());
                saldo = Double.parseDouble(jTableSesiones.getValueAt(jTableSesiones.getSelectedRow() - 1, columnaX).toString());
                restante = saldo - abono;
                if (restante >= 0) {
                    jTableSesiones.setValueAt(restante, filaX, columnaX);
                } else {
                    JOptionPane.showMessageDialog(null, "Esa cantidad no es aceptada.");
                }
                if (restante == 0) {
                    TratamientoTerminado(Integer.parseInt(jLabel33.getText()), jLabel35.getText());
                    JOptionPane.showMessageDialog(null, "Tratamiento termiando =)");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Seleccione la casilla correcta");
            }

        }
        if (columnaX == 6 && filaX >= 0) {
            GUI_Principal programaPrincipal = new GUI_Principal();
            programaPrincipal.setVisible(true);
        }


    }//GEN-LAST:event_jTableSesionesMouseReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limpiarTablaFacturas();
        recuperarFacturas();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        GUI_Principal programaPrincipal = new GUI_Principal();
        programaPrincipal.setVisible(true);
        try {
            ruta = ruta + ultimoControl() + ".png";
            System.out.println("LA RUTA SIGUIENTE ES:   " + ruta);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

        //this.repaint();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        propiedades();
        String rutax = null;
        try {
            rutax = "/Firmas/FIRMA"+ultimoControl()+".png";
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LA ULTIMA RUTA ES: " + rutax);
        propiedades();
        ImageIcon imagen = new ImageIcon(getClass().getResource(rutax));
        Image conversion = imagen.getImage();
        Image tamano = conversion.getScaledInstance(170, 55, Image.SCALE_SMOOTH);
        ImageIcon fin = new ImageIcon(tamano);
        jTableSesiones.setValueAt(new JLabel(fin), 0, 0);


    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.ButtonGroup Grupo1;
    private javax.swing.JPopupMenu PopMenu;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnObtener;
    private javax.swing.JButton btnSesion;
    private javax.swing.JButton btnTratamiento;
    private javax.swing.JCheckBox cbEmbarazada;
    private javax.swing.JTabbedPane indiceConsulta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
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
    public javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableDeudores;
    private javax.swing.JTable jTableEnfermedades;
    private javax.swing.JTable jTablePacientes;
    private javax.swing.JTable jTableSesiones;
    public javax.swing.JTable jTableTratamientos;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable jtableFactuas;
    private javax.swing.ButtonGroup juntos;
    private javax.swing.JPanel pnlConsultas;
    private javax.swing.JPanel pnlPacientes;
    private javax.swing.JPanel pnlVentas;
    private javax.swing.JTextField txtAlergias;
    private javax.swing.JTextField txtComplicacion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEnfermo;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtMedicamento;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtPaciente;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTratRecibido;
    private javax.swing.JTextField txtTx;
    private javax.swing.JTextField txtVisita;
    // End of variables declaration//GEN-END:variables

    public void recuperarPaciente(int id) {
        jLabel29.setText(jTablePacientes.getValueAt(id - 1, 0).toString());
        txtPaciente.setText(jTablePacientes.getValueAt(id - 1, 1).toString());
        txtIdentificacion.setText(jTablePacientes.getValueAt(id - 1, 6).toString());
        txtDireccion.setText(jTablePacientes.getValueAt(id - 1, 2).toString());
        txtTelefono.setText(jTablePacientes.getValueAt(id - 1, 3).toString());
        txtEdad.setText(jTablePacientes.getValueAt(id - 1, 4).toString());
        String sexo = jTablePacientes.getValueAt(id - 1, 5).toString();
        txtNit.setText(jTablePacientes.getValueAt(id - 1, 7).toString());
        if ("F".equals(sexo)) {
            jCheckBox2.setSelected(true);
            jCheckBox1.setEnabled(false);
        }
        if ("M".equals(sexo)) {
            jCheckBox1.setSelected(true);
            jCheckBox2.setEnabled(false);
        }
    }

    public void recuperarHistoriaMedica(int id) {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT Enfermedad,Tratamiento,Medicamento, Alergico, Embarazada FROM HistoriaMedica WHERE Paciente_id = '" + id + "'";
        String[] datos = new String[5];
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                txtEnfermo.setText(rs.getString(1));
                txtTx.setText(rs.getString(2));
                txtMedicamento.setText(rs.getString(3));
                txtAlergias.setText(rs.getString(4));
                boolean embar = rs.getBoolean(5);
                if (embar == true) {
                    cbEmbarazada.setSelected(true);
                } else {
                    cbEmbarazada.setSelected(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarHistoriaOdontologica(int id) {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT Visita,Tratamiento, Complicacion FROM HistoriaOdontologica WHERE Paciente_id = '" + id + "'";
        String[] datos = new String[5];
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                txtVisita.setText(rs.getString(1));
                txtTratRecibido.setText(rs.getString(2));
                txtComplicacion.setText(rs.getString(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarEnfermedades(int id) {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT Diabetes, Alergias, AltRespiratorias, Presion, Corazon, Herpes, Trastornos, Otros FROM Enfermedades WHERE Paciente_id = '" + id + "'";

        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                diabetes = rs.getString(1);
                alergias = rs.getString(2);
                altres = rs.getString(3);
                presion = rs.getString(4);
                corazon = rs.getString(5);
                herpes = rs.getString(6);
                trastornos = rs.getString(7);
                otros = rs.getString(8);
            }
            if (diabetes == null) {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 0, 2);

            } else {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 0, 2);
                jTableEnfermedades.setValueAt(diabetes, 0, 3);
            }
            if (alergias != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 1, 2);
                jTableEnfermedades.setValueAt(alergias, 1, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 1, 2);
            }
            if (altres != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 2, 2);
                jTableEnfermedades.setValueAt(altres, 2, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 2);
            }
            if (presion != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 3, 2);
                jTableEnfermedades.setValueAt(presion, 3, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 3, 2);
            }
            if (corazon != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 4, 2);
                jTableEnfermedades.setValueAt(corazon, 4, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 4, 2);
            }
            if (herpes != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 5, 2);
                jTableEnfermedades.setValueAt(herpes, 5, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 5, 2);
            }
            if (trastornos != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 6, 2);
                jTableEnfermedades.setValueAt(trastornos, 6, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 6, 2);
            }
            if (otros != null) {
                jTableEnfermedades.setValueAt(Boolean.TRUE, 7, 2);
                jTableEnfermedades.setValueAt(otros, 7, 3);
            } else {
                jTableEnfermedades.setValueAt(Boolean.FALSE, 7, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarTratamientos(int id) {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT Orden , Pieza , Tratamiento, Valor FROM Tratamiento WHERE Paciente_id = '" + id + "'";

        String[] datos = new String[4];

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = String.valueOf(rs.getDouble(4));
                modelo1Tratamientos.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarSesiones(int paciente, int tratamientos) {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT Fecha , Pieza , Tratamiento, Valor, Abono, Saldo FROM Sesion WHERE Paciente_id = '" + paciente + "' AND Tratamiento_id = '" + tratamientos + "'";

        String[] datos = new String[6];

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = String.valueOf(rs.getDouble(4));
                datos[4] = String.valueOf(rs.getDouble(5));
                datos[5] = String.valueOf(rs.getDouble(6));
                modelo2Sesiones.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarFacturas() {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT id, Fecha, Nombre, Nit, FechaInicio ,FechaFin ,Telefono, Orden, Pieza, Tratamiento, Valor, Tipo FROM Factura";

        String[] datos = new String[13];

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt(1));  //id
                datos[1] = rs.getString(2);         //fecha
                datos[2] = rs.getString(3);         //nombre
                datos[3] = rs.getString(4);         //nit
                datos[4] = rs.getString(5);         //fechaInicio
                datos[5] = rs.getString(6);         //FechaFin
                datos[6] = rs.getString(7);         //teefono    
                datos[7] = rs.getString(8);         //Orden
                datos[8] = String.valueOf(rs.getBoolean(9)); //pieza
                datos[9] = rs.getString(10);    //tRATAMIENTO
                datos[10] = String.valueOf(rs.getDouble(11));   //VALOR
                datos[11] = String.valueOf(rs.getBoolean(12));//TIPO

                modelo3Facturas.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CrearFilas() {
        String[] datos = new String[4];
        datos[0] = null;
        datos[1] = null;
        datos[2] = null;
        datos[3] = null;
        for (int i = 0; i < 8; i++) {
            modelo1Tratamientos.addRow(datos);
        }
    }

    public void CrearFilas1() {
        String[] datos = new String[4];
        datos[0] = null;
        datos[1] = null;
        datos[2] = null;
        datos[3] = null;
        for (int i = 0; i < 8; i++) {
            modelo2Sesiones.addRow(datos);
        }
    }

    public void limpiarTablaTratamientos() {
        try {

            int a = jTableTratamientos.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                modelo1Tratamientos.removeRow(modelo1Tratamientos.getRowCount() - 1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void limpiarTablaPacientes() {
        try {

            int a = jTablePacientes.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                modeloPacientes.removeRow(modeloPacientes.getRowCount() - 1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void limpiarTablaSesiones() {
        try {

            int a = jTableSesiones.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                modelo2Sesiones.removeRow(modelo2Sesiones.getRowCount() - 1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void limpiarTablaFacturas() {
        try {

            int a = jtableFactuas.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                modelo3Facturas.removeRow(modelo3Facturas.getRowCount() - 1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void limpiarTablaEnfermedades() {
        jTableEnfermedades.setValueAt("", 3, 0);
        jTableEnfermedades.setValueAt("", 3, 1);
        jTableEnfermedades.setValueAt("", 3, 2);
        jTableEnfermedades.setValueAt("", 3, 3);
        jTableEnfermedades.setValueAt("", 3, 4);
        jTableEnfermedades.setValueAt("", 3, 5);
        jTableEnfermedades.setValueAt("", 3, 6);
        jTableEnfermedades.setValueAt("", 3, 7);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 0);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 1);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 2);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 3);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 4);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 5);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 6);
        jTableEnfermedades.setValueAt(Boolean.FALSE, 2, 7);

    }

    public void TratamientoTerminado(int paciente, String orden) {

        String sql = "UPDATE Tratamiento SET Finalizado = ? WHERE Paciente_id = '" + paciente + "' AND Orden = '" + orden + "' ";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setBoolean(1, true);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION");
        }

    }

    public void propiedades() {

        jTableSesiones.setDefaultRenderer(Object.class, new ImgTabla());
        // modelo2Sesiones.addRow(new Object[]{"fabian","rojo",new JLabel(new ImageIcon(getClass().getResource("")))});

        //jTableSesiones.setModel(modelo2Sesiones);
    }
}

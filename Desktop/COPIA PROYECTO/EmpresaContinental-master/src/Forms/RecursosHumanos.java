package Forms;

import Clases.Conexion;
import Clases.RRHH;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class RecursosHumanos extends javax.swing.JPanel {
      TableRowSorter trs;
    public DefaultTableModel modeloEmpleados = new DefaultTableModel();
    int NumeroEmpleado;
    String curriculum = "";
    boolean tipo = false;
    RRHH ingreso = new RRHH();

    public RecursosHumanos() {
        initComponents();
        buttonGroup2.add(cbAdministrador1);
        buttonGroup2.add(cbempleado1);
        modeloEmpleados.addColumn("No.");
        modeloEmpleados.addColumn("NOMBRES");
        modeloEmpleados.addColumn("APELLIDOS");
        modeloEmpleados.addColumn("DIRECCION");
        modeloEmpleados.addColumn("TELEFONO");
        modeloEmpleados.addColumn("EDAD");
        modeloEmpleados.addColumn("No. Identificacion");
        modeloEmpleados.addColumn("CARGO");
        modeloEmpleados.addColumn("OBSERVACIONES");
        jTableEmpleados.setModel(modeloEmpleados);
        RecuperarEmpleados();
        buttonGroup1.add(cbAdministrador);
        buttonGroup1.add(cbempleado);

        jTableEmpleados.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    txtId.setText(jTableEmpleados.getValueAt(jTableEmpleados.getSelectedRow(), 0).toString());
                    recuperarEmpleado(Integer.parseInt(jTableEmpleados.getValueAt(jTableEmpleados.getSelectedRow(), 0).toString()));
                    indiceConsulta.setSelectedIndex(1);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        indiceConsulta = new javax.swing.JTabbedPane();
        pnlPersonal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmpleados = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        pnlEmitir = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtSangre1 = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        txtDpi1 = new javax.swing.JTextField();
        txtEdad1 = new javax.swing.JTextField();
        txtDireccion1 = new javax.swing.JTextField();
        txtApellido1 = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtCargo1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtSalario1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtHoraEntrada1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtHoraSalida1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtNombre2 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        cbAdministrador1 = new javax.swing.JRadioButton();
        cbempleado1 = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        txtComision = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtAdelanto = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        pnlIngresar = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDpi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        btnCurriculum = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtSangre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtHoraSalida = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHoraEntrada = new javax.swing.JTextField();
        txtFechaIncio = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtContra = new javax.swing.JTextField();
        cbAdministrador = new javax.swing.JRadioButton();
        cbempleado = new javax.swing.JRadioButton();
        btbAgregarCargo = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnew = new javax.swing.JButton();

        setLayout(null);

        indiceConsulta.setForeground(new java.awt.Color(255, 255, 255));
        indiceConsulta.setToolTipText("");
        indiceConsulta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        indiceConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indiceConsultaclick(evt);
            }
        });

        pnlPersonal.setBackground(new java.awt.Color(36, 41, 46));
        pnlPersonal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableEmpleados.setRowHeight(40);
        jScrollPane1.setViewportView(jTableEmpleados);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(60, 190, 1220, 480);

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 51, 204));
        jLabel25.setText("Buscar:");
        jPanel1.add(jLabel25);
        jLabel25.setBounds(70, 130, 150, 24);

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 51, 204));
        jLabel42.setText("LISTADO GENERAL EMPLEADOS");
        jPanel1.add(jLabel42);
        jLabel42.setBounds(360, 60, 610, 47);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(150, 130, 250, 30);

        pnlPersonal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 760));

        indiceConsulta.addTab("Personal", pnlPersonal);

        pnlEmitir.setForeground(new java.awt.Color(51, 51, 51));
        pnlEmitir.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText("Apellidos:");
        pnlEmitir.add(jLabel12);
        jLabel12.setBounds(50, 290, 110, 40);

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 204));
        jLabel21.setText("Numero");
        pnlEmitir.add(jLabel21);
        jLabel21.setBounds(50, 170, 110, 40);

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 204));
        jLabel22.setText("Dirección:");
        pnlEmitir.add(jLabel22);
        jLabel22.setBounds(50, 330, 110, 40);

        jLabel23.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 204));
        jLabel23.setText("Edad:");
        pnlEmitir.add(jLabel23);
        jLabel23.setBounds(50, 380, 110, 40);

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 204));
        jLabel24.setText("DPI");
        pnlEmitir.add(jLabel24);
        jLabel24.setBounds(50, 430, 110, 40);

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 204));
        jLabel26.setText("Teléfono:");
        pnlEmitir.add(jLabel26);
        jLabel26.setBounds(50, 480, 110, 40);

        jLabel27.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 204));
        jLabel27.setText("Tipo Sangre:");
        pnlEmitir.add(jLabel27);
        jLabel27.setBounds(50, 530, 110, 40);

        txtSangre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtSangre1);
        txtSangre1.setBounds(170, 530, 220, 40);

        txtTelefono1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtTelefono1);
        txtTelefono1.setBounds(170, 480, 220, 40);

        txtDpi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtDpi1);
        txtDpi1.setBounds(170, 430, 220, 40);

        txtEdad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtEdad1);
        txtEdad1.setBounds(170, 380, 220, 40);

        txtDireccion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtDireccion1);
        txtDireccion1.setBounds(170, 330, 220, 40);

        txtApellido1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtApellido1);
        txtApellido1.setBounds(170, 280, 220, 40);

        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setEnabled(false);
        pnlEmitir.add(txtId);
        txtId.setBounds(170, 170, 220, 40);

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 204));
        jLabel28.setText("Cargo");
        pnlEmitir.add(jLabel28);
        jLabel28.setBounds(520, 170, 70, 40);
        pnlEmitir.add(txtCargo1);
        txtCargo1.setBounds(640, 170, 130, 40);

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 51, 204));
        jLabel29.setText("Salario:");
        pnlEmitir.add(jLabel29);
        jLabel29.setBounds(520, 220, 80, 40);
        pnlEmitir.add(txtSalario1);
        txtSalario1.setBounds(640, 220, 130, 40);

        jLabel31.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 51, 204));
        jLabel31.setText("Horario Entrada");
        pnlEmitir.add(jLabel31);
        jLabel31.setBounds(520, 270, 120, 40);
        pnlEmitir.add(txtHoraEntrada1);
        txtHoraEntrada1.setBounds(640, 270, 130, 40);

        jLabel32.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 204));
        jLabel32.setText("Horario Salida");
        pnlEmitir.add(jLabel32);
        jLabel32.setBounds(520, 320, 110, 40);
        pnlEmitir.add(txtHoraSalida1);
        txtHoraSalida1.setBounds(640, 320, 130, 40);

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 51, 204));
        jLabel33.setText("DATOS PERSONALES");
        pnlEmitir.add(jLabel33);
        jLabel33.setBounds(70, 70, 330, 30);

        jLabel34.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 204));
        jLabel34.setText("Nombres:");
        pnlEmitir.add(jLabel34);
        jLabel34.setBounds(50, 230, 110, 40);

        txtNombre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlEmitir.add(txtNombre2);
        txtNombre2.setBounds(170, 230, 220, 40);

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 204));
        jLabel35.setText("Adelantos/Observaciones");
        pnlEmitir.add(jLabel35);
        jLabel35.setBounds(960, 70, 310, 30);
        pnlEmitir.add(jSeparator1);
        jSeparator1.setBounds(850, 20, 480, 40);
        pnlEmitir.add(jSeparator5);
        jSeparator5.setBounds(20, 22, 420, 40);

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlEmitir.add(jSeparator13);
        jSeparator13.setBounds(430, 20, 30, 710);

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlEmitir.add(jSeparator14);
        jSeparator14.setBounds(1330, 20, 30, 710);

        jLabel36.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 204));
        jLabel36.setText("TIPO DE ACCESIBILIDAD");
        pnlEmitir.add(jLabel36);
        jLabel36.setBounds(560, 470, 200, 50);

        cbAdministrador1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbAdministrador1.setForeground(new java.awt.Color(0, 51, 204));
        cbAdministrador1.setText("ADMINISTRADOR");
        pnlEmitir.add(cbAdministrador1);
        cbAdministrador1.setBounds(570, 510, 140, 40);

        cbempleado1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbempleado1.setForeground(new java.awt.Color(0, 51, 204));
        cbempleado1.setText("EMPLEADO");
        pnlEmitir.add(cbempleado1);
        cbempleado1.setBounds(570, 550, 140, 40);

        jLabel37.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 204));
        jLabel37.setText("Comisiones");
        pnlEmitir.add(jLabel37);
        jLabel37.setBounds(930, 170, 90, 40);
        pnlEmitir.add(txtComision);
        txtComision.setBounds(1050, 170, 130, 40);

        jLabel38.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 204));
        jLabel38.setText("observaciones");
        pnlEmitir.add(jLabel38);
        jLabel38.setBounds(1060, 280, 130, 40);
        pnlEmitir.add(txtAdelanto);
        txtAdelanto.setBounds(1050, 220, 130, 40);

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 204));
        jLabel39.setText("Cargo/Puesto");
        pnlEmitir.add(jLabel39);
        jLabel39.setBounds(560, 70, 160, 30);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        pnlEmitir.add(jScrollPane2);
        jScrollPane2.setBounds(930, 330, 360, 150);
        pnlEmitir.add(jSeparator6);
        jSeparator6.setBounds(430, 20, 420, 40);

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlEmitir.add(jSeparator15);
        jSeparator15.setBounds(850, 20, 30, 710);

        jLabel40.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 51, 204));
        jLabel40.setText("Adelantos");
        pnlEmitir.add(jLabel40);
        jLabel40.setBounds(930, 220, 80, 40);

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        pnlEmitir.add(btnActualizar);
        btnActualizar.setBounds(520, 600, 230, 25);

        jButton1.setText("CARTA RECOMENDACION");
        pnlEmitir.add(jButton1);
        jButton1.setBounds(1110, 610, 180, 100);

        jButton2.setText("DESPEDIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pnlEmitir.add(jButton2);
        jButton2.setBounds(920, 610, 110, 100);

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 51, 204));
        jLabel30.setText("Usuario");
        pnlEmitir.add(jLabel30);
        jLabel30.setBounds(520, 370, 70, 40);
        pnlEmitir.add(txtUser);
        txtUser.setBounds(640, 370, 130, 40);

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 51, 204));
        jLabel41.setText("Contraseña");
        pnlEmitir.add(jLabel41);
        jLabel41.setBounds(520, 420, 80, 40);
        pnlEmitir.add(txtContrasena);
        txtContrasena.setBounds(640, 420, 130, 40);

        indiceConsulta.addTab("Emitir", pnlEmitir);

        pnlIngresar.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresar.setEnabled(false);
        pnlIngresar.setLayout(null);

        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setEnabled(false);
        pnlIngresar.add(txtNombre);
        txtNombre.setBounds(150, 210, 220, 40);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Nombres:");
        pnlIngresar.add(jLabel1);
        jLabel1.setBounds(34, 210, 110, 40);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Apellidos:");
        pnlIngresar.add(jLabel2);
        jLabel2.setBounds(34, 260, 110, 40);

        txtApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellido.setEnabled(false);
        pnlIngresar.add(txtApellido);
        txtApellido.setBounds(150, 260, 220, 40);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Edad:");
        pnlIngresar.add(jLabel3);
        jLabel3.setBounds(30, 360, 110, 40);

        txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEdad.setEnabled(false);
        pnlIngresar.add(txtEdad);
        txtEdad.setBounds(150, 360, 220, 40);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Curriculum");
        pnlIngresar.add(jLabel4);
        jLabel4.setBounds(30, 550, 70, 40);

        txtDpi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDpi.setEnabled(false);
        pnlIngresar.add(txtDpi);
        txtDpi.setBounds(150, 410, 220, 40);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Cargo");
        pnlIngresar.add(jLabel5);
        jLabel5.setBounds(550, 210, 70, 40);

        txtCargo.setEnabled(false);
        pnlIngresar.add(txtCargo);
        txtCargo.setBounds(670, 210, 130, 40);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("Salario:");
        pnlIngresar.add(jLabel6);
        jLabel6.setBounds(550, 260, 80, 40);

        txtSalario.setEnabled(false);
        pnlIngresar.add(txtSalario);
        txtSalario.setBounds(670, 260, 130, 40);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Usuario");
        pnlIngresar.add(jLabel7);
        jLabel7.setBounds(1040, 90, 100, 30);
        pnlIngresar.add(jSeparator2);
        jSeparator2.setBounds(80, 30, 390, 30);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlIngresar.add(jSeparator10);
        jSeparator10.setBounds(470, 30, 30, 710);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("DPI");
        pnlIngresar.add(jLabel8);
        jLabel8.setBounds(30, 410, 110, 40);

        btnCurriculum.setText("cargar");
        btnCurriculum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurriculumActionPerformed(evt);
            }
        });
        pnlIngresar.add(btnCurriculum);
        btnCurriculum.setBounds(150, 560, 220, 25);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("Teléfono:");
        pnlIngresar.add(jLabel9);
        jLabel9.setBounds(30, 460, 110, 40);

        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setEnabled(false);
        pnlIngresar.add(txtTelefono);
        txtTelefono.setBounds(150, 460, 220, 40);

        txtSangre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSangre.setEnabled(false);
        pnlIngresar.add(txtSangre);
        txtSangre.setBounds(150, 510, 220, 40);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setText("Tipo Sangre:");
        pnlIngresar.add(jLabel10);
        jLabel10.setBounds(30, 510, 110, 40);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 204));
        jLabel11.setText("DATOS PERSONALES");
        pnlIngresar.add(jLabel11);
        jLabel11.setBounds(100, 90, 330, 30);

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 204));
        jLabel13.setText("Horario Salida");
        pnlIngresar.add(jLabel13);
        jLabel13.setBounds(550, 410, 110, 40);

        txtHoraSalida.setEnabled(false);
        pnlIngresar.add(txtHoraSalida);
        txtHoraSalida.setBounds(670, 410, 130, 40);
        pnlIngresar.add(jSeparator3);
        jSeparator3.setBounds(470, 30, 400, 30);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlIngresar.add(jSeparator11);
        jSeparator11.setBounds(870, 30, 30, 710);

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setText("Dirección:");
        pnlIngresar.add(jLabel14);
        jLabel14.setBounds(30, 310, 110, 40);

        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion.setEnabled(false);
        pnlIngresar.add(txtDireccion);
        txtDireccion.setBounds(150, 310, 220, 40);

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setText("Fecha Inicio:");
        pnlIngresar.add(jLabel15);
        jLabel15.setBounds(550, 310, 100, 40);

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 204));
        jLabel16.setText("Horario Entrada");
        pnlIngresar.add(jLabel16);
        jLabel16.setBounds(550, 360, 120, 40);

        txtHoraEntrada.setEnabled(false);
        pnlIngresar.add(txtHoraEntrada);
        txtHoraEntrada.setBounds(670, 360, 130, 40);

        txtFechaIncio.setEnabled(false);
        pnlIngresar.add(txtFechaIncio);
        txtFechaIncio.setBounds(670, 310, 130, 40);
        pnlIngresar.add(jSeparator4);
        jSeparator4.setBounds(870, 30, 410, 30);

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlIngresar.add(jSeparator12);
        jSeparator12.setBounds(1280, 30, 30, 710);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 204));
        jLabel17.setText("Cargo/Puesto");
        pnlIngresar.add(jLabel17);
        jLabel17.setBounds(620, 90, 160, 30);

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 204));
        jLabel18.setText("Usuario:");
        pnlIngresar.add(jLabel18);
        jLabel18.setBounds(910, 200, 110, 40);

        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setEnabled(false);
        pnlIngresar.add(txtUsuario);
        txtUsuario.setBounds(1020, 200, 220, 40);

        jLabel19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 204));
        jLabel19.setText("TIPO DE ACCESIBILIDAD");
        pnlIngresar.add(jLabel19);
        jLabel19.setBounds(990, 320, 200, 40);

        txtContra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContra.setEnabled(false);
        pnlIngresar.add(txtContra);
        txtContra.setBounds(1020, 250, 220, 40);

        cbAdministrador.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbAdministrador.setForeground(new java.awt.Color(0, 51, 204));
        cbAdministrador.setText("ADMINISTRADOR");
        cbAdministrador.setEnabled(false);
        pnlIngresar.add(cbAdministrador);
        cbAdministrador.setBounds(1000, 390, 140, 27);

        cbempleado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbempleado.setForeground(new java.awt.Color(0, 51, 204));
        cbempleado.setText("EMPLEADO");
        cbempleado.setEnabled(false);
        pnlIngresar.add(cbempleado);
        cbempleado.setBounds(1000, 430, 140, 27);

        btbAgregarCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Agrega_empleado.png"))); // NOI18N
        btbAgregarCargo.setBorder(null);
        btbAgregarCargo.setBorderPainted(false);
        btbAgregarCargo.setContentAreaFilled(false);
        btbAgregarCargo.setEnabled(false);
        btbAgregarCargo.setFocusPainted(false);
        btbAgregarCargo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btbAgregarCargo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Agrega_empleado2.png"))); // NOI18N
        btbAgregarCargo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Agrega_empleado1.png"))); // NOI18N
        btbAgregarCargo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btbAgregarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAgregarCargoActionPerformed(evt);
            }
        });
        pnlIngresar.add(btbAgregarCargo);
        btbAgregarCargo.setBounds(1040, 610, 101, 102);

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 204));
        jLabel20.setText("Contraseña:");
        pnlIngresar.add(jLabel20);
        jLabel20.setBounds(910, 250, 110, 40);

        btnew.setText("NEW");
        btnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnewActionPerformed(evt);
            }
        });
        pnlIngresar.add(btnew);
        btnew.setBounds(20, 40, 57, 25);

        indiceConsulta.addTab("Ingresar Nuevo Personal", pnlIngresar);

        add(indiceConsulta);
        indiceConsulta.setBounds(0, 0, 1375, 804);
    }// </editor-fold>//GEN-END:initComponents

    private void indiceConsultaclick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indiceConsultaclick

    }//GEN-LAST:event_indiceConsultaclick

    private void btbAgregarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAgregarCargoActionPerformed
        int edad;
        double salario;
        if (!"".equals(txtCargo.getText()) && !"".equals(txtNombre.getText()) && !"".equals(txtApellido.getText()) && !"".equals(txtDireccion.getText()) && !"".equals(txtTelefono.getText()) && !"".equals(txtSalario.getText()) && !"".equals(txtUsuario.getText()) && !"".equals(txtContra.getText()) && !"".equals(txtFechaIncio.getText())) {
            try {
                edad = Integer.parseInt(txtEdad.getText());
                salario = Double.parseDouble(txtSalario.getText());
                if (edad > 0) {
                    if (salario > 0) {
                        if (cbAdministrador.isSelected()) {
                            tipo = true;
                        }
                        if (cbempleado.isSelected()) {
                            tipo = false;
                        }
                        ingreso.insertarEmpleado(txtNombre.getText(), txtApellido.getText(), txtDireccion.getText(), Integer.parseInt(txtEdad.getText()), txtDpi.getText(), txtTelefono.getText(), txtSangre.getText(), curriculum, txtCargo.getText(), txtFechaIncio.getText(), txtHoraEntrada.getText(), txtHoraSalida.getText(), txtUsuario.getText(), txtContra.getText(), tipo, Double.parseDouble(txtSalario.getText()), 0, 0, ".");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "VERIFIQUE BIEN LOS CAMPOS");
            }
            JOptionPane.showMessageDialog(null, "EMPLEADO INGRESADO EXITOSAMENTE :) ");

        } else {
            JOptionPane.showMessageDialog(null, "ERROR, ALGUNOS CAMPOS NO PUEDEN ESTAR INCOMPLETOS");
        }
        limpiarTablaEmpleados();
        RecuperarEmpleados();
        CrearFilas1();

    }//GEN-LAST:event_btbAgregarCargoActionPerformed

    private void btnCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurriculumActionPerformed

    }//GEN-LAST:event_btnCurriculumActionPerformed

    private void btnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnewActionPerformed
        try {
            ultimoID();
        } catch (SQLException ex) {
            Logger.getLogger(RecursosHumanos.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtCargo.setEnabled(true);
        txtEdad.setEnabled(true);
        txtDpi.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtContra.setEnabled(true);
        txtFechaIncio.setEnabled(true);
        txtHoraEntrada.setEnabled(true);
        txtHoraSalida.setEnabled(true);
        cbAdministrador.setEnabled(true);
        cbempleado.setEnabled(true);
        btbAgregarCargo.setEnabled(true);
        txtSangre.setEnabled(true);
        txtSalario.setEnabled(true);
        btnew.setEnabled(false);
    }//GEN-LAST:event_btnewActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String url = "C:\\Users\\ismar\\Desktop\\CartaDeDespido.docx";
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(RecursosHumanos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int edad;
        double salario;
        if (!"".equals(txtCargo1.getText()) && !"".equals(txtNombre2.getText()) && !"".equals(txtApellido1.getText()) && !"".equals(txtDireccion1.getText()) && !"".equals(txtTelefono1.getText()) && !"".equals(txtSalario1.getText())) {
            try {
                edad = Integer.parseInt(txtEdad1.getText());
                salario = Double.parseDouble(txtSalario1.getText());
                if (edad > 0) {
                    if (salario > 0) {
                        if (cbAdministrador1.isSelected()) {
                            tipo = true;
                        }
                        if (cbempleado1.isSelected()) {
                            tipo = false;
                        }
                        ingreso.ActualizarEmpleado(txtNombre2.getText(), txtApellido1.getText(),txtDireccion1.getText(), edad, txtDpi1.getText(), txtTelefono1.getText(), txtSangre1.getText(), txtCargo1.getText(), txtHoraEntrada1.getText(), txtHoraSalida1.getText(), tipo, salario, Double.parseDouble(txtComision.getText()),Double.parseDouble(txtAdelanto.getText()) , jTextArea1.getText() ,txtUser.getText(),txtContrasena.getText(),Integer.parseInt(txtId.getText()));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "VERIFIQUE BIEN LOS CAMPOS");
            }
            JOptionPane.showMessageDialog(null, "EMPLEADO INGRESADO EXITOSAMENTE :) ");

        } else {
            JOptionPane.showMessageDialog(null, "ERROR, ALGUNOS CAMPOS NO PUEDEN ESTAR INCOMPLETOS");
        }
        limpiarTablaEmpleados();
        RecuperarEmpleados();
        CrearFilas1();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
             // FILTRO DEL JTABLE
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), 1));
            }
        });

        trs = new TableRowSorter(modeloEmpleados);
        jTableEmpleados.setRowSorter(trs);
            
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbAgregarCargo;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCurriculum;
    private javax.swing.JButton btnew;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton cbAdministrador;
    private javax.swing.JRadioButton cbAdministrador1;
    private javax.swing.JRadioButton cbempleado;
    private javax.swing.JRadioButton cbempleado1;
    private javax.swing.JTabbedPane indiceConsulta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTableEmpleados;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlEmitir;
    private javax.swing.JPanel pnlIngresar;
    private javax.swing.JPanel pnlPersonal;
    private javax.swing.JTextField txtAdelanto;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCargo1;
    private javax.swing.JTextField txtComision;
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccion1;
    private javax.swing.JTextField txtDpi;
    private javax.swing.JTextField txtDpi1;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEdad1;
    private javax.swing.JTextField txtFechaIncio;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtHoraEntrada1;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtHoraSalida1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtSalario1;
    private javax.swing.JTextField txtSangre;
    private javax.swing.JTextField txtSangre1;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    public int ultimoID() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        int x = 0;
        String sql = "SELECT MAX(id) AS ultimo FROM RecursosHumanos";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            NumeroEmpleado = rs.getInt("ultimo");
        }
        System.out.println("NUMERO DE ID CORRESPONDIENTE ES: " + NumeroEmpleado + 1);
        return NumeroEmpleado + 1;
    }

    public void RecuperarEmpleados() {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT id,Nombres,Apellidos,Direccion, Telefono, Edad, Dpi ,Cargo,Observaciones FROM RecursosHumanos";

        String[] datos = new String[9];
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
                datos[8] = rs.getString(9);

                modeloEmpleados.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarTablaEmpleados() {
        try {

            int a = jTableEmpleados.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                modeloEmpleados.removeRow(modeloEmpleados.getRowCount() - 1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void CrearFilas1() {
        String[] datos = new String[9];
        datos[0] = "";
        datos[1] = "";
        datos[2] = "";
        datos[3] = "";
        datos[4] = "";
        datos[5] = "";
        datos[6] = "";
        datos[7] = "";
        datos[8] = "";
        for (int i = 0; i < 8; i++) {
            modeloEmpleados.addRow(datos);
        }
    }

    public void recuperarEmpleado(int empleado) {
        
        
        
        
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        String sql = "SELECT Nombres , Apellidos, Direccion, Edad,Acceso, Dpi, Telefono, TipoSangre,Cargo, HoraEntrada,HoraSalida,Salario,Comisiones,Prestamos,Observaciones FROM RecursosHumanos WHERE id = '" + empleado + "'";
        String[] datos = new String[5];
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                txtNombre2.setText(rs.getString(1));
                txtApellido1.setText(rs.getString(2));
                txtDireccion1.setText(rs.getString(3));
                txtEdad1.setText(String.valueOf(rs.getInt(4)));
                boolean acces = rs.getBoolean(5);
                if (acces == true) {
                    cbAdministrador1.setSelected(true);
                } else {
                    cbempleado1.setSelected(true);
                }
                txtDpi1.setText(rs.getString(6));
                txtTelefono1.setText(rs.getString(7));
                txtSangre1.setText(rs.getString(8));
                txtCargo1.setText(rs.getString(9));
                txtHoraEntrada1.setText(rs.getString(10));
                txtHoraSalida1.setText(rs.getString(11));
                txtSalario1.setText(String.valueOf(rs.getDouble(12)));
                txtComision.setText(String.valueOf(rs.getDouble(13)));
                txtAdelanto.setText(String.valueOf(rs.getDouble(14)));
                jTextArea1.setText(rs.getString(15));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

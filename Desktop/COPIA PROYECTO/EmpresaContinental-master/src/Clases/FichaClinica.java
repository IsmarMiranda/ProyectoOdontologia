/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ismar
 */
public class FichaClinica {
   
   public int orden,id;
   public String pieza;
   public String tratamiento;
   public double valor;
   ///////---------------------------------INSERCION DE DATOS-------------------------------------////////////////////////////////
   public void control(int control){
          String sql = "INSERT INTO Control(ControlFirma) VALUES(?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();

        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setInt(1,control);
          
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DE SESION");
        }
   }
    public void insertarPaciente(String nombre, String direccion, String telefono, int edad, String sexo,String dpi,String nit) {
        String sql = "INSERT INTO Paciente(Nombre,Direccion,Telefono,edad,Sexo,Identificacion,Nit) VALUES(?,?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, nombre);
            pps.setString(2, direccion);
            pps.setString(3, telefono);
            pps.setInt(4, edad);
            pps.setString(5, sexo);
            pps.setString(6, dpi);
            pps.setString(7, nit);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DE PACIENTE");
        }
    }

    //INSERTAMOS UNA HISTORIA MEDICA DEL PACIENTE
    public void insertarHistoriaMed(String enfermo, String tx, String medicamento, String alergia, boolean embarazada, int paciente) {
        String sql = "INSERT INTO HistoriaMedica(Enfermedad,Tratamiento,Medicamento,Alergico,Embarazada,Paciente_id) VALUES(?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, enfermo);
            pps.setString(2, tx);
            pps.setString(3, medicamento);
            pps.setString(4, alergia);
            pps.setBoolean(5, embarazada);
            pps.setInt(6, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION DE HISTORIA MEDICA");
        }
    }

    public void insertarEnfermedades(String diabetes, String alergias, String alteraciones, String presion, String corazon, String herpes, String trastornos, String otros, int paciente) {
        String sql = "INSERT INTO Enfermedades(Diabetes,Alergias,AltRespiratorias,Presion,Corazon,Herpes,Trastornos,Otros,Paciente_id) VALUES(?,?,?,?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, diabetes);
            pps.setString(2, alergias);
            pps.setString(3, alteraciones);
            pps.setString(4, presion);
            pps.setString(5, corazon);
            pps.setString(6, herpes);
            pps.setString(7, trastornos);
            pps.setString(8, otros);
            pps.setInt(9, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION DE ENFERMEDADES");
        }
    }

    //INSERTAMOS UNA HISTORIA ODONTOLOGICA
    public void insertarHistoriaOdontologica(String visita, String tratamiento, String complicacion, int paciente) {
        String sql = "INSERT INTO HistoriaOdontologica(Visita,Tratamiento,Complicacion,Paciente_id) VALUES(?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, visita);
            pps.setString(2, tratamiento);
            pps.setString(3, complicacion);
            pps.setInt(4, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION HISTORIA ODONTOLOGICA");
        }
    }

    public void insertarTratamiento(String nombre, String fecha, int orden, String pieza, String tratamiento, double valor, int paciente) {
        java.util.Date d = new java.util.Date();
        java.sql.Date date2 = new java.sql.Date(d.getTime());
        String sql = "INSERT INTO Tratamiento(Nombre,Fecha,Orden,Pieza,Tratamiento,Valor,Finalizado,Paciente_id) VALUES(?,?,?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();

        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, nombre);
            pps.setDate(2, date2);
            pps.setInt(3, orden);
            pps.setString(4, pieza);
            pps.setString(5, tratamiento);
            pps.setDouble(6, valor);
            pps.setBoolean(7, false);
            pps.setString(8, "");
            pps.setInt(8, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DEL TRATAMIENTO");
        }
    }
      public void insertarSesion(String fecha ,String pieza ,String tratamiento, double valor, double abono, double saldo, int paciente,int tratamientoID){
        String sql = "INSERT INTO Sesion(Fecha,Pieza,Tratamiento,Valor,Abono,Saldo,Paciente_id,Tratamiento_id) VALUES(?,?,?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();

        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, fecha);
            pps.setString(2, pieza);
            pps.setString(3, tratamiento);
            pps.setDouble(4, valor);
            pps.setDouble(5, abono);
            pps.setDouble(6, saldo);
            pps.setInt(7, paciente);
            pps.setInt(8, tratamientoID);
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DE SESION");
        }
    }
       public void insertarFactura(String Nombre, String Direccion, String telefono, String nit, String fecha, String inicio, String fin, boolean tipo, String ordenX,String pieza,String Trat,double valor,int paciente) {
        String sql = "INSERT INTO Factura(Nombre,Direccion,Telefono,Nit,Fecha,FechaInicio,FechaFin,Tipo,Orden,Pieza,Tratamiento,Valor,Paciente_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, Nombre);
            pps.setString(2, Direccion);
            pps.setString(3, telefono);
            pps.setString(4, nit);
            pps.setString(5, fecha);
            pps.setString(6, inicio);
            pps.setString(7, fin);
            pps.setBoolean(8, tipo);
            pps.setString(9, ordenX);
            pps.setString(10, pieza);
            pps.setString(11, Trat);
            pps.setDouble(12, valor);
            pps.setInt(13, paciente);
            
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION DE ENFERMEDADES");
        }
    }
    ///////////////////////////-------------------------------MODIFICACION DE DATOS-----------------------------------------///////////////////
      public void ModificarPaciente(String nombre, String direccion, String telefono, int edad, String sexo,String dpi,String nit,int id) {
        String sql = "UPDATE Paciente SET Nombre = ?,Direccion = ?,Telefono = ?,edad = ?,Sexo = ? ,Identificacion =?, Nit =?  WHERE id = '"+id+"'";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, nombre);
            pps.setString(2, direccion);
            pps.setString(3, telefono);
            pps.setInt(4, edad);
            pps.setString(5, sexo);
            pps.setString(6, dpi);
            pps.setString(7, nit);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION DEL PACIENTE, POR FAVOR VEA LOS DATOS INGRESADOS");
        }
    }
      
       public void ModificarHistoriaMed(String enfermo, String tx, String medicamento, String alergia, boolean embarazada, int paciente,int id) {
        String sql = "UPDATE HistoriaMedica SET Enfermedad = ?,Tratamiento = ? ,Medicamento = ?,Alergico =?,Embarazada =? WHERE Paciente_id = '"+id+"'";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, enfermo);
            pps.setString(2, tx);
            pps.setString(3, medicamento);
            pps.setString(4, alergia);
            pps.setBoolean(5, embarazada);
//            pps.setInt(6, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION DE HISTORIA MEDICA. POR FAVOR, VEA LOS DATOS INGREASADOS");
        }
    }
       
     public void modificarEnfermedades(String diabetes, String alergias, String alteraciones, String presion, String corazon, String herpes, String trastornos, String otros, int paciente,int id) {
        String sql = "UPDATE Enfermedades SET Diabetes = ?,Alergias =?,AltRespiratorias = ?,Presion = ?,Corazon = ?,Herpes = ?,Trastornos = ?,Otros = ? WHERE Paciente_id = '"+id+"'" ;
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, diabetes);
            pps.setString(2, alergias);
            pps.setString(3, alteraciones);
            pps.setString(4, presion);
            pps.setString(5, corazon);
            pps.setString(6, herpes);
            pps.setString(7, trastornos);
            pps.setString(8, otros);
           // pps.setInt(9, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION DE ENFERMEDADES. POR FAVOR, VEA LOS DATOS INGRESADOS");
        }
    }
       public void ModificarHistoriaOdontologica(String visita, String tratamiento, String complicacion, int paciente,int id) {
        String sql = "UPDATE HistoriaOdontologica SET Visita = ? ,Tratamiento = ?,Complicacion = ? WHERE Paciente_id = '"+id+"'";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, visita);
            pps.setString(2, tratamiento);
            pps.setString(3, complicacion);
           // pps.setInt(4, paciente);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION");
        }
    }
     
       

       
       
       
}

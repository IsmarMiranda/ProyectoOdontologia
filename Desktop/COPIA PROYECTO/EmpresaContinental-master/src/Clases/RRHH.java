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
public class RRHH {
    public void insertarEmpleado(String nombres, String apellidos,String direccion,int edad,String dpi,String telefono,String tiposangre,String curriculum,String cargo,String fechai,String entrada,String salida,String user,String pas, boolean acceso ,double salario,double comision,double prestamo,String observaciones){
     String sql = "INSERT INTO RecursosHumanos(Nombres,Apellidos,Direccion,Edad,Dpi,Telefono,TipoSangre,Curriculum,Cargo,FechaInicio,HoraEntrada,HoraSalida,Usuario,Contrasena,Acceso,Salario,Comisiones,Prestamos,Observaciones) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, nombres);
            pps.setString(2, apellidos);
            pps.setString(3, direccion);
            pps.setInt(4, edad);
            pps.setString(5, dpi);
            pps.setString(6, telefono);
            pps.setString(7, tiposangre);
            pps.setString(8, curriculum);
            pps.setString(9, cargo);
            pps.setString(10, fechai);
            pps.setString(11, entrada);
            pps.setString(12, salida);
            pps.setString(13,user);
            pps.setString(14, pas);
            pps.setBoolean(15, acceso);
            pps.setDouble(16, salario);
            pps.setDouble(17, comision);
            pps.setDouble(18, prestamo);
            pps.setString(19, observaciones);
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "EMPLEADO INGRESADO EXITOSAMENTE");
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DEL EMPLEADO");
        }   
    }
  
    public void ActualizarEmpleado(String nombres, String apellidos,String direccion,int edad,String dpi,String telefono,String tiposangre,String cargo,String entrada,String salida, boolean acceso ,double salario,double comision,double prestamo,String observaciones,String user,String contra,int id){
     String sql = "UPDATE RecursosHumanos SET Nombres =?,Apellidos =? , Direccion =?, Edad =?, Dpi =? , Telefono =?, TipoSangre =?,Cargo =?,"
             + "  HoraEntrada =?, HoraSalida =?, Acceso =?, Salario =? ,Comisiones =?, Prestamos =?,Observaciones =?, Usuario = ?, Contrasena =? WHERE id = '"+id+"'";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1, nombres);
            pps.setString(2, apellidos);
            pps.setString(3, direccion);
            pps.setInt(4, edad);
            pps.setString(5, dpi);
            pps.setString(6, telefono);
            pps.setString(7, tiposangre);
            pps.setString(8, cargo);
            pps.setString(9, entrada);
            pps.setString(10, salida);
            pps.setBoolean(11, acceso);
            pps.setDouble(12, salario);
            pps.setDouble(13, comision);
            pps.setDouble(14, prestamo);
            pps.setString(15, observaciones);
            pps.setString(16, user);
            pps.setString(17, contra);
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO ACTUALIZADOS EXITOSAMENTE");
        } catch (SQLException ex) {
            Logger.getLogger(FichaClinica.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DEL EMPLEADO");
        }   
    }
}

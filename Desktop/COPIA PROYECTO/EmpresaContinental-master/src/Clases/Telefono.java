/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Forms.Citas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author igeni
 */
public class Telefono {
    private PreparedStatement Insertar;
    private Statement St;
    
     
    public void Insertar(String numero, int empleado) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("INSERT INTO telefono (numero, empleados_id_empleados)"+" values (?,?)");
        
            Insertar.setString(1, numero);
            Insertar.setInt(2, empleado);
        
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void Modificar(String numero, String anterior) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE telefono SET numero = ? WHERE numero = ?");
        
            Insertar.setString(1, numero);
            Insertar.setString(2, anterior);
        
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void Eliminar(String telefono) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("DELETE from telefono WHERE numero = ?");
        
            Insertar.setString(1, telefono);
        
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public DefaultTableModel Buscar(DefaultTableModel modelo, int indice)
    {
        
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[1];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT numero FROM telefono WHERE empleados_id_empleados = " + (int)indice);
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            modelo.addRow(datos);
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(Citas.class.getName()).log(Level.SEVERE, null, ex);
          }
        return modelo;
    }
    
    public DefaultTableModel Filtro(DefaultTableModel modelo, String telefono, int id)
    {
        
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[1];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT numero FROM telefono WHERE numero like"+'"'+ telefono+ "%"+'"' + "AND empleados_id_empleados = " + id);
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            modelo.addRow(datos);
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(Citas.class.getName()).log(Level.SEVERE, null, ex);
          }
        return modelo;
    }
    
    public boolean Encontrado(String telefono)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[1];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT numero FROM telefono");
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            if (datos[0].equals(telefono))
                return true;
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(Citas.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;
    }
}

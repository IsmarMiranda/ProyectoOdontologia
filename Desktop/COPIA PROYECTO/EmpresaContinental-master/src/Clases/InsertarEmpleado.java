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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author igeni
 */
public class InsertarEmpleado {
    
    private PreparedStatement Insertar;
    private Statement St;
    Clases.Contrasena contrasena = new Clases.Contrasena();
     
    public void Insertar(String nombres, String apellidos, String direccion, String usuario, String contrasena, int administrador, String pregunta, String respuesta) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("INSERT INTO empleados (nombre, apellido, estado, direccion, usuario, contrasena, administrador"
                    + ", pregunta, respuesta)"+" values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
            Insertar.setString(1, nombres);
            Insertar.setString(2, apellidos);
            Insertar.setString(3, "Activo");
            Insertar.setString(4, direccion);
            Insertar.setString(5, usuario);
            Insertar.setString(6, contrasena);
            Insertar.setInt(7, administrador);
            Insertar.setString(8, pregunta);
            Insertar.setString(9, respuesta);
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void Modificar(String nombres, String apellidos, String direccion, int id) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE empleados SET nombre = ?, apellido = ?, direccion = ? WHERE id_empleados = ?");
        
            Insertar.setString(1, nombres);
            Insertar.setString(2, apellidos);
            Insertar.setString(3, direccion);
            Insertar.setInt(4, id);
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void Eliminar(int id) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE empleados SET estado = ? WHERE id_empleados = ?");
        
            Insertar.setString(1, "Inactivo");
            Insertar.setInt(2, (int)id);
            
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void InsertarUsuario(String usuario, String contrasena, int fila, int administrador) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE empleados SET usuario = ?, contrasena = ?, administrador = ? WHERE id_empleados = ?");
            Insertar.setString(1, usuario);
            Insertar.setString(2, contrasena);
            Insertar.setInt(3, administrador);
            Insertar.setInt(4, (int)fila);
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void ModificarUsuario(String contrasena, Object fila) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE empleados contrasena = ? WHERE id_empleados = ?");
            Insertar.setString(1, contrasena);
            Insertar.setInt(2, (int)fila);
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void EliminarUsuario(String contrasena, Object fila) throws SQLException
    {
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE empleados usuario = ?, contrasena = ? WHERE id_empleados = ?");
            Insertar.setString(1, "");
            Insertar.setString(2, "");
            Insertar.setInt(3, (int)fila);
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public boolean Activo(String cuenta)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[6];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT estado from empleados where usuario = "+'"'+cuenta+'"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        if (datos[0].equals("Activo"))
            return true;
        else
            return false;
    }
    public DefaultTableModel Buscar(DefaultTableModel modelo)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[20];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT id_empleados, nombre, apellido, estado, direccion, usuario, administrador FROM empleados");
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            System.out.println(datos[0]);
            datos[1]= rs.getString(2);
            System.out.println(datos[1]);
            datos[2]= rs.getString(3);
            System.out.println(datos[2]);
            datos[3]= rs.getString(5);
            System.out.println(datos[3]);
            datos[4]= rs.getString(4);
            System.out.println(datos[4]);
            datos[5]= rs.getString(6);
            System.out.println(datos[5]);
            datos[6]= rs.getString(7);
            if (datos[6].equals("0"))
                datos[6] = "Vendedor";
            else if (datos[6].equals("1"))
                datos[6] = "Administrador";
            if (datos[4].equals("Activo"))
                modelo.addRow(datos);
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return modelo;
    }
    
    public DefaultTableModel Filtro(DefaultTableModel modelo, String busqueda)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[6];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT id_empleados, nombre, apellido, estado, direccion, usuario FROM empleados WHERE nombre like"+'"'+ busqueda +"%"+'"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            System.out.println(datos[0]);
            datos[1]= rs.getString(2);
            System.out.println(datos[1]);
            datos[2]= rs.getString(3);
            System.out.println(datos[2]);
            datos[3]= rs.getString(5);
            System.out.println(datos[3]);
            datos[4]= rs.getString(4);
            System.out.println(datos[4]);
            datos[5]= rs.getString(6);
            System.out.println(datos[5]);
            if (!datos[4].equals("Inactivo"))
                modelo.addRow(datos);
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return modelo;
    }
    
    public int ObtenerId(String nick)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[6];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT id_empleados from empleados where usuario = "+'"'+nick+'"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            return Integer.parseInt(datos[0]);
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return 0;
    }
    
    public boolean Vacio()
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[6];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT id_empleados from empleados");
              
        while (rs.next()) {
            return false;
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return true;
    }
    
    public boolean BuscarUsuario(String usuario)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[5];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT usuario FROM empleados");
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            try
            {
                if (datos[0].equals(usuario))
                return true;
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
                return false;
            }
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;
    }
    
    public String ObtenerPregunta(String usuario)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[1];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT pregunta FROM empleados where usuario = " + '"' + usuario + '"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            try
            {
                return datos[0];
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return "";
    }
    
    public String ObtenerContrasena(String usuario)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[1];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT contrasena FROM empleados where usuario = " + '"' + usuario + '"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            try
            {
                return contrasena.Desencriptar(datos[0]);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return "";
    }
    
    public String ObtenerRespuesta(String usuario, String respuesta)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[1];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT respuesta FROM empleados where usuario = " + '"' + usuario + '"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            try
            {
                if (datos[0].equals(respuesta))
                return datos[0];
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return "";
    }
    
    public boolean Administrador(String usuario)
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[5];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT administrador FROM empleados where usuario = "+'"'+usuario+'"');
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            try
            {
                if (datos[0].equals("1"))
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;
    }
}

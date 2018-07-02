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
public class Inventarios {
     String sql= "INSERT INTO Inventario(Nombre,Precio,Existencia,total) VALUES(?,?,?,?)";
           String sql1 = "DELETE FROM inventario WHERE id_proveedor =?";
           String sql2 = "UPDATE inventario SET Nombre=?,Precio=?,Existencia=?,Total=? WHERE repartidor=?";
    public void insertar(String nombre,double precio,int existencia){
        
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
            try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setString(1,nombre);
            pps.setDouble(2,precio);
            pps.setInt(3,existencia);
            pps.setDouble(4,precio*existencia);
            
            pps.executeUpdate();
                System.out.println("se agrego exitosamente el proveedor");
            } catch (SQLException ex) {
                 Logger.getLogger(Inventarios.class.getName()).log(Level.SEVERE, null, ex);
                 JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION");
            }
    }
    
    public void Modificar(int id,String nombre,String empresa,String correo,String direccion, String telefono){
        
    }
    
    public void eliminar(int id) throws SQLException{
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
               try (PreparedStatement pps = cn.prepareStatement(sql1)) {
                   pps.setInt(1, id);
                 //  pps.execute();
               }
   }
    
    public void update(String nombre, double precio, int existencia,int id) throws SQLException
    {
        PreparedStatement Insertar;
        Clases.Conexion con = new Clases.Conexion();
        Connection cn=con.ConectarBaseDatos();
        try
        {
            Insertar = cn.prepareStatement("UPDATE inventario SET Nombre = ?,Precio = ?, Existencia = ?,Total = ? WHERE id_Inventario ='"+id+"'");
            Insertar.setString(1, nombre);
            Insertar.setDouble(2, precio);
            Insertar.setInt(3, existencia);
            Insertar.setDouble(4, precio*existencia);
            Insertar.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}


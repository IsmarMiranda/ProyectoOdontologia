package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Cliente {
   private String nombre,direccion,nit;
   private int idCliente;
   
   public Cliente(){}
   
    public int InsertarCliente(String NombreClien, String DireccionClien,String NitClien,Connection ConexionBase){
       try {
           PreparedStatement stm;
           //Insertar un cliente en la base de datos
           stm = ConexionBase.prepareStatement("INSERT INTO cliente(nombre,direccion,nit) VALUES(?,?,?)");
           stm.setString(1, NombreClien);
           stm.setString(2, DireccionClien);
           stm.setString(3, NitClien);
           stm.executeUpdate();
           stm.close();
           //Obtener el id del cliente insertado
           Statement st;
           st = ConexionBase.createStatement();
           ResultSet consulta = st.executeQuery("select last_insert_id()");
           consulta.next(); //leo los datos
           return consulta.getInt(1);
       } catch (SQLException ex) {
           Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
           return 0;
       }    
  }
}

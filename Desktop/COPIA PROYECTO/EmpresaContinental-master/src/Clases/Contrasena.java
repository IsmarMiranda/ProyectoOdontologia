
package Clases;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Contrasena {
        private boolean tipo;
        public void setTipo(boolean x){
            tipo = x;
        }
        public boolean getTipo(){
            return tipo;
        }
    public String Encriptar(String texto) {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
 
    public String Desencriptar(String textoEncriptado) throws Exception {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    
    public boolean Buscar(String usuario, String contrasena) throws Exception
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[2];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT usuario, contrasena FROM empleados");
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            System.out.println(datos[0]);
            datos[1]= rs.getString(2);
            System.out.println(datos[1]);
            System.out.println(Desencriptar(datos[1]));
            if ((usuario.equals(String.valueOf(datos[0])) && contrasena.equals(Desencriptar(datos[1]))))
                return true;
            }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
          return false;
    }
    public boolean BuscarMio(String usuario, String contrasena) throws Exception
    {
        Conexion con = new Conexion();
        Connection cn=con.ConectarBaseDatos();
        String[] datos = new String[2];
        Statement st;
          try {
              st=cn.createStatement();
              ResultSet rs=st.executeQuery("SELECT Usuario, Contrasena,Acceso FROM RecursosHumanos");
              
        while (rs.next()) {
            datos[0]= rs.getString(1);
            System.out.println(datos[0]);
            datos[1]= rs.getString(2);
            System.out.println(datos[1]);
            System.out.println(Desencriptar(datos[1]));
            boolean acceso = rs.getBoolean(3);
            setTipo(acceso);
            if ((usuario.equals(String.valueOf(datos[0])) && contrasena.equals((datos[1]))))
                return true;
            }
              
          } catch (SQLException ex) {
              Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
          }
          return false;
    }
    public boolean accesoX(boolean dato){
        return dato;
    }
}

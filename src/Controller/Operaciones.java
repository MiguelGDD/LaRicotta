/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexiones.Conexion;
import Model.Productos;
import Model.NodoAll;
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
 * @author angel
 */
public class Operaciones {

    public Productos prod;

    DefaultTableModel model = new DefaultTableModel();
    NodoAll userProd = new NodoAll();
    public static NodoAll ConsultInforme = new NodoAll();

    Statement st = null;
    ResultSet rs = null;
    Connection con2 = null;
    String query;

    public Operaciones() {
        prod = new Productos();
        userProd = null;
    }

    public void RegistrarProducto(String valores) {

        String campos = "NomProducto,Cantidad,FechaObtencion";
        String tabla = "Productos";
        try {
            query = "insert into " + tabla + " (" + campos + ") values (" + valores + ")";
            Connection con = null;
            Conexion conect = new Conexion();
            con = conect.getConnection();
            Statement st = con.createStatement();
            st.execute(query);
            JOptionPane.showMessageDialog(null, "Regitro guardado exitosamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error sql" + ex);
        }

    }

    public boolean ValidarCedulaRegistro(String NomProducto){
        try {
            Connection con1 = null;
            Conexion conect1 = new Conexion();
            con1 = conect1.getConnection();
            String sql = "SELECT * FROM Productos";
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               prod.NomProducto= rs.getString("NomProducto");
                if (prod.NomProducto.equals(NomProducto)) {
                    return false;
                }

            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"El producto "+prod.NomProducto+" Ya esta registrado"+e, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
 public boolean EliminarUser (int ID){
        try {
            Connection con = null;
                    Conexion conect = new Conexion();
                    con = conect.getConnection();
                    Statement st = con.createStatement();
                    String sql = "delete from Productos where IDProducto = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, ID);
                    int n = pst.executeUpdate();
                    if (n > 0)
                    {
                        return true;
                    }
            
            
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return false;
        
    }
}

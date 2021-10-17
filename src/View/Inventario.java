/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Operaciones;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Conexiones.Conexion;
import static java.lang.Character.toUpperCase;
import static java.lang.Character.toUpperCase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author angel
 */
public class Inventario extends javax.swing.JInternalFrame {

    Conexion enlace = new Conexion();
    Connection conect = enlace.getConnection();
    DefaultTableModel m;
    Operaciones op = new Operaciones();

    /**
     * Creates new form Inventario
     */
    public Inventario() {
        initComponents();
        Mostrarinventario();
        Bucar(0, null);
    }

    public void Nuevo() {
        txtnombre.setText("");
        txtcantidad.setText("");
        cmbdia.setSelectedIndex(0);
        cmbmes.setSelectedIndex(0);
    }

    public void Mostrarinventario() {
        DefaultTableModel tcliente = new DefaultTableModel();
        tcliente.addColumn("ID");
        tcliente.addColumn("Nombre Prod");
        tcliente.addColumn("Cantidad Und");
        tcliente.addColumn("Fecha de Compra");
        jTable1.setModel(tcliente);

        String[] datos = new String[4];
        try {
            Statement Leer = conect.createStatement();
            ResultSet resultado = Leer.executeQuery("SELECT * FROm Productos");
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                tcliente.addRow(datos);

            }
            jTable1.setModel(tcliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "Error en la consulta");
        }
    }

    public void ELiminarProducto() {
        int fila = jTable1.getSelectedRow();
        String valor = jTable1.getValueAt(fila, 0).toString();
        try {
            PreparedStatement eliminar = conect.prepareStatement("DELETE FROM Productos WHERE IDProducto = '" + valor + "'");
            eliminar.execute();
            JOptionPane.showMessageDialog(null, "Producto eliminado");
            Mostrarinventario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error" + e, "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);

        }

    }

    public void Actualizar() {
        int fila = jTable1.getSelectedRow();

        if (fila < 1) {
            JOptionPane.showMessageDialog(null, "Debe editar antes un dato", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);

        }
        String id = jTable1.getValueAt(fila, 0).toString();
        String nombre = jTable1.getValueAt(fila, 1).toString();
        String cantidad = jTable1.getValueAt(fila, 2).toString();
        String fecha = jTable1.getValueAt(fila, 3).toString();
        try {
            PreparedStatement actu = conect.prepareStatement("UPDATE Productos SET NomProducto='" + nombre + "',Cantidad ='" + cantidad + "',FechaObtencion='" + fecha + "' WHERE IDProducto='" + id + "'");
            actu.execute();
            Mostrarinventario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "  Ocurrio un error", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);

        }
    }

    public void Bucar(int opbuscar, String valor) {
        DefaultTableModel tcliente = new DefaultTableModel();
        tcliente.addColumn("ID");
        tcliente.addColumn("Nombre Prod");
        tcliente.addColumn("Cantidad Und");
        tcliente.addColumn("Fecha de Compra");
        jTable1.setModel(tcliente);
        String codsql;
        if (opbuscar == 0 && valor == null) {
            codsql = "SELECT * FROM Productos";
        } else {
            if (opbuscar == 1 && valor != null) {
                codsql = "SELECT * FROM Productos WHERE NomProducto LIKE '"+valor+"%'" ;
            } else {
                if (opbuscar == 2 && valor != null) {

                    codsql = "SELECT * FROM Productos WHERE FechaObtencion LIKE '" + valor + "%'";
                } else {
                    codsql = "SELECT * FROM Productos ";
                }

            }

        }

        /**
         * else if (opbuscar == 1 && valor != null) { codsql = "SELECT * FROM
         * Productos WHERE NomProducto='" + valor + "'"; } else if (opbuscar ==
         * 2 && valor != null) {
         *
         * codsql = "SELECT * FROM Productos WHERE FechaObtencion='" + valor +
         * "'"; } else {
         *
         * codsql = "SELECT * FROM Productos "; }
         */
        String[] datos = new String[4];
        try {
            Statement Leer = conect.createStatement();
            ResultSet resultado = Leer.executeQuery(codsql);
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                tcliente.addRow(datos);

            }
            jTable1.setModel(tcliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "Error en la consulta");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnagregar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        txtnombre = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        cmbdia = new javax.swing.JComboBox<>();
        cmbmes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cmbuscar = new javax.swing.JComboBox<>();
        txtbuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setForeground(new java.awt.Color(0, 51, 153));
        getContentPane().setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Cantidad", "Fecha de compra"
            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 200, 452, 150);

        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregar);
        btnagregar.setBounds(340, 40, 79, 40);

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar);
        btneliminar.setBounds(329, 120, 100, 30);

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnbuscar);
        btnbuscar.setBounds(240, 140, 70, 30);

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnactualizar);
        btnactualizar.setBounds(330, 160, 100, 30);

        txtnombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtnombre);
        txtnombre.setBounds(20, 50, 100, 30);

        txtcantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });
        getContentPane().add(txtcantidad);
        txtcantidad.setBounds(129, 50, 100, 30);

        cmbdia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(cmbdia);
        cmbdia.setBounds(240, 30, 83, 30);

        cmbmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
        getContentPane().add(cmbmes);
        cmbmes.setBounds(240, 60, 83, 30);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bucar por", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 110, 314, 80);

        cmbuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Nombre", "Fecha" }));
        getContentPane().add(cmbuscar);
        cmbuscar.setBounds(20, 140, 100, 30);

        txtbuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtbuscar);
        txtbuscar.setBounds(120, 140, 120, 30);

        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 10, 430, 90);

        jLabel3.setText("Nombre Prod");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 34, 90, 20);

        jLabel4.setText("Cantidad Und");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(140, 34, 90, 20);

        jMenu1.setText("File");

        jMenuItem1.setText("Como un producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Como usar el buscar");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Como editar un producto");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Como agregar un producto");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        ELiminarProducto();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

        int opcion = cmbuscar.getSelectedIndex();
        String valorbuscado = txtbuscar.getText();
        Bucar(opcion, valorbuscado);
txtbuscar.setText("");


    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        Actualizar();
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed

        if (txtnombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe poner el nombre del producto", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);
            txtnombre.requestFocus();
        } else if (txtcantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe la cantidad comprada", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);
            txtcantidad.requestFocus();
        } else if (cmbdia.getSelectedItem().equals("Dia")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el dia de compra", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else if (cmbmes.getSelectedItem().equals("Mes")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el mes de compra", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {
            String nombre, cantidad, dia, mes, CadFecha, valores = null;

            nombre = txtnombre.getText();
            cantidad = txtcantidad.getText();
            dia = cmbdia.getSelectedItem().toString();
            mes = cmbmes.getSelectedItem().toString();
            if (op.ValidarCedulaRegistro(nombre)) {
                CadFecha = dia + "/" + mes + "/" + "2021";
                valores = "'" + nombre + "','" + cantidad + "','" + CadFecha + "'";
                op.RegistrarProducto(valores);
                Nuevo();
                Mostrarinventario();
            } else {
                JOptionPane.showMessageDialog(null, "Producto ya registrado", "ARVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnagregarActionPerformed

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        char Validacion = evt.getKeyChar();
        if (Validacion != KeyEvent.VK_BACK_SPACE) {
            if (Validacion < '0' || Validacion > '9') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        txtnombre.setText(txtnombre.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped

    }//GEN-LAST:event_txtnombreKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

    }//GEN-LAST:event_jTable1KeyReleased

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtbuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JComboBox<String> cmbdia;
    private javax.swing.JComboBox<String> cmbmes;
    private javax.swing.JComboBox<String> cmbuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}

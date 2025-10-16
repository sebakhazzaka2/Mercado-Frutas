//Gonzalo Javier Bracco Varela - 233154
//Sebastian Khazzaka - 233518
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Dominio.Dueno;
import Dominio.Sistema;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ConsultaDuenos extends javax.swing.JFrame implements PropertyChangeListener {
    private Sistema modelo;
    /**
     * Creates new form ConsultaDueños
     */
    public ConsultaDuenos() {
        initComponents();
    }
    
    public ConsultaDuenos(Sistema unModelo) {
        this.modelo = unModelo;
        modelo.addPropertyChangeListener(this);
        initComponents();
        actualizarTablaDueños(); 
        setTitle("Consulta de Dueños");
        pack();  // ajusta eltamaño del Jframe para que todos los elementos del mismo y se vean
        setLocationRelativeTo(null); // cuando abre el Jframe lo abre centrado en el medio de la pantalla
    }
    
        @Override
    public void propertyChange(PropertyChangeEvent evt) {
        actualizarTablaDueños();
    }


    private void actualizarTablaDueños() {  //ordena la tabla de dueños por nombre
       ArrayList<Dueno> dueñosOrdenados = modelo.getListaDueños();
       Collections.sort(dueñosOrdenados, Comparator.comparing(Dueno::getNombre));
       String[][] datosDueños = new String [dueñosOrdenados.size()][3];

       for (int i = 0; i < dueñosOrdenados.size(); i++) {
       Dueno dueño = dueñosOrdenados.get(i);
       datosDueños[i][0] = dueño.getNombre();
       datosDueños[i][1] = String.valueOf(dueño.getEdad());
       datosDueños[i][2] = String.valueOf(dueño.getExperiencia());
       }
     
       DefaultTableModel modeloTabla = (DefaultTableModel) jTableDuenos.getModel();   // obtiene el modelo da la tabla del la interfaz
       modeloTabla.setDataVector(datosDueños, new String[]{"Nombre", "Edad", "Experiencia"}); // Actualiza los datos del modelo de tabla
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableDuenos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTableDuenos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Edad", "Años de Experiencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(jTableDuenos);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        modelo.removePropertyChangeListener(this);
    }//GEN-LAST:event_formWindowClosing
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTableDuenos;
    // End of variables declaration//GEN-END:variables
}

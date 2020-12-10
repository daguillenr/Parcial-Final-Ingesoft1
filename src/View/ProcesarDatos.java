/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ProcesarDatos extends javax.swing.JPanel {

    /**
     * Creates new form ProcesarDato
     */
    public ProcesarDatos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        procesarBB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTramas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        promedioLabel = new javax.swing.JLabel();

        jLabel1.setText("ID de la instalación");

        procesarBB.setText("Procesar");
        procesarBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesarBBActionPerformed(evt);
            }
        });

        tablaTramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Toma", "ID Instalación", "Ubicación", "Valor tomado", "Fecha y hora", "Procesamiento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTramas);

        jLabel2.setText("VALOR PROMEDIO: ");

        promedioLabel.setText("N/A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(procesarBB)
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(promedioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(procesarBB)
                    .addComponent(jLabel2)
                    .addComponent(promedioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void procesarBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesarBBActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel)tablaTramas.getModel();
        model.setRowCount(0);
        ArrayList<Object[]> rows = Controller.Gestor.procesarDatos(Integer.parseInt(jTextField1.getText()));
        
        if(rows.size() != 0){
            
            int promedio = 0;
        
            for (int i = 0; i < rows.size(); i++) {

                promedio += (int)rows.get(i)[3];
            }

            promedio = promedio / rows.size();
            promedioLabel.setText(String.valueOf(promedio));
            
        }
        else{
            promedioLabel.setText("N/A");
        }
        
        
        
        for (int i = 0; i < rows.size(); i++) {
            model.addRow(rows.get(i));
        }
    }//GEN-LAST:event_procesarBBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton procesarBB;
    private javax.swing.JLabel promedioLabel;
    private javax.swing.JTable tablaTramas;
    // End of variables declaration//GEN-END:variables
}

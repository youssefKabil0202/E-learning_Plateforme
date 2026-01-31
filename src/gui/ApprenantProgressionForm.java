/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Apprenant;
import entities.Progression;
import service.ApprenantService;
import service.ProgressionService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class ApprenantProgressionForm extends javax.swing.JInternalFrame {

     private ApprenantService as = null;
    private ProgressionService ps = null;
    private DefaultTableModel model = null;
    
    public ApprenantProgressionForm() {
        initComponents();
         as = new ApprenantService();
        ps = new ProgressionService();
        this.setTitle("Suivi par Apprenant");
        model = (DefaultTableModel) tableProgression.getModel();
        loadApprenants();
    }

    void loadApprenants() {
        comboApprenant.removeAllItems();
        comboApprenant.addItem("-- Sélectionner un apprenant --");
        for (Apprenant a : as.findAll()) {
            comboApprenant.addItem(a);
        }
    }
    void loadProgression(Apprenant apprenant) {
        model.setRowCount(0); // Clear table
        
        if (apprenant == null) {
            jLabel4.setText("Veuillez sélectionner un apprenant");
            return;
        }
        
        // Get all progressions for this learner
        List<Progression> progressions = ps.findByApprenant(apprenant.getId());
        
        if (progressions.isEmpty()) {
            jLabel4.setText(apprenant.getNom() + " - Aucune progression trouvée");
            return;
        }
        
        double totalScore = 0;
        int count = 0;
        
        for (Progression p : progressions) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(p.getDate());
            double score = p.getScore();
            
            model.addRow(new Object[]{
                p.getId(),
                p.getCours().getTitre(),
                p.getCours().getCategorie(),
                dateStr,
                score
            });
            
            totalScore += score;
            count++;
        }
        
        // Calculate and display statistics
        double moyenne = totalScore / count;
        DecimalFormat df = new DecimalFormat("#.##");
        jLabel4.setText(
            "<html><b>" + apprenant.getNom() + "</b><br>" +
            count + " cours complété(s) | Score moyen: " + df.format(moyenne) + "</html>"
        );
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatistique = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboApprenant = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProgression = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 204, 204));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Suivi par apprenant");

        lblStatistique.setBackground(new java.awt.Color(0, 204, 204));
        lblStatistique.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrer"));
        lblStatistique.setToolTipText("");

        jLabel1.setText("Sélectionner un apprenant:");

        jButton1.setText("Voir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Statistiques : ");

        jLabel4.setText("apprenant");

        javax.swing.GroupLayout lblStatistiqueLayout = new javax.swing.GroupLayout(lblStatistique);
        lblStatistique.setLayout(lblStatistiqueLayout);
        lblStatistiqueLayout.setHorizontalGroup(
            lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblStatistiqueLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(99, 99, 99)
                .addComponent(comboApprenant, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblStatistiqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(467, 467, 467))
        );
        lblStatistiqueLayout.setVerticalGroup(
            lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblStatistiqueLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboApprenant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addGap(53, 53, 53)
                .addGroup(lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Affichage"));

        tableProgression.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cours", "Catégorie ", "Date", "Score"
            }
        ));
        tableProgression.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProgressionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProgression);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatistique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblStatistique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableProgressionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProgressionMouseClicked
       
    }//GEN-LAST:event_tableProgressionMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (comboApprenant.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, 
            "Veuillez sélectionner un apprenant!",
            "Attention", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    Apprenant selectedApprenant = (Apprenant) comboApprenant.getSelectedItem();
    loadProgression(selectedApprenant);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboApprenant;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lblStatistique;
    private javax.swing.JTable tableProgression;
    // End of variables declaration//GEN-END:variables
}

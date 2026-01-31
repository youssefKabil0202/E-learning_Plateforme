/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Apprenant;
import entities.Cours;
import entities.Progression;
import service.ProgressionService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.text.SimpleDateFormat;
import service.ApprenantService;
import service.CoursService;
import java.util.ArrayList;       
import java.util.Collections;   
import java.util.stream.Collectors;
public class ProgressionForm extends javax.swing.JInternalFrame {

    private ProgressionService ps = null;
    private CoursService cs = null;
    private ApprenantService as = null;
    private DefaultTableModel model = null;
    private static int id = 0;
    
    public ProgressionForm() {
          initComponents();
        ps = new ProgressionService();
        cs = new CoursService();
        as = new ApprenantService();
        this.setTitle("Gestion des progressions");
        model = (DefaultTableModel) tableProgression.getModel();
        loadComboBoxes();
        load();
    }
    void loadComboBoxes() {
        comboCours.removeAllItems();
        for (Cours c : cs.findAll()) {
            comboCours.addItem(c);
        }
        
        comboApprenant.removeAllItems();
        for (Apprenant a : as.findAll()) {
            comboApprenant.addItem(a);
        }
         comboFilterCategorie.removeAllItems();
         comboFilterCategorie.addItem("-- Toutes les catégories --");
    
    // Get all unique categories from courses
    List<String> categories = new ArrayList<>();
    for (Cours c : cs.findAll()) {
        String categorie = c.getCategorie();
        if (!categories.contains(categorie)) {
            categories.add(categorie);
        }
    }
    
    // Sort alphabetically (optional)
    Collections.sort(categories);
    
    // Add to combo box
    for (String categorie : categories) {
        comboFilterCategorie.addItem(categorie);
    }
    }

   void load() {
        model.setRowCount(0);
        List<Progression> progressions = ps.findAll();
        
        // Apply filters
        progressions = applyFilters(progressions);
        
        for (Progression p : progressions) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(p.getDate());
            model.addRow(new Object[]{
                p.getId(),
                p.getCours().getTitre(),
                p.getApprenant().getNom(),
                dateStr,
                p.getScore()
            });
        }
        
        // Update statistics
        updateStatistics(progressions);
    }
    private List<Progression> applyFilters(List<Progression> progressions) {
        // Filter by category
        if (comboFilterCategorie.getSelectedIndex() > 0) {
            String selectedCategorie = (String) comboFilterCategorie.getSelectedItem();
            progressions = progressions.stream()
                .filter(p -> p.getCours().getCategorie().equals(selectedCategorie))
                .collect(Collectors.toList());
        }
        
        // Filter by score range
        try {
            if (!txtScoreMin.getText().trim().isEmpty()) {
                double minScore = Double.parseDouble(txtScoreMin.getText().trim());
                progressions = progressions.stream()
                    .filter(p -> p.getScore() >= minScore)
                    .collect(Collectors.toList());
            }
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }
        
        try {
            if (!txtScoreMax.getText().trim().isEmpty()) {
                double maxScore = Double.parseDouble(txtScoreMax.getText().trim());
                progressions = progressions.stream()
                    .filter(p -> p.getScore() <= maxScore)
                    .collect(Collectors.toList());
            }
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }
        
        return progressions;
    }
    
    // ADD this method to update statistics
    private void updateStatistics(List<Progression> progressions) {
        if (progressions.isEmpty()) {
            // You can add a label to show statistics if you want
            // For now, just leave it
            return;
        }
        
        double totalScore = progressions.stream()
            .mapToDouble(Progression::getScore)
            .sum();
        double averageScore = totalScore / progressions.size();
        
        // You can show this in a message or add a label
        // For example: lblStats.setText(String.format("%d progressions | Moyenne: %.2f", progressions.size(), averageScore));
        
        // Or show in title
        this.setTitle("Gestion des progressions - " + progressions.size() + 
                     " résultat(s) | Moyenne: " + String.format("%.2f", averageScore));
    }
    
    void clearFields() {
        comboCours.setSelectedIndex(0);
        comboApprenant.setSelectedIndex(0);
        dateChooserProgression.setDate(null);
        txtScore.setText("");
    }
     void clearFilters() {
        comboFilterCategorie.setSelectedIndex(0);
        txtScoreMin.setText("");
        txtScoreMax.setText("");
        load();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboCours = new javax.swing.JComboBox();
        comboApprenant = new javax.swing.JComboBox();
        dateChooserProgression = new com.toedter.calendar.JDateChooser();
        txtScore = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboFilterCategorie = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtScoreMin = new javax.swing.JTextField();
        txtScoreMax = new javax.swing.JTextField();
        btnFiltrer = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAjouterProg = new javax.swing.JButton();
        btnModifierProg = new javax.swing.JButton();
        btnSupprimerProg = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProgression = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion Progression");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion des Progressions"));
        jPanel1.setToolTipText("");

        jLabel1.setText("Cours : ");

        jLabel2.setText("Apprenant :");

        jLabel3.setText("Date :");

        jLabel4.setText("Score :");

        comboCours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCoursActionPerformed(evt);
            }
        });

        jLabel5.setText("Filtrer par categorie:");

        comboFilterCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterCategorieActionPerformed(evt);
            }
        });

        jLabel6.setText("Score Min:");

        jLabel7.setText("Score Max:");

        txtScoreMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtScoreMaxActionPerformed(evt);
            }
        });

        btnFiltrer.setText("Filtrer");
        btnFiltrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrerActionPerformed(evt);
            }
        });

        btnReset.setText("Réinitialiser");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboCours, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboApprenant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooserProgression, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(txtScore)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(comboFilterCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtScoreMin, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtScoreMax, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(btnFiltrer, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboApprenant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateChooserProgression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboFilterCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtScoreMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtScoreMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrer)
                    .addComponent(btnReset))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        btnAjouterProg.setText("Ajouter");
        btnAjouterProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterProgActionPerformed(evt);
            }
        });

        btnModifierProg.setText("Modifier");
        btnModifierProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierProgActionPerformed(evt);
            }
        });

        btnSupprimerProg.setText("Supprimer");
        btnSupprimerProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerProgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSupprimerProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModifierProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAjouterProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(82, 82, 82))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnAjouterProg)
                .addGap(50, 50, 50)
                .addComponent(btnModifierProg)
                .addGap(50, 50, 50)
                .addComponent(btnSupprimerProg)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("list de progression"));

        tableProgression.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cours", "Apprenant", "Date", "Score"
            }
        ));
        tableProgression.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProgressionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProgression);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboCoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCoursActionPerformed
        // TODO add your handlin
    }//GEN-LAST:event_comboCoursActionPerformed

    private void btnAjouterProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterProgActionPerformed
     Cours cours = (Cours) comboCours.getSelectedItem();
        Apprenant apprenant = (Apprenant) comboApprenant.getSelectedItem();
        java.util.Date date = dateChooserProgression.getDate();
        String scoreText = txtScore.getText();
        
        if (cours == null || apprenant == null || date == null || scoreText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!");
            return;
        }
        
        try {
            double score = Double.parseDouble(scoreText);
            Progression progression = new Progression(cours, apprenant, date, score);    
            if (ps.create(progression)) {
                load();
                JOptionPane.showMessageDialog(this, "Progression ajoutée avec succès!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Score doit être un nombre!");
        }
    }//GEN-LAST:event_btnAjouterProgActionPerformed

    private void btnModifierProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierProgActionPerformed
         if (id == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une progression à modifier!");
            return;
        }
        
        Cours cours = (Cours) comboCours.getSelectedItem();
        Apprenant apprenant = (Apprenant) comboApprenant.getSelectedItem();
        java.util.Date date = dateChooserProgression.getDate();
        String scoreText = txtScore.getText();
        
        if (cours == null || apprenant == null || date == null || scoreText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!");
            return;
        }
        
        try {
            double score = Double.parseDouble(scoreText);
            Progression progression = ps.findById(id);
            
            if (progression != null) {
                progression.setCours(cours);
                progression.setApprenant(apprenant);
                progression.setDate(date);
                progression.setScore(score);
                
                int reponse = JOptionPane.showConfirmDialog(this, 
                    "Voulez-vous vraiment modifier cette progression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
                
                if (reponse == JOptionPane.YES_OPTION) {
                    if (ps.update(progression)) {
                        load();
                        JOptionPane.showMessageDialog(this, "Progression modifiée avec succès!");
                        clearFields();
                        id = 0;
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors de la modification!");
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Score doit être un nombre!");
        }
    }//GEN-LAST:event_btnModifierProgActionPerformed

    private void btnSupprimerProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerProgActionPerformed
         if (id == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une progression à supprimer!");
            return;
        }
        
        int reponse = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vraiment supprimer cette progression?",
            "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (reponse == JOptionPane.YES_OPTION) {
            Progression progression = ps.findById(id);
            if (progression != null) {
                if (ps.delete(progression)) {
                    load();
                    JOptionPane.showMessageDialog(this, "Progression supprimée avec succès!");
                    clearFields();
                    id = 0;
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression!");
                }
            }
        }
    }//GEN-LAST:event_btnSupprimerProgActionPerformed

    private void tableProgressionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProgressionMouseClicked
        int selectedRow = tableProgression.getSelectedRow();
        if (selectedRow >= 0) {
            id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            
            String coursTitre = model.getValueAt(selectedRow, 1).toString();
            for (int i = 0; i < comboCours.getItemCount(); i++) {
                Cours c = (Cours) comboCours.getItemAt(i);
                if (c.getTitre().equals(coursTitre)) {
                    comboCours.setSelectedIndex(i);
                    break;
                }
            }
                  
            String apprenantNom = model.getValueAt(selectedRow, 2).toString();
            for (int i = 0; i < comboApprenant.getItemCount(); i++) {
                Apprenant a = (Apprenant) comboApprenant.getItemAt(i);
                if (a.getNom().equals(apprenantNom)) {
                    comboApprenant.setSelectedIndex(i);
                    break;
                }
            }
            
            String dateStr = model.getValueAt(selectedRow, 3).toString();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(dateStr);
                dateChooserProgression.setDate(date);
            } catch (Exception e) {
                dateChooserProgression.setDate(null);
            }
                
            txtScore.setText(model.getValueAt(selectedRow, 4).toString());
        }
    }//GEN-LAST:event_tableProgressionMouseClicked

    private void txtScoreMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScoreMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtScoreMaxActionPerformed

    private void comboFilterCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterCategorieActionPerformed
        load();
    }//GEN-LAST:event_comboFilterCategorieActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
     comboFilterCategorie.setSelectedIndex(0); // Reset to "-- Toutes les catégories --"
    txtScoreMin.setText(""); // Clear min score
    txtScoreMax.setText(""); // Clear max scorecomboFilterCategorie
    load(); // 
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnFiltrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrerActionPerformed
       load();
    }//GEN-LAST:event_btnFiltrerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterProg;
    private javax.swing.JButton btnFiltrer;
    private javax.swing.JButton btnModifierProg;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSupprimerProg;
    private javax.swing.JComboBox comboApprenant;
    private javax.swing.JComboBox comboCours;
    private javax.swing.JComboBox comboFilterCategorie;
    private com.toedter.calendar.JDateChooser dateChooserProgression;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProgression;
    private javax.swing.JTextField txtScore;
    private javax.swing.JTextField txtScoreMax;
    private javax.swing.JTextField txtScoreMin;
    // End of variables declaration//GEN-END:variables

   
}

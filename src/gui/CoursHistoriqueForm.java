/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Cours;
import entities.Progression;
import service.CoursService;
import service.ProgressionService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class CoursHistoriqueForm extends javax.swing.JInternalFrame {

     private CoursService cs = null;
    private ProgressionService ps = null;
    private DefaultTableModel model = null;
    public CoursHistoriqueForm() {
        initComponents();
          cs = new CoursService();
        ps = new ProgressionService();
        this.setTitle("Historique du Cours");
        model = (DefaultTableModel) tableHistorique.getModel();
        loadCours();
    }

    void loadCours() {
        comboCours.removeAllItems();
        comboCours.addItem("-- Sélectionner un cours --");
        for (Cours c : cs.findAll()) {
            comboCours.addItem(c);
        }
    }
    
    
    void loadHistorique(Cours cours) {
        model.setRowCount(0); 
        
        if (cours == null) {
            lblStatistiques.setText("Veuillez sélectionner un cours");
            return;
        }
        
        List<Progression> progressions = ps.findByCours(cours.getId());
        
        if (progressions.isEmpty()) {
            lblStatistiques.setText(cours.getTitre() + " - Aucun historique trouvé");
            return;
        }
        
        double totalScore = 0;
        int count = 0;
        double maxScore = 0;
        double minScore = 100;
        
        for (Progression p : progressions) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(p.getDate());
            double score = p.getScore();
            String note = getNoteFromScore(score);
            
            model.addRow(new Object[]{
                p.getId(),
                p.getApprenant().getNom(),
                dateStr,
                score,
                note
            });
            
            totalScore += score;
            count++;
                      
            if (score > maxScore) maxScore = score;
            if (score < minScore) minScore = score;
        }
        
        double moyenne = totalScore / count;
        DecimalFormat df = new DecimalFormat("#.##");
        
        lblStatistiques.setText(
            "<html><b>" + cours.getTitre() + "</b><br>" +
            count + " apprenant(s) | Moyenne: " + df.format(moyenne) + 
            " | Min: " + df.format(minScore) + " | Max: " + df.format(maxScore) + "</html>"
        );
    }
    
    private String getNoteFromScore(double score) {
        if (score >= 90) return "Excellent";
        else if (score >= 80) return "Très Bien";
        else if (score >= 70) return "Bien";
        else if (score >= 60) return "Passable";
        else return "Insuffisant";
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatistique = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboCours = new javax.swing.JComboBox();
        btnVoirHistorique = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblStatistiques = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistorique = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("historique cours");

        lblStatistique.setBackground(new java.awt.Color(0, 204, 204));
        lblStatistique.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrer"));
        lblStatistique.setToolTipText("");

        jLabel1.setText("Sélectionner un cour:");

        btnVoirHistorique.setText("Voir");
        btnVoirHistorique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoirHistoriqueActionPerformed(evt);
            }
        });

        jLabel2.setText("Statistiques : ");

        lblStatistiques.setText("lblstat");

        javax.swing.GroupLayout lblStatistiqueLayout = new javax.swing.GroupLayout(lblStatistique);
        lblStatistique.setLayout(lblStatistiqueLayout);
        lblStatistiqueLayout.setHorizontalGroup(
            lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblStatistiqueLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(99, 99, 99)
                .addComponent(comboCours, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVoirHistorique, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblStatistiqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(lblStatistiques, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(467, 467, 467))
        );
        lblStatistiqueLayout.setVerticalGroup(
            lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblStatistiqueLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboCours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVoirHistorique))
                    .addComponent(jLabel1))
                .addGap(53, 53, 53)
                .addGroup(lblStatistiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblStatistiques))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Affichage"));

        tableHistorique.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Apprenant ", "Date", "Score", "Note"
            }
        ));
        tableHistorique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHistoriqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHistorique);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
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

    private void btnVoirHistoriqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoirHistoriqueActionPerformed
      if (comboCours.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un cours!",
                "Attention", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Cours selectedCours = (Cours) comboCours.getSelectedItem();
        loadHistorique(selectedCours);
    
    }//GEN-LAST:event_btnVoirHistoriqueActionPerformed

    private void tableHistoriqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHistoriqueMouseClicked

    }//GEN-LAST:event_tableHistoriqueMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoirHistorique;
    private javax.swing.JComboBox comboCours;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lblStatistique;
    private javax.swing.JLabel lblStatistiques;
    private javax.swing.JTable tableHistorique;
    // End of variables declaration//GEN-END:variables
}

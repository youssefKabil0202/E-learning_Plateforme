package gui;

import entities.Cours;
import service.CoursService;
import service.ProgressionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartForm extends javax.swing.JInternalFrame {
    
    private ProgressionService progressionService;
    private CoursService coursService;
    
    public ChartForm() {
        initComponents();
        progressionService = new ProgressionService();
        coursService = new CoursService();
        createChart();
        this.setLocation(100, 100); // Position within parent
    }
    
    private void createChart() {
        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Get statistics from service
        List<Object[]> stats = progressionService.getStatsByCours();
        
        if (stats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucune donnée de progression disponible pour créer le graphique.");
            return;
        }
        
        // Add data to dataset
        for (Object[] stat : stats) {
            String coursTitre = (String) stat[0];
            double moyenne = (Double) stat[1];
            dataset.addValue(moyenne, "Moyenne des scores", coursTitre);
        }
        
        // Create chart
        JFreeChart barChart = ChartFactory.createBarChart(
            "Moyenne des Scores par Cours", // Chart title
            "Cours", // X-Axis Label
            "Moyenne des Scores", // Y-Axis Label
            dataset,
            PlotOrientation.VERTICAL,
            true, // Show legend
            true, // Use tooltips
            false // Configure chart to generate URLs?
        );
        
        // Customize chart
        barChart.setBackgroundPaint(Color.white);
        
        // Create panel to display chart
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(750, 550));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        // Add to internal frame
        getContentPane().removeAll();
        getContentPane().add(chartPanel, BorderLayout.CENTER);
        pack();
        revalidate();
        repaint();
    }
    
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Graphique - Moyenne des Scores par Cours");
        setPreferredSize(new Dimension(800, 600));
        
        pack();
    }
}
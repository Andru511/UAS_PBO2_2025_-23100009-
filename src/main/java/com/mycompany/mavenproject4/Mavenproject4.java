/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject4;

/**
 *
 * @author ASUS
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Mavenproject4 extends JFrame {

    private JTable visitTable;
    private DefaultTableModel tableModel;
    
    private JTextField nameField;
    private JTextField nimField;
    private JComboBox<String> studyProgramBox;
    private JComboBox<String> purposeBox;
    private JButton addButton;
    private JButton clearButton;
    
    private boolean actionColumnsAdded = false;

    public Mavenproject4() {
        setTitle("Library Visit Log");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        nameField = new JTextField();
        nimField = new JTextField();
        studyProgramBox = new JComboBox<>(new String[] {"Sistem dan Teknologi Informasi", "Bisnis Digital", "Kewirausahaan"});
        purposeBox = new JComboBox<>(new String[] {"Membaca", "Meminjam/Mengembalikan Buku", "Research", "Belajar"});
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");

        inputPanel.setBorder(BorderFactory.createTitledBorder("Visit Entry Form"));
        inputPanel.add(new JLabel("NIM:"));
        inputPanel.add(nimField);
        inputPanel.add(new JLabel("Name Mahasiswa:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Program Studi:"));
        inputPanel.add(studyProgramBox);
        inputPanel.add(new JLabel("Tujuan Kunjungan:"));
        inputPanel.add(purposeBox);
        inputPanel.add(addButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        String[] columns = {"Waktu Kunjungan", "NIM", "Nama", "Program Studi", "Tujuan Kunjungan"};
        tableModel = new DefaultTableModel(columns, 0);
        visitTable = new JTable(tableModel);

            
        JScrollPane scrollPane = new JScrollPane(visitTable);
        add(scrollPane, BorderLayout.CENTER);


        setVisible(true);
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("control G"), "showActions");

        getRootPane().getActionMap().put("showActions", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!actionColumnsAdded) {
                    addActionColumns();
                    actionColumnsAdded = true;
                }
            }
        });
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            addActionColumns();
    }
        
        
    private void addActionColumns() {
        tableModel.addColumn("Action");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt("Action", i, tableModel.getColumnCount() - 2);
        }
        visitTable.getColumn("Action").setCellRenderer(new ButtonRenderer());

        visitTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private void tambahnama() {}
    private String sendGraphQLRequest(String json) throws Exception {
            URL url = new URL("http://localhost:4567/graphql");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }
            try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                return sb.toString();
            } 
            }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Mavenproject4::new);
    }
}
 class GraphQLQuery {
            String query;
            GraphQLQuery(String query) {
            this.query = query;
            }
        }

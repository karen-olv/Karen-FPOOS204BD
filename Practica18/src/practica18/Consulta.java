package Practica18;

import javax.swing.JOptionPane;

public class Consulta extends javax.swing.JFrame {
    private UserCrud crud;

    public Consulta() {
        iniciarComponentes();
        crud = new UserCrud();
    }

    @SuppressWarnings("unchecked")
    private void iniciarComponentes() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Usuario");

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 0, 18));
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Consultar Usuario");

        jLabel2.setText("ID:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAccionRealizada(evt);
            }
        });

        btnLimpiar.setForeground(new java.awt.Color(0, 0, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarAccionRealizada(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnLimpiar)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(30, 30, 30)
                .addComponent(btnLimpiar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnBuscarAccionRealizada(java.awt.event.ActionEvent evt) {
        try {
            java.sql.ResultSet rs = crud.buscarTodos();
            
            if (rs != null) {
                StringBuilder mensaje = new StringBuilder();
                while (rs.next()) {
                    mensaje.append("ID: ").append(rs.getInt("id")).append("\n")
                          .append("Nombre: ").append(rs.getString("Nombre")).append("\n")
                          .append("Correo: ").append(rs.getString("Correo")).append("\n")
                          .append("Contraseña: ").append(rs.getString("Contrasena")).append("\n\n");
                }
                
                if (mensaje.length() > 0) {
                    JOptionPane.showMessageDialog(this, mensaje.toString(), "Usuarios Encontrados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron usuarios", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al buscar usuarios", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLimpiarAccionRealizada(java.awt.event.ActionEvent evt) {
        txtID.setText("");
    }

    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtID;
}
package swing;

import java.awt.*;
import model.m_Menu;

public class MenuItem extends javax.swing.JPanel {

    private boolean pilihan;

    public MenuItem(m_Menu data) {
        initComponents();
        setOpaque(false);
        if (data.getTipe() == m_Menu.TipeMenu.MENU) {
            lb_Icon.setIcon(data.keIcon());
            lb_Nama.setText(data.getNama());
        } else if (data.getTipe() == m_Menu.TipeMenu.JUDUL) {
            lb_Icon.setText(data.getNama());
            lb_Icon.setFont(new Font("sansserif", 1, 12));
            lb_Nama.setVisible(false);
        } else {
            lb_Nama.setText("  ");
        }
    }

    public void setPilihan(boolean pilihan) {
        this.pilihan = pilihan;
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_Icon = new javax.swing.JLabel();
        lb_Nama = new javax.swing.JLabel();

        lb_Icon.setForeground(new java.awt.Color(255, 255, 255));

        lb_Nama.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lb_Nama.setForeground(new java.awt.Color(255, 255, 255));
        lb_Nama.setText("Nama Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb_Icon)
                .addGap(18, 18, 18)
                .addComponent(lb_Nama)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lb_Nama, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (pilihan) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 80));
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_Icon;
    private javax.swing.JLabel lb_Nama;
    // End of variables declaration//GEN-END:variables
}

package com.loket.swing;

import com.loket.model.m_Menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuItem extends javax.swing.JPanel {

    private boolean pilihan;
    private boolean over;

    public MenuItem(m_Menu data) {
        initComponents();
        setOpaque(false);
        if (data.getType() == m_Menu.TipeMenu.MENU) {
            lb_Icon.setIcon(data.toIcon());
            lb_Nama.setText(data.getName());
        } else if (data.getType() == m_Menu.TipeMenu.TITLE) {
            lb_Icon.setText(data.getName());
            lb_Icon.setFont(new Font("sansserif", 1, 12));
            lb_Nama.setVisible(false);
        } else {
            lb_Nama.setText(" ");
        }
    }

    public void setPilihan(boolean pilihan) {
        this.pilihan = pilihan;
        repaint();
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_Icon = new javax.swing.JLabel();
        lb_Nama = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        lb_Icon.setForeground(new java.awt.Color(255, 255, 255));

        lb_Nama.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lb_Nama.setForeground(new java.awt.Color(255, 255, 255));
        lb_Nama.setText("Menu Name");

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
            .addComponent(lb_Nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (pilihan) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (pilihan) {
                g2.setColor(new Color(255, 255, 255, 80));
            } else {
                g2.setColor(new Color(255, 255, 255, 20));
            }
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_Icon;
    private javax.swing.JLabel lb_Nama;
    // End of variables declaration//GEN-END:variables
}

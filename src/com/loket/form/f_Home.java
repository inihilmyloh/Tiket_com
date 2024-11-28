package com.loket.form;

import com.loket.model.m_Card;
import com.loket.service.DataFetcher;
import javax.swing.ImageIcon;

public class f_Home extends javax.swing.JPanel {

    public f_Home() {
        initComponents();
        card1.setData(DataFetcher.getCardData("gold", "/com/loket/icon/stock.png", "Stock Gold"));
        card2.setData(DataFetcher.getCardData("silver", "/com/loket/icon/stock.png", "Stock Silver"));
        card1.setData(new m_Card(new ImageIcon(getClass().getResource("/com/loket/icon/profit.png")), "Total Pendapatan", "Rp 3000000", "Increased by 70%"));
        card2.setData(new m_Card(new ImageIcon(getClass().getResource("/com/loket/icon/profit.png")), "Total Pendapatan", "Rp 3000000", "Increased by 70%"));
        card3.setData(new m_Card(new ImageIcon(getClass().getResource("/com/loket/icon/profit.png")), "Total Pendapatan", "Rp 3000000", "Increased by 70%"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.loket.komponen.Card();
        card2 = new com.loket.komponen.Card();
        card3 = new com.loket.komponen.Card();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(189, 195, 199));
        card1.setColor2(new java.awt.Color(44, 62, 80));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(243, 249, 167));
        card2.setColor2(new java.awt.Color(202, 197, 49));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(56, 239, 125));
        card3.setColor2(new java.awt.Color(17, 153, 142));
        panel.add(card3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.loket.komponen.Card card1;
    private com.loket.komponen.Card card2;
    private com.loket.komponen.Card card3;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}

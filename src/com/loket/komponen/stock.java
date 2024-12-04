package com.loket.komponen;

import com.loket.main.Main;
import com.loket.main.Main.Refreshable;
import com.loket.model.m_Card;
import com.loket.service.DataFetcher;
import javax.swing.ImageIcon;

public class stock extends javax.swing.JPanel {

    public stock() {
        initComponents();
        card1.setData(DataFetcher.getCardData("silver", "/com/loket/icon/tiket1.png", "Stock Silver"));
        card2.setData(DataFetcher.getCardData("gold", "/com/loket/icon/tiket1.png", "Stock Gold"));

    }

    public void updateStock() {
        card1.setData(DataFetcher.getCardData("silver", "/com/loket/icon/tiket1.png", "Stock Silver"));
        card2.setData(DataFetcher.getCardData("gold", "/com/loket/icon/tiket1.png", "Stock Gold"));

        // Tambahkan log jika diperlukan untuk debugging
        System.out.println("Stock updated!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.loket.komponen.Card();
        card2 = new com.loket.komponen.Card();

        setBackground(new java.awt.Color(230, 240, 250));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(145, 107, 191));
        card1.setColor2(new java.awt.Color(110, 72, 156));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(60, 179, 113));
        card2.setColor2(new java.awt.Color(32, 128, 92));
        panel.add(card2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 296, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.loket.komponen.Card card1;
    private com.loket.komponen.Card card2;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}

package com.loket.form;

import com.loket.main.Main;
import com.loket.service.DataFetcher;

public class f_Home extends javax.swing.JPanel implements Main.Refreshable {

    public f_Home() {
        initComponents();
        card1.setData(DataFetcher.getJenisTiket("/com/loket/icon/tiket1.png", "Jumlah Jenis Tiket"));
        card2.setData(DataFetcher.getTotalStockAndPrices("/com/loket/icon/tiket1.png", "Total Stock Dan Harga Setiap Tiket"));
        card3.setData(DataFetcher.getPendapatan("/com/loket/icon/profit.png", "Total Pendapatan"));

    }

    @Override
    public void refresh() {
        loadDataFromDatabase(); // Muat ulang data saat diminta refresh
    }

    private void loadDataFromDatabase() {
        // Logika memuat data dari sumber

        card1.setData(DataFetcher.getJenisTiket("/com/loket/icon/tiket1.png", "Jumlah Jenis Tiket"));
        card2.setData(DataFetcher.getTotalStockAndPrices("/com/loket/icon/tiket1.png", "Total Stock Dan Harga Setiap Tiket"));
        card3.setData(DataFetcher.getPendapatan("/com/loket/icon/profit.png", "Total Pendapatan"));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.loket.komponen.Card();
        card2 = new com.loket.komponen.Card();
        card3 = new com.loket.komponen.Card();

        setBackground(new java.awt.Color(230, 240, 250));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(215, 187, 248));
        card1.setColor2(new java.awt.Color(179, 157, 219));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(167, 239, 203));
        card2.setColor2(new java.awt.Color(128, 203, 196));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(163, 217, 246));
        card3.setColor2(new java.awt.Color(118, 199, 219));
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
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.loket.komponen.Card card1;
    private com.loket.komponen.Card card2;
    private com.loket.komponen.Card card3;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}

package com.loket.main;

import Awalan.main;
import com.formdev.flatlaf.*;
import com.loket.event.EventMenuPilihan;
import com.loket.form.CRUD_ADE;
import com.loket.form.Transaksi_dewa;
import com.loket.form.f_Home;
import com.loket.form.form_test2;
import com.loket.swing.ScrollBar;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import javax.swing.UIManager;

public class Main extends javax.swing.JFrame {

    private f_Home home;
    private CRUD_ADE form1;
    private Transaksi_dewa form2;
    private form_test2 form3;

    public Main() {
        tema();
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        home = new f_Home();
        form1 = new CRUD_ADE();
        form2 = new Transaksi_dewa();
        form3 = new form_test2();
        menu.initMoving(Main.this);
        menu.addEventMenuPilihan(new EventMenuPilihan() {
            @Override
            public void pilihan(int index) {
                if (index == 0) {
                    setForm(home);
                } else if (index == 1) {
                    setForm(form1);
                } else if (index == 2) {
                    setForm(form2);
                } else if (index == 3) {
                    setForm(form3);
                } else if (index == 5) {
                    logoutAction();
                }
            }
        });
        setForm(new f_Home());
    }

    public void tema() {
        System.out.println("Tema diterapkan");
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("crazypanel");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        if (com instanceof Refreshable) {
            ((Refreshable) com).refresh(); // Memanggil refresh untuk memperbarui data
        }
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public interface Refreshable {

        void refresh(); // Metode untuk merefresh data
    }

    private void logoutAction() {
        int response = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        // Jika pengguna memilih Yes, lakukan logout
        if (response == JOptionPane.YES_OPTION) {
            // Menutup frame utama
            this.dispose();

            // Menampilkan halaman login
            main loginFrame = new main();
            loginFrame.setVisible(true);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null); // Memposisikan login di tengah layar
        }
        // Jika pengguna memilih No, tidak ada aksi yang dilakukan (logout dibatalkan)
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_Border1 = new com.loket.swing.pn_Border();
        menu = new com.loket.komponen.Menu();
        header1 = new com.loket.komponen.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pn_Border1Layout = new javax.swing.GroupLayout(pn_Border1);
        pn_Border1.setLayout(pn_Border1Layout);
        pn_Border1Layout.setHorizontalGroup(
            pn_Border1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Border1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pn_Border1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                    .addGroup(pn_Border1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pn_Border1Layout.setVerticalGroup(
            pn_Border1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
            .addGroup(pn_Border1Layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Border1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Border1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.loket.komponen.Header header1;
    private javax.swing.JPanel mainPanel;
    private com.loket.komponen.Menu menu;
    private com.loket.swing.pn_Border pn_Border1;
    // End of variables declaration//GEN-END:variables
}

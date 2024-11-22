package komponen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.m_Menu;

public class Menu extends javax.swing.JPanel {

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();

    }

    private void init() {
        listMenu1.addItem(new m_Menu("1", "Dashboard", m_Menu.TipeMenu.MENU));
        listMenu1.addItem(new m_Menu("2", "Tiket", m_Menu.TipeMenu.MENU));
        listMenu1.addItem(new m_Menu("3", "Transaksi", m_Menu.TipeMenu.MENU));
        listMenu1.addItem(new m_Menu("4", "Laporan", m_Menu.TipeMenu.MENU));
        listMenu1.addItem(new m_Menu("", "", m_Menu.TipeMenu.KOSONG));
        listMenu1.addItem(new m_Menu("5", "Logout", m_Menu.TipeMenu.MENU));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_gerak = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        listMenu1 = new swing.ListMenu<>();

        pn_gerak.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/11.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 56));

        javax.swing.GroupLayout pn_gerakLayout = new javax.swing.GroupLayout(pn_gerak);
        pn_gerak.setLayout(pn_gerakLayout);
        pn_gerakLayout.setHorizontalGroup(
            pn_gerakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_gerakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_gerakLayout.setVerticalGroup(
            pn_gerakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_gerakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_gerak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_gerak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#8E2DE2"), 0, getHeight(), Color.decode("#4A00E0"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void gerakM(JFrame fram) {
        pn_gerak.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        pn_gerak.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel pn_gerak;
    // End of variables declaration//GEN-END:variables
}

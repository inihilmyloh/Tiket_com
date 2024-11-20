package model;

import javax.swing.*;

public class m_Menu {
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public TipeMenu getTipe() {
        return tipe;
    }
    
    public void setTipe(TipeMenu tipe) {
        this.tipe = tipe;
    }
    
    public m_Menu(String icon, String nama, TipeMenu tipe) {
        this.icon = icon;
        this.nama = nama;
        this.tipe = tipe;
    }
    
    public m_Menu() {
    }
    
    private String icon;
    private String nama;
    private TipeMenu tipe;
    
    public Icon keIcon() {
        return new ImageIcon(getClass().getResource("/icon/" + icon + ".png"));
    }
    
    public static enum TipeMenu {
        JUDUL, MENU, KOSONG
        
    }
}

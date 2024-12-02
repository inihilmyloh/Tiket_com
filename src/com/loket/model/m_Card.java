package com.loket.model;

import javax.swing.Icon;

public class m_Card {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public m_Card(Icon icon, String title, String values, String deskripsi) {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.deskripsi = deskripsi;
    }

    public m_Card() {
    }

    private Icon icon;
    private String title;
    private String values;
    private String deskripsi;

}

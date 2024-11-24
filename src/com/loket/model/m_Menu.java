package com.loket.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class m_Menu {

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipeMenu getType() {
        return type;
    }

    public void setType(TipeMenu type) {
        this.type = type;
    }

    public m_Menu(String icon, String name, TipeMenu type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public m_Menu() {
    }
    private String icon;
    private String name;
    private TipeMenu type;

    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/com/loket/icon/" + icon + ".png"));
    }

    public static enum TipeMenu {
        TITLE, MENU, EMPTY
    }
}

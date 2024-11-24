package com.loket.swing;

import com.loket.model.m_Menu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListMenu<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int pilihanIndex = -1;

    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    Object o = model.getElementAt(index);
                    if (o instanceof m_Menu) {
                        m_Menu menu = (m_Menu) o;
                        if (menu.getType() == m_Menu.TipeMenu.MENU) {
                            pilihanIndex = index;
                        }
                    } else {
                        pilihanIndex = index;
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean pilihan, boolean focus) {
                m_Menu data;
                if (o instanceof m_Menu) {
                    data = (m_Menu) o;
                } else {
                    data = new m_Menu("", o + "", m_Menu.TipeMenu.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setPilihan(pilihanIndex== index);
                return item;
            }
        };
    }

    public void addItem(m_Menu data) {
        model.addElement(data);
    }
}

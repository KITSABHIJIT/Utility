package com.test.renderer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class StatusRenderer extends DefaultTableCellRenderer {
    private Icon completeImage;
    private Icon pendingImage;

    public StatusRenderer() {
        super();

        completeImage = new ImageIcon("resources/complete.png");
        pendingImage = new ImageIcon("resources/pending.png");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, null, isSelected, hasFocus, row, column);

        String status = (String)value;

        if("done".equalsIgnoreCase(status) 
        		|| "completed".equalsIgnoreCase(status)
        		|| "paid".equalsIgnoreCase(status)
        		|| "yes".equalsIgnoreCase(status)){
            setIcon(completeImage);
        } else {
            setIcon(pendingImage);
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }
}

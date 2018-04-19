package com.test.editor;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

public class ShippingModeEditor extends DefaultCellEditor {
   
	public enum shippingMode {
	    Standard,
	    Expedited
	}
	public ShippingModeEditor() {
        super(new JComboBox(shippingMode.values()));
    }
}
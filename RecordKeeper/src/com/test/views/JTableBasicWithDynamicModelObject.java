package com.test.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.test.editor.ShippingModeEditor;
import com.util.read.excel.StringUtil;

public class JTableBasicWithDynamicModelObject extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DynamicModelObject model = new DynamicModelObject();

	private JTable table;

	public JTableBasicWithDynamicModelObject() {
		super();
		setTitle("Record Keeper");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		table = new JTable(model){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);
		        if (c instanceof JComponent) {
		          // if(column == X){
		            //X is your particlur column number
		            JComponent jc = (JComponent) c;
		            jc.setToolTipText(StringUtil.trim(getValueAt(row, column)));
		          // }
		        }
		        return c;
		    }
		};
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getColumnModel().getTotalColumnWidth(), 50));
		/*table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

        for (int column = 0; column < table.getColumnCount(); column++)
        {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth( preferredWidth );
        }*/
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);
		//table.getColumnModel().getColumn(13).setCellRenderer(new StatusRenderer());
		//table.getColumnModel().getColumn(14).setCellRenderer(new StatusRenderer());
		//table.getColumnModel().getColumn(15).setCellRenderer(new StatusRenderer());

		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(8).setCellEditor(new ShippingModeEditor());

		/*Enumeration<TableColumn> tableColumns=table.getColumnModel().getColumns();
        while(tableColumns.hasMoreElements()){
        	TableColumn column=tableColumns.nextElement();
        	column.setCellRenderer(new WordWrapCellRenderer());
        }*/
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel buttons = new JPanel();

		buttons.add(new JButton(new AddAction()));
		buttons.add(new JButton(new RemoveAction()));
		buttons.add(new JButton(new FilterAction()));
		buttons.add(new JButton(new CloseAction()));

		getContentPane().add(buttons, BorderLayout.SOUTH);

		pack();
	}

	public static void main(String[] args) {
		new JTableBasicWithDynamicModelObject().setVisible(true);
	}

	private class AddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private AddAction() {
			super("Add");
		}

		public void actionPerformed(ActionEvent e) {
			//model.addFriend(new CraftRecord("Megan", "Sami", Color.green, false, Sport.SWIMMING));
		}
	}

	private class RemoveAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private RemoveAction() {
			super("Remove");
		}

		public void actionPerformed(ActionEvent e) {
			int[] selection = table.getSelectedRows();

			for(int i = selection.length - 1; i >= 0; i--){
				model.removeBurlap(selection[i]);
			}
		}
	}

	private class FilterAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private FilterAction() {
			super("Filter");
		}

		public void actionPerformed(ActionEvent e) {
			showMultipleInputMessageDialog();
		}
	}

	private void showMultipleInputMessageDialog() {

		String[] headers = new String[table.getTableHeader().getColumnModel().getColumnCount()];
		for(int i=0;i<table.getTableHeader().getColumnModel().getColumnCount();i++){
			headers[i]=StringUtil.trim(table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue());
		}
		JTextField textField1 = new JTextField();
		JComboBox<String> headerList = new JComboBox<>(headers);
		Object[] inputFields = {"Filter Column ", headerList,
				"Enter Search String", textField1};
		int option = JOptionPane.showConfirmDialog(this, inputFields, "Filter Data", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (option == JOptionPane.OK_OPTION) {
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);
			sorter.setRowFilter(RowFilter.regexFilter(StringUtil.trim(textField1.getText()), 0, headerList.getSelectedIndex()));
		}
	}

	private class CloseAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private CloseAction() {
			super("close");
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}

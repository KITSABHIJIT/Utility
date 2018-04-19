package com.test.views;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.test.controller.Controller;
import com.util.pojo.BurlapRecord;
import com.util.read.excel.StringUtil;

public class DynamicModelObject extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<BurlapRecord> burlapRecords = new ArrayList<BurlapRecord>();

    private final String[] headers = {"Retailer", "Order<br>Date", "Item", "Order No"
    		, "Quantity", "Making<br>Charge", "Shipping<br>Charge", "Total", "Shipping<br>Mode", "Shipping<br>User"
    		, "Customization", "Color", "Shipping<br>Batch No", "Completed", "Paid", "Shipped"};

    public DynamicModelObject() {
        super();

        burlapRecords= Controller.getBurlapRecords();
    }

    public int getRowCount() {
        return burlapRecords.size();
    }

    public int getColumnCount() {
        return headers.length;
    }

    public String getColumnName(int columnIndex) {
        return "<html><center><b>"+headers[columnIndex]+"</b></center></html>";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return burlapRecords.get(rowIndex).getRetailer();
            case 1:
                return StringUtil.getSomeDateString(burlapRecords.get(rowIndex).getOrderDate(), "MM/dd/yyyy");
            case 2:
                return burlapRecords.get(rowIndex).getItem();
            case 3:
                return burlapRecords.get(rowIndex).getOrderNo();
            case 4:
                return burlapRecords.get(rowIndex).getQuantity();
            case 5:
                return StringUtil.decimalPlaceUpto(burlapRecords.get(rowIndex).getMakingCharge(), 2);
            case 6:
                return StringUtil.decimalPlaceUpto(burlapRecords.get(rowIndex).getShippingCharge(), 2);
            case 7:
                return StringUtil.decimalPlaceUpto((burlapRecords.get(rowIndex).getQuantity()*burlapRecords.get(rowIndex).getMakingCharge())+(burlapRecords.get(rowIndex).getShippingCharge()), 2);
            case 8:
                return burlapRecords.get(rowIndex).getShippingMode();
            case 9:
                return burlapRecords.get(rowIndex).getShippingUser();
            case 10:
                return burlapRecords.get(rowIndex).getCustomization();
            case 11:
                return burlapRecords.get(rowIndex).getColor();
            case 12:
                return burlapRecords.get(rowIndex).getShippingBatchNo();
            case 13:
                return burlapRecords.get(rowIndex).getTaskCompleted();
            case 14:
                return burlapRecords.get(rowIndex).getPaid();
            case 15:
                return burlapRecords.get(rowIndex).getShipped();
            default:
                return null; //Must never happens
        }
    }

    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==4
        		||columnIndex==8
        		||columnIndex==9
        		||columnIndex==10
        		||columnIndex==11
        		||columnIndex==12
        		||columnIndex==13
        		||columnIndex==14
        		||columnIndex==15){
        	return true; //All the cells editable
        }else{
        	return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
        	BurlapRecord burlap = burlapRecords.get(rowIndex);

            switch(columnIndex){
                case 4:
                	burlap.setQuantity(StringUtil.convertIntoNumeric((String)aValue));
                    break;
                case 8:
                	burlap.setShippingMode(StringUtil.trim(aValue));
                	break;
                case 9:
                	burlap.setShippingUser(StringUtil.trim(aValue));
                	break;
                case 10:
                	burlap.setCustomization(StringUtil.trim(aValue));
                	break;
                case 11:
                	burlap.setColor(StringUtil.trim(aValue));
                	break;
                case 12:
                	burlap.setShippingBatchNo(StringUtil.trim(aValue));
                	break;
                case 13:
                	burlap.setTaskCompleted(StringUtil.trim(aValue));
                	break;
                case 14:
                	burlap.setPaid(StringUtil.trim(aValue));
                	break;
                case 15:
                	burlap.setShipped(StringUtil.trim(aValue));
                	break;
            }
        }
    }
    public void addBurlap(BurlapRecord craftRecord) {
    	burlapRecords.add(craftRecord);

        fireTableRowsInserted(burlapRecords.size() -1, burlapRecords.size() -1);
    }

    public void removeBurlap(int rowIndex) {
    	burlapRecords.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}

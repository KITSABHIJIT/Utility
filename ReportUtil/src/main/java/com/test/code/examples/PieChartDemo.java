package com.test.code.examples;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
public class PieChartDemo {  
        public static void main(String[] args) throws Exception{                
                /* Read Excel and the Chart Data */
                FileInputStream chart_file_input = new FileInputStream(new File("C:\\Users\\royab001\\Google Drive\\Expense\\Expense Dumps\\Test Exported Data\\2017-Mar.xls"));
                /* Read chart data from XLSX Workbook */
                HSSFWorkbook my_workbook = new HSSFWorkbook(chart_file_input);
                /* Read worksheet that has pie chart input data information */
                HSSFSheet my_sheet = my_workbook.getSheetAt(1);
                /* Create JFreeChart object that will hold the Pie Chart Data */
                DefaultPieDataset my_pie_chart_data = new DefaultPieDataset();
                /* We have to get the input data into DefaultPieDataset object */
                /* So, we iterate over the rows and cells */
                /* Create an Iterator object */
                /*Iterator<Row> rowIterator = my_sheet.iterator(); 
                 Loop through worksheet data and populate Pie Chart Dataset 
                String chart_label="a";
                Number chart_data=0;            
                while(rowIterator.hasNext()) {
                        //Read Rows from Excel document
                        Row row = rowIterator.next();  
                        //Read cells in Rows and get chart data
                        Iterator<Cell> cellIterator = row.cellIterator();
                                while(cellIterator.hasNext()) {
                                        Cell cell = cellIterator.next(); 
                                        switch(cell.getCellType()) { 
                                        case Cell.CELL_TYPE_NUMERIC:
                                                chart_data=cell.getNumericCellValue();
                                                break;
                                        case Cell.CELL_TYPE_STRING:
                                                chart_label=cell.getStringCellValue();
                                                break;
                                        }
                                }
                 Add data to the data set           
                my_pie_chart_data.setValue(chart_label,chart_data);
                }*/         
                my_pie_chart_data.setValue("abc",2.30);
                my_pie_chart_data.setValue("arfbc",86.2);
                my_pie_chart_data.setValue("rer",56.3);
                my_pie_chart_data.setValue("at5rbc",81.3);
                my_pie_chart_data.setValue("ab323c",5.3);
                
                /* Create a logical chart object with the chart data collected */
                JFreeChart myPieChart=ChartFactory.createPieChart("Excel Pie Chart Java Example",my_pie_chart_data,true,true,false);
                /* Specify the height and width of the Pie Chart */
                int width=640; /* Width of the chart */
                int height=480; /* Height of the chart */
                
                myPieChart.setBackgroundPaint(Color.WHITE);
                myPieChart.getPlot().setBackgroundPaint(Color.WHITE);
                
                /* We don't want to create an intermediate file. So, we create a byte array output stream 
                and byte array input stream
                And we pass the chart data directly to input stream through this */             
                /* Write chart as JPG to Output Stream */
                ByteArrayOutputStream chart_out = new ByteArrayOutputStream();          
                ChartUtilities.writeChartAsPNG(chart_out,myPieChart,width,height);
                /* We now read from the output stream and frame the input chart data */
                /* We don't need InputStream, as it is required only to convert the output chart to byte array */
                /* We can directly use toByteArray() method to get the data in bytes */
                /* Add picture to workbook */
                int my_picture_id = my_workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
                /* Close the output stream */
                chart_out.close();
                /* Create the drawing container */
                HSSFPatriarch drawing = my_sheet.createDrawingPatriarch();
                /* Create an anchor point */
                ClientAnchor my_anchor = new HSSFClientAnchor();
                /* Define top left corner, and we can resize picture suitable from there */
                my_anchor.setCol1(4);
                my_anchor.setRow1(5);
                /* Invoke createPicture and pass the anchor point and ID */
                HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
                /* Call resize method, which resizes the image */
                my_picture.resize();
                /* Close the FileInputStream */
                chart_file_input.close();               
                /* Write Pie Chart back to the XLSX file */
                FileOutputStream out = new FileOutputStream(new File("C:\\Users\\royab001\\Google Drive\\Expense\\Expense Dumps\\Test Exported Data\\2017-Mar.xls"));
                my_workbook.write(out);
                out.close();            
        }
}

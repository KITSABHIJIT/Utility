package com.test.code.examples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class LineChart {  
        public static void main(String[] args) throws Exception{                
                /* Read the bar chart data from the excel file */
                FileInputStream chart_file_input = new FileInputStream(new File("C:\\Users\\royab001\\Google Drive\\Expense\\Expense Dumps\\Test Exported Data\\2017-Jan.xls"));
                /* HSSFWorkbook object reads the full Excel document. We will manipulate this object and
                write it back to the disk with the chart */
                HSSFWorkbook my_workbook = new HSSFWorkbook(chart_file_input);
                /* Read chart data worksheet */
                HSSFSheet my_sheet = my_workbook.getSheetAt(1);
                /* Create Dataset that will take the chart data */
                DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
                /* We have to load bar chart data now */
                /* Begin by iterating over the worksheet*/
                /* Create an Iterator object */
               /* Iterator<Row> rowIterator = my_sheet.iterator(); 
                 Loop through worksheet data and populate bar chart dataset 
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
                 We don't have grouping in the bar chart, so we put them in fixed group 
                chart_label+="abc";
                my_bar_chart_dataset.addValue(chart_data.doubleValue(),"Marks",chart_label);
                }  */       
                my_bar_chart_dataset.addValue(2.65,"Marks","sdas");
                my_bar_chart_dataset.addValue(3.65,"Marks","dass");
                my_bar_chart_dataset.addValue(45.32,"Marks","453ff");
                my_bar_chart_dataset.addValue(6.32,"Marks","r4few");
                
                /* Create a logical chart object with the chart data collected */
                JFreeChart BarChartObject=ChartFactory.createBarChart("Subject Vs Marks","Subject","Marks",my_bar_chart_dataset,PlotOrientation.VERTICAL,true,true,false);  
                /* Dimensions of the bar chart */               
                int width=640; /* Width of the chart */
                int height=480; /* Height of the chart */               
                /* We don't want to create an intermediate file. So, we create a byte array output stream 
                and byte array input stream
                And we pass the chart data directly to input stream through this */             
                /* Write chart as PNG to Output Stream */
                ByteArrayOutputStream chart_out = new ByteArrayOutputStream();          
                ChartUtilities.writeChartAsPNG(chart_out,BarChartObject,width,height);
                /* We can now read the byte data from output stream and stamp the chart to Excel worksheet */
                int my_picture_id = my_workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_PNG);
                /* we close the output stream as we don't need this anymore */
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
                /* Write changes to the workbook */
                FileOutputStream out = new FileOutputStream(new File("C:\\Users\\royab001\\Google Drive\\Expense\\Expense Dumps\\Test Exported Data\\2017-Jan.xls"));
                my_workbook.write(out);
                out.close();            
        }
}


 
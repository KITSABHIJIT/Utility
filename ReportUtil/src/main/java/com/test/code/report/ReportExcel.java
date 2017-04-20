package com.test.code.report;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import com.test.code.pojo.BarChartData;
import com.test.code.pojo.PieChartData;
import com.test.code.pojo.ReportData;
import com.test.code.pojo.TableData;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class ReportExcel {

	public static void writeExcel(Map<String,ReportData> data, String excelFilePath){
		HSSFWorkbook workbook = new HSSFWorkbook();

		Set<Entry<String, ReportData>> mapData=data.entrySet();
		for(Entry<String, ReportData> dataSet:mapData){
			HSSFSheet sheet = (HSSFSheet) workbook.createSheet(dataSet.getKey());

			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getTableData())){
				for(TableData tableData : dataSet.getValue().getTableData()){
					writeTabularData(workbook,sheet,tableData);
				}
			}
			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getPieData())){
				for(PieChartData pieData : dataSet.getValue().getPieData()){
					writePieChart(workbook, sheet, pieData);
				}
			}
			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getBarData())){
				for(BarChartData barData : dataSet.getValue().getBarData()){
					writeBarChart(workbook, sheet, barData);
				}
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("File generated: "+excelFilePath);
	}

	public static void writeTabularData(Workbook workbook,HSSFSheet sheet,TableData data){
		int rowCount=0;
		int startRowPosition = data.getRowPosition();
		for (List<Object> aBook : data.getReportData()) {
			Row row = sheet.createRow(startRowPosition);
			int StartColumnPosition = data.getColumnPosition();
			for (Object field : aBook) {
				Cell cell = row.createCell(StartColumnPosition);
				CellStyle cellStyle = workbook.createCellStyle();

				sheet.autoSizeColumn((short) (StartColumnPosition));//Set word wrap

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

				if(rowCount==0){
					Font font = workbook.createFont();//Create font
					font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
					cellStyle.setFont(font);//set it to bold
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				}

				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);
				} else if (field instanceof Date) {
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(PropertiesUtil.getProperty("excelDataFormat")));
					cell.setCellValue((Date) field);
				}else if (field instanceof BigDecimal) {
					cell.setCellValue(((BigDecimal) field).doubleValue());
				}
				cell.setCellStyle(cellStyle);//Apply style to cell
				StartColumnPosition++;
			}
			startRowPosition++;
			rowCount++;
		}
	}

	public static void writePieChart(Workbook workbook,HSSFSheet sheet,PieChartData data){
		DefaultPieDataset my_pie_chart_data = data.getDataset();
		JFreeChart myPieChart=ChartFactory.createPieChart(data.getTitle(),my_pie_chart_data,data.isLegend(),data.isTooltips(),data.isUrls());
		try {
			ByteArrayOutputStream chart_out = new ByteArrayOutputStream();          
			ChartUtilities.writeChartAsPNG(chart_out,myPieChart,data.getLength(),data.getHeight());
			int my_picture_id = workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
			chart_out.close();
			HSSFPatriarch drawing = sheet.createDrawingPatriarch();
			ClientAnchor my_anchor = new HSSFClientAnchor();
			my_anchor.setCol1(data.getColumnPosition());
			my_anchor.setRow1(data.getRowPosition());
			HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
			my_picture.resize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeBarChart(Workbook workbook,HSSFSheet sheet,BarChartData data){
		DefaultCategoryDataset bar_chart_data = data.getDataset();
		JFreeChart BarChartObject=ChartFactory.createBarChart(data.getTitle(),data.getCategoryAxisLabel(),data.getValueAxisLabel(),bar_chart_data,PlotOrientation.VERTICAL,data.isLegend(),data.isTooltips(),data.isUrls());  
		try {
			ByteArrayOutputStream chart_out = new ByteArrayOutputStream();

			LegendTitle legend = BarChartObject.getLegend();
			legend.setPosition(RectangleEdge.RIGHT);
			
			CategoryAxis domainAxis = BarChartObject.getCategoryPlot().getDomainAxis();  
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));


			ChartUtilities.writeChartAsPNG(chart_out,BarChartObject,data.getLength(),data.getHeight());
			int my_picture_id = workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
			chart_out.close();
			HSSFPatriarch drawing = sheet.createDrawingPatriarch();
			ClientAnchor my_anchor = new HSSFClientAnchor();
			my_anchor.setCol1(data.getColumnPosition());
			my_anchor.setRow1(data.getRowPosition());
			HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
			my_picture.resize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

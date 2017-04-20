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
import org.jfree.data.general.DefaultPieDataset;

import com.test.code.pojo.PieChartData;
import com.test.code.pojo.ReportData;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class ReportExcel {

	public static void writeExcel(Map<String,ReportData> data, String excelFilePath){
		HSSFWorkbook workbook = new HSSFWorkbook();

		Set<Entry<String, ReportData>> mapData=data.entrySet();
		for(Entry<String, ReportData> dataSet:mapData){
			HSSFSheet sheet = (HSSFSheet) workbook.createSheet(dataSet.getKey());

			writeTabularData(workbook,sheet,dataSet.getValue().getReportData());
			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getPieData())){
				for(PieChartData pieData : dataSet.getValue().getPieData()){
					writePieChart(workbook, sheet, pieData);
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

	public static void writeTabularData(Workbook workbook,HSSFSheet sheet,List<List<Object>> data){
		int rowCount = 0;
		for (List<Object> aBook : data) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (Object field : aBook) {
				Cell cell = row.createCell(++columnCount);
				CellStyle cellStyle = workbook.createCellStyle();

				sheet.autoSizeColumn((short) (columnCount));//Set word wrap

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

				if(rowCount==1){
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
			}
		}
	}

	public static void writePieChart(Workbook workbook,HSSFSheet sheet,PieChartData data){
		DefaultPieDataset my_pie_chart_data = data.getDataset();
		JFreeChart myPieChart=ChartFactory.createPieChart(data.getTitle(),my_pie_chart_data,data.isLegend(),data.isTooltips(),data.isUrls());
		//myPieChart.setBackgroundPaint(Color.WHITE);
		//myPieChart.getPlot().setBackgroundPaint(Color.WHITE);
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


}

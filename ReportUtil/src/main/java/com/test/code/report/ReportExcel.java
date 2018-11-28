package com.test.code.report;

import java.awt.BasicStroke;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import com.test.code.pojo.BarChartData;
import com.test.code.pojo.LineChartData;
import com.test.code.pojo.PieChartData;
import com.test.code.pojo.ReportData;
import com.test.code.pojo.TableData;
import com.test.code.util.ExcelCellStyleUtil;
import com.test.code.util.FileUtil;
import com.test.code.util.StringUtil;

public class ReportExcel {
	final static Logger logger = Logger.getLogger(ReportExcel.class);
	public static void writeExcel(Map<String,ReportData> data, String excelFilePath){
		FileUtil.deleteFile(excelFilePath);
		HSSFWorkbook workbook = new HSSFWorkbook();
		ExcelCellStyleUtil cellStyleUtil=new ExcelCellStyleUtil(workbook);
		Set<Entry<String, ReportData>> mapData=data.entrySet();
		for(Entry<String, ReportData> dataSet:mapData){
			HSSFSheet sheet = (HSSFSheet) workbook.createSheet(dataSet.getKey());

			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getTableData())){
				for(TableData tableData : dataSet.getValue().getTableData()){
					writeTabularData(workbook,cellStyleUtil,sheet,tableData);
					logger.debug(dataSet.getKey()+" table added to sheet");
				}
			}
			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getPieData())){
				for(PieChartData pieData : dataSet.getValue().getPieData()){
					writePieChart(workbook, sheet, pieData);
					logger.debug(pieData.getTitle()+" Pie chart added to sheet: "+dataSet.getKey());
				}
			}
			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getBarData())){
				for(BarChartData barData : dataSet.getValue().getBarData()){
					writeBarChart(workbook, sheet, barData);
					logger.debug(barData.getTitle()+" Pie chart added to sheet: "+dataSet.getKey());
				}
			}
			if(!StringUtil.isBlankOrEmpty(dataSet.getValue().getLineData())){
				for(LineChartData lineData : dataSet.getValue().getLineData()){
					writeLineChart(workbook, sheet, lineData);
					logger.debug(lineData.getTitle()+" Pie chart added to sheet: "+dataSet.getKey());
				}
			}
			logger.debug("Sheet: "+dataSet.getKey()+" added to Excel.");
		}
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.debug("File generated: "+excelFilePath);
	}

	public static void writeTabularData(Workbook workbook,ExcelCellStyleUtil cellStyleUtil,HSSFSheet sheet,TableData data){
		int rowCount=0;
		int startRowPosition = data.getRowPosition();

		for (List<Object> aBook : data.getReportData()) {
			Row row = sheet.createRow(startRowPosition);
			int StartColumnPosition = data.getColumnPosition();
			for (Object field : aBook) {
				Cell cell = row.createCell(StartColumnPosition);
				sheet.autoSizeColumn((short) (StartColumnPosition));//Set word wrap
				if(rowCount==0){
					cell.setCellStyle(cellStyleUtil.getHeaderCellStyle());
				}else{
					cell.setCellStyle(cellStyleUtil.getBodyCellStyle());
				}
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);
					cell.setCellStyle(cellStyleUtil.getBodyNumericCellStyle());
				} else if (field instanceof Date) {
					cell.setCellValue((Date) field);
					cell.setCellStyle(cellStyleUtil.getBodyDateCellStyle());
				}else if (field instanceof BigDecimal) {
					cell.setCellValue(((BigDecimal) field).doubleValue());
				}
				StartColumnPosition++;
			}
			startRowPosition++;
			rowCount++;
		}
	}

	public static void writePieChart(Workbook workbook,HSSFSheet sheet,PieChartData data){
		DefaultPieDataset my_pie_chart_data = data.getDataset();
		JFreeChart myPieChart=null;
		try(ByteArrayOutputStream chart_out = new ByteArrayOutputStream();) {
			if(data.is3D()){
				myPieChart=ChartFactory.createPieChart3D(data.getTitle(),my_pie_chart_data,data.isLegend(),data.isTooltips(),data.isUrls());
				final PiePlot3D plot = ( PiePlot3D ) myPieChart.getPlot( );             
				plot.setStartAngle(data.getStartAngle());             
				plot.setForegroundAlpha(data.getForeGroundAlpha());             
				plot.setInteriorGap(data.getInteriorGap()); 
				plot.setDepthFactor(0.07);
				PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
						"{0}: ${1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
				plot.setLabelGenerator(gen);
			}else{
				myPieChart=ChartFactory.createPieChart(data.getTitle(),my_pie_chart_data,data.isLegend(),data.isTooltips(),data.isUrls());
				final PiePlot plot = ( PiePlot ) myPieChart.getPlot( );             
				plot.setStartAngle(data.getStartAngle());             
				plot.setForegroundAlpha(data.getForeGroundAlpha());             
				plot.setInteriorGap(data.getInteriorGap()); 
				PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
						"{0}: ${1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
				plot.setLabelGenerator(gen);

			}
			ChartUtilities.writeChartAsPNG(chart_out,myPieChart,data.getLength(),data.getHeight());
			int my_picture_id = workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
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
		JFreeChart barChartObject=(data.isIs3D())?ChartFactory.createBarChart3D(data.getTitle(),data.getCategoryAxisLabel(),data.getValueAxisLabel(),bar_chart_data,(data.isVerticalBar())?PlotOrientation.VERTICAL:PlotOrientation.HORIZONTAL,data.isLegend(),data.isTooltips(),data.isUrls())
				:ChartFactory.createBarChart(data.getTitle(),data.getCategoryAxisLabel(),data.getValueAxisLabel(),bar_chart_data,(data.isVerticalBar())?PlotOrientation.VERTICAL:PlotOrientation.HORIZONTAL,data.isLegend(),data.isTooltips(),data.isUrls());  
		try (
			ByteArrayOutputStream chart_out = new ByteArrayOutputStream();){

			LegendTitle legend = barChartObject.getLegend();
			legend.setPosition(RectangleEdge.RIGHT);
			CategoryAxis domainAxis = barChartObject.getCategoryPlot().getDomainAxis();  
			if(data.isVerticalLabel()){
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));
			}
			if(data.isDisplayValueOnBar()){
				final CategoryItemRenderer renderer = barChartObject.getCategoryPlot().getRenderer();
				BarRenderer r = (BarRenderer) renderer;
				r.setMaximumBarWidth(0.05);
				StandardCategoryItemLabelGenerator categoryItemLabelGenerator =new StandardCategoryItemLabelGenerator("${2}", NumberFormat.getInstance());
				r.setBaseItemLabelGenerator(categoryItemLabelGenerator);
				r.setBaseItemLabelsVisible(true);
				barChartObject.getCategoryPlot().setRenderer(r);
			}
			ChartUtilities.writeChartAsPNG(chart_out,barChartObject,data.getLength(),data.getHeight());
			int my_picture_id = workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
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
	
	public static void writeLineChart(Workbook workbook,HSSFSheet sheet,LineChartData data){
		DefaultCategoryDataset bar_chart_data = data.getDataset();
		JFreeChart lineChartObject=ChartFactory.createLineChart(data.getTitle(),data.getCategoryAxisLabel(),data.getValueAxisLabel(),bar_chart_data,PlotOrientation.VERTICAL,data.isLegend(),data.isTooltips(),data.isUrls());  
		try (
				ByteArrayOutputStream chart_out = new ByteArrayOutputStream();){

			LegendTitle legend = lineChartObject.getLegend();
			legend.setPosition(RectangleEdge.RIGHT);
			CategoryAxis domainAxis = lineChartObject.getCategoryPlot().getDomainAxis();  
			if(data.isVertcalLabel()){
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));
			}
			if(data.isDisplayValueOnBar()){
				final CategoryItemRenderer renderer = lineChartObject.getCategoryPlot().getRenderer();
				LineAndShapeRenderer  r = (LineAndShapeRenderer ) renderer;
				StandardCategoryItemLabelGenerator categoryItemLabelGenerator =new StandardCategoryItemLabelGenerator("${2}", NumberFormat.getInstance());
				r.setBaseItemLabelGenerator(categoryItemLabelGenerator);
				r.setBaseItemLabelsVisible(true);
				r.setSeriesStroke(0, new BasicStroke(4));
				lineChartObject.getCategoryPlot().setRenderer(r);
			}
			ChartUtilities.writeChartAsPNG(chart_out,lineChartObject,data.getLength(),data.getHeight());
			int my_picture_id = workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
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

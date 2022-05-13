package com.build.beanToPdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
	/*public static final String DEST = "results/tables/small_table.pdf";
	public static void main(String[] args) throws IOException, DocumentException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		SalaryBean bean =DataBuilder.getResponseBean();
		new CreatePDF().createPdf(DEST,bean);
	}
	public void createPdf(String dest,SalaryBean bean) throws IOException, DocumentException {

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(dest));
		document.open();
		PdfPTable table = new PdfPTable(5);
		Image image = Image.getInstance("results/tables/index.png");
		image.scaleAbsolute(130f, 70f);
        PdfPCell cell2 = new PdfPCell(image, false);
        cell2.setColspan(5);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
		table.setWidthPercentage(100);
		//Row 1 
		addCellToHeader(table,"Invoice Number: "+bean.getINVOICE_NUMBER(),3);
		addCellToHeader(table,"Invoice Date: "+bean.getINVOICE_DATE(),1);
		addCellToHeader(table,"Process Group: "+bean.getAUTOMATCH_PRO_GRP(),1);
		//Row 2
		addCellToHeader(table,"Vendor No: "+bean.getVENDOR_NUMBER(),1);
		addCellToHeader(table,"Vendor Name: "+bean.getVENDOR_NAME(),3);
		addCellToHeader(table,"Ship To: "+bean.getSHIP_TO_WAREHOUSE_STORE(),1);
		//Row 3
		addCellToHeader(table,"Alt Vendor No: "+bean.getALT_VENDOR_NUMBER(),1);
		addCellToHeader(table,"Alt Vendor Name: "+bean.getALT_VENDOR_NAME(),3);
		addCellToHeader(table,"Ship Date: "+bean.getSHIP_DATE(),1);
		//Row 4
		addCellToHeader(table,"PO No: "+bean.getPURCHASE_ORDER_NUMBER(),1);
		addCellToHeader(table,"Order Date: "+bean.getPO_ENTRY_DATE(),1);
		addCellToHeader(table,"EDI Date Time: "+bean.getEDI_DATE()+" "+bean.getEDI_TIME(),3);
		//Row 5
		addCellToHeader(table,"BOL: "+bean.getBILL_OF_LADING_NUMBER(),1);
		addCellToHeader(table,"SCAC: "+bean.getSTANDARD_CARRIER_ALPHA_CODE(),1);
		addCellToHeader(table,"Units: "+bean.getTOTAL_UNITS(),1);
		addCellToHeader(table,"App Code: "+bean.getAPPLICATION_ID(),1);
		addCellToHeader(table,"PO Terms: "+bean.getTERMS_CODE(),1);
		//Row 6
		addCellToHeader(table,"Net Due Date: "+bean.getNET_INVOICE_DUE_DATE(),1);
		addCellToHeader(table,"Term Discount%: "+bean.getDISCOUNT_PERCENTAGE(),1);
		addCellToHeader(table,"Discount Due Date: "+bean.getDISCOUNT_DATE(),1);
		addCellToHeader(table,"FOB: "+bean.getLOCATION_QUALIFIER(),2);
		//Row 7
		addCellToHeader(table,"Amount Subject to Discount: "+bean.getAMOUNT_SUBJECT_TO_DISCOUNT(),2);
		addCellToHeader(table,"Discount Amount Due: "+bean.getDISCOUNTED_AMOUNT_DUE(),2);
		addCellToHeader(table,"Discount Amount: "+bean.getTERMS_DISCOUNT_AMOUNT(),1);
		//Row 8
		addCellToHeader(table,"Merchendise Amount: "+bean.getMERCHANDISE_AMT(),1);
		addCellToHeader(table,"Total Allowances: "+bean.getTOTAL_ALLOWANCES(),1);
		addCellToHeader(table,"Total Charges: "+bean.getTOTAL_CHARGES(),1);
		addCellToHeader(table,"Total Freight: "+bean.getTOTAL_FREIGHT(),1);
		addCellToHeader(table,"Total Invoice Amount: "+bean.getTOTAL_INVOICE_AMOUNT(),1);
		document.add(table);

		
		if(null !=bean.getDetailList() && bean.getDetailList().size()>0){
			PdfPTable detailTable = new PdfPTable(9);
			detailTable.setWidthPercentage(100);
			detailTable.setSpacingBefore(10f);
			//Row 1 
			addCellAsLabel(detailTable,"Details",9);
			//Row 2 
			addCellToTableHeader(detailTable,"SKU No",1);
			addCellToTableHeader(detailTable,"Vendor Part No",1);
			addCellToTableHeader(detailTable,"UPC Code",1);
			addCellToTableHeader(detailTable,"SKU Desc",1);
			addCellToTableHeader(detailTable,"UOM",1);
			addCellToTableHeader(detailTable,"SUCP",1);
			addCellToTableHeader(detailTable,"Units",1);
			addCellToTableHeader(detailTable,"Unit cost",1);
			addCellToTableHeader(detailTable,"Ext cost",1);
			for(DetailBean detailBean: bean.getDetailList()){
				addCellToTableDetail(detailTable,detailBean.getSKU_NUMBER(),1);
				addCellToTableDetail(detailTable,detailBean.getVEND_MODEL_NUM(),1);
				addCellToTableDetail(detailTable,detailBean.getVEND_UPC_NUM(),1);
				addCellToTableDetail(detailTable,detailBean.getITEM_DESC(),1);
				addCellToTableDetail(detailTable,detailBean.getUOM_CODE(),1);
				addCellToTableDetail(detailTable,detailBean.getCASE_PACK(),1);
				addCellToTableDetail(detailTable,detailBean.getQTY_INVOICED(),1);
				addCellToTableDetail(detailTable,detailBean.getPRICE_PER_UOM(),1);
				addCellToTableDetail(detailTable,detailBean.getEXTENDED_COST(),1);
			}
				document.add(detailTable);
		}

		if(null !=bean.getPackageTrackingBean() && bean.getPackageTrackingBean().size()>0){
			PdfPTable pkgTrcTable = new PdfPTable(2);
			pkgTrcTable.setSpacingBefore(10f);
			pkgTrcTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			//Row 1 
			addCellAsLabel(pkgTrcTable,"Package Tracking Details",2);
			//Row 2 
			addCellToTableHeader(pkgTrcTable,"SKU Number",1);
			addCellToTableHeader(pkgTrcTable,"Package Tracking Number",1);
			for(PackageTrackingBean pkgBean: bean.getPackageTrackingBean()){
				addCellToTableDetail(pkgTrcTable,pkgBean.getSKU_NUMBER(),1);
				addCellToTableDetail(pkgTrcTable,pkgBean.getPACKAGE_TRACKING_NUMBER(),1);
			}
			document.add(pkgTrcTable);
		}
		
		if(null !=bean.getErrorList() && bean.getErrorList().size()>0){
			PdfPTable errorTable = new PdfPTable(3);
			errorTable.setSpacingBefore(10f);
			errorTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			//Row 1 
			addCellAsLabel(errorTable,"Error Details",3);
			//Row 21 
			addCellToTableHeader(errorTable,"Error Code",1);
			addCellToTableHeader(errorTable,"Error",1);
			addCellToTableHeader(errorTable,"Error Description",1);
			for(ErrorBean errBean: bean.getErrorList()){
				addCellToTableDetail(errorTable,errBean.getERROR_CODE(),1);
				addCellToTableDetail(errorTable,errBean.getERROR(),1);
				addCellToTableDetail(errorTable,errBean.getERROR_DESCRIPTION(),1);
			}
			document.add(errorTable);
		}
		document.close();

	}

	private void addCellToHeader(PdfPTable table,String data,int colSpace){
		Font smallfont = new Font(FontFamily.HELVETICA, 7,Font.BOLD);
		PdfPCell cell  = new PdfPCell(new Paragraph(data,smallfont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(colSpace);
		table.addCell(cell);

	}
	private void addCellToTableHeader(PdfPTable table,String data,int colSpace){
		Font smallfont = new Font(FontFamily.HELVETICA, 6,Font.BOLD);
		PdfPCell cell  = new PdfPCell(new Paragraph(data,smallfont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(colSpace);
		table.addCell(cell);

	}
	private void addCellToTableDetail(PdfPTable table,String data,int colSpace){
		Font smallfont = new Font(FontFamily.HELVETICA, 6);
		PdfPCell cell  = new PdfPCell(new Paragraph(data,smallfont));
		cell.setColspan(colSpace);
		table.addCell(cell);

	}
	private void addCellAsLabel(PdfPTable table,String data,int colSpace){
		Font smallfont = new Font(FontFamily.HELVETICA, 8,Font.BOLD);
		PdfPCell cell  = new PdfPCell(new Paragraph(data,smallfont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(colSpace);
		table.addCell(cell);

	}
*/
}

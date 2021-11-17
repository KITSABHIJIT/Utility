package com.test.spicework;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;


public class ReadFiles {

	private static String sourcePDFDir="/Users/abhijit/Downloads/spicework";
	private static String googleDrivePath="/Users/abhijit/Desktop";
	private static String outputPath=googleDrivePath+"/SpiceWorkData.xls";

	public static void main(String[] args) {
		List<Tickets> list=new ArrayList<Tickets>();
		processPDFOrders(sourcePDFDir,list);

	}

	public static void processPDFOrders(String sourceDirectory,List<Tickets> list){
		File _folder = new File(sourceDirectory); 
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(); 
		for (File string : filesInFolder){ 
			if(!".DS_Store".equals(string.getName())) {
				String pdfData = getTextFromPDF(string.getAbsolutePath());
				list.add(extractData(pdfData,string.getName()));
				System.out.println(string.getName()+ " appended Successfully.");
			}
		}
		list.sort(Comparator.comparing(Tickets::getTicketNo));
		ReportExcel.writeExcel(getFieldsData(list), outputPath);
	}


	public static String getTextFromPDF(String fileName) {
		PDDocument document = null;
		String result=null;
		try {
			document = PDDocument.load(fileName);
			document.getClass();
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition( true );
			PDFTextStripper Tstripper = new PDFTextStripper();
			result = Tstripper.getText(document);
		}catch(Exception e){
			System.out.println("Error out file "+fileName);
			e.printStackTrace();
		}finally{
			try {
				if(null!=document) {
					document.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Tickets extractData(String data,String fileName) {
		String [] temp=null;
		Tickets tickets=new Tickets();
		List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
		try {
			for(int i=0;i<myList.size();i++) {
				if((myList.get(i).contains("Shop"))) {
					i=i+1;
					tickets.setTicketShop(StringUtil.trim(myList.get(i)));
				}
				if((myList.get(i).contains("Order")) && myList.get(i).contains("#")) {
					temp=myList.get(i).split(" ");
					tickets.setOrderNumber(temp[1].substring(temp[1].indexOf("#")+1));
				}

				if((myList.get(i).contains("Ship to"))) {
					i=i+1;
					tickets.setShippedTo(StringUtil.trim(myList.get(i)));
				}
				if((myList.get(i).contains("Order date"))) {
					i=i+1;
					try{
						tickets.setCreatedOn(DateUtil.getSomeDate(myList.get(i), "MMM dd, yyyy"));
					}catch(ParseException e) {
						tickets.setCreatedOn(DateUtil.getSomeDate(myList.get(i), "dd MMM, yyyy"));
					}
				}
				if((myList.get(i).contains("item")) && (myList.get(i).split(" ")).length<3) {
					temp=myList.get(i).split(" ");
					tickets.setQuantity(Integer.parseInt(StringUtil.trim(temp[0])));
					i=i+1;
					temp=myList.get(i).split("[|]");
					if(tickets.getCustomization()==null)
						tickets.setCustomization(StringUtil.trim(temp[0]));
				}
				
				if((myList.get(i).contains("Personalization")) || (myList.get(i).contains("Personalisation"))) {
					temp=myList.get(i).split(":");
					tickets.setCustomization(StringUtil.trim(temp[1]));
					//break;
				}
				if("Gift message".equals(StringUtil.trim(myList.get(i)))) {
					i=i+1;
					tickets.setGiftMessage(StringUtil.trim(myList.get(i)));
				}
			}
			temp=fileName.split("[.]");
			tickets.setTicketNo(Integer.parseInt(temp[0]));
		}catch(Exception e){
			System.out.println("Error out Data "+data);
			e.printStackTrace();
		}
		return tickets;
	}

	public static List<List<Object>> getFieldsData(List<Tickets> tickets){
		List<List<Object>> data=new ArrayList<List<Object>>();
		List<Object> header=new ArrayList<Object>();
		header.add("Shop");
		header.add("Date");
		header.add("Work");
		header.add("Order Number");
		header.add("Ticket");
		header.add("Quantity");
		header.add("Status");
		header.add("Amount");
		header.add("Payment Status");
		header.add("Shipped");
		header.add("Shipping Mode");
		header.add("Shipped User");
		header.add("Customization");
		header.add("Category");
		header.add("Gift Message");
		data.add(header);
		for(Tickets ticket: tickets) {
			List<Object> lines=new ArrayList<Object>();
			lines.add(ticket.getTicketShop());
			lines.add(ticket.getCreatedOn());
			lines.add("SPOON");
			lines.add(ticket.getOrderNumber());
			lines.add(ticket.getTicketNo());
			lines.add(ticket.getQuantity());
			lines.add("");
			lines.add(ticket.getQuantity()*2.5);
			lines.add("PENDING");
			lines.add("");
			lines.add("");
			lines.add(ticket.getShippedTo());
			lines.add(ticket.getCustomization());
			lines.add("Tea");
			lines.add(ticket.getGiftMessage());
			data.add(lines);
		}

		return data;
	}
}

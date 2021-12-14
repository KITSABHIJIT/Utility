package com.test.spicework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

import com.opencsv.CSVReader;


public class ReadFiles {

	private static String sourceDir="/Users/tanayachattopadhyay/Google Drive/My Drive/spicework";
	private static String googleDrivePath="/Users/tanayachattopadhyay/Google Drive/My Drive";
	private static String outputPath=googleDrivePath+"/SpiceWorkData.xls";
	
	//private static String sourceDir="/Users/abhijit/Downloads/spicework";
	//private static String googleDrivePath="/Users/abhijit/Desktop";
	//private static String outputPath=googleDrivePath+"/SpiceWorkData.xls";
	

	public static void main(String[] args) {
		List<Tickets> list=new ArrayList<Tickets>();
		processOrders(sourceDir,list);

	}

	public static void processOrders(String sourceDirectory,List<Tickets> list){
		File _folder = new File(sourceDirectory); 
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(); 
		for (File string : filesInFolder){ 
			if(!".DS_Store".equals(string.getName())) {
				//String pdfData = getTextFromPDF(string.getAbsolutePath());
				//list.add(extractData(pdfData,string.getName()));
				list = getTickets(string);
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

				if((myList.get(i).contains("Ship to")) || (myList.get(i).contains("Deliver to"))) {
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
			lines.add(ticket.getQuantity()*2);
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

	public static List<Tickets> getTickets(File file){

		List<Tickets> records = new ArrayList<Tickets>();
		try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
			String[] values = null;
			int counter=0;
			while ((values = csvReader.readNext()) != null) {

				if(counter>0) {
					Tickets tickets=new Tickets();
					tickets.setTicketNo(Integer.parseInt(values[0]));
					tickets.setCreatedOn(getDate(values[8],"MMM dd, yyyy @ HH:mm a"));
					String tempDesc = new String(values[1].getBytes("UTF-8"), "UTF-8");
					if(tempDesc.contains("-")) {
						String [] tmpArr=tempDesc.split("-");
						tickets.setOrderNumber(StringUtil.trim(tmpArr[0].substring(tmpArr[0].indexOf("#")+1)));
						tickets.setCustomization(StringUtil.trim(tempDesc.substring(tempDesc.indexOf("-")+1,tempDesc.toUpperCase().indexOf("QTY")-1)));
						tickets.setQuantity(Integer.parseInt(StringUtil.trim(tempDesc.substring(tempDesc.toUpperCase().indexOf("QTY")+3).replaceAll("-", ""))));
					}else {
						tickets.setCustomization(StringUtil.trim(tempDesc.substring(tempDesc.indexOf(":")+1)));
					}
					tickets.setTicketShop(StringUtil.trim(values[3].substring(values[3].indexOf(" ")+1)));
					records.add(tickets);
					System.out.println(tickets.toString());
				}

				counter++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;

	}
	public static Date getDate(final String str, final String inputFormat)
			throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		return sdf.parse(str);
	}
}

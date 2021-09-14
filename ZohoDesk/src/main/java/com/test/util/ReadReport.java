package com.test.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;

public class ReadReport {

	public static void main(String ...strings) {
		String inputPath="C:\\Users\\home\\Google Drive (tanaya.chatt90@gmail.com)\\TicketData.csv";
		String outputPath="C:\\Users\\\\home\\Google Drive (tanaya.chatt90@gmail.com)\\TicketData.xls";
		List<Tickets> tickets= getTickets(inputPath);
		tickets.sort(Comparator.comparing(Tickets::getTicketNo));
		ReportExcel.writeExcel(getFieldsData(tickets), outputPath);

	}

	public static List<Tickets> getTickets(String file){

		List<Tickets> records = new ArrayList<Tickets>();
		try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
			String[] values = null;
			int counter=0;
			while ((values = csvReader.readNext()) != null) {

				if(counter>0) {
					Tickets tickets=new Tickets();
					tickets.setTicketNo(Integer.parseInt(values[0]));
					tickets.setCreatedOn(getDate(values[19],"yyyy-MM-dd HH:mm:ss"));
					//tickets.setCreatedOn(getDate(values[19],"MM/dd/yyyy HH:mm"));
					String tempDesc = new String(values[9].getBytes("UTF-8"), "UTF-8");
					tempDesc = tempDesc.replaceAll("“", "|");
					tempDesc = tempDesc.replaceAll("”", "|");

					//String tempDesc =values[9];
					if(values[4].trim().equalsIgnoreCase(TicketConstants.MANMEET)
							|| values[4].trim().equalsIgnoreCase(TicketConstants.MANMMET)) {
						// Setting Order Number
						if(values[8].contains("#Order#")) {
							tickets.setOrderNumber((values[8].substring(values[8].indexOf("#Order#")+7,(values[8].indexOf("-")))).trim());
						}else if (values[8].contains("Order")) {
							tickets.setOrderNumber((values[8].substring(values[8].indexOf("#")+1,(values[8].indexOf("-")))).trim());
						}else {
							tickets.setOrderNumber("0");
						}
						// Setting Quantity
						if(values[8].contains("Qty-")) {
							tickets.setQuantity(Integer.parseInt((values[8].substring(values[8].indexOf("Qty-")+4,(values[8].lastIndexOf("-")))).trim()));
						}else {
							tickets.setQuantity(0);
						}

						// Setting Shipped To
						if(tempDesc.indexOf("1em; font-weight: 400")>0) {
							String tempShippedTo=(tempDesc.substring(tempDesc.indexOf("1em; font-weight: 400")+23,(tempDesc.indexOf("<br",tempDesc.indexOf("1em; font-weight: 400")+27)))).trim();
							tickets.setShippedTo((tempShippedTo.contains("<div>"))?tempShippedTo.substring(tempShippedTo.lastIndexOf("<div>")+5):tempShippedTo);
						}else if(tempDesc.indexOf("Deliver to")>0) {
							String tempShippedTo=(tempDesc.substring(tempDesc.indexOf("\n",tempDesc.indexOf("Deliver to")),(tempDesc.indexOf("\n",tempDesc.indexOf("Deliver to")+29)))).trim();
							tickets.setShippedTo((tempShippedTo.contains("<div>"))?tempShippedTo.substring(tempShippedTo.lastIndexOf("<div>")+5):tempShippedTo);
						}

						// Setting Gift Message
						tickets.setGiftMessage(null);

						//setting spoon Type
						tickets.setSpoonType(TicketConstants.TEA);


					}else if(values[4].trim().equalsIgnoreCase(TicketConstants.GANGA)) {
						// Setting Order Number
						tickets.setOrderNumber((values[8].substring(values[8].lastIndexOf("-")+1).trim()));
						// Setting Quantity
						if(values[8].contains("Amyjacksoncrafts:")) {
							tickets.setQuantity(Integer.parseInt((values[8].substring(values[8].indexOf(":")+1,(values[8].indexOf("-")))).trim()));
						}

						//setting spoon Type
						StringBuffer spoonTypes=new StringBuffer();
						int spoonLineTypes=countWordsUsingStringTokenizer(tempDesc,"Spoon size:");
						int spoonTypeCount=tempDesc.indexOf("Spoon size:")-1;
						for(int i=0;i<spoonLineTypes;i++) {
							if(tempDesc.indexOf("Spoon size:")>0) {
								if(tempDesc.indexOf("</li>",tempDesc.indexOf("Spoon size:",spoonTypeCount)+3)<0) {
									spoonTypes.append((i==0)?tempDesc.substring(tempDesc.indexOf("<b>",tempDesc.indexOf("Spoon size:",spoonTypeCount)+3)+3,(tempDesc.indexOf("poon",tempDesc.indexOf("<b>",tempDesc.indexOf("Spoon size:",spoonTypeCount)+3))-1)).trim()
											:"|"+tempDesc.substring(tempDesc.indexOf("<b>",tempDesc.indexOf("Spoon size:",spoonTypeCount)+3)+3,(tempDesc.indexOf("poon",tempDesc.indexOf("<b>",tempDesc.indexOf("Spoon size:",spoonTypeCount)+3))-1)).trim());
								}else {
									spoonTypes.append((i==0)?tempDesc.substring(tempDesc.indexOf(":",tempDesc.indexOf("Spoon size",spoonTypeCount))+1,(tempDesc.indexOf("poon",tempDesc.indexOf(":",tempDesc.indexOf("Spoon size",spoonTypeCount)))-1)).trim()
											:"|"+tempDesc.substring(tempDesc.indexOf(":",tempDesc.indexOf("Spoon size",spoonTypeCount))+1,(tempDesc.indexOf("poon",tempDesc.indexOf(":",tempDesc.indexOf("Spoon size",spoonTypeCount)))-1)).trim());
								}
							}else {
								spoonTypes.append(TicketConstants.TEA);
							}
							spoonTypeCount+=500;
						}
						tickets.setSpoonType((spoonTypes.length()>0)?spoonTypes.toString():TicketConstants.TEA);
						
						
						// Setting Customization
						StringBuffer customization=new StringBuffer();
						int spoonCount=countWordsUsingStringTokenizer(tempDesc,"Personalization:");
						int lineCount=tempDesc.indexOf("Personalization:")-1;
						for(int i=0;i<spoonCount;i++) {
							if(tempDesc.contains("Personalization:")) {
								String tempCustomization=(tempDesc.substring(tempDesc.indexOf("Personalization:",lineCount)+20,(tempDesc.indexOf("</b>",tempDesc.indexOf("Personalization:",lineCount)+20)))).trim().replaceAll("(\\t|\\r?\\n)+", " ");
								if(tempCustomization.contains("Not requested") && tempDesc.indexOf("poon")>0) {
									customization.append((i==0)?(tempDesc.substring(tempDesc.indexOf("<td style=\"\">",lineCount-400)+13,(tempDesc.indexOf("poon",tempDesc.indexOf("<td style=\"\">",lineCount-400)+13))-1)).trim()
											:"|"+(tempDesc.substring(tempDesc.indexOf("<td style=\"\">",lineCount-400)+13,(tempDesc.indexOf("poon",tempDesc.indexOf("<td style=\"\">",lineCount-400)+13))-1)).trim());
								}else if(tempDesc.indexOf("</li>",tempDesc.indexOf("Personalization:"))>0) {
									customization.append((i==0)?(tempDesc.substring(tempDesc.indexOf("Personalization:",lineCount)+20,(tempDesc.indexOf("</span>",tempDesc.indexOf("Personalization:",lineCount)+20)))).trim().replaceAll("(\\t|\\r?\\n)+", " ")
											:"|"+(tempDesc.substring(tempDesc.indexOf("Personalization:",lineCount)+20,(tempDesc.indexOf("</span>",tempDesc.indexOf("Personalization:",lineCount)+20)))).trim().replaceAll("(\\t|\\r?\\n)+", " "));
								}else {
									customization.append((i==0)?tempCustomization:"|"+tempCustomization);
								}
							}
							lineCount+=500;
						}
						tickets.setCustomization(customization.toString());


						// Setting Shipped To
						tickets.setShippedTo((tempDesc.substring(tempDesc.indexOf("<p>",tempDesc.indexOf("Shipping address"))+3,(tempDesc.indexOf("<br />",tempDesc.indexOf("<p>",tempDesc.indexOf("Shipping address"))+3)))).trim());

						// Setting Gift Message
						if(tempDesc.indexOf("Gift message")>0)
							tickets.setGiftMessage((tempDesc.substring(tempDesc.indexOf("<p>",tempDesc.indexOf("Gift message"))+3,(tempDesc.indexOf("</p>",tempDesc.indexOf("<p>",tempDesc.indexOf("Gift message"))+3)))).trim());

					}else if(values[4].trim().equalsIgnoreCase(TicketConstants.TARUNYA)) {
						// Setting Order Number
						if(values[8].startsWith("Order")) {
							tickets.setOrderNumber((values[8].substring(values[8].indexOf("Order")+5,(values[8].indexOf("-")))).trim());
						}else{
							tickets.setOrderNumber((values[8].substring(values[8].indexOf("Order")+5,(values[8].lastIndexOf("-")))).trim());
						}
						// Setting Quantity
						if(values[8].contains("Qty")) {
							tickets.setQuantity(Integer.parseInt((values[8].substring(values[8].indexOf("Qty")+3,(values[8].indexOf("-",values[8].indexOf("Qty")+3)))).trim()));
						}else {
							tickets.setQuantity(0);
						}

						// Setting Shipped To
						tickets.setShippedTo((tempDesc.substring(tempDesc.indexOf("<div><div>",tempDesc.indexOf("Delivery Address :"))+10,(tempDesc.indexOf("</div>",tempDesc.indexOf("<div><div>",tempDesc.indexOf("Delivery Address :"))+10)))).trim());

						// Setting Gift Message
						tickets.setGiftMessage(null);


						// Setting Customization
						if(tempDesc.indexOf("|")>0) {
							tickets.setCustomization((tempDesc.substring(tempDesc.indexOf("|")+1,(tempDesc.indexOf("|",tempDesc.indexOf("|")+1)))).trim());
						}else {
							tickets.setCustomization((tempDesc.substring(tempDesc.indexOf(":",tempDesc.indexOf("Product"))+1,(tempDesc.indexOf("spoon",tempDesc.indexOf(":",tempDesc.indexOf("Product"))))+5)).trim());
						}
						//setting spoon Type
						tickets.setSpoonType(TicketConstants.TEA);

					}else if(values[4].trim().equalsIgnoreCase(TicketConstants.VETRI)) {
						if(values[8].startsWith("Laurapartyshop")) {
							// Setting Order Number
							tickets.setOrderNumber((values[8].substring(values[8].lastIndexOf("-")+1)).trim());
							// Setting Quantity
							tickets.setQuantity(Integer.parseInt((values[8].substring(values[8].indexOf(":")+1,(values[8].indexOf("-")))).trim()));
							// Setting Shipped To
							tickets.setShippedTo((tempDesc.substring(tempDesc.indexOf("Shipping address:")+34,(tempDesc.indexOf("<br />",tempDesc.indexOf("Shipping address:")+34)))).trim());
							// Setting Gift Message
							tickets.setGiftMessage((tempDesc.substring(tempDesc.indexOf("Gift message")+29,(tempDesc.indexOf("</p>",tempDesc.indexOf("Gift message")+29)))).trim());
							// Setting Customization
							String tempCustomization=(tempDesc.substring(tempDesc.indexOf("Buyer message:")+31,(tempDesc.indexOf("</p>",tempDesc.indexOf("Buyer message:")+31)))).trim();
							tempCustomization.replaceAll("(\\t|\\r?\\n)+", " ");
							if(!isBlankOrEmpty(tempCustomization) && !"None".equals(tempCustomization) && tempCustomization.length()>10) {
								tickets.setCustomization(tempCustomization);
							}else if(tempDesc.contains("Personalization:")) {
								tickets.setCustomization((tempDesc.substring(tempDesc.indexOf("Personalization:")+20,(tempDesc.indexOf("</b>",tempDesc.indexOf("Personalization:")+20)))).trim().replaceAll("(\\t|\\r?\\n)+", " "));
							}else if(tempDesc.contains("</td><td style=\"\">")) {
								tempCustomization=(tempDesc.substring(tempDesc.indexOf("<td style=\"\">")+13,(tempDesc.indexOf("</td>",tempDesc.indexOf("<td style=\"\">")+13)))).trim();
								if(tempCustomization.contains("|")) {
									tickets.setCustomization(tempCustomization.substring(0,tempCustomization.indexOf("|")).replaceAll("(\\t|\\r?\\n)+", " "));
								}else {
									tickets.setCustomization((tempCustomization.indexOf("-")>0)?tempCustomization.substring(0,tempCustomization.indexOf("-")).replaceAll("(\\t|\\r?\\n)+", " "):tempCustomization);
								}
							}
							//setting spoon Type
							if(tempDesc.contains("Size:")) {
								String tempSize=(tempDesc.substring(tempDesc.indexOf("Size:")+9,(tempDesc.indexOf("</b>",tempDesc.indexOf("Size:")+9)))).trim();
								if(tempSize.contains("8.5")){
									tickets.setSpoonType(TicketConstants.TABLE);
								}else {
									tickets.setSpoonType(TicketConstants.TEA);
								}
							}else {
								tickets.setSpoonType(TicketConstants.TEA);
							}



						}else{
							// Setting Order Number
							tickets.setOrderNumber((values[8].substring(values[8].indexOf("#")+1,(values[8].indexOf("-",values[8].indexOf("#")+1)))).trim());
							// Setting Quantity
							tickets.setQuantity(Integer.parseInt((tempDesc.substring(tempDesc.indexOf("-")+1,(tempDesc.indexOf("<br />")))).trim()));

							// Setting Shipped To
							tickets.setShippedTo((tempDesc.substring(tempDesc.indexOf("name\">")+6,(tempDesc.indexOf("</span>",tempDesc.indexOf("name\">")+6)))).trim());

							// Setting Gift Message
							tickets.setGiftMessage(null);

							// Setting Customization
							if(tempDesc.contains("Personalisation:")) {
								tickets.setCustomization((tempDesc.substring(tempDesc.indexOf("Personalisation:")+57,(tempDesc.indexOf("</span>",tempDesc.indexOf("Personalisation:")+57)))).trim().replaceAll("(\\t|\\r?\\n)+", " "));
							}else {
								tickets.setCustomization(replaceNewLineWithSpace((tempDesc.substring(tempDesc.indexOf("title=")+7,(tempDesc.indexOf("-",tempDesc.indexOf("title=")+7)))).trim()));
							}
							//setting spoon Type
							tickets.setSpoonType(TicketConstants.TEA);

						}
					}
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
	public static boolean isBlankOrEmpty(Object obj){
		if(null==obj){
			return true;
		}else{
			String temp=obj.toString();
			if("".equals(temp.trim())){
				return true;
			}else{
				return false;
			}
		}
	}

	public static String replaceNewLineWithSpace(Object obj){
		if(null==obj){
			return null;
		}else{
			String temp=obj.toString();
			return temp.replaceAll("(\\t|\\r?\\n)+", " ");
		}
	}

	public static Date getDate(final String str, final String inputFormat)
			throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		return sdf.parse(str);
	}

	public static int countWordsUsingStringTokenizer(String sentence, String delemeter) {
		if (sentence == null || sentence.isEmpty()) {
		      return 0;
		    }

		    String[] words = sentence.split(delemeter);
		    return words.length-1;
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
			lines.add("ETSY");
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
			lines.add(ticket.getSpoonType());
			lines.add(ticket.getGiftMessage());
			data.add(lines);
		}

		return data;
	}

}

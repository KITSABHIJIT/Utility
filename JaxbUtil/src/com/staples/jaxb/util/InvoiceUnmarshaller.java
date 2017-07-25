package com.staples.jaxb.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.staples.jaxb.beans.Invoice;

public class InvoiceUnmarshaller {
	public static void main(String[] args) {

		 try {

			File file = new File("resources//EDI810.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Invoice invoice = (Invoice) jaxbUnmarshaller.unmarshal(file);
			
			System.out.println("   __________________________");
			System.out.println("__/       UnMarshalled      /");
			System.out.println("  \\_________________________\\");
			System.out.println(invoice.getHeader().getInvoiceHeader().getTotalList().getAmountList().getInvoice().getCurrency().getNmb());
			
			

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }

		}
}

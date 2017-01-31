package main.rkm;

import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;
import com.staples.kmc.StaplesKMClient;
import com.staples.kmc.StaplesKMInterface;
import com.staples.kmc.common.StaplesKMException;

public class RKMEngineSvc27{
	
	//private static final Logger LOG = LoggerFactory.getLogger(RKMEngineSvc27.class);
	private static final Logger LOG = Logger.getLogger(RKMEngineSvc27.class);
	public static StaplesKMInterface engineRKMcontext = null;
	public static StaplesKMClient engineRKMConfig = null;

	
	/**
	 * This method is used to initialize RKM
	 * @param configFile
	 * @throws StaplesKMException
	 */
	public boolean initializeRKM(String configFile) throws StaplesKMException {
		
		LOG.info("Config File Name-->" + configFile);
		//LOG.debug("Config File Name-->" + configFile);
		engineRKMConfig = StaplesKMClient.getInstance(configFile);
		engineRKMcontext = engineRKMConfig.newStaplesKMContext();
		
		boolean isRKMServerAvailable = engineRKMcontext.isServerAvailable();
		LOG.info("Is RKM server available? " + isRKMServerAvailable);
		//LOG.debug("Is RKM server available? " + engineRKMcontext.isServerAvailable());
		return isRKMServerAvailable;
	}

	
	/**
	 * This method is used to encrypt the data by RKM method.
	 * @param data
	 * @return
	 * @throws StaplesKMException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptData(String data) throws StaplesKMException,
	UnsupportedEncodingException {
		byte[] plainText = data.getBytes("UTF-8");
		String cipherText = engineRKMcontext.encryptBase64("VirtualTermKC", plainText); // TODO - should be differ for virtual terminal
		//LOG.debug("RKM encryptData--> " + cipherText);
		return cipherText;
	}
	

	/**
	 * This method is used to decrypt the data by RKM method.
	 * @param data
	 * @return
	 * @throws StaplesKMException
	 * @throws UnsupportedEncodingException
	 */
	public static String decryptData(String data) throws StaplesKMException,
	UnsupportedEncodingException {
		
		byte[] cipherText = engineRKMcontext.decryptBase64(data);
		return new String(cipherText);
	}
	
	

	/**
	 * This method is used to shutdown the RKM Engine
	 * @throws StaplesKMException
	 */
	public static void shutdown() throws StaplesKMException {
		engineRKMConfig.shutdown();
	}

	
	/**
	 * This method is used to Authenticate the Signature after getting from the response back from Authorization
	 * @param paymentResponse
	 * @param paymentAuthResponse
	 * @return
	 * @throws StaplesKMException
	 */
	public static boolean validateSignature(String paymentResponse, String paymentAuthResponse) throws StaplesKMException {
		boolean isSignatureValid = engineRKMcontext.verifySignedData(paymentResponse.getBytes(), paymentAuthResponse.getBytes());
		return isSignatureValid;
	}
	
	/*public boolean validateSignature(String paymentResponse, String paymentAuthResponse) throws StaplesKMException {
		boolean isSignatureValid = engineRKMcontext.verifySignedData(paymentResponse.getBytes(), paymentResponse.getBytes());
		return isSignatureValid;
	}*/
	
	
	
	
	
	
	
	@SuppressWarnings("static-access")
	public static void main(String arg[]) throws Exception{
		
		
		RKMEngineSvc27 rkm = new RKMEngineSvc27();
		rkm.initializeRKM("C:\\app\\kmc\\config\\virtualtermkc.cfg");
		//rkm.initializeRKM("C:\\app\\payserv\\edi\\config\\TestProd\\EdiCnfg.cfg");
		
		/*String encryptedSt = rkm.encryptData("HelloWorld");
		System.out.println(encryptedSt);
		
		System.out.println(decryptData(encryptedSt));*/

		
		/*String str ="NDg4NjE1NDI1ADz5dk4E5PeyG3VQ6PjselNV06xVFr1AByNnQgoRVRlv2ZlyIEfwm6kTh/Wtc9RZ4mtBoKi3uEZ04701zDkuXpmEmvahlQBx9B4tvxU2yKDP2vkyNHvmXzo8vw9BPnULwmT3BIvLHw+J80/KSG7DUVfcGt40X0wdCEgROWlT/KTufOymadLWxH4UjADPRzUpSW0YoCjk8R/XzmtiFuF1Qfm0pUwkxrqlDZf3kuBv8F91VYq2HW5w3RR5sIGwNswZulLJHoK2mx3mTIRmWYyS0/D0K5lZB0MIFgeHz8NG5XOG76nt7sN1CkEA93k6DGWS8uJjEQ1goRDww7oqmeV4XpwCzzV4eLaePuRJFG2xf2JfE+RQxz9ayoZd9RYnjGVaVvIF+Lci+yPZx7EOjGnS73Bn78dydxzhkaU3X7cxIZF3MjOojpygZ96S348W60R802BQjAiSRjD5jgV0pSYdaN/EdAi6dib2PK9LxFN/2crQ56yND+bAugrHEzGXR70e3fiWIA8d2VrqrRsFgecWk6PVIerwPqj6Lo2I1VFppdDy4OcQfrfG7FQKgUsDD8twMV9HSKvtmGbsqQM7jVSG352Smvfp201X8vkR5tQa+dW15X+4V51VpGsvRV6HQE8HPT/5YktFe4uKpJDoQo4/jevAw9+blsUtwuLRzPbXm+glUNG5JVBdckhAZWexOOiptbdGBfDt9xub0dsIss8SJpL1jTqiaByPnT+L8RuUyP1f1tn+D4szvVcYXSNrP3JB+d8+AzdXDg7xnK5Z9SQkxfID1gH11SgwcnAzOjIP2mizMXadblYiy3NkYxO6zPoh+c9UP395h0/3UaiPiERucd38haULRAItzwWls051CywcmSYmMPX2h9pHqsYvaDDJQh9M+qvwSczd970eHOli0D4SKAdOABra/HdrhJqdBHQ7t2YEN+y8ycV7jsGDvKfJPdpjkEX5RVR5ab/i38q5pTMz0wxnD1KDV0Hz2BmdzyKHfuC1/45HV0cSCrHQBK2/hYTfuC6Xu6prCjAqWDzvBTg=";
		System.out.println(decryptData(str));*/
		
		
		String str ="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";
		System.out.println(decryptData(str));
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    

		
		
		rkm.shutdown();
		LOG.info("Exiting from RKM service class");
	}
}

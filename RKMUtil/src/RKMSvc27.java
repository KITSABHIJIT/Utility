

import org.apache.log4j.Logger;

import com.rsa.kmclient.KMSException;
import com.staples.kmc.StaplesHeaderFormatEnum;
import com.staples.kmc.StaplesKMClient;
import com.staples.kmc.StaplesKMInterface;
import com.staples.kmc.common.StaplesKMException;

public class RKMSvc27 {
	public static StaplesKMInterface context = null;
	public static StaplesKMClient kmConfig = null;
	private static String[] keyClassArray;
	private static String[] keyClassHMACArray;
	static Logger log4j = Logger.getLogger(RKMSvc27.class.getName());


	public void initializeRKM(String configFile) throws StaplesKMException {

		log4j.debug("Config File Name: " + configFile);
		log4j.debug("Config File Name: " + configFile);
		String keyclasses = ConfigProperties.RKM_VIEW_KEYCLASSES;
		log4j.debug(keyclasses);
		if (keyclasses == null || keyclasses.trim().equals("")) {
			log4j.error("RKMSvc: RKM initialization failed, check RKM keyclasses");
		}

		kmConfig = StaplesKMClient.getInstance(configFile);

		context = kmConfig.newStaplesKMContext();
		//context.initFPEClient("GpasFpeHMACLog");
		//context.initFPEClient("HMACKeyClass2");
		context.initFPEClient(ConfigProperties.FPEKEY); // LOS Implementation
		// 27/02/2015[START]
		log4j.debug("" + context.isServerAvailable());

		String HMackeyclasses = ConfigProperties.RKM_VIEW_HMAC_KEYCLASSES;
		// log4j.debug(HMackeyclasses);
		if (HMackeyclasses == null || HMackeyclasses.trim().equals("")) {
			log4j.debug("RKMSvc: RKM initialization failed, check RKM HMackeyclasses");
		}
		keyClassArray = keyclasses.split(",");
		// log4j.debug(keyClassArray);
		keyClassHMACArray = HMackeyclasses.split(",");

	}

	public String decryptDataByView(String encryptedData, String sView)
			throws StaplesKMException {
		log4j.error("Corrected encrypted Input: " + encryptedData);
		byte[] recoveredText = context.decryptBase64(encryptedData.trim());
		log4j.error("recoveredText : " + recoveredText);
		return new String(recoveredText);
	}

	public String encryptDataByView(String encryptedData, String sView)
			throws Exception {
		byte[] plainText = encryptedData.getBytes("UTF-8");
		String cipherText = context.encryptBase64(sView, plainText);
		// byte[] recoveredText = context.decryptBase64(cipherText);
		log4j.debug("encryptedSting : "+cipherText);
		return cipherText;
	}

	private static String getKeyClassByView(String sView) throws Exception {
		int length = keyClassArray.length;
		if (sView == null || sView.trim().equals("")) {
			log4j.debug("Unable to get corresponding key class for view: " + sView);
		}
		int view = 1;
		try {
			view = Integer.parseInt(sView);
		} catch (NumberFormatException ex) {
			log4j.error("Unable to get corresponding key class for view: " + sView);
		}
		if (view > length) {
			log4j.debug("Unable to get corresponding key class for view: " + sView);
		}
		return (keyClassArray[view - 1]);
	}

	private static String getHmacKeyClassByView(String sView)
			throws Exception {
		int length = keyClassHMACArray.length;
		// log4j.debug(sView+"----------------"+length);
		/*
		 * for ( int i=1; i < length ; i++) {
		 * log4j.debug(i+" = "+keyClassHMACArray[i - 1]); }
		 */
		if (sView == null || sView.trim().equals("")) {
			log4j.debug("Unable to get corresponding key class for view: " + sView);
		}
		int view = 1;
		try {
			view = Integer.parseInt(sView);
		} catch (NumberFormatException ex) {
			log4j.debug("Unable to get corresponding key class for view: " + sView);
		}
		if (view > length) {
			log4j.error("Unable to get corresponding key class for view: " + sView);
		}
		return (keyClassHMACArray[view - 1]);
	}

	public static String HMACData(String signature, String sView)
			throws KMSException, Exception {
		long t1 = System.currentTimeMillis();
		String keyClass = getHmacKeyClassByView(sView);
		// log4j.debug("Key Class Chosen : "+keyClass);
		// log4j.debug("Signature : "+signature);
		long t2 = System.currentTimeMillis();
		byte[] inputData = signature.getBytes();
		/*
		 * byte[] requestSignature = null; try { requestSignature =
		 * context.computeMAC(keyClass, inputData,
		 * StaplesHeaderFormatEnum.VERSION_1_5);
		 * 
		 * } catch (StaplesKMException e) {
		 * ProxyLogger.errln("Error in HMACing signature."); }
		 * sun.misc.BASE64Encoder b64 = new sun.misc.BASE64Encoder(); String
		 * b64ed = b64.encode(requestSignature);
		 * 
		 * 
		 * t2 = System.currentTimeMillis();
		 * 
		 * log4j.debug("Request Signature byte array : "+requestSignature
		 * );
		 */
		String b64ed = "";
		try {
			b64ed = context.computeMACBase64(keyClass, inputData,
					StaplesHeaderFormatEnum.VERSION_1_5);
		} catch (StaplesKMException ex) {
			log4j.debug("Exception in Computing HMAC " + keyClass);
		}
		log4j.debug("Request String : " + b64ed);
		log4j.debug("RKM Decryption in " + (t2 - t1));
		return (b64ed);
	}

	public void shutdown() throws StaplesKMException {
		kmConfig.shutdown();
	}

	// LOS Implementation 27/02/2015 [START]
	/**
	 * This method returns losdecrypt value
	 * 
	 * @throws StaplesKMException
	 */
	public String losDecrypt(String losDecrypt) {
		try {
			return context.losDecrypt(losDecrypt);
		} catch (StaplesKMException ex) {
			try {
				log4j.debug("Error doing LOS Decrypt");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * This method returns encrypt value
	 * 
	 * @throws StaplesKMException
	 */
	public String losEncrypt(String losEcrypt) {
		try {
			return context.losEncrypt(losEcrypt);
		} catch (StaplesKMException ex) {
			try {
				log4j.debug("Error doing LOS Decrypt");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}

	// LOS Implementation [END]

	public static void main(String arg[]){
		try{
			RKMSvc27 rkm = new RKMSvc27();
			if(arg.length<3){
				System.out.println("Invalid Input.. Please enter data : \n"
						+ "1st Input: auth/settle\n"
						+ "2nd Input: 1(Encryption)/2(Decryption)/3(HMAC Data)\n"
						+ "3rd Input: View\n"
						+ "copy String in Data.txt");
			}else{
				ConfigProperties.initProperties();
				if("auth".equals(arg[0])){
					rkm.initializeRKM(ConfigProperties.RKM_AUTH_CONFIG_PATH);
				}else{
					rkm.initializeRKM(ConfigProperties.RKM_SETTLE_CONFIG_PATH);
				}
				String dataString=FileManager.getData(ConfigProperties.DATA_FILE);
				if("1".equals(arg[1])){
					log4j.debug("Data to Encrypt: "+dataString+ " with view: "+arg[2]);
					System.out.println("Data to Encrypt: "+dataString+ " with view: "+arg[2]);
					String keyClass="";
					if(arg[2].length()>2){
						keyClass=arg[2];
					}else{
						keyClass=getKeyClassByView(arg[2]);
					}
					log4j.debug("Keyclass used for encryption: "+keyClass);
					System.out.println("Keyclass used for encryption: "+keyClass);
					String encryptedData=rkm.encryptDataByView(dataString, keyClass);
					log4j.debug("Encrypted data: "+encryptedData);
					System.out.println("Encrypted data: "+encryptedData);
				
				}else if("2".equals(arg[1])){
					log4j.debug("Data to Decrypt: "+dataString+ " with view: "+arg[2]);
					System.out.println("Data to Decrypt: "+dataString+ " with view: "+arg[2]);
					String decryptedData=rkm.decryptDataByView(dataString, arg[2]);
					log4j.debug("Decrypted data: "+decryptedData);
					System.out.println("Decrypted data: "+decryptedData);
				}else if("3".equals(arg[1])){
					log4j.debug("Data for HMAC: "+dataString+ " with view: "+arg[2]);
					System.out.println("Data for HMAC: "+dataString+ " with view: "+arg[2]);
					String HMACData=RKMSvc27.HMACData(dataString, arg[2]);
					log4j.debug("HMAC data: "+HMACData);
					System.out.println("HMAC data: "+HMACData);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}

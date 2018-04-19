
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.rsa.dps.common.exception.DPSException;

/**
 * FileName: ProxyProperties.java
 * 
 * @author gupma001 Date: Feb 25, 2009 Description:
 * 
 */
public class ConfigProperties {
	private static Properties props = null;
	private static File configFile;
	static Logger log4j = Logger.getLogger(ConfigProperties.class.getName());
	public static String 
			RKM_VIEW_HMAC_KEYCLASSES,
			RKM_SETTLE_CONFIG_PATH,
			RKM_AUTH_CONFIG_PATH,
			FPEKEY,
			RKM_VIEW_KEYCLASSES,
			DATA_FILE;

	public static void initProperties() throws IOException,
			NoSuchAlgorithmException, DPSException {

		configFile = new File("config/config.properties");
		InputStream in = new FileInputStream(configFile);
		props = new Properties();
		props.load(in);


		RKM_SETTLE_CONFIG_PATH = loadPropertyAndLog("RKM_SETTLE_CONFIG_PATH");
		RKM_AUTH_CONFIG_PATH = loadPropertyAndLog("RKM_AUTH_CONFIG_PATH");
		RKM_VIEW_KEYCLASSES = loadPropertyAndLog("RKM_VIEW_KEYCLASSES");
		RKM_VIEW_HMAC_KEYCLASSES = loadPropertyAndLog("RKM_VIEW_HMAC_KEYCLASSES");
		FPEKEY = loadProperty("FPEKEY");
		DATA_FILE = loadPropertyAndLog("DATA_FILE");
		in.close();
	}

	private static String loadProperty(String propertyName) {
		String str = props.getProperty(propertyName);
		str = str == null ? null : str.trim();
		return (str);
	}

	private static String loadPropertyAndLog(String propertyName) {
		String str = props.getProperty(propertyName);
		str = str == null ? null : str.trim();
		log4j.debug("--" + propertyName + "= " + str);
		return (str);
	}
}
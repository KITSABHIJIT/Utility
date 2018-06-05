package com.test.code;

import java.util.List;

public class InstallJartoMaven {
	private static String FILE_MAPPING="config/jarMapping.config";
	public static void main(String ...strings ) {
		List<String> mappings=ProcessUtil.readFromFile(FILE_MAPPING);
		String mavenCommand=PropertiesUtil.getProperty("maven_install");
		for(String maping : mappings) {
			ProcessUtil.downloadFile(maping);
			ProcessUtil.runMavenCommand(maping, mavenCommand);
			ProcessUtil.deleteFile(maping);
		}
		
	}
}

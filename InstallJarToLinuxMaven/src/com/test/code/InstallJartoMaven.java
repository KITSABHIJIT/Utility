package com.test.code;

import java.util.List;

public class InstallJartoMaven {
	private static String FILE_MAPPING="jarMapping.config";
	private static String SCRIPT_FILE="jarMapping.sh";
	public static void main(String ...strings ) {
		List<String> mappings=ProcessUtil.readFromFile(FILE_MAPPING);
		String mavenCommand=PropertiesUtil.getProperty("maven_install");
		String deleteCommand=PropertiesUtil.getProperty("delete");
		StringBuffer scriptData =new StringBuffer(PropertiesUtil.getProperty("export_path"));
		for(String maping : mappings) {
			ProcessUtil.downloadFile(maping);
			scriptData.append("\n").append(ProcessUtil.createUnixCommand(maping, mavenCommand));
			scriptData.append("\n").append(ProcessUtil.createDeleteCommand(maping, deleteCommand));
		}
		ProcessUtil.writeToFile(scriptData.toString(),SCRIPT_FILE);
		
	}
}

package com.test.code;

import java.util.List;

public class InstallJartoMaven {
	private static String FILE_MAPPING="jarMapping.config";
	private static String SCRIPT_FILE="jarMapping.sh";
	private static String HTML_FILE="jarMapping.html";
	public static void main(String ...strings ) {
		List<String> mappings=ProcessUtil.readFromFile(FILE_MAPPING);
		String mavenCommand=PropertiesUtil.getProperty("maven_install");
		String deleteCommand=PropertiesUtil.getProperty("delete");
		StringBuffer scriptData =new StringBuffer(PropertiesUtil.getProperty("export_path"));
		String mavenDependencyCode=PropertiesUtil.getProperty("maven_dependency_code");
		StringBuffer htmlData =new StringBuffer();
		for(String maping : mappings) {
			ProcessUtil.downloadResource(maping);
			scriptData.append("\n").append(ProcessUtil.createUnixCommand(maping, mavenCommand));
			htmlData.append(ProcessUtil.generateHtmlData(maping,mavenDependencyCode));
		}
		for(String maping : mappings) {
			scriptData.append("\n").append(ProcessUtil.createDeleteCommand(maping, deleteCommand));
			scriptData.append("\n").append(ProcessUtil.createDeletePOMCommand(maping, deleteCommand));
		}
		ProcessUtil.writeToFile(scriptData.toString(),SCRIPT_FILE);
		ProcessUtil.generateHtmlFile(HTML_FILE,htmlData.toString());
		
	}
}

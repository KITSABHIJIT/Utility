package com.test.code;

import java.util.List;

public class InstallJartoMaven {
	private static String FILE_MAPPING="config/jarMapping.config";
	private static String HTML_FILE="html/jarMapping.html";
	public static void main(String ...strings ) {
		List<String> mappings=ProcessUtil.readFromFile(FILE_MAPPING);
		String mavenCommand=PropertiesUtil.getProperty("maven_install");
		String mavenDependency=PropertiesUtil.getProperty("maven_dependency");
		String mavenDependencyCode=PropertiesUtil.getProperty("maven_dependency_code");
		StringBuffer htmlData =new StringBuffer();
		for(String maping : mappings) {
			ProcessUtil.downloadFile(maping);
			ProcessUtil.runMavenCommand(maping, mavenCommand,mavenDependency);
			ProcessUtil.deleteFile(maping);
			htmlData.append(ProcessUtil.generateHtmlData(maping,mavenDependencyCode));
		}
		ProcessUtil.generateHtmlFile(HTML_FILE,htmlData.toString());
		
	}
}

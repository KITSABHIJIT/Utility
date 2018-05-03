package com.staples.jenkins.build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PushBuild {
	public static void main(String ...strings) {
		if(strings.length<6) {
			System.out.println("****************Invalid parameter provided****************");
			System.exit(1);
		}else {
			BuildLog buildLog=new BuildLog();
			final String uuid = UUID.randomUUID().toString().replace("-", "");
			buildLog.setBuildId(uuid);
			buildLog.setBuildTag(strings[0]);
			buildLog.setBuildUrl(strings[1]);
			buildLog.setGitCommit(strings[2]);
			buildLog.setGitBranch(strings[3]);
			buildLog.setAs400JobName(strings[4]);
			buildLog.setAs400IfsPath(strings[5]);
			int inserted = ConnectionUtil.insertBuildData(buildLog);
			if(inserted>0) {
				System.out.println("****************Build Triggered****************");
				System.out.println("Build Application: "+buildLog.getBuildTag());
				System.out.println("Build Date Time: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z").format(new Date()));	
				System.out.println(buildLog.toString());	
			}
			System.exit(0);
		}
	}
}

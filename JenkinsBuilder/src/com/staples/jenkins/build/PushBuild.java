package com.staples.jenkins.build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PushBuild {
	public static DataQueueConfig config;
	public static void main(String ...strings) {
		int inserted =0;
		if(strings.length<6) {
			System.out.println("********************************************************");
			System.out.println("*             Invalid parameter provided               *");
			System.out.println("********************************************************");
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
			if(ConnectionUtil.updateCaRule(buildLog.getAs400JobName(),"0")>0) {
				System.out.println("******************************************************************");
				System.out.println("CARULE RULSTS updated to 0 for "+buildLog.getAs400JobName()+" to post the kill message to CA_CMD_Q");
				System.out.println("******************************************************************");
			}
			try {
				System.out.println("******************************************************************");
				System.out.println("                  Waiting "+(Long.parseLong(PropertiesUtil.getProperty("updateDelay"))/1000)+" seconds ...                           ");
				System.out.println("******************************************************************");
				Thread.sleep(Long.parseLong(PropertiesUtil.getProperty("updateDelay")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ConnectionUtil.updateCaRule(buildLog.getAs400JobName(),"1")>0) {
				System.out.println("********************************************************");
				System.out.println("CARULE RULSTS updated back to 1 for "+buildLog.getAs400JobName());
				System.out.println("********************************************************");
			}
			//inserted = ConnectionUtil.insertBuildData(buildLog);


			/*CaRule caRule=ConnectionUtil.getCaRuleData(buildLog.getAs400JobName());
			if(!StringUtil.isBlankOrEmpty(caRule.getControlProgram())) {
				try {
					config=new DataQueueConfig();
					String dataQueueMessage=config.buildJobData(buildLog, caRule);
					System.out.println("Data Queue Message: "+dataQueueMessage);
					config.sendData(dataQueueMessage);
				} catch (Exception e) {
					System.out.println("********************************************************");
					System.out.println(" Exception occured while posting message to Data Queue  ");
					System.out.println(e.getMessage());
					System.out.println("********************************************************");
					System.exit(1);
				}

			}else {
				System.out.println("********************************************************");
				System.out.println("*             CARULE Config Unavailable                *");
				System.out.println("*             AS400 Job will not restart               *");
				System.out.println("********************************************************");
				System.exit(1);
			}*/
			if(inserted>0) {
				System.out.println("*********************************************************************************");
				System.out.println("                                Build Triggered                                  ");
				System.out.println("Build Application: "+buildLog.getBuildTag());
				System.out.println("Build Date Time: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z").format(new Date()));	
				System.out.println(buildLog.toString());	
				System.out.println("*********************************************************************************");
			}
			System.exit(0);
		}
	}
}

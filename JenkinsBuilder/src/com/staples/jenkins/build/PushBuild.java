package com.staples.jenkins.build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushBuild {
	//public static DataQueueConfig config;
	private final static Logger logger = LoggerFactory.getLogger(PushBuild.class);
	public static void main(String ...strings) {
		int inserted =0;
		if(strings.length<6) {
			logger.debug("********************************************************");
			logger.debug("*             Invalid parameter provided               *");
			logger.debug("********************************************************");
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
			buildLog.setBuildDate(Long.parseLong(StringUtil.getCurrentDate("yyyyMMdd")));
			buildLog.setBuildTime(Long.parseLong(StringUtil.getCurrentDate("HHmmss")));
			if(ConnectionUtil.updateCaRule(buildLog.getAs400JobName(),"0")>0) {
				logger.debug("******************************************************************");
				logger.debug("CARULE RULSTS updated to 0 for "+buildLog.getAs400JobName()+" to post the kill message to CA_CMD_Q");
				logger.debug("******************************************************************");
			}
			try {
				logger.debug("******************************************************************");
				logger.debug("                  Waiting "+(Long.parseLong(PropertiesUtil.getProperty("updateDelay"))/1000)+" seconds ...                           ");
				logger.debug("******************************************************************");
				Thread.sleep(Long.parseLong(PropertiesUtil.getProperty("updateDelay")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(ConnectionUtil.updateCaRule(buildLog.getAs400JobName(),"1")>0) {
				logger.debug("********************************************************");
				logger.debug("CARULE RULSTS updated back to 1 for "+buildLog.getAs400JobName());
				logger.debug("********************************************************");
			}
			inserted = ConnectionUtil.insertBuildData(buildLog);


			/*CaRule caRule=ConnectionUtil.getCaRuleData(buildLog.getAs400JobName());
			if(!StringUtil.isBlankOrEmpty(caRule.getControlProgram())) {
				try {
					config=new DataQueueConfig();
					String dataQueueMessage=config.buildJobData(buildLog, caRule);
					logger.debug("Data Queue Message: "+dataQueueMessage);
					config.sendData(dataQueueMessage);
				} catch (Exception e) {
					logger.debug("********************************************************");
					logger.debug(" Exception occured while posting message to Data Queue  ");
					logger.debug(e.getMessage());
					logger.debug("********************************************************");
					System.exit(1);
				}

			}else {
				logger.debug("********************************************************");
				logger.debug("*             CARULE Config Unavailable                *");
				logger.debug("*             AS400 Job will not restart               *");
				logger.debug("********************************************************");
				System.exit(1);
			}*/
			if(inserted>0) {
				logger.debug("*********************************************************************************");
				logger.debug("                                Build Triggered                                  ");
				logger.debug("Build Application: "+buildLog.getBuildTag());
				logger.debug("Build Date Time: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z").format(new Date()));	
				logger.debug(buildLog.toString());	
				logger.debug("*********************************************************************************");
			}
			System.exit(0);
		}
	}
}

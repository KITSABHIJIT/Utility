package com.staples.kafka.log.pojo;

import java.util.List;

public class LogFile {
	private String applicationID;
	private String jobName;
	private String logLevel;
	private String logfilePath;
	private String rolledOutLogfilePath;
	private String kafkaTopic;
	private Boolean isAync;
	private List<String> excludedLog;
	
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getLogfilePath() {
		return logfilePath;
	}
	public void setLogfilePath(String logfilePath) {
		this.logfilePath = logfilePath;
	}
	public String getRolledOutLogfilePath() {
		return rolledOutLogfilePath;
	}
	public void setRolledOutLogfilePath(String rolledOutLogfilePath) {
		this.rolledOutLogfilePath = rolledOutLogfilePath;
	}
	
	public String getKafkaTopic() {
		return kafkaTopic;
	}
	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}
	public Boolean getIsAync() {
		return isAync;
	}
	public void setIsAync(Boolean isAync) {
		this.isAync = isAync;
	}
	public List<String> getExcludedLog() {
		return excludedLog;
	}
	public void setExcludedLog(List<String> excludedLog) {
		this.excludedLog = excludedLog;
	}
	@Override
	public String toString() {
		return "LogFile [applicationID=" + applicationID + ", jobName=" + jobName + ", logLevel=" + logLevel
				+ ", logfilePath=" + logfilePath + ", rolledOutLogfilePath=" + rolledOutLogfilePath + ", kafkaTopic="
				+ kafkaTopic + ", isAync=" + isAync + ", excludedLog=" + excludedLog + "]";
	}
	
}

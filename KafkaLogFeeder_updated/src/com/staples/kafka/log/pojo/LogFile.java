package com.staples.kafka.log.pojo;

public class LogFile {
	private String applicationID;
	private String logfilePath;
	private String rolledOutLogfilePath;
	private String kafkaTopic;
	private Boolean isAync;
	
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
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
	@Override
	public String toString() {
		return "LogFile [applicationID=" + applicationID + ", logfilePath=" + logfilePath + ", rolledOutLogfilePath="
				+ rolledOutLogfilePath + ", kafkaTopic=" + kafkaTopic + ", isAync=" + isAync + "]";
	}
	
}

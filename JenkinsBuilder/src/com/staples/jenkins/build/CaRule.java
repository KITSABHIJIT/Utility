package com.staples.jenkins.build;

public class CaRule {
	private String programName;
	private double maxInstance;
	private String status;
	private String jobD;
	private String jobName;
	private String controlProgram;
	private String delay;
	private long priority;
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public double getMaxInstance() {
		return maxInstance;
	}
	public void setMaxInstance(double maxInstance) {
		this.maxInstance = maxInstance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJobD() {
		return jobD;
	}
	public void setJobD(String jobD) {
		this.jobD = jobD;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getControlProgram() {
		return controlProgram;
	}
	public void setControlProgram(String controlProgram) {
		this.controlProgram = controlProgram;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public long getPriority() {
		return priority;
	}
	public void setPriority(long priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "CaRule [programName=" + programName + ", maxInstance=" + maxInstance + ", status=" + status + ", jobD="
				+ jobD + ", jobName=" + jobName + ", controlProgram=" + controlProgram + ", delay=" + delay
				+ ", priority=" + priority + "]";
	}
	
		
}

package com.staples.jenkins.build;

public class BuildLog {
	private String buildId;
	private String buildTag;
	private String buildUrl;
	private String gitCommit;
	private String gitBranch;
	private String as400JobName;
	private String as400IfsPath;
	private long buildDate;
	private long buildTime;
	
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getBuildTag() {
		return buildTag;
	}
	public void setBuildTag(String buildTag) {
		this.buildTag = buildTag;
	}
	public String getBuildUrl() {
		return buildUrl;
	}
	public void setBuildUrl(String buildUrl) {
		this.buildUrl = buildUrl;
	}
	public String getGitCommit() {
		return gitCommit;
	}
	public void setGitCommit(String gitCommit) {
		this.gitCommit = gitCommit;
	}
	public String getGitBranch() {
		return gitBranch;
	}
	public void setGitBranch(String gitBranch) {
		this.gitBranch = gitBranch;
	}
	public String getAs400JobName() {
		return as400JobName;
	}
	public void setAs400JobName(String as400JobName) {
		this.as400JobName = as400JobName;
	}
	public String getAs400IfsPath() {
		return as400IfsPath;
	}
	public void setAs400IfsPath(String as400IfsPath) {
		this.as400IfsPath = as400IfsPath;
	}
	public long getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(long buildDate) {
		this.buildDate = buildDate;
	}
	public long getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(long buildTime) {
		this.buildTime = buildTime;
	}
	@Override
	public String toString() {
		return "BuildLog [buildId=" + buildId + ", buildTag=" + buildTag + ", buildUrl=" + buildUrl + ", gitCommit="
				+ gitCommit + ", gitBranch=" + gitBranch + ", as400JobName=" + as400JobName + ", as400IfsPath="
				+ as400IfsPath + ", buildDate=" + buildDate + ", buildTime=" + buildTime + "]";
	}
	
	
}

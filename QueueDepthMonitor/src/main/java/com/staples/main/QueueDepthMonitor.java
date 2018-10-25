package com.staples.main;

import java.util.Timer;

import com.staples.util.MQConfigureUtil;
import com.staples.util.PropertiesUtil;

public class QueueDepthMonitor{
	public static void main(String ... args) throws InterruptedException {
		PropertiesUtil.loadProperties();
		PropertiesUtil.loadMqDetails();
		MQConfigureUtil.initializeMQ();
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
		time.schedule(st, 0, Long.parseLong(PropertiesUtil.getProperty("jobRunFrequency"))); // Create Repetitively task for every jobRunFrequency millisecs
	}
}

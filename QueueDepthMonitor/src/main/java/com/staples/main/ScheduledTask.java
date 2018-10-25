package com.staples.main;

import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.staples.util.MQConfigureUtil;
import com.staples.util.PropertiesUtil;

public class ScheduledTask  extends TimerTask {
	private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);
	private static StringBuffer buffer=new StringBuffer();
	// Added our task here
	public void run() {
		for(MQDetail  mqDetail: PropertiesUtil.getMQDetails()) {
			String alert=MQConfigureUtil.checkMQDepth(mqDetail);
			buffer.append((null!=alert)?alert:"");
		}
		if(buffer.length()>0) {
			//LOG.error(buffer.toString());
			buffer.setLength(0);
		}
	}


}

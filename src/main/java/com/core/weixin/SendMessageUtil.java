package com.core.weixin;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class SendMessageUtil {
	
	public void sendMessageByTask(){
		Timer timer = new Timer(); 
	    timer.schedule(new SendTask(), 10 * 1000);
	}
	
	public void sendMessageByTask(Date time){
		Timer timer = new Timer(); 
	    timer.schedule(new SendTask(), time);
	}
}

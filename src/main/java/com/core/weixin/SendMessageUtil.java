package com.citi.test;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class SendMessageUtil {
	
	private String type;
	private Data data;
	private List<String> tousers;
	private SendTask sendTask;
	private Timer timer;
	
	public SendMessageUtil(String type, Data data, List<String> tousers) {
		this.type = type;
		this.data = data;
		this.tousers = tousers;
		init();
	}
	
	public void init(){
		sendTask = new SendTask();
		timer = new Timer();
		sendTask.getSendMessage(type, data, tousers);
	}

	public void sendMessageByTask(){
	    timer.schedule(sendTask, 10 * 1000);
	}
	
	public void sendMessageByTask(Date time){
	    timer.schedule(sendTask, time);
	}
}

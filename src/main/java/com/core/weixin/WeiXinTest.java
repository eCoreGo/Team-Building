package com.core.weixin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import net.sf.json.JSONObject;

public class WeiXinTest {
	public static void main(String[] args) {
		WeiXinUtil weiXinUtil = new WeiXinUtil();
		AccessToken accessToken = weiXinUtil.getAccessToken();
		String menu = JSONObject.fromObject(weiXinUtil.initMenu()).toString();
		//create menu
		int result = weiXinUtil.createMenu(accessToken.getAccess_token(), menu);
		
		//推送格式，'#173177'字体颜色
		//新建活动
		Data data1 = new Data(
			new Vad("title", "#173177"),
			new Vad("活动名称", "#173177"),
			new Vad("活动时间", "#173177"),
			new Vad("组织者", "#173177"),
			new Vad("结束语", "#173177")
			);
		//活动通知
		Data data2 = new Data(
				new Vad("title", "#173177"),
				new Vad("活动名称", "#173177"),
				new Vad("活动开始时间", "#173177"),
				new Vad("同车人群", "#173177"),
				new Vad("结束语", "#173177")
				);
		//消息通知
		Data data3 = new Data(
				new Vad("title", "#173177"),
				new Vad("活动名称", "#173177"),
				new Vad("消 息", "#173177"),
				new Vad("结束语", "#173177")
				);
		//活动报销
		Data data4 = new Data(
				new Vad("title", "#173177"),
				new Vad("活动名称", "#173177"),
				new Vad("消费总金额", "#173177"),
				new Vad("我的消费", "#173177"),
				new Vad("结束语", "#173177")
				);
		//要推送的人
		List<String> tousers = new ArrayList<String>();
		tousers.add("oi1ght-ntzae0WXIiYhLLBcHalq0");
		tousers.add("oi1ght-E7fjJy-DCBOgg3MHjPkiI");
		tousers.add("oi1ghtwu4Vq7gYOfADjOC__RxkPQ");
		tousers.add("oi1ght45-KEcgeqnr5dPyMNc23Fs");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		try {
			time = sdf.parse("2016-04-09 11:45:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//推送类型
		//create_activity  --> 新建活动
		//activity_info  --> 活动通知
		//information  --> 消息通知
		//activity  --> 活动报销
		SendMessageUtil sendMessageUtil = new SendMessageUtil("create_activity", data1, tousers);
		//定时推送
		//sendMessageUtil.sendMessageByTask(time);
		//即时推送
		sendMessageUtil.sendMessageByTask();
		if(result == 0){
			System.out.println("create successful");
		}else{
			System.out.println(result);
		}
	}
}

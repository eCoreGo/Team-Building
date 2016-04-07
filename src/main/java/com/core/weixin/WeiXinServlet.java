package com.core.weixin;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.citi.test.Articles;
import com.citi.test.Item;
import com.citi.test.MessageUtil;
import com.citi.test.NewsMessage;
import com.citi.test.TextMessage;
import com.core.bean.Member;
import com.core.service.MemberService;

import net.sf.json.JSONObject;

public class WeiXinServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		// TOKEN ������΢��ƽ̨����ģʽ�����õ�Ŷ
		public static final String TOKEN = "teambuilding";

		/**
		 * ����΢�ŷ�������֤
		 * 
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String signature = "56f79458a1bfb1164f6c9831550c89ddf6257c90";
			String timestamp = "1457964881";
			String nonce = "983366937";
			String echostr = "7965823133216669681";
			
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 2621444383666420433L;

				public String toString() {
					return this.get(0) + this.get(1) + this.get(2);
				}
			};
			list.add(TOKEN);
			list.add(timestamp);
			list.add(nonce);
			Collections.sort(list);
			System.out.println(list.toString());
			String tmpStr = hex(list.toString());
			Writer out = response.getWriter();
			if (signature.equals(tmpStr)) {
				out.write(echostr);
			} else {
				out.write("");
			}
			out.flush();
			out.close();
			
			
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter out = response.getWriter();
			
			try{
				Map<String,String> map = MessageUtil.xmlToMap(request);
				String fromUserName = map.get("FromUserName");
				String toUserName = map.get("ToUserName");
				String msgType = map.get("MsgType");
				String content = map.get("Content");
				
				String message = null;
				
				if("text".equals(msgType)){
					TextMessage text = new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime()+"");
					text.setContent("请按菜单项进行选择");
					message = MessageUtil.textMessageToXML(text);
				}
				else if("event".equals(msgType)){
					String eventType = map.get("Event");
					if("CLICK".equals(eventType)){
						String eventKey = map.get("EventKey");
						NewsMessage news = new NewsMessage();
						news.setFromUserName(toUserName);
						news.setToUserName(fromUserName);
						news.setMsgType("news");
						news.setCreateTime(new Date().getTime()+"");
						news.setMsgType("news");
						news.setArticleCount(1);
						Articles articles = new Articles();
						Item item1 = new Item();
						//
						if("activtes".equals(eventKey.trim())){
							item1.setTitle("活动");
							item1.setDescription("发布群组里的各种活动");
							item1.setPicUrl("http://cms.yl-blog.com/themes/blue/images/logo.png");
							item1.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/");
						}
						if("group".equals(eventKey.trim())){
							item1.setTitle("群组");
							item1.setDescription("查看个人群组信息");
							item1.setPicUrl("http://cms.yl-blog.com/themes/blue/images/logo.png");
							item1.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/");
						}
						if("aboutMe".equals(eventKey.trim())){
							item1.setTitle("我的");
							item1.setDescription("个人信息");
							item1.setPicUrl("http://cms.yl-blog.com/themes/blue/images/logo.png");
							item1.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/");
						}
						articles.setItem(item1);
						news.setArticles(articles );
						message = MessageUtil.newsMessageToXML(news);
					}
					else if("subscribe".equals(eventType)){
						TextMessage text = new TextMessage();
						text.setFromUserName(toUserName);
						text.setToUserName(fromUserName);
						text.setMsgType("text");
						text.setCreateTime(new Date().getTime()+"");
						text.setContent("欢迎加入TeamBuilding大家庭！");
						message = MessageUtil.textMessageToXML(text);
						MemberService memberService = new MemberService();
						Member member = new Member();
						member.setId(fromUserName);
						memberService.addMember(member);
					}
				}
				out.print(message);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public String hex(String str){
			if(str == null || str.length() ==0){
				return null;
			}
			char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			try {
				MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
				mdTemp.update(str.getBytes("UTF-8"));
				byte[] md = mdTemp.digest();
				int j = md.length;
				char buf[] = new char[j*2];
				int k=0;
				for(int i=0;i<j;i++){
					byte byte0 = md[i];
					buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
					buf[k++] = hexDigits[byte0 & 0xf];
				}
				return new String(buf);
			} catch (Exception e) {
				return null;
			}
		}
}

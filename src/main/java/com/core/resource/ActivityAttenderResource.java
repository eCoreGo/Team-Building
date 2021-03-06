package com.core.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.core.bean.Activity;
import com.core.bean.ActivityAttender;
import com.core.bean.CarArrange;
import com.core.bean.Member;
import com.core.service.ActivityAttenderService;
import com.core.service.ActivityService;
import com.core.service.TeamService;
import com.core.weixin.Data;
import com.core.weixin.SendMessageUtil;
import com.core.weixin.Vad;


@Path(value = "/ActivityAttender")
public class ActivityAttenderResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private TeamService teamService = new TeamService();
	private ActivityAttenderService activityAttenderService = new ActivityAttenderService();
	private ActivityService activityService = new ActivityService();
	
	private Logger logger = Logger.getLogger(ActivityAttenderResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "updateActivityAttender")
	public String updateActivityAttender(@FormParam(value = "activityId") Integer activityId,
					@FormParam(value = "userId") String userId,
					@FormParam(value = "seatsleave") Integer seatsleave,
					@FormParam(value = "attended") Boolean attended,
					@Context HttpServletResponse response) {
				try {
					ActivityAttender activityAttender = new ActivityAttender();
					activityAttender.setActivityId(activityId);
					activityAttender.setUserId(userId);
					activityAttender.setSeatsleave(seatsleave);
					activityAttender.setAttended(attended);
					activityAttenderService.updateActivityAttender(activityAttender);
				} catch (Exception e) {
					e.printStackTrace();
					return FAIL;
				}
				return SUCCESSFULLY;
			}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "initActivityAttender")
	public String initActivityAttender(@FormParam(value = "activityId") Integer activityId,
					@FormParam(value = "teamId") Integer teamId,
					@Context HttpServletResponse response) {
		if(activityId == null || teamId == null) {
			return FAIL;
		}
		
		try {
			activityService.updateActivityStatus(activityId, 1);
			Activity activity=activityService.getActivityById(activityId);
			//if(activity.getOpenCarSchedule()!=null && activity.getOpenCarSchedule()==true) {
				List<ActivityAttender> activityAttenders = new ArrayList<ActivityAttender>();
				
				List<Member> members = teamService.getMembers(teamId);
				List<String> tousers = new ArrayList<String>();
				for (Member member : members) {
					ActivityAttender activityAttender = new ActivityAttender();
					activityAttender.setActivityId(activityId);
					activityAttender.setUserId(member.getId());
					activityAttender.setUserName(member.getName());
					
					activityAttenders.add(activityAttender);
					tousers.add(member.getId());
				}
				
				activityAttenderService.insertInitActivityAttender(activityAttenders);
			//}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Data data = new Data(
						new Vad("欢迎参加 "+activity.getName(), "#173177"),
						new Vad(activity.getName(), "#173177"),
						new Vad(formatter.format(activity.getStartTime()), "#173177"),
						new Vad(activity.getTeam().getName(), "#173177"),
						new Vad("速速报名，位置有限", "#173177")
						);
				SendMessageUtil sendMessageUtil = new SendMessageUtil("create_activity", data, tousers);
				//即时推送
				sendMessageUtil.sendMessageByTask();
				
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "invokeCarSchedule")
	public String invokeCarSchedule(@FormParam(value = "activityId") Integer activityId,
					@FormParam(value = "userId") String userId,
					@Context HttpServletResponse response) {
		try {
			activityService.updateActivityStatus(activityId, 2);
			activityAttenderService.arangeTaix(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "endActivity")
	public String endActivity(@FormParam(value = "activityId") Integer activityId,
					@FormParam(value = "userId") String userId,
					@Context HttpServletResponse response) {
		try {
			activityService.updateActivityStatus(activityId, 3);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getArangeTaixInfo")
	public String getArangeTaixInfo(@FormParam(value = "activityId") Integer activityId,
					@Context HttpServletResponse response) {
		String result = "[]";
		try {
			List<ActivityAttender> activityAttenders = activityAttenderService.getArangeTaixInfo(activityId);
			
			result = objectMapper.writeValueAsString(convert(activityAttenders));
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}
	
	private List<CarArrange> convert(List<ActivityAttender> attenders) {
		Map<Integer, CarArrange> map = new HashMap<Integer, CarArrange>();
		
		for (ActivityAttender attender : attenders) {
			Integer carNo = attender.getCarNo();
			String userName = attender.getUserName();
			
			CarArrange carArrange = null;
			
			if(!map.containsKey(carNo)) {
				// 初始化车辆编号map
				carArrange = new CarArrange();
				carArrange.setCarNo(carNo);
				carArrange.setPassengers(userName);
				map.put(carNo, carArrange);
			}else{
				// 追加拼车人员
				carArrange = map.get(carNo);
				String passengers = carArrange.getPassengers();
				passengers = passengers + ", " + userName;
				carArrange.setPassengers(passengers);
				map.put(carNo, carArrange);
			}
			
			// 设置车主
			if(attender.getSeatsleave() >= 0) {
				carArrange.setDriver(userName);
			}
				
		}
		
		int size = map.size();
		
		// 构造页面车辆安排列表
		List<CarArrange> carArranges = new ArrayList<CarArrange>();
		for(int i = 1; i <= size; ++i) {
			CarArrange carArrange = map.get(i);
			carArranges.add(carArrange);
		}
		
		return carArranges;
	}
}


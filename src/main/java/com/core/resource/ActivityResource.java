package com.core.resource;

import com.core.bean.Activity;
import com.core.bean.Member;
import com.core.bean.Team;
import com.core.bean.TeamMember;
import com.core.service.ActivityService;
import com.core.service.MemberService;
import com.core.util.StringUtils;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path(value = "/Activity")
public class ActivityResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private ActivityService activityService = new ActivityService();
	private MemberService memberService = new MemberService();

	private Logger logger = Logger.getLogger(ActivityResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "addActivity")
	public String addActivity(@FormParam(value = "id") Integer id,
			@FormParam(value = "name") String name,
			@FormParam(value = "totalFoundationCost") String totalFoundationCost,
			@FormParam(value = "startTime") String startTime,
			@FormParam(value = "endTime") String endTime,
			@FormParam(value = "description") String description,
			@FormParam(value = "teamId") Integer teamId,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		try {
			Activity activity= new Activity();
			activity.setName(name);
			activity.setTotalFoundationCost(Double.valueOf(totalFoundationCost));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			activity.setStartTime(sdf.parse(startTime));
			activity.setEndTime(sdf.parse(endTime));
			activity.setDescription(description);
			activity.setStatus(Activity.TODO);
			activity.setTeamId(0);
			activityService.addActivity(activity);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getOngoingActivities")
	public String getOngoingActivities(@FormParam(value = "id") Integer teamID) {
		String result = "[]";
		try {
			List<Activity> activities = activityService.getOngoingActivities(teamID);
			result = objectMapper.writeValueAsString(activities);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getAllActivities")
	public String getAllActivities(@FormParam(value = "id") Integer teamID) {
		String result = "[]";
		try {
			List<Activity> activities = activityService.getAllActivities(teamID);
			result = objectMapper.writeValueAsString(activities);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getAllActivitiesByMemberId")
	public String getAllActivitiesByMemberId(@FormParam(value = "memberId") String memberId) {
		String result = "[]";
		try {
			List<Team> teams = memberService.getTeams(memberId);
			Integer[] teamIds = new Integer[teams.size()];
			for (int i = 0; i < teams.size(); i++) {
				teamIds[i] = teams.get(i).getId();
			}
			List<Activity> activities = activityService.getAllActivities(teamIds);
			result = objectMapper.writeValueAsString(activities);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

}

package com.core.resource;

import com.core.bean.Activity;
import com.core.bean.Member;
import com.core.bean.Team;
import com.core.bean.TeamMember;
import com.core.service.ActivityService;
import com.core.service.MemberService;
import com.core.service.TeamService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path(value = "/Activity")
public class ActivityResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private ActivityService activityService = new ActivityService();
	private MemberService memberService = new MemberService();
	private TeamService teamService = new TeamService();

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
			@FormParam(value = "openCarSchedule") Boolean openCarSchedule,
			@FormParam(value = "openExchangeModule") Boolean openExchangeModule,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		try {
			Team team = teamService.getTeamById(teamId);
			Activity activity= new Activity();
			activity.setName(name);
			activity.setTotalFoundationCost(Double.valueOf(totalFoundationCost));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			activity.setStartTime(sdf.parse(startTime));
			activity.setEndTime(sdf.parse(endTime));
			activity.setDescription(description);
			activity.setStatus(Activity.TODO);
			activity.setTeam(team);
			activity.setOpenCarSchedule(openCarSchedule);
			activity.setOpenExchangeModule(openExchangeModule);
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
	@Path(value = "getTeamActivities")
	public String getTeamActivities(@FormParam(value = "id") Integer teamID) {
		String result = "[]";
		try {
			//List<Activity> activities = activityService.getAllActivities(teamID);
			List<Activity> activities = activityService.getTeamActivities(teamID);
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
	public String getAllActivities() {
		String result = "[]";
		try {
			//List<Activity> activities = activityService.getAllActivities(teamID);
			List<Activity> activities = activityService.getAllActivities();
			result = objectMapper.writeValueAsString(activities);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getAllActivitiesByStatus")
	public String getAllActivitiesByStatus(@FormParam(value = "status") String statusString) {
		String result = "[]";
		List<Activity> activities = new ArrayList<Activity>();
		try {
			if(StringUtils.isNullOrEmpty(statusString)) {
				activities = activityService.getAllActivities();
			} else {
				String[] statusStr = statusString.split(",");
				Integer[] status = new Integer[statusStr.length];
				for(int i = 0; i < statusStr.length; i++) {
					status[i] = Integer.valueOf(statusStr[i]);
				}
				activities = activityService.getAllActivitiesByStatus(status);
			}
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
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getActivityByActivityId")
	public String getActivityByActivityId(@FormParam(value = "id") Integer id) {
		String result = "[]";
		try {
			Activity activity = activityService.getActivityById(id);
			result = objectMapper.writeValueAsString(activity);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}


}

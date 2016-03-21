package com.core.resource;

import com.core.bean.Activity;
import com.core.service.ActivityService;
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
import java.util.Date;
import java.util.List;

@Path(value = "/Activity")
public class ActivityResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private ActivityService activityService = new ActivityService();

	private Logger logger = Logger.getLogger(ActivityResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "addActivity")
	public String addActivity(@FormParam(value = "id") Integer id,
			@FormParam(value = "name") String name,
			@FormParam(value = "totalCost") Double totalCost,
			@FormParam(value = "totalFoundationCost") Double totalFoundationCost,
			@FormParam(value = "time") Date time,
			@FormParam(value = "description") String description,
			@FormParam(value = "status") Activity.Status status,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		try {
			Activity activity= new Activity();
			activity.setId(id);
			activity.setName(name);
			activity.setTotalCost(totalCost);
			activity.setTotalFoundationCost(totalFoundationCost);
			activity.setTime(time);
			activity.setDescription(description);
			activity.setStatus(status);
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
	 public String getOngoingActivities(String teamID) {
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


}

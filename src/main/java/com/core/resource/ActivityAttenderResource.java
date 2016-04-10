package com.core.resource;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.core.bean.ActivityAttender;
import com.core.service.ActivityAttenderService;


@Path(value = "/ActivityAttender")
public class ActivityAttenderResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private ActivityAttenderService activityAttenderService = new ActivityAttenderService();
	
	private Logger logger = Logger.getLogger(ActivityAttenderResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "updateActivityAttender")
	public String updateActivityAttender(@FormParam(value = "activityId") Integer activityId,
					@FormParam(value = "userId") Integer userId,
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
}


package com.core.resource;

import com.core.service.TeamMemberService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/TeamMember")
public class TeamMemberResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private TeamMemberService teamMemberService = new TeamMemberService();

	private Logger logger = Logger.getLogger(TeamMemberResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "leave")
	public String leave(@FormParam(value = "teamId") Integer teamId,
			@FormParam(value = "memberId") String memberId) {
		try {
			teamMemberService.leave(teamId, memberId);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}


}

package com.core.resource;

import com.core.bean.Team;
import com.core.service.TeamService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(value = "/Team")
public class TeamResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private TeamService teamService = new TeamService();

	private Logger logger = Logger.getLogger(TeamResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getAllTeams")
	public String getAllTeams(@Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String result = "[]";
		try {
			List<Team> teams = teamService.getTeams();
			result = objectMapper.writeValueAsString(teams);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getTeamDetail")
	public String getTeamDetail(@FormParam(value = "id") Integer id,
			@Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String result = "[]";
		try {
			Team team = teamService.getTeamById(id);
			result = objectMapper.writeValueAsString(team);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}



}

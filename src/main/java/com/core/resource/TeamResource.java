package com.core.resource;

import com.core.bean.Member;
import com.core.bean.Team;
import com.core.bean.TeamMember;
import com.core.service.TeamMemberService;
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
import java.util.Date;
import java.util.List;

@Path(value = "/Team")
public class TeamResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private TeamService teamService = new TeamService();
	private TeamMemberService teamMemberService = new TeamMemberService();

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
	@Path(value = "getTeamsByNameWildCard")
	public String getTeamsByNameWildCard(@FormParam(value = "name") String name) {
		String result = "[]";
		try {
			List<Team> teams = teamService.getTeamsByNameWildCard(name);
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
		String result = "{}";
		try {
			Team team = teamService.getTeamById(id);
			result = objectMapper.writeValueAsString(team);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getTeamMembers")
	public String getTeamMembers(@FormParam(value = "id") Integer id,
			@Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String result = "[]";
		try {
			List<Member> members = teamService.getMembers(id);
			result = objectMapper.writeValueAsString(members);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "addTeam")
	public String addTeam(@FormParam(value = "name") String name,
			@FormParam(value = "desc") String description,
			@FormParam(value = "members") String memberIds,
			@Context HttpServletResponse response) {
		try {
			Team team = new Team();
			team.setName(name);
			team.setDescription(description);
			team.setTemp(false);
			team.setTotalUserBalance(0d);
			team.setTotalFoundation(0d);
			team.setCreationTime(new Date());

			String[] memberIdArr = memberIds.split(",");
			teamService.addTeam(team);
			team = teamService.getTeamByName(name);
			for(String memberId : memberIdArr) {
				Member member = new Member();
				member.setId(memberId);
				TeamMember teamMember = new TeamMember();
				teamMember.setTeam(team);
				teamMember.setMember(member);
				teamMember.setAttendTime(new Date());
				teamMember.setBalance(0d);
				teamMemberService.join(teamMember);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "updateTeam")
	public String updateTeam(
			@FormParam(value = "id") Integer id,
			@FormParam(value = "name") String name,
			@FormParam(value = "desc") String description,
			@FormParam(value = "members") String memberIds,
			@Context HttpServletResponse response) {
		try {
			Team team = new Team();
			team.setId(id);
			team.setName(name);
			team.setDescription(description);

			String[] memberIdArr = memberIds.split(",");
			teamService.updateTeam(team);
			team = teamService.getTeamByName(name);
			teamMemberService.leaveAll(id);
			for(String memberId : memberIdArr) {
				Member member = new Member();
				member.setId(memberId);
				TeamMember teamMember = new TeamMember();
				teamMember.setTeam(team);
				teamMember.setMember(member);
				teamMember.setAttendTime(new Date());
				teamMember.setBalance(0d);
				teamMemberService.join(teamMember);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}

}

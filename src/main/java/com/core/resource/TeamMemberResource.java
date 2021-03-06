package com.core.resource;

import com.core.bean.Member;
import com.core.bean.Team;
import com.core.bean.TeamMember;
import com.core.service.MemberService;
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

@Path(value = "/TeamMember")
public class TeamMemberResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private TeamMemberService teamMemberService = new TeamMemberService();
	private MemberService memberService = new MemberService();
	private TeamService teamService = new TeamService();
	
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

        @POST
        @Produces(MediaType.TEXT_PLAIN)
        @Path(value = "getTeamMembersByTeamId")
        public String getTeamMembersByTeamId(@FormParam(value = "id") Integer teamId) {
                String result = "[]";
                try {
                        List<TeamMember> teamMembers = teamMemberService.getTeamMembersByTeamId(teamId);
                        result = objectMapper.writeValueAsString(teamMembers);
                } catch (Exception e) {
                        e.printStackTrace();
                        return FAIL;
                }
                return result;
        }
        
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path(value = "getTeamMembersByMemberId")
    public String getTeamMembersByMemberId(@FormParam(value = "memberId") String memberId,
                        @Context HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String result = "[]";
        try {
            List<TeamMember> teamMembers = teamMemberService.getTeamMembersByMemberId(memberId);
                result = objectMapper.writeValueAsString(teamMembers);
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return result;
    }
        
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "join")
	public String join(@FormParam(value = "teamId") Integer teamId,
			@FormParam(value = "memberId") String memberId,
			@FormParam(value = "balance") Double balance,
			@FormParam(value = "attendTime") Date attendTime) {
		try {
			Team team = teamService.getTeamById(teamId);
			Member member = memberService.getMemberById(memberId);
			TeamMember teamMember = new TeamMember();
			teamMember.setTeam(team);
			teamMember.setMember(member);
			teamMember.setBalance(balance);
			teamMember.setAttendTime(attendTime);
			teamMemberService.join(teamMember);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getTeamMemberInfo")
	public String getTeamMemberInfo(@FormParam(value = "teamId") Integer teamId,
			@FormParam(value = "memberId") String memberId) {
		String result = "{}";
		try {
			TeamMember teamMember;
			teamMember = teamMemberService.getTeamMemberInfo(teamId, memberId);
			result = objectMapper.writeValueAsString(teamMember);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}
	


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getTeams")
	public String getTeams(@FormParam(value = "memberId") String memberId) {
		String result = "{}";
		try {
			List<Team> teams;
			teams = teamMemberService.getTeams(memberId);
			result = objectMapper.writeValueAsString(teams);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getMemberBalancesByTeamId")
	public String getMemberBalancesByTeamId(@FormParam(value = "id") Integer teamId) {
		String result = "[]";
		try {
			List<TeamMember.MemberBalance> memberBalances = teamMemberService.getMemberBalancesByTeamId(teamId);
			result = objectMapper.writeValueAsString(memberBalances);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getTeamBalancesByMemberId")
	public String getTeamBalancesByMemberId(@FormParam(value = "id") String memberId) {
		String result = "[]";
		try {
			List<TeamMember.TeamBalance> teamBalances = teamMemberService.getTeamBalancesByMemberId(memberId);
			result = objectMapper.writeValueAsString(teamBalances);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}
}

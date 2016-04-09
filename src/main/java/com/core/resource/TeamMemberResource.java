package com.core.resource;

import com.core.bean.TeamMember;
import com.core.service.TeamMemberService;
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

}
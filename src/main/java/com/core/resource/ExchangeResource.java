package com.core.resource;

/**
 * Created by wanglan on 16/3/29.
 */
import com.core.bean.*;
import com.core.service.ExchangeService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path(value = "/Exchange")
public class ExchangeResource {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private ExchangeService exchangeService = new ExchangeService();

    private Logger logger = Logger.getLogger(ExchangeResource.class);
    private static final String SUCCESSFULLY = "Successful";
    private static final String FAIL = "Failed";

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path(value = "getExchangesByTeamId")
    public String getExchangesByTeamId(@FormParam(value = "id") Integer teamId) {
        String result = "[]";
        try {
            List<Exchange> exchanges = exchangeService.getExchangesByTeamId(teamId);
            result = objectMapper.writeValueAsString(exchanges);
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return result;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path(value = "addExchange")
    public String addExchange(@FormParam(value = "member_id") String memberId,
                              @FormParam(value = "team_id") Integer teamId,
                              @FormParam(value = "value") Double value,
                              @FormParam(value = "type") Integer type,
                              @FormParam(value = "date") Date date,
                              @FormParam(value = "activity_id") Integer activityId) {
        try {
            Exchange exchange = new Exchange();
            Member member = new Member();
            member.setId(memberId);

            Team team = new Team();
            team.setId(teamId);

            Activity activity = new Activity();
            activity.setId(activityId);

            exchange.setMember(member);
            exchange.setTeam(team);
            exchange.setActivity(activity);
            exchange.setValue(value);
            exchange.setType(type);
            exchange.setDate(date);
            exchangeService.addExchange(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESSFULLY;
    }
}
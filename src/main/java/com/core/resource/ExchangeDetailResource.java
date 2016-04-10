package com.core.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by wanglan on 16/3/29.
 */
import com.core.bean.ExchangeDetail;
import com.core.service.ExchangeDetailService;

@Path(value = "/ExchangeDetail")
public class ExchangeDetailResource {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private ExchangeDetailService exchangeDetailService = new ExchangeDetailService();

    private Logger logger = Logger.getLogger(ExchangeDetailResource.class);
    private static final String SUCCESSFULLY = "Successful";
    private static final String FAIL = "Failed";

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path(value = "insertExchangeDetail")
    public String insertExchangeDetail(@FormParam(value = "id") int id,
                              @FormParam(value = "memberId") String memberId,
                              @FormParam(value = "activityId") Integer activityId,
                              @FormParam(value = "teamId") Integer teamId,
                              @FormParam(value = "exchange") Double exchange,
                              @FormParam(value = "exchangeStatus") int exchangeStatus,
                              @FormParam(value = "date") Date date,
                              @Context HttpServletRequest request, @Context HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");

        try {
            ExchangeDetail exchangeDetail= new ExchangeDetail();
            exchangeDetail.setId(id);
            exchangeDetail.setMemberId(memberId);
            exchangeDetail.setActivityId(activityId);
            exchangeDetail.setTeamId(teamId);
            exchangeDetail.setExchange(exchange);
            exchangeDetail.setExchangeStatus(exchangeStatus);
            exchangeDetail.setDate(date);
            exchangeDetailService.insertExchangeDetail(exchangeDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESSFULLY;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path(value = "getExchangeDetails")
    public String getExchangeDetails(@FormParam(value = "memberId") String memberId, 
                                                                 @FormParam(value = "teamId") Integer teamId, 
                                                                 @FormParam(value = "duration") Integer duration) {
        String result = "[]";
        try {
                List<ExchangeDetail> exchangeDetails = new ArrayList<ExchangeDetail>();
                if(teamId == null && duration == null) {
                        exchangeDetails = exchangeDetailService.getExchangeDetailsByMemberId(memberId);
                } else if(duration == null) {
                        exchangeDetails = exchangeDetailService.getExchangeDetailsByMemberTeamId(memberId, teamId);
                } else if(teamId == null) {
                        Date to = new Date(System.currentTimeMillis());
                        Date from = getFromDate(duration, to);
                        exchangeDetails = exchangeDetailService.getExchangeDetailsByMemberIdDuration(memberId, duration, from, to);
                } else {
                        Date to = new Date(System.currentTimeMillis());
                        Date from = getFromDate(duration, to);
                        exchangeDetails = exchangeDetailService.getExchangeDetails(memberId, teamId, from, to);
                }
            result = objectMapper.writeValueAsString(exchangeDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return result;
    }

        private Date getFromDate(Integer duration, Date to) {
                Date from = new Date();
                switch(duration) {
                case 1: //past week
                        from = new Date(to.getTime() - (7 * 24 * 3600000));
                        break;
                case 2: //past month
                        from = new Date(to.getTime() - (30 * 24 * 3600000));
                        break;
                case 3: //path year
                        from = new Date(to.getTime() - (365 * 24 * 3600000));
                        break;
                default :
                        break;
                }
                return from;
        }
}
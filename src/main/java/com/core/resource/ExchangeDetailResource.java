package com.core.resource;

/**
 * Created by wanglan on 16/3/29.
 */
import com.core.bean.ExchangeDetail;
import com.core.service.ExchangeDetailService;
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
    public String addActivity(@FormParam(value = "id") String id,
                              @FormParam(value = "memberId") String memberId,
                              @FormParam(value = "activityId") Integer activityId,
                              @FormParam(value = "teamId") Integer teamId,
                              @FormParam(value = "exchange") Double exchange,
                              @FormParam(value = "exchangeStatus") ExchangeDetail.ExchangeStatus exchangeStatus,
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
    public String getExchangeDetails(String memberId, Integer teamId, Date from, Date to) {
        String result = "[]";
        try {
            List<ExchangeDetail> exchangeDetails = exchangeDetailService.getExchangeDetails(memberId, teamId, from, to);
            result = objectMapper.writeValueAsString(exchangeDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return result;
    }

}
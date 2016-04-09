package com.core.resource;

/**
 * Created by wanglan on 16/3/29.
 */
import com.core.bean.Exchange;
import com.core.service.ExchangeService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

}
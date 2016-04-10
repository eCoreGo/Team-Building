package com.core.service;

import com.core.bean.Exchange;
import com.core.mapper.TeamMemberMapper;
import com.core.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by wanglan on 16/3/29.
 */
public class ExchangeService {

    public List<Exchange> getExchangesByTeamId(Integer teamId) throws RuntimeException {
        List<Exchange> exchanges;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            exchanges = session.selectList("com.core.bean.ExchangeMapper.getExchangesByTeamId",teamId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get exchanges!", e);
        } finally {
            session.close();
        }
        return exchanges;
    }

    public void addExchange(Exchange exchange) throws RuntimeException {
        List<Exchange> exchanges;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();
        try {
            exchanges = session.selectList("com.core.bean.ExchangeMapper.addExchange", exchange);

            String memberId = exchange.getMember().getId();
            Integer teamId = exchange.getTeam().getId();
            Double delta = exchange.getValue();

            Integer type = exchange.getType();
            if(!Exchange.ACTIVITY_TOTAL_COST.equals(type) && !Exchange.BAD_DEBT.equals(type)) {
                session.getMapper(TeamMemberMapper.class).updateMemberFee(memberId, teamId, delta);
            }

            session.commit();
        } catch (Exception e) {
            throw new RuntimeException("Fail to add exchange!", e);
        } finally {
            session.close();
        }
    }

}
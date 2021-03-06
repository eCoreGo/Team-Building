package com.core.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.core.bean.ExchangeDetail;
import com.core.bean.TeamMember;
import com.core.mapper.ExchangeDetailMapper;
import com.core.util.GetSqlSessionFactory;

/**
 * Created by wanglan on 16/3/29.
 */
public class ExchangeDetailService {

    public void insertExchangeDetail(ExchangeDetail exchangeDetail) {
        exchangeDetail.setExpired(false);

        ExchangeDetail unexpiredDetail = getUnexpiredTeamDetail(exchangeDetail.getTeamId());
        if (unexpiredDetail != null) {
            unexpiredDetail.setExpired(true);
            exchangeDetail.setTeamTotal(unexpiredDetail.getTeamTotal() + exchangeDetail.getExchange().intValue());

            addExchangeDetail(Arrays.asList(unexpiredDetail, exchangeDetail));
        } 
//        else {
//            exchangeDetail.setTeamTotal(exchangeDetail.getExchange().intValue());
//            addExchangeDetail(Arrays.asList(exchangeDetail));
//        }

    }

    public List<ExchangeDetail> getExchangeDetails(String memberId, Integer teamId, Date from, Date to) throws RuntimeException {
        List<ExchangeDetail> exchangeDetails;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            exchangeDetails = session.getMapper(ExchangeDetailMapper.class).getExchangeDetails(memberId, teamId, from, to);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return exchangeDetails;
    }
    
    public List<ExchangeDetail> getExchangeDetailsByMemberId(String memberId) throws RuntimeException {
        List<ExchangeDetail> exchangeDetails;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            exchangeDetails = session.getMapper(ExchangeDetailMapper.class).getExchangeDetailsByMemberId(memberId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return exchangeDetails;
    }
    
    public List<ExchangeDetail> getExchangeDetailsByMemberTeamId(String memberId, Integer teamId) throws RuntimeException {
        List<ExchangeDetail> exchangeDetails;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            exchangeDetails = session.getMapper(ExchangeDetailMapper.class).getExchangeDetailsByMemberTeamId(memberId, teamId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return exchangeDetails;
    }
    
    public List<ExchangeDetail> getExchangeDetailsByMemberIdDuration(String memberId, Integer teamId, Date from, Date to) throws RuntimeException {
        List<ExchangeDetail> exchangeDetails;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            exchangeDetails = session.getMapper(ExchangeDetailMapper.class).getExchangeDetailsByMemberIdDuration(memberId, from, to);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return exchangeDetails;
    }
    
    private void addExchangeDetail(List<ExchangeDetail> exchangeDetails) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(ExchangeDetailMapper.class).addExchangeDetail(exchangeDetails);
        } catch (Exception e) {
            throw new RuntimeException("Fail to add ExchangeDetail!", e);
        } finally {
            session.close();
        }
    }

    private ExchangeDetail getUnexpiredTeamDetail(Integer teamId) {
        ExchangeDetail unexpiredGroupDetails;

        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            unexpiredGroupDetails = session.getMapper(ExchangeDetailMapper.class).getUnexpiredTeamDetail(teamId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get unexpired exchange detail!", e);
        } finally {
            session.close();
        }

        return unexpiredGroupDetails;
    }


}
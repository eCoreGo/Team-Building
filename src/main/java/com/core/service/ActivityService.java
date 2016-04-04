package com.core.service;

import com.core.bean.Activity;
import com.core.bean.Member;
import com.core.mapper.ActivityMapper;
import com.core.mapper.MemberMapper;
import com.core.util.GetSqlSessionFactory;
import com.core.util.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ActivityService {

    @SuppressWarnings("static-access")
    public void addActivity(Activity activity) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(ActivityMapper.class).addActivity(activity);
        } catch (Exception e) {
            throw new RuntimeException("Fail to add activity!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public List<Activity> getOngoingActivities(Integer teamID) throws RuntimeException {
        List<Activity> activities;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            activities = session.getMapper(ActivityMapper.class).getOngoingActivities(teamID);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return activities;
    }

    @SuppressWarnings("static-access")
    public List<Activity> getAllActivities(Integer teamID) throws RuntimeException {
        List<Activity> activities;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            activities = session.getMapper(ActivityMapper.class).getAllActivitiesByTeamId(teamID);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get activities!", e);
        } finally {
            session.close();
        }
        return activities;
    }

    @SuppressWarnings("static-access")
    public List<Activity> getAllActivities(Integer[] teamIDs) throws RuntimeException {
        List<Activity> activities;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            String inString = StringUtils.generateInString(teamIDs);
            activities = session.getMapper(ActivityMapper.class).getAllActivitiesByTeamIds(inString);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return activities;
    }

}

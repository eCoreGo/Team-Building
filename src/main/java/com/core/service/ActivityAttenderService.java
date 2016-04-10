package com.core.service;

import com.core.bean.ActivityAttender;
import com.core.mapper.ActivityAttenderMapper;
import com.core.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huihui
 */

public class ActivityAttenderService {

	@SuppressWarnings("static-access")
	public List<ActivityAttender> getAttendersByActivityId(Integer activityId)
			throws RuntimeException {
		List<ActivityAttender> activityAttenderList = new ArrayList<ActivityAttender>();
		SqlSession session = GetSqlSessionFactory.getInstance()
				.getSqlSessionFactory().openSession(true);
		try {
			activityAttenderList = session.getMapper(
					ActivityAttenderMapper.class).getAttendersByActivityId(activityId);
		} catch (Exception e) {
			throw new RuntimeException("Fail to get seat info!", e);
		} finally {
			session.close();
		}
		return activityAttenderList;
	}

	@SuppressWarnings("static-access")
	public void updateActivityAttender(ActivityAttender activityAttender) throws RuntimeException {
		SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
		try {
			session.getMapper(ActivityAttenderMapper.class).updateActivityAttender(activityAttender);
		} catch (Exception e) {
			throw new RuntimeException("Fail to update activityAttender!", e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("static-access")
	public List<ActivityAttender> getArangeTaixInfo(Integer activityId)
			throws RuntimeException {
		List<ActivityAttender> activityAttenderList = new ArrayList<ActivityAttender>();
		SqlSession session = GetSqlSessionFactory.getInstance()
				.getSqlSessionFactory().openSession(true);
		try {
			activityAttenderList = session.getMapper(
					ActivityAttenderMapper.class).getSeatNumberByActivityId(activityId);
		} catch (Exception e) {
			throw new RuntimeException("Fail to get seat info!", e);
		} finally {
			session.close();
		}
		return activityAttenderList;
	}

	@SuppressWarnings("static-access")
	public void arangeTaix(Integer activityId) throws RuntimeException {
		List<ActivityAttender> carAttenderList = getCarAttender(activityId);
		List<ActivityAttender> nocarAttenderList = getNoCarAttender(activityId);
		int seatNo = 1;
		for (ActivityAttender carInfo : carAttenderList) {
			int i = 0;
			for (ActivityAttender nocarInfo : nocarAttenderList) {
				if (i == carInfo.getSeatsleave()) {
					break;
				}
				i++;
				insertSeatNo(seatNo, nocarInfo.getUserId());
			}
			seatNo++;
		}
		List<ActivityAttender> whoNotArrangeTaixList = getWhoNotArrangeTaix(activityId);
		int k = 0;
		for (ActivityAttender no : whoNotArrangeTaixList) {
			if (k == 3) {
				break;
			}
			k++;
			insertSeatNo(seatNo, no.getUserId());
		}
	}

	private List<ActivityAttender> getNoCarAttender(Integer activityId) {
		List<ActivityAttender> activityAttenderList = new ArrayList<ActivityAttender>();
		SqlSession session = GetSqlSessionFactory.getInstance()
				.getSqlSessionFactory().openSession(true);
		try {
			activityAttenderList = session.getMapper(
					ActivityAttenderMapper.class).getNoCarAttendByActivityId(activityId);
		} catch (Exception e) {
			throw new RuntimeException("Fail to get activity attender info!", e);
		} finally {
			session.close();
		}
		return activityAttenderList;
	}

	private List<ActivityAttender> getCarAttender(Integer activityId) {
		List<ActivityAttender> activityAttenderList = new ArrayList<ActivityAttender>();
		SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
		try {
			activityAttenderList = session.getMapper(
					ActivityAttenderMapper.class).getCarByActivityId(activityId);
		} catch (Exception e) {
			throw new RuntimeException("Fail to get activity attender info!", e);
		} finally {
			session.close();
		}
		return activityAttenderList;
	}

	private void insertSeatNo(Integer seatNo, String user_id) {
		SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
		try {
			session.getMapper(ActivityAttenderMapper.class).insertSeartNo(seatNo, user_id);
		} catch (Exception e) {
			throw new RuntimeException("Fail to insert seatNo!", e);
		} finally {
			session.close();
		}
	}

	private List<ActivityAttender> getWhoNotArrangeTaix(Integer activityId) {
		List<ActivityAttender> activityAttenderList = new ArrayList<ActivityAttender>();
		SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
		try {
			activityAttenderList = session.getMapper(
					ActivityAttenderMapper.class).getWhoNotArrangeTaixUser(activityId);
		} catch (Exception e) {
			throw new RuntimeException("Fail to get activity attender info!", e);
		} finally {
			session.close();
		}
		return activityAttenderList;
	}

	@SuppressWarnings("static-access")
	public void insertInitActivityAttender(List<ActivityAttender> activityAttenders) {
		SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
		try {
			session.selectList("com.core.bean.ActivityAttenderMapper.initActivityAttender", activityAttenders);
		} catch (Exception e) {
			throw new RuntimeException("Fail to get activity attender info!", e);
		} finally {
			session.close();
		}
	}
}

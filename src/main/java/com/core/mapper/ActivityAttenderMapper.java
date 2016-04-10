package com.core.mapper;

import com.core.bean.ActivityAttender;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author alice
 */

public interface ActivityAttenderMapper {

	@Select("select seatsLeave,user_id,user_name from activity_attender where activityId=${activityId} and in=true and seat = -1")
	List<ActivityAttender> getNoCarAttendByActivityId(@Param(value  = "activityId") Integer activityId);

	@Select("select seatsLeave,user_id,user_name from activity_attender where activityId=${activityId} and in=true and seat >=0")
	List<ActivityAttender> getCarByActivityId(@Param(value  = "activityId") Integer activityId);

	@Select("select seatsNo,user_id,user_name from activity_attender where activityId=${activityId} and in=true and seatNo >0")
	List<ActivityAttender> getSeatNoByActivityId(@Param(value  = "activityId") Integer activityId);

	@Select("select seatsNo,user_id,user_name from activity_attender where activityId=${activityId} and in=true")
	List<ActivityAttender> getAttendersByActivityId(@Param(value  = "activityId") Integer activityId);

	@Update("update activity_attender(seatNo) values(${seatNo}) where user_id = ${user_id}")
	void insertSeartNo(@Param(value  = "seatNo") Integer seatNo,@Param(value  = "user_id") String user_id);

	@Select("select seatsNo,user_id,user_name from activity_attender where activityId=${activityId} and in=true and seatNo =0")
	List<ActivityAttender> getWhoNotArrangeTaixUser(@Param(value  = "activityId") Integer activityId);

	@Update("update activity_attender set seats=#{activityAttender.seatsleave}, attended=#{activityAttender.attended}"
			+ " where acitivity_id=#{activityAttender.activityId} and user_id=#{activityAttender.userId}")
	void updateActivityAttender(@Param(value = "activityAttender") ActivityAttender activityAttender);

}


package com.core.service;

import com.core.bean.Activity;
import com.core.bean.ActivityAttender;
import com.core.bean.Exchange;
import com.core.bean.Member;
import com.core.bean.Team;
import com.core.util.GetSqlSessionFactory;
import com.core.weixin.Data;
import com.core.weixin.SendMessageUtil;
import com.core.weixin.Vad;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanglan on 16/3/29.
 */
public class ExchangeService {

    private static TeamService teamService = new TeamService();
    private static TeamMemberService teamMemberService = new TeamMemberService();
    private static ActivityAttenderService activityAttenderService = new ActivityAttenderService();

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
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();
        try {
            session.selectList("com.core.bean.ExchangeMapper.addExchange", exchange);
            doExtraUpdate(session, exchange);
            session.commit();
            
            Activity activity = exchange.getActivity();
            List<Member> members = teamService.getMembers(activity.getTeam().getId());
            List<String> tousers = new ArrayList<String>();
			for (Member member : members) {
				tousers.add(member.getId());
			}
			Double aa = activity.getTotalCost()/activity.getTeam().getMembers().size();
			if(exchange.getType()==6){
	            Data data = new Data(
	    				new Vad("账单通知出来啦！", "#173177"),
	    				new Vad(activity.getName(), "#173177"),
	    				new Vad(activity.getTotalCost().toString(), "#173177"),
	    				new Vad(aa.toString(), "#173177"),
	    				new Vad("", "#173177")
	    				);
	            SendMessageUtil sendMessageUtil = new SendMessageUtil("activity", data, tousers);
				//即时推送
				sendMessageUtil.sendMessageByTask();
			}
            
        } catch (Exception e) {
            throw new RuntimeException("Fail to add exchange!", e);
        } finally {
            session.close();
        }
    }

    private void doExtraUpdate(SqlSession session, Exchange exchange) {
        Integer type = exchange.getType();

        Integer teamId = exchange.getTeam().getId();
        Integer activityId = exchange.getActivity().getId();
        String memberId = exchange.getMember().getId();
        Double value = exchange.getValue();
        Date date = exchange.getDate();


        switch (type) {
            //TEAM_FOUNDATION
            case 1:
                Team team1 = teamService.getTeamById(teamId);
                team1.setTotalFoundation(team1.getTotalFoundation() + value);
                teamService.updateTeam(team1);
                break;
            //BAD_DEBT
            case 2:
                Team team2 = teamService.getTeamById(teamId);
                team2.setTotalUserBalance(team2.getTotalUserBalance() - value);
                teamService.updateTeam(team2);

                List<Member> members2 = team2.getMembers();
                Double average2 = value / members2.size();
                for(Member member : members2) {
                    teamMemberService.updateMemberFee(member.getId(), teamId, average2);
                    Exchange exchange2 = new Exchange();
                    exchange2.setTeam(team2);
                    exchange2.setMember(member);
                    exchange2.setDate(date);
                    exchange2.setValue(average2);
                    exchange2.setType(Exchange.BAD_DEBT);
                    session.selectList("com.core.bean.ExchangeMapper.addExchange", exchange2);
                }
                break;
            //RECHARGE
            case 3:
                //TAXI_FEE
            case 4:
                Team team4 = teamService.getTeamById(teamId);
                team4.setTotalUserBalance(team4.getTotalUserBalance() + value);
                teamService.updateTeam(team4);

                teamMemberService.updateMemberFee(memberId, teamId, value);
                break;
            //DRAWBACK
            case 5:
                Team team5 = teamService.getTeamById(teamId);
                team5.setTotalUserBalance(team5.getTotalUserBalance() - value);
                teamService.updateTeam(team5);

                teamMemberService.updateMemberFee(memberId, teamId, 0 - value);
                break;
            //SHARE_EQUALLY
            case 6:
                Team team6 = teamService.getTeamById(teamId);
                team6.setTotalUserBalance(team6.getTotalUserBalance() - value);
                teamService.updateTeam(team6);

                List<ActivityAttender> attenders = activityAttenderService.getAttendersByActivityId(activityId);

                Double average6 = value / attenders.size();
                for(ActivityAttender attender : attenders) {
                    teamMemberService.updateMemberFee(attender.getUserId(), teamId, average6);
                    Exchange exchange6 = new Exchange();
                    exchange6.setTeam(team6);
                    Member member = new Member();
                    member.setId(attender.getUserId());
                    exchange6.setMember(member);
                    exchange6.setDate(date);
                    exchange6.setValue(average6);
                    exchange6.setType(Exchange.SHARE_EQUALLY);
                    session.selectList("com.core.bean.ExchangeMapper.addExchange", exchange6);
                }
                break;
        }
    }

}
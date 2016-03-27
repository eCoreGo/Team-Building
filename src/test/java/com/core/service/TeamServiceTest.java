package com.core.service;

import com.core.bean.Member;
import com.core.bean.Team;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class TeamServiceTest {

    private TeamService service = new TeamService();

    @Test
    public void testAddTeam() {
        Team team = new Team();
        team.setId(3);
        team.setName("team3");
        team.setTemp(false);
        team.setTotalFoundation(200d);
        team.setTotalUserBalance(300d);
        team.setCreationTime(new Date());
        service.addTeam(team);
    }

    @Test
    public void testGetTeams() {
        List<Team> teams = service.getTeams();
        System.out.println(teams);
    }

    @Test
    public void testGetMembers() {
        List<Member> members = service.getMembers(1);
        System.out.println(members);
    }
}

package com.core.service;

import com.core.bean.Member;
import org.junit.Test;

import java.util.List;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class MemberServiceTest {

    private MemberService service = new MemberService();

    @Test
    public void testGetMembers() {
        List<Member> members = service.getMembers();
        System.out.println(members);
    }
}

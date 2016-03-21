package com.core.resource;

import com.core.bean.Member;
import com.core.service.MemberService;
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
import java.util.List;

@Path(value = "/Member")
public class MemberResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private MemberService memberService = new MemberService();

	private Logger logger = Logger.getLogger(MemberResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "addMember")
	public String addMember(@FormParam(value = "id") String id,
			@FormParam(value = "name") String name,
			@FormParam(value = "phone") String phone,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		try {
			Member member= new Member();
			member.setId(id);
			member.setName(name);
			member.setPhone(phone);
			memberService.addMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}

	@POST
	 @Produces(MediaType.TEXT_PLAIN)
	 @Path(value = "getMembers")
	 public String getMembers() {
		String result = "[]";
		try {
			List<Member> members = memberService.getMembers();
			result = objectMapper.writeValueAsString(members);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "getMemberById")
	public String getMemberById(@FormParam(value = "id") String id) {
		String result = "{}";
		try {
			Member member = memberService.getMemberById(id);
			result = objectMapper.writeValueAsString(member);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return result;
	}

}

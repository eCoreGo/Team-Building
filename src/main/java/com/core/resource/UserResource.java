package com.core.resource;

import com.core.bean.User;
import com.core.service.UserService;
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

@Path(value = "/User")
public class UserResource {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserService();

	private Logger logger = Logger.getLogger(UserResource.class);
	private static final String SUCCESSFULLY = "操作成功！";
	private static final String FAIL = "操作失败！";


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "addAppUser")
	public String addAppUser(@FormParam(value = "id") String id,
			@FormParam(value = "name") String name,
			@FormParam(value = "phone") String phone,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		try {
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setPhone(phone);
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESSFULLY;
	}

}

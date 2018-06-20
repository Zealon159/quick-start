package cn.zealon.controller;

import java.util.Collection;
import java.util.Iterator;
import cn.zealon.org.entity.OrgUser;
import cn.zealon.shiro.api.RedisSessionDAO;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/session/")
@Controller
public class SessionController {

	@Autowired
	private RedisSessionDAO redisSessionDAO;

	@RequestMapping("getAllSessions")
	@ResponseBody
	public String getAllSessions() {
		Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		Iterator<Session> it = sessions.iterator();
		String users = "";
		while (it.hasNext()) {
			Session session = it.next();
			users += ((OrgUser) session.getAttribute("user")).getUserName() + ",";
		}
		return "count:" + sessions.size() + ";sessions:" + users;
	}
}
package handler;

import javax.servlet.http.HttpSession;

import com.wheejuni.spring.domain.User;
import com.wheejuni.spring.domain.UserRepository;

public class UserSessioninfoHandler {
	
	User user;
	String username;
	
	public UserSessioninfoHandler(HttpSession session) {
		if (session.getAttribute("loginuser") == null) {
			return;
		}
		
		this.user = (User) session.getAttribute("loginuser");
		
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public long getUniqueId() {
		return user.getUniqueId();
	}
	
	public String getUserId() {
		return user.getId();
	}
	
	public String getUserEmail() {
		return user.getEmail();
	}

}

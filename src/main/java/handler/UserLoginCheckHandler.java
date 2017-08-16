package handler;

import javax.servlet.http.HttpSession;

import com.wheejuni.spring.domain.User;

public class UserLoginCheckHandler {
	
	public static boolean LoginChecker(String username, HttpSession session) {
		User loginedUser = (User)session.getAttribute("loginuser");
		
		if (loginedUser == null) {
			return false;
		}
		
		if (username.equals(loginedUser.getUsername()) == false) {
			return false;
		}
		
		return true;
	}

}

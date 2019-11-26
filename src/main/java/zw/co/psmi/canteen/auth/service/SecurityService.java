package zw.co.psmi.canteen.auth.service;

import javax.servlet.http.HttpServletRequest;

import zw.co.psmi.canteen.auth.entity.Login;

public interface SecurityService {

	String findLoggedInUsername();

	 public   void autologin(String username, String password);
	 public String getIPAddress(HttpServletRequest request);
	 public String getBrowser(HttpServletRequest request);
	
}

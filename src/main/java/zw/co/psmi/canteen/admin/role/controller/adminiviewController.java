package zw.co.psmi.canteen.admin.role.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.auth.entity.User;

@Controller
public class adminiviewController {

	@RequestMapping(value = "/admin/adminiview", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model, @ModelAttribute User user) {        
        model.setViewName("admin/adminiview");
        model.addObject("login", Getuser());
        return model;
   }
	 
	 @RequestMapping(value = "/admin/adminprofile", method = RequestMethod.GET)
		public String adminprofile() {
			return "/admin/adminprofile";
		}
                
       @Bean
	 @Qualifier("user")	
	 public Login Getuser() {
	        try {
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            if (authentication != null && authentication.isAuthenticated()) {
	            	 System.out.print( (Login) authentication.getPrincipal());
	                return (Login) authentication.getPrincipal();
	               
	            }
	        } catch (Exception e) {
	        	System.out.printf("AuthenticationError:" + e.getMessage());
	        }
	        return null;
	    }
}

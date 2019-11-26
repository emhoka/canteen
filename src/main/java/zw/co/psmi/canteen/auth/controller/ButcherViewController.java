/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.auth.controller;

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

/**
 *
 * @author euny
 */
@Controller
public class ButcherViewController {
    
    @RequestMapping(value = "/stocks/butcherview", method = RequestMethod.GET)
    public ModelAndView butcherview(ModelAndView model, @ModelAttribute User user){
           model.setViewName("/stocks/butcherview");
        model.addObject("login", Getuser());
    return model;
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

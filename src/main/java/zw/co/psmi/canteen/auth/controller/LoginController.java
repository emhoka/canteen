package zw.co.psmi.canteen.auth.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.auth.entity.User;
import zw.co.psmi.canteen.auth.service.UserService;


@Controller
@Slf4j
public class LoginController {
@Autowired
private UserService userService;
    @RequestMapping("/")
    public ModelAndView index(ModelAndView model) {
        SecurityContextHolder.clearContext();
        model.addObject("user", new User());
        model.setViewName("index");
        return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model, @ModelAttribute User user) {        
        model.setViewName("index");
        return model;   
    }
    @RequestMapping(value = "/roleasses", method = RequestMethod.GET)
    public String roleAsses(Model model, @AuthenticationPrincipal Login login) {
    	String username = login.getUsername();
    	User user = userService.findByUsername(username);
    	 if(user.getRole().getRoleName().toLowerCase().equals("butcher")){       	
             return "/stocks/butcherview";
         }
         else if(user.getRole().getRoleName().toLowerCase().equals("admin")){       	
             return "/admin/adminiview";
         }
        return "index";
   }
    
@Bean
@Scope("prototype")
@Qualifier("user")
public User user() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
            	return userService.findByUsername(((Login) authentication.getPrincipal()).getUsername());
            }
        } catch (Exception e) {
        	System.out.printf("AuthenticationError:" + e.getMessage());
        }
        return null;
    }




}

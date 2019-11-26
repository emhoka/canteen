package zw.co.psmi.canteen.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zw.co.psmi.canteen.auth.service.UserService;

import zw.co.psmi.canteen.admin.role.entity.Role;
import zw.co.psmi.canteen.admin.role.service.RoleService;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.auth.entity.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String users(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("userss", this.userService.findAll());
        return "/admin/users";
    }
    
    	 @RequestMapping(value = "/admin/createuser", method = RequestMethod.GET)
		public String createuser(@ModelAttribute User user,  @ModelAttribute Role role,  Model model) {       
		 //user.setDepartment(departmentService.getAll());
		 model.addAttribute("users", user);
		 model.addAttribute("roles", roleService.findAll());
		 return "/admin/createuser";
		}

    @RequestMapping(value = "/admin/deleteuser/{Id}", method = RequestMethod.GET)
    public String deleteuser(@PathVariable("Id") Long Id, @ModelAttribute Role role, Model model, RedirectAttributes redirectAttributes) {
        String msg = userService.delete(Id);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/edituser/{Id}", method = RequestMethod.GET)
    public String edituser(@PathVariable("Id") Long Id, @ModelAttribute Role role, Model model) {
        User user = this.userService.getByID(Id);

        if (user == null) {
            user = new User();
        }
        model.addAttribute("users", user);
        model.addAttribute("roles", roleService.findAll());
        return "admin/createuser";
    }
    @RequestMapping(value = "/admin/createuserform", method = RequestMethod.POST)
    public String createuserform(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.userService.save(user);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/admin/users";
    }
    
}

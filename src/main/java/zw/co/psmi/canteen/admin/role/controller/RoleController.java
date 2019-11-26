package zw.co.psmi.canteen.admin.role.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zw.co.psmi.canteen.admin.role.service.RoleService;
import zw.co.psmi.canteen.admin.role.entity.Role;
import zw.co.psmi.canteen.auth.entity.Login;


@Controller
public class RoleController {
	
 private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

  @RequestMapping(value = "/admin/roles", method = RequestMethod.GET)
    public String roles(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("roless", this.roleService.findAll());
        return "/admin/roles";
    }
    @RequestMapping(value = "/admin/addrole", method = RequestMethod.GET)
    public String addrole(@ModelAttribute Role role, Model model) {
        model.addAttribute("roles", role);
        return "/admin/addrole";
    }
    
    @RequestMapping(value = "/admin/deleterole/{Id}", method = RequestMethod.GET)
	   public String deleterole( @PathVariable("Id") Long Id,  @ModelAttribute Role role,  Model model, RedirectAttributes redirectAttributes) {
		 String msg = roleService.delete(Id);
		 redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
       return "redirect:/admin/roles";
	    }
              @RequestMapping(value = "/admin/editrole/{Id}", method = RequestMethod.GET)
	   public String editrole( @PathVariable("Id") Long Id, Model model) {
               Role role = this.roleService.getByID(Id);
               if(role == null){
               role = new Role();
               }
               model.addAttribute("roles", role);
       return "/admin/addrole";
	    }

    @RequestMapping(value = "/admin/roleform", method = RequestMethod.POST)
    public String roleform(@ModelAttribute Role role, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.roleService.save(role);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/admin/roles";

    }
    
    
          
      
      
  @RequestMapping(value = "/admin/testpage", method = RequestMethod.GET)
		public String testpage() {
			return "/admin/testpage";
		}


}

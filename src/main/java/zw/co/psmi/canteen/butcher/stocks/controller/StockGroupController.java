/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
import zw.co.psmi.canteen.butcher.stocks.service.StockGroupService;

/**
 *
 * @author euny
 */
@Controller
public class StockGroupController {
    private StockGroupService stockGroupService;

    @Autowired
    public StockGroupController(StockGroupService stockGroupService) {
        this.stockGroupService = stockGroupService;
    }

    @RequestMapping(value = "/stocks/stockgroup", method = RequestMethod.GET)
    public String stockgroup(@AuthenticationPrincipal Login login, Model model) {
         model.addAttribute("stockgroupss", this.stockGroupService.findAll());
        return "/stocks/stockgroup";
    }
     @RequestMapping(value = "/stocks/addstockgroup", method = RequestMethod.GET)
    public String addstockgroup(@ModelAttribute StockGroup stockgroup, Model model) {
        model.addAttribute("stockgroups", stockgroup);
        return "/stocks/addstockgroup";
    }
     @RequestMapping(value = "/stocks/deletestockgroups/{Id}", method = RequestMethod.GET)
	   public String deletemeasurement( @PathVariable("Id") Long Id,  @ModelAttribute StockGroup stockgroup,  Model model, RedirectAttributes redirectAttributes) {
		 String msg = stockGroupService.delete(Id);
		 redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
       return "redirect:/stocks/stockgroup";
	    }
    
              @RequestMapping(value = "/stocks/editstockgroups/{Id}", method = RequestMethod.GET)
	   public String editstockgroups( @PathVariable("Id") Long Id, Model model) {
               StockGroup stockgroup = this.stockGroupService.getByID(Id);
               if(stockgroup == null){
               stockgroup = new StockGroup();
               }
               model.addAttribute("stockgroups", stockgroup);
       return "/stocks/addstockgroup";
	    }

    @RequestMapping(value = "/stocks/stockgroupform", method = RequestMethod.POST)
    public String roleform(@ModelAttribute StockGroup stockgroup, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.stockGroupService.save(stockgroup);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/stockgroup";

    }
    
}

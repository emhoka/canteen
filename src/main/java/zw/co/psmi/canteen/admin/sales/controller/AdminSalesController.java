/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.admin.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.auth.entity.User;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;
import zw.co.psmi.canteen.butcher.stocks.service.MeasurementService;
import zw.co.psmi.canteen.butcher.stocks.service.StockGroupService;
import zw.co.psmi.canteen.butcher.stocks.service.TransactionService;
import zw.co.psmi.canteen.butcher.supplier.service.SupplierService;

/**
 *
 * @author euny
 */
@Controller
public class AdminSalesController {
      private InventoryService inventoryService;
       private TransactionService transactionService;

    @Autowired
    public AdminSalesController(InventoryService inventoryService, TransactionService transactionService) {
        this.inventoryService = inventoryService;
        this.transactionService = transactionService;
    }
    @RequestMapping(value = "/admin/transaction", method = RequestMethod.GET)
     public String transaction(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("transactionss", this.transactionService.findAll());
    return "/admin/transaction";
    }
      @RequestMapping(value = "/admin/inventory", method = RequestMethod.GET)
    public String inventory(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("inventoryss", this.inventoryService.findAll());
        return "/admin/inventory";
    }
    
//    
//    	@RequestMapping(value = "/templates/admin/adminsales", method = RequestMethod.GET)
//    public ModelAndView adminsales(ModelAndView model, @ModelAttribute User user) {        
//        model.setViewName("/templates/admin/adminsales");
//        model.addObject("login", Getuser());
//        return model;
//   }
    
}

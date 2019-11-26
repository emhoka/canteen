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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;
import zw.co.psmi.canteen.butcher.stocks.service.StockRequestService;
import zw.co.psmi.canteen.butcher.stocks.service.TransactionService;

/**
 *
 * @author euny
 */
@Controller
public class CheckoutController {
        private StockRequestService stockTakeService;
    private TransactionService transactionService;
    @Autowired
    public CheckoutController(StockRequestService stockTakeService, TransactionService transactionService){
    this.stockTakeService = stockTakeService;
    this.transactionService = transactionService;
    }
    
    
    @RequestMapping(value = "/stocks/checkoutproduct", method = RequestMethod.GET)
    public String checkoutproduct(@AuthenticationPrincipal Login login, Model model){
        model.addAttribute("stocktakess", this.stockTakeService.findAll());
    return "/stocks/checkoutproduct";
    }
      @RequestMapping(value = "/stocks/checkoutcart", method = RequestMethod.GET)
     public String checkoutcart(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("transactionss", this.transactionService.findAll());
    return "/stocks/checkoutcart";
    }
    
        @RequestMapping(value = "/stocks/stocks", method = RequestMethod.GET)
    public String stocks(){
    return "/stocks/stocks";
    }
}

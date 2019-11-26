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
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.entity.Transaction;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;
import zw.co.psmi.canteen.butcher.stocks.service.StockRequestService;

/**
 *
 * @author euny
 */
@Controller
public class StockRequestController {

    
    private StockRequestService stockRequestService;
    private InventoryService inventoryService;

    @Autowired
    public StockRequestController(InventoryService inventoryService, StockRequestService stockRequestService) {
        this.inventoryService = inventoryService;
        this.stockRequestService = stockRequestService;
    }

    @RequestMapping(value = "/stocks/stockrequests", method = RequestMethod.GET)
    public String stockrequests(@AuthenticationPrincipal Login login,Model model) {
        model.addAttribute("stockRequestss", this.stockRequestService.findAll());
        return "/stocks/stockrequests";
    }

    @RequestMapping(value = "/stocks/deletestockrequests/{Id}", method = RequestMethod.GET)
    public String deletestockrequests(@PathVariable("Id") Long Id, Model model, RedirectAttributes redirectAttributes) {
        String msg = stockRequestService.delete(Id);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/stockrequests";
    }

    @RequestMapping(value = "/stocks/editstockrequests/{Id}", method = RequestMethod.GET)
    public String editstockrequests(@PathVariable("Id") Long Id, Model model) {
        StockRequest stockRequest = this.stockRequestService.getByID(Id);
        if (stockRequest == null) {
            stockRequest = new StockRequest();
        }
         model.addAttribute("stockRequest", stockRequest);
        model.addAttribute("inventorys", this.inventoryService.findAll());
        return "/stocks/addstockrequest";
    }
      @RequestMapping(value = "/stocks/addstockrequest/{id}", method = RequestMethod.GET)
    public String addstockrequest(@PathVariable("id") Long id, Model model) {
        Inventory inventory = this.inventoryService.getByID(id);
        StockRequest stockRequest = new StockRequest();
        stockRequest.setInventory(inventory);
        model.addAttribute("stockRequest", stockRequest);
        return "/stocks/addstockrequest";
    }
    
    @RequestMapping(value = "/stocks/stockrequestform", method = RequestMethod.POST)
    public String stockcountform(@ModelAttribute StockRequest stockRequest, Model model, RedirectAttributes redirectAttributes) {
        String msg = stockRequestService.saveStockRequest(stockRequest);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/stockrequests";
    }
    
}

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
import zw.co.psmi.canteen.butcher.stocks.entity.PaymentMode;
import zw.co.psmi.canteen.butcher.stocks.service.MeasurementService;
import zw.co.psmi.canteen.butcher.stocks.service.PaymentModeService;

/**
 *
 * @author euny
 */
@Controller
public class PaymentModeController {
    
    private PaymentModeService paymentModeService;
    
    @Autowired
    public PaymentModeController(PaymentModeService paymentModeService) {
        this.paymentModeService = paymentModeService;
    }

    
     @RequestMapping(value = "/stocks/paymentmode", method = RequestMethod.GET)
    public String paymentmode(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("paymentModess", this.paymentModeService.findAll());
        return "/stocks/paymentmode";
    }
    @RequestMapping(value = "/stocks/addpaymentmode", method = RequestMethod.GET)
    public String addpaymentmode(@ModelAttribute PaymentMode paymentMode, Model model) {
        model.addAttribute("paymentModes", paymentMode);
        return "/stocks/addpaymentmode";
    }
    
    @RequestMapping(value = "/stocks/deletepaymentmode/{Id}", method = RequestMethod.GET)
	   public String deletepaymentmode( @PathVariable("Id") Long Id,  @ModelAttribute PaymentMode paymentMode,  Model model, RedirectAttributes redirectAttributes) {
		 String msg = paymentModeService.delete(Id);
		 redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
       return "redirect:/stocks/paymentmode";
	    }
              @RequestMapping(value = "/stocks/editpaymentmode/{Id}", method = RequestMethod.GET)
	   public String editpaymentmode( @PathVariable("Id") Long Id, Model model) {
               PaymentMode paymentMode = this.paymentModeService.getByID(Id);
               if(paymentMode == null){
               paymentMode = new PaymentMode();
               }
                model.addAttribute("paymentModes", paymentMode);
       return "/stocks/addpaymentmode";
	    }

    @RequestMapping(value = "/stocks/paymentmodeform", method = RequestMethod.POST)
    public String paymentmodeform(@ModelAttribute PaymentMode paymentMode, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.paymentModeService.save(paymentMode);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/paymentmode";

    }

}

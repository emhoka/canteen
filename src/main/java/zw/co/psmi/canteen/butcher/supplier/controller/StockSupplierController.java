/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.supplier.controller;

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
import zw.co.psmi.canteen.butcher.supplier.entity.Supplier;
import zw.co.psmi.canteen.butcher.supplier.service.SupplierService;

/**
 *
 * @author euny
 */
@Controller
public class StockSupplierController {
    private SupplierService supplierService;

    @Autowired
    public StockSupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

 @RequestMapping(value = "/stocks/supplier", method = RequestMethod.GET)
    public String supplier(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("supplierss", this.supplierService.findAll());
    return "/stocks/supplier";
    }
    @RequestMapping(value = "/stocks/addsupplier", method = RequestMethod.GET)
    public String addsupplier(@ModelAttribute Supplier supplier, Model model) {
        model.addAttribute("suppliers", supplier);
        return "/stocks/addsupplier";
    }
    
    @RequestMapping(value = "/stocks/deletesupplier/{Id}", method = RequestMethod.GET)
	   public String deletesupplier( @PathVariable("Id") Long Id,  @ModelAttribute Supplier supplier,  Model model, RedirectAttributes redirectAttributes) {
		 String msg = supplierService.delete(Id);
		 redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
       return "redirect:/stocks/supplier";
	    }
           
             
     @RequestMapping(value = "/stocks/editsupplier/{Id}", method = RequestMethod.GET)
    public String editsupplier(@PathVariable("Id") Long Id,  Model model) {
        Supplier supplier = this.supplierService.getByID(Id);

        if (supplier == null) {
            supplier = new Supplier();
        }
        model.addAttribute("suppliers", supplier);
        return "/stocks/addsupplier";
    }

    @RequestMapping(value = "/stocks/supplierform", method = RequestMethod.POST)
    public String supplierform(@ModelAttribute Supplier supplier, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.supplierService.save(supplier);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/supplier";

    }
    
    
}

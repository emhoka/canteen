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
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.entity.Measurement;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;
import zw.co.psmi.canteen.butcher.stocks.service.MeasurementService;
import zw.co.psmi.canteen.butcher.stocks.service.StockGroupService;
import zw.co.psmi.canteen.butcher.supplier.entity.Supplier;
import zw.co.psmi.canteen.butcher.supplier.service.SupplierService;

/**
 *
 * @author euny
 */
@Controller
public class InventoryController {

    private InventoryService inventoryService;
    private SupplierService supplierService;
    private StockGroupService stockGroupService;
    private MeasurementService measurementService;

    @Autowired
    public InventoryController(InventoryService inventoryService, SupplierService supplierService, StockGroupService stockGroupService, MeasurementService measurementService) {
        this.inventoryService = inventoryService;
        this.supplierService = supplierService;
        this.stockGroupService = stockGroupService;
        this.measurementService = measurementService;
    }

    @RequestMapping(value = "/stocks/inventory", method = RequestMethod.GET)
    public String inventory(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("inventoryss", this.inventoryService.findAll());
        return "/stocks/inventory";
    }

    @RequestMapping(value = "/stocks/addinventory", method = RequestMethod.GET)
    public String addinventory(@ModelAttribute Inventory inventory, @ModelAttribute Measurement measurement, @ModelAttribute StockGroup stockgroup, @ModelAttribute Supplier supplier, Model model) {
        model.addAttribute("inventorys", inventory);
        model.addAttribute("measurements", this.measurementService.findAll());
        model.addAttribute("stockgroups", this.stockGroupService.findAll());
        model.addAttribute("suppliers", this.supplierService.findAll());
        return "/stocks/addinventory";
    }

    @RequestMapping(value = "/stocks/deleteinventory/{Id}", method = RequestMethod.GET)
    public String deleteinventory(@PathVariable("Id") Long Id, @ModelAttribute Measurement measurement, @ModelAttribute StockGroup stockgroup, @ModelAttribute Supplier supplier, Model model, RedirectAttributes redirectAttributes) {
        String msg = inventoryService.delete(Id);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/inventory";
    }

    @RequestMapping(value = "/stocks/editinventory/{Id}", method = RequestMethod.GET)
    public String editinventory(@PathVariable("Id") Long Id, @ModelAttribute Measurement measurement, @ModelAttribute StockGroup stockgroup, @ModelAttribute Supplier supplier, Model model) {
        Inventory inventory = this.inventoryService.getByID(Id);

        if (inventory == null) {
            inventory = new Inventory();
        }
        model.addAttribute("inventorys", inventory);
        model.addAttribute("measurements", this.measurementService.findAll());
        model.addAttribute("stockgroups", this.stockGroupService.findAll());
        model.addAttribute("suppliers", this.supplierService.findAll());
        return "/stocks/addinventory";
    }
    

    @RequestMapping(value = "/stocks/inventoryform", method = RequestMethod.POST)
    public String inventoryform(@ModelAttribute Inventory inventory, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.inventoryService.save(inventory);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/inventory";
    }
    

   
}

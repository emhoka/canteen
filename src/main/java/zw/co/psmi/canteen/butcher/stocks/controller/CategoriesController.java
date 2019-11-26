/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.butcher.stocks.entity.Categories;
import zw.co.psmi.canteen.butcher.stocks.entity.Measurement;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
import zw.co.psmi.canteen.butcher.stocks.service.CategoriesService;
import zw.co.psmi.canteen.butcher.stocks.service.MeasurementService;
import zw.co.psmi.canteen.butcher.stocks.service.StockGroupService;

/**
 *
 * @author euny
 */
@Controller
public class CategoriesController {

    private CategoriesService categoriesService;
    private StockGroupService stockGroupService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService, StockGroupService stockGroupService) {
        this.categoriesService = categoriesService;
        this.stockGroupService = stockGroupService;
    }

    @RequestMapping(value = "/stocks/category", method = RequestMethod.GET)
    public String category(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("categoriess", this.categoriesService.findAll());
        return "/stocks/category";
    }

    @RequestMapping(value = "/stocks/categorybystockgroup", method = RequestMethod.GET)
    @ResponseBody
    public List<Categories> getCategoryByStockGroup() {
        StockGroup stockGroup = stockGroupService.getByID(1L);
        return categoriesService.getByStockGroup(stockGroup);
    }

    @RequestMapping(value = "/stocks/addcategory/{id}", method = RequestMethod.GET)
    public String addcategory(@PathVariable("id") Long id, Model model) {
        StockGroup stockGroup = this.stockGroupService.getByID(id);
        Categories categories = new Categories();
        categories.setStockGroup(stockGroup);
        model.addAttribute("categories", categories);
        return "/stocks/addcategory";
    }

    @RequestMapping(value = "/stocks/deletecategory/{Id}", method = RequestMethod.GET)
    public String deletecategory(@PathVariable("Id") Long Id, @ModelAttribute Categories categories, Model model, RedirectAttributes redirectAttributes) {
        String msg = categoriesService.delete(Id);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/category";
    }

    @RequestMapping(value = "/stocks/editcategory/{Id}", method = RequestMethod.GET)
    public String editcategory(@PathVariable("Id") Long Id, Model model) {
        Categories categories = this.categoriesService.getByID(Id);
        if (categories == null) {
            categories = new Categories();
        }
        model.addAttribute("categories", categories);
        return "/stocks/addcategory";
    }

    @RequestMapping(value = "/stocks/categoryform", method = RequestMethod.POST)
    public String categoryform(@ModelAttribute Categories categories, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.categoriesService.saveCategory(categories);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/category";

    }

}

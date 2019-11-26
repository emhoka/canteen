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
import zw.co.psmi.canteen.butcher.stocks.entity.Measurement;
import zw.co.psmi.canteen.butcher.stocks.service.MeasurementService;

/**
 *
 * @author euny
 */
@Controller
public class MeasurementController {

    private MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }
    @RequestMapping(value = "/stocks/measurement", method = RequestMethod.GET)
    public String measurement(@AuthenticationPrincipal Login login, Model model) {
         model.addAttribute("measurementss", this.measurementService.findAll());
        return "/stocks/measurement";
    }
        @RequestMapping(value = "/stocks/addmeasurement", method = RequestMethod.GET)
    public String addmeasurement(@ModelAttribute Measurement measurement, Model model) {
        model.addAttribute("measurements", measurement);
        return "/stocks/addmeasurement";
    }
    @RequestMapping(value = "/stocks/deletemeasurement/{Id}", method = RequestMethod.GET)
	   public String deletemeasurement( @PathVariable("Id") Long Id,  @ModelAttribute Measurement measurement,  Model model, RedirectAttributes redirectAttributes) {
		 String msg = measurementService.delete(Id);
		 redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
       return "redirect:/stocks/measurement";
	    }
              @RequestMapping(value = "/stocks/editmeasurement/{Id}", method = RequestMethod.GET)
	   public String editmeasurement( @PathVariable("Id") Long Id, Model model) {
               Measurement measurement = this.measurementService.getByID(Id);
               if(measurement == null){
               measurement = new Measurement();
               }
              model.addAttribute("measurements", measurement);
       return "/stocks/addmeasurement";
	    }

    @RequestMapping(value = "/stocks/measurementform", method = RequestMethod.POST)
    public String measurementform(@ModelAttribute Measurement measurement, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.measurementService.save(measurement);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/measurement";

    }


}

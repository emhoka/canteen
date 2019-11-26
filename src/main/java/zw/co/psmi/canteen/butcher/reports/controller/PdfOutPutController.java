package zw.co.psmi.canteen.butcher.reports.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.butcher.reports.dao.PdfOutputDao;
import zw.co.psmi.canteen.butcher.reports.entity.OutputType;
import zw.co.psmi.canteen.butcher.reports.service.BasePdfOutputService;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;

@Controller
@Slf4j
public class PdfOutPutController {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private BasePdfOutputService basePdfOutputService;
    @Autowired
    private PdfOutputDao reportDao;
     @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/reports/inventoryreport", method = RequestMethod.GET)
    public String inventoryreport(@AuthenticationPrincipal Login login, Model model) {
        
        List<Long> ids = new ArrayList<>();
        for( Inventory inventorys:this.inventoryService.findAll()){
        ids.add(inventorys.getId());
        }
        model.addAttribute("ids", ids);
        return "/reports/inventoryreport";
    }
    
    @RequestMapping(value = "/reports/transactionreport", method = RequestMethod.GET)
    public String transactionreport(Model model) {
        return "/reports/transactionreport";
    }
    
      @RequestMapping(value = "/reports/pdfoutput/{id}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] outputReport(@PathVariable Long id, @PathVariable OutputType type) throws DocumentException {
        return basePdfOutputService.outputReport(id, type);
    }
    @RequestMapping(value = "/reports/pdfoutputbyids/{idsString}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] outputReport(@PathVariable String idsString, @PathVariable OutputType type) {
        List<Long> ids = null;
        try {
            ids = mapper.readValue(idsString, new TypeReference<List<Long>>() {
            });
        } catch (Exception e) {
        }
        return basePdfOutputService.outputReportFromList(ids, type);
    }
}

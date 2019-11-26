package zw.co.psmi.canteen.butcher.stocks.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import zw.co.psmi.canteen.auth.entity.User;
import zw.co.psmi.canteen.auth.service.UserService;
import zw.co.psmi.canteen.butcher.stocks.entity.PaymentMode;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.entity.Transaction;
import zw.co.psmi.canteen.butcher.stocks.service.CategoriesService;
import zw.co.psmi.canteen.butcher.stocks.service.PaymentModeService;
import zw.co.psmi.canteen.butcher.stocks.service.StockRequestService;
import zw.co.psmi.canteen.butcher.stocks.service.TransactionService;

/**
 *
 * @author euny
 */
@Controller
public class TransactionController {

    private TransactionService transactionService;
    private StockRequestService stockRequestService;
    private PaymentModeService paymentModeService;
    private UserService userService;
    private CategoriesService categoriesService;

    @Autowired
    public TransactionController(CategoriesService categoriesService, TransactionService transactionService, StockRequestService stockRequestService, PaymentModeService paymentModeService, UserService userService) {
        this.transactionService = transactionService;
        this.stockRequestService = stockRequestService;
        this.paymentModeService = paymentModeService;
        this.userService = userService;
        this.categoriesService = categoriesService;
    }

    @RequestMapping(value = "/stocks/transaction", method = RequestMethod.GET)
    public String transaction(@AuthenticationPrincipal Login login, Model model) {
        model.addAttribute("transactionss", this.transactionService.findAll());
        return "/stocks/transaction";
    }

    @RequestMapping(value = "/stocks/addtocart/{id}", method = RequestMethod.GET)
    public String transaction(@PathVariable("id") Long id, Model model) {
        StockRequest stockRequest = this.stockRequestService.getByID(id);
        StockGroup stockGroup = stockRequest.getInventory().getStockgroup();
        Transaction transaction = new Transaction();
        transaction.setStockRequest(stockRequest);
        model.addAttribute("transaction", transaction);
        model.addAttribute("paymentModes", this.paymentModeService.findAll());
        model.addAttribute("categoriess", categoriesService.getByStockGroup(stockGroup));
//         model.addAttribute("categoriess", this.categoriesService.findAll());
        return "/stocks/addtocart";
    }

    @RequestMapping(value = "/stocks/transactionform", method = RequestMethod.POST)
    public String transactionform(@ModelAttribute Transaction transaction, Model model, RedirectAttributes redirectAttributes) {
        String msg = transactionService.saveStockTransaction(transaction);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/checkoutcart";
    }
}

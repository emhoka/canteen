/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service;

import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.stocks.entity.Transaction;

/**
 *
 * @author euny
 */
public interface TransactionService extends BasicService<Transaction> {
	
public	Transaction findByQuantitySold(String quantitySold);
String delete(long id);
 public String saveStockTransaction(Transaction transaction);
}

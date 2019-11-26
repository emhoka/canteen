/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.entity.Transaction;

/**
 *
 * @author euny
 */
public interface TransactionDao extends JpaRepository<Transaction, Long> {
	public	Transaction findByQuantitySold(String quantitySold);
        public StockRequest findByStockRequest (StockRequest stockRequest);

}
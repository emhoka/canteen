/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.dao;

import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;


/**
 *
 * @author euny
 */
public interface StockRequestDao extends JpaRepository<StockRequest, Long> {
	 public StockRequest findByStockRequestQuantity(String stockRequestQuantity);
         public StockRequest findByInventory(Inventory inventory);
}

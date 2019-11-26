/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service;

import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;

/**
 *
 * @author euny
 */
public interface StockRequestService  extends BasicService<StockRequest> {
    public StockRequest findByStockRequestQuantity(String stockRequestQuantity);
    String delete(long id);
    public String saveStockRequest(StockRequest stockRequest);
}

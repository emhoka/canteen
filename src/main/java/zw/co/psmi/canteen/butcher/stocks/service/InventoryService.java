/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service;

import java.util.List;
import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
/**
 *
 * @author euny
 */
public interface InventoryService extends BasicService<Inventory> {
    public Inventory findByOriginalPrice(String originalPrice);
    String delete(long id);
}

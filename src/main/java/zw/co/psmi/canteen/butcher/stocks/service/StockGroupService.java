/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service;

import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;

/**
 *
 * @author euny
 */
public interface StockGroupService extends BasicService<StockGroup> {
    public StockGroup findByName(String name);
String delete(long id);
    
}

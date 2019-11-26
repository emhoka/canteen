/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
/**
 *
 * @author euny
 */
public interface StockGroupDao extends JpaRepository<StockGroup, Long> {
	  public StockGroup findByName(String name);
    
}

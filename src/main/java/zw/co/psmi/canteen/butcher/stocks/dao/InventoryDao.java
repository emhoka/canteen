/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;

/**
 *
 * @author euny
 */
public interface InventoryDao extends JpaRepository<Inventory, Long> {

    public Inventory findByOriginalPrice(String originalPrice);

    
}

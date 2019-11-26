/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.canteen.butcher.stocks.dao.InventoryDao;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;
import zw.co.psmi.canteen.butcher.stocks.dao.StockRequestDao;

/**
 *
 * @author euny
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private StockRequestDao stockTakeDao;
    private InventoryDao inventoryDao;

    @Autowired
    public InventoryServiceImpl(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(Inventory inventory) {
        this.inventoryDao.save(inventory);
        return "Sucessfully saved inventory";
    }

    @Override
    public String delete(long id) {
        this.inventoryDao.delete(id);
        return "Sucessfully deleted inventory";
    }

    @Override
    public Inventory getByID(Long inventoryID) {
        Inventory inventory = this.inventoryDao.findOne(inventoryID);
        if (inventory == null) {
            inventory = new Inventory();
        }
        return inventory;
    }

    @Override
    public JpaRepository<Inventory, Long> getDao() {
        return inventoryDao;
    }

    @Override
    public Inventory findByOriginalPrice(String originalPrice) {
        // TODO Auto-generated method stub
        return inventoryDao.findByOriginalPrice(originalPrice);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryDao.findAll();
    }
}

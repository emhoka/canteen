/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.canteen.butcher.stocks.dao.InventoryDao;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.service.StockRequestService;
import zw.co.psmi.canteen.butcher.stocks.dao.StockRequestDao;

/**
 *
 * @author euny
 */
@Service
public class StockRequestServiceImpl implements StockRequestService {

    private StockRequestDao stockRequestDao;
    private InventoryDao inventoryDao;

    @Autowired
    public StockRequestServiceImpl(StockRequestDao stockRequestDao, InventoryDao inventoryDao) {
        this.stockRequestDao = stockRequestDao;
        this.inventoryDao = inventoryDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(StockRequest stockRequest) {
        this.stockRequestDao.save(stockRequest);
        return "Sucessfully saved current Stock";
    }

    @Override
    public String delete(long id) {
        this.stockRequestDao.delete(id);
        return "Sucessfully deleted current Stock";
    }

    @Override
    public StockRequest getByID(Long currentStockID) {
        StockRequest stockRequest = this.stockRequestDao.findOne(currentStockID);
        if (stockRequest == null) {
            stockRequest = new StockRequest();
        }
        return stockRequest;
    }

    @Override
    public JpaRepository<StockRequest, Long> getDao() {
        return stockRequestDao;
    }

    @Override
    public StockRequest findByStockRequestQuantity(String stockRequestQuantity) {
        return stockRequestDao.findByStockRequestQuantity(stockRequestQuantity);
    }

    @Override
    public List<StockRequest> findAll() {
        return stockRequestDao.findAll();
    }

    @Override
    public String saveStockRequest(StockRequest stockRequest) {
        Inventory inventory = stockRequest.getInventory();
        int qua = Integer.parseInt(inventory.getQuantity());
        int qua2 = Integer.parseInt(stockRequest.getStockRequestQuantity());
        int leftQuantity = qua - qua2;
        inventory.setQuantity(String.valueOf(leftQuantity));
        inventoryDao.save(inventory);
        this.stockRequestDao.save(stockRequest);
        return "Saved succefully";
    }
}

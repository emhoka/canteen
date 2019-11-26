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
import zw.co.psmi.canteen.auth.dao.UserDao;
import zw.co.psmi.canteen.auth.entity.User;
import zw.co.psmi.canteen.butcher.stocks.dao.StockRequestDao;
import zw.co.psmi.canteen.butcher.stocks.dao.TransactionDao;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.entity.StockRequest;
import zw.co.psmi.canteen.butcher.stocks.entity.Transaction;
import zw.co.psmi.canteen.butcher.stocks.service.TransactionService;

/**
 *
 * @author euny
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;
    private StockRequestDao stockRequestDao;
    private UserDao userDao;

    @Autowired
    public TransactionServiceImpl(StockRequestDao stockRequestDao, TransactionDao transactionDao, UserDao userDao) {
        this.stockRequestDao = stockRequestDao;
        this.transactionDao = transactionDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(Transaction transaction) {
        this.transactionDao.save(transaction);
        return "Sucessfully saved transaction ";
    }

    @Override
    public String delete(long id) {
        this.stockRequestDao.delete(id);
        return "Sucessfully deleted transaction";
    }

    @Override
    public Transaction getByID(Long currentStockID) {
        Transaction transaction = this.transactionDao.findOne(currentStockID);
        if (transaction == null) {
            transaction = new Transaction();
        }
        return transaction;
    }

    @Override
    public JpaRepository<Transaction, Long> getDao() {
        return transactionDao;
    }

    @Override
    public Transaction findByQuantitySold(String quantitySold) {
        // TODO Auto-generated method stub
        return transactionDao.findByQuantitySold(quantitySold);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public String saveStockTransaction(Transaction transaction) {
        StockRequest stockRequest = transaction.getStockRequest();
        int qua = Integer.parseInt(stockRequest.getStockRequestQuantity());
        int qua2 = Integer.parseInt(transaction.getQuantitySold());
        int leftQuantity = qua - qua2;
        stockRequest.setStockRequestQuantity(String.valueOf(leftQuantity));
        stockRequestDao.save(stockRequest);
        this.transactionDao.save(transaction);
        return "Saved succefully";
    }
    

}

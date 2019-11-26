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
import zw.co.psmi.canteen.butcher.stocks.dao.StockGroupDao;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
import zw.co.psmi.canteen.butcher.stocks.service.StockGroupService;

/**
 *
 * @author euny
 */
@Service
public class StockGroupServiceImpl implements StockGroupService{
    
	 private StockGroupDao stockGroupDao;

	    @Autowired
	    public StockGroupServiceImpl(StockGroupDao stockGroupDao) {
	        this.stockGroupDao = stockGroupDao;
	    }

	    @Override
	    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	    public String save(StockGroup stockgroup) {
	    	this.stockGroupDao.save(stockgroup);
	        return "Sucessfully saved stock group";
	    }
	    
	    @Override
	    public String delete(long id) {
	        this.stockGroupDao.delete(id);
	        return "Sucessfully deleted stock group";
	    }

	    @Override
	    public StockGroup getByID(Long stockGroupID) {
	    	StockGroup stockgroup = this.stockGroupDao.findOne(stockGroupID);
	        if (stockgroup == null) {
	        	stockgroup = new StockGroup();
	        }
	        return stockgroup;
	    }

		@Override
		public JpaRepository<StockGroup, Long> getDao() {
			 return stockGroupDao;
		}


		@Override
		public StockGroup findByName(String name) {
			// TODO Auto-generated method stub
			return stockGroupDao.findByName(name);
		}

		  @Override
		    public List<StockGroup> findAll() {
		        return stockGroupDao.findAll();
		    }

    
}

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
import zw.co.psmi.canteen.butcher.stocks.dao.PaymentModeDao;
import zw.co.psmi.canteen.butcher.stocks.entity.PaymentMode;
import zw.co.psmi.canteen.butcher.stocks.service.PaymentModeService;

/**
 *
 * @author euny
 */
@Service
public class PaymentModeServiceImpl implements PaymentModeService{

	
	 private PaymentModeDao paymentModeDao;

	    @Autowired
	    public PaymentModeServiceImpl(PaymentModeDao paymentModeDao) {
	        this.paymentModeDao = paymentModeDao;
	    }

	    @Override
	    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	    public String save(PaymentMode paymentMode) {
	    	this.paymentModeDao.save(paymentMode);
	        return "Sucessfully saved paymentMode";
	    }
	    
	    @Override
	    public String delete(long id) {
	        this.paymentModeDao.delete(id);
	        return "Sucessfully deleted paymentMode";
	    }

	    @Override
	    public PaymentMode getByID(Long paymentModeID) {
	    	PaymentMode paymentMode = this.paymentModeDao.findOne(paymentModeID);
	        if (paymentMode == null) {
	        	paymentMode = new PaymentMode();
	        }
	        return paymentMode;
	    }

		@Override
		public JpaRepository<PaymentMode, Long> getDao() {
			 return paymentModeDao;
		}


		@Override
		public PaymentMode findByName(String name) {
			// TODO Auto-generated method stub
			return paymentModeDao.findByName(name);
		}

		  @Override
		    public List<PaymentMode> findAll() {
		        return paymentModeDao.findAll();
		    }

		
}

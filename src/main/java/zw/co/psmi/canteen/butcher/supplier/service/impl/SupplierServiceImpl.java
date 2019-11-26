/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.supplier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.canteen.butcher.supplier.dao.SupplierDao;
import zw.co.psmi.canteen.butcher.supplier.entity.Supplier;
import zw.co.psmi.canteen.butcher.supplier.service.SupplierService;

/**
 *
 * @author euny
 */
@Service
public class SupplierServiceImpl implements SupplierService {
     private SupplierDao supplierDao;

	    @Autowired
	    public SupplierServiceImpl(SupplierDao supplierDao) {
	        this.supplierDao = supplierDao;
	    }

	    @Override
	    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	    public String save(Supplier supplier) {
	    	this.supplierDao.save(supplier);
	        return "Sucessfully saved Supplier";
	    }
	    
	    @Override
	    public String delete(long id) {
	        this.supplierDao.delete(id);
	        return "Sucessfully deleted supplier";
	    }

	    @Override
	    public Supplier getByID(Long supplierID) {
	    	Supplier supplier = this.supplierDao.findOne(supplierID);
	        if (supplier == null) {
	        	supplier = new Supplier();
	        }
	        return supplier;
	    }

		@Override
		public JpaRepository<Supplier, Long> getDao() {
			 return supplierDao;
		}


		@Override
		public Supplier findByName(String name) {
			return supplierDao.findByName(name);
		}

		  @Override
		    public List<Supplier> findAll() {
		        return supplierDao.findAll();
		    }

    
}

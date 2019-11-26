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
import zw.co.psmi.canteen.butcher.stocks.dao.MeasurementDao;
import zw.co.psmi.canteen.butcher.stocks.entity.Measurement;
import zw.co.psmi.canteen.butcher.stocks.service.MeasurementService;

/**
 *
 * @author euny
 */
@Service
public class MeasurementServiceImpl implements MeasurementService{

	
	 private MeasurementDao measurementDao;

	    @Autowired
	    public MeasurementServiceImpl(MeasurementDao measurementDao) {
	        this.measurementDao = measurementDao;
	    }

	    @Override
	    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	    public String save(Measurement measurement) {
	    	this.measurementDao.save(measurement);
	        return "Sucessfully saved measurement name";
	    }
	    
	    @Override
	    public String delete(long id) {
	        this.measurementDao.delete(id);
	        return "Sucessfully deleted measurement name";
	    }

	    @Override
	    public Measurement getByID(Long measurementID) {
	    	Measurement measurement = this.measurementDao.findOne(measurementID);
	        if (measurement == null) {
	        	measurement = new Measurement();
	        }
	        return measurement;
	    }

		@Override
		public JpaRepository<Measurement, Long> getDao() {
			 return measurementDao;
		}


		@Override
		public Measurement findByMeasurementName(String measurementName) {
			// TODO Auto-generated method stub
			return measurementDao.findByMeasurementName(measurementName);
		}

		  @Override
		    public List<Measurement> findAll() {
		        return measurementDao.findAll();
		    }

}

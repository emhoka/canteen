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
import zw.co.psmi.canteen.butcher.stocks.dao.CategoriesDao;
import zw.co.psmi.canteen.butcher.stocks.dao.StockGroupDao;
import zw.co.psmi.canteen.butcher.stocks.entity.Categories;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;
import zw.co.psmi.canteen.butcher.stocks.service.CategoriesService;

/**
 *
 * @author euny
 */
@Service
public class CategoriesServiceImpl implements CategoriesService{

	
	 private CategoriesDao categoriesDao;
         private StockGroupDao stockGroupDao;

	    @Autowired
	    public CategoriesServiceImpl(CategoriesDao categoriesDao, StockGroupDao stockGroupDao) {
	        this.categoriesDao = categoriesDao;
                this.stockGroupDao = stockGroupDao;
	    }

	    @Override
	    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	    public String save(Categories categories) {
	    	this.categoriesDao.save(categories);
	        return "Sucessfully saved categories";
	    }
	    
	    @Override
	    public String delete(long id) {
	        this.categoriesDao.delete(id);
	        return "Sucessfully deleted categories";
	    }

	    @Override
	    public Categories getByID(Long categoriesID) {
	    	Categories categories = this.categoriesDao.findOne(categoriesID);
	        if (categories == null) {
	        	categories = new Categories();
	        }
	        return categories;
	    }

		@Override
		public JpaRepository<Categories, Long> getDao() {
			 return categoriesDao;
		}


		@Override
		public Categories findByCategory(String category) {
			// TODO Auto-generated method stub
			return categoriesDao.findByCategory(category);
		}

		  @Override
		    public List<Categories> findAll() {
		        return categoriesDao.findAll();
		    }
                    
                     @Override
    public String saveCategory(Categories categories) {
        StockGroup stockGroup  = categories.getStockGroup();
         stockGroupDao.save(stockGroup);
        this.categoriesDao.save(categories);
        return "Saved succefully";
    }

    @Override
    public List<Categories> getByStockGroup(StockGroup stockGroup) {
       return categoriesDao.findByStockGroup(stockGroup);
    }

}

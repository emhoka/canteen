/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.canteen.butcher.stocks.entity.Categories;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;

/**
 *
 * @author euny
 */
public interface CategoriesDao extends JpaRepository<Categories, Long> {
	public	Categories findByCategory(String category);
        //public Categories findByStockGroup(StockGroup stockGroup);
        public List<Categories> findByStockGroup(StockGroup stockGroup);
}
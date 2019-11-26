/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service;

import java.util.List;
import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.stocks.entity.Categories;
import zw.co.psmi.canteen.butcher.stocks.entity.StockGroup;

/**
 *
 * @author euny
 */
public interface CategoriesService extends BasicService<Categories> {

    public Categories findByCategory(String category);

    String delete(long id);

    public String saveCategory(Categories categories);

    public List<Categories> getByStockGroup(StockGroup stockGroup);
}

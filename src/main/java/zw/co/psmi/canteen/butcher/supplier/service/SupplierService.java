/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.supplier.service;
import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.supplier.entity.Supplier;

/**
 *
 * @author euny
 */
public interface SupplierService extends BasicService<Supplier> {
    public Supplier findByName(String name);
String delete(long id);
}

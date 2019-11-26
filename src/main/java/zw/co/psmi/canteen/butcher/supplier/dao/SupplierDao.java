/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.supplier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.canteen.butcher.supplier.entity.Supplier;

/**
 *
 * @author euny
 */
public interface SupplierDao extends JpaRepository<Supplier, Long> {
    public	Supplier findByName(String name);
}

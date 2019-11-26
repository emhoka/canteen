/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.canteen.basic.BaseEntity;
import zw.co.psmi.canteen.butcher.supplier.entity.Supplier;

/**
 *
 * @author euny
 */
@Entity
@Data
@Table(name = "_inventory")
public class Inventory extends BaseEntity {

    private String quantity;
    private String originalPrice;
    @ManyToOne
    private StockGroup stockgroup;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Measurement measurement;

    @Override
    public String toString() {
        return "inventory{" + "id=" + id + ", quantity=" + quantity + ",originalPrice=" + originalPrice + "}";
    }
  @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final BaseEntity other = (BaseEntity) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }
}

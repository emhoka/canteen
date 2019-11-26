/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.supplier.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.canteen.basic.BaseEntity;

/**
 *
 * @author euny
 */
@Entity
@Data
@Table(name ="_suppliers")
public class Supplier extends BaseEntity{
    private String name;
    private String address;
    private String contact;
    
    @Override
    public String toString(){
        return "suppliers{" + "id=" + id + ", name=" + name + ", address ="+address+",contact ="+contact+" }";
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

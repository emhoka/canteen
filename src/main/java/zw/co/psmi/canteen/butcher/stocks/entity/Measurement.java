/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.entity;

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
@Table(name="_measurement")
public class Measurement extends BaseEntity{
	private String measurementName;
        	@Override
    public String toString() {
        return "role{" + "id=" + id + ", measurementName=" + measurementName + "}";
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

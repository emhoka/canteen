package zw.co.psmi.canteen.admin.role.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import zw.co.psmi.canteen.basic.BaseEntity;


@Entity
@Data
@Table(name="_role")
public class Role extends BaseEntity{
	private String roleName;
	
	@Override
    public String toString() {
        return "role{" + "id=" + id + ", roleName=" + roleName + "}";
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

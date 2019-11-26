package zw.co.psmi.canteen.auth.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.canteen.admin.role.entity.Role;
import zw.co.psmi.canteen.basic.BaseEntity;

@Entity
@Data
@Table(name="_users")
public class User extends BaseEntity{
    private String name;
    private String surname;
    private String username;
      private String phone;
    
    @ManyToOne
    private Role role;
    @javax.persistence.Lob
    private String password;
    

    @Override
    public String toString() {
        return "users{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password + ", phone = "+ phone+"}";
    }

}

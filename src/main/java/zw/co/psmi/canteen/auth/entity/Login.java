package zw.co.psmi.canteen.auth.entity;

import java.util.Collection;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import zw.co.psmi.canteen.auth.entity.Login;

@Data
public class Login extends User{
	
	private Long userID;
	//private String unitName;
	    
	    public Login(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	        super(username, password, authorities);
	        // TODO Auto-generated constructor stub
	    }

		public void setUserID(Object id) {
			// TODO Auto-generated method stub
			
		}
}

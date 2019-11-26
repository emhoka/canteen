package zw.co.psmi.canteen.auth.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import zw.co.psmi.canteen.auth.entity.Login;
import zw.co.psmi.canteen.auth.entity.User;
import zw.co.psmi.canteen.auth.service.UserService;

@Data
@Slf4j

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

   
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 System.out.printf("User request:{}",username);
    	try {
            User user = userService.findByUsername(username);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Login login = new Login(user.getUsername(), user.getPassword(), grantedAuthorities);
            login.setUserID(user.getId());
            return login;
        } catch (Exception e) {
        	System.out.printf("Wrong Username", e);
        }
        return null;
    }
    
 
}

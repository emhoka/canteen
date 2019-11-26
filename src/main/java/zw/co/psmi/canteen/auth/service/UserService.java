package zw.co.psmi.canteen.auth.service;

import java.util.List;

import zw.co.psmi.canteen.auth.entity.User;
import zw.co.psmi.canteen.basic.BasicService;

public interface UserService extends BasicService<User>{
public User findByUsername(String username);

String delete(long id);

}
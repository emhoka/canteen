package zw.co.psmi.canteen.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zw.co.psmi.canteen.auth.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	public	User findByUsername(String username);
}
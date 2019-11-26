package zw.co.psmi.canteen.admin.role.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zw.co.psmi.canteen.admin.role.entity.Role;


public interface RoleDao extends JpaRepository<Role, Long> {
	public	Role findByRoleName(String roleName);

}

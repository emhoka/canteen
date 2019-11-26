package zw.co.psmi.canteen.admin.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import zw.co.psmi.canteen.admin.role.dao.RoleDao;
import zw.co.psmi.canteen.admin.role.entity.Role;
import zw.co.psmi.canteen.admin.role.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{

	
	 private RoleDao roleDao;

	    @Autowired
	    public RoleServiceImpl(RoleDao roleDao) {
	        this.roleDao = roleDao;
	    }

	    @Override
	    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	    public String save(Role role) {
	    	this.roleDao.save(role);
	        return "Sucessfully saved Role";
	    }
	    
	    @Override
	    public String delete(long id) {
	        this.roleDao.delete(id);
	        return "Sucessfully deleted role";
	    }

	    @Override
	    public Role getByID(Long roleID) {
	    	Role role = this.roleDao.findOne(roleID);
	        if (role == null) {
	        	role = new Role();
	        }
	        return role;
	    }

		@Override
		public JpaRepository<Role, Long> getDao() {
			 return roleDao;
		}


		@Override
		public Role findByRoleName(String roleName) {
			// TODO Auto-generated method stub
			return roleDao.findByRoleName(roleName);
		}

		  @Override
		    public List<Role> findAll() {
		        return roleDao.findAll();
		    }

		
}

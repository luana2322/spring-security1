package com.security.demosecurity3.sevrice;

import com.security.demosecurity3.models.Role;
import com.security.demosecurity3.models.Users;

public interface UserService {
	
	Users saveUsers(Users User);
	
	Role saveRole(Role role);
	
	void addtoUser(String user_name,String name);

}

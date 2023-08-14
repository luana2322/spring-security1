package com.security.demosecurity3.repository;

import java.util.List;

import javax.xml.transform.Transformer;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.security.demosecurity3.models.Role;
import com.security.demosecurity3.models.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleCustomRepo {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Role> getRole(Users user) {
		StringBuilder sql =new StringBuilder()
				.append("select * from \n"
						+ "user u join user_role ur \n"
						+ "on u.userId=ur.userId \n"
						+ "join role r \n"
						+ "on ur.roleId=r.roleId ");
		sql.append(" where 1=1 ");
		if(user.getEmail()!=null) {
			sql.append("and u.email=:email");
			
		}
		NativeQuery<Role> query=((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
		
		if(user.getEmail()!=null) {
			query.setParameter("email", user.getEmail());
		}
		query.addScalar("name",StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(Role.class));
		
		return query.list();
	
	}
}

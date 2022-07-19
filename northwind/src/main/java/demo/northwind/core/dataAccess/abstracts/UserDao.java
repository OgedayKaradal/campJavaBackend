package demo.northwind.core.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.northwind.core.entities.concrete.User;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}

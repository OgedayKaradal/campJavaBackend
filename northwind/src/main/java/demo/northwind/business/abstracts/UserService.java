package demo.northwind.business.abstracts;

import demo.northwind.core.entities.concrete.User;
import demo.northwind.core.utilities.results.concretes.DataResult;
import demo.northwind.core.utilities.results.concretes.Result;

public interface UserService {
	Result add(User user);
	DataResult<User> findByEmail(String email);
}

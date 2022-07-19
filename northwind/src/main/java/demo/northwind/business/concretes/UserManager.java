package demo.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.northwind.business.abstracts.UserService;
import demo.northwind.business.constants.Messages;
import demo.northwind.core.dataAccess.abstracts.UserDao;
import demo.northwind.core.entities.concrete.User;
import demo.northwind.core.utilities.results.concretes.DataResult;
import demo.northwind.core.utilities.results.concretes.Result;
import demo.northwind.core.utilities.results.concretes.SuccessDataResult;
import demo.northwind.core.utilities.results.concretes.SuccessResult;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult(Messages.userAdded);
	}

	@Override
	public DataResult<User> findByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email), Messages.userFound);
	}

}

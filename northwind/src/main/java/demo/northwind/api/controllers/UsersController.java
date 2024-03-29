package demo.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.northwind.business.abstracts.UserService;
import demo.northwind.business.constants.Messages;
import demo.northwind.core.entities.concrete.User;
import demo.northwind.core.utilities.results.concretes.DataResult;
import demo.northwind.core.utilities.results.concretes.ErrorDataResult;

@RestController
@RequestMapping("/api/users/")
public class UsersController {
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));	
	}
	
	@GetMapping("findByEmail")
	public DataResult<User> findByEmail(String email){
		return this.userService.findByEmail(email);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String, String> validationErrors = new HashMap<String, String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, Messages.validationFailed);
		return errors;
	}
}

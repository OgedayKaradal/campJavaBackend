package demo.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.northwind.business.abstracts.ProductService;
import demo.northwind.business.constants.Messages;
import demo.northwind.core.utilities.results.concretes.DataResult;
import demo.northwind.core.utilities.results.concretes.Result;
import demo.northwind.core.utilities.results.concretes.SuccessDataResult;
import demo.northwind.core.utilities.results.concretes.SuccessResult;
import demo.northwind.dataAccess.abstracts.ProductDao;
import demo.northwind.entities.concretes.Product;

@Service
public class ProductManager implements ProductService {

	private ProductDao productDao;

	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(), Messages.productsListed); 
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult(Messages.productAdded);
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>(this.productDao.getByProductName(productName), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameAndCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameAndCategoryId(productName, categoryId), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategoryId(productName, categoryId), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIdIn(categories), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName), Messages.productsListed);
	}
}

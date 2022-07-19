package demo.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import demo.northwind.business.abstracts.ProductService;
import demo.northwind.business.constants.Messages;
import demo.northwind.core.utilities.results.concretes.DataResult;
import demo.northwind.core.utilities.results.concretes.Result;
import demo.northwind.core.utilities.results.concretes.SuccessDataResult;
import demo.northwind.core.utilities.results.concretes.SuccessResult;
import demo.northwind.dataAccess.abstracts.ProductDao;
import demo.northwind.entities.concretes.Product;
import demo.northwind.entities.dtos.ProductWithCategoryDto;

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
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIn(categories), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);				
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent(), Messages.productsListed);
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC, "productName");
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort), Messages.productsListed);
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails(), Messages.productsListed);
	}
}

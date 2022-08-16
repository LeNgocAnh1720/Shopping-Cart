package poly.store.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.ProductDAO;
import poly.store.entity.Product;
import poly.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productDao;

	@Override
	public List<Product> findAll() {
		
		return productDao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		
		return productDao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		
		return productDao.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		
		return productDao.save(product);
	}

	@Override
	public Product update(Product product) {
		
		return productDao.save(product);
	}

	@Override
	public void delete(Integer id) {
		productDao.deleteById(id);

	}

	
}

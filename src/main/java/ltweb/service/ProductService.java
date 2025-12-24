package ltweb.service;

import ltweb.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

	void delete(Long id);

	Product get(Long id);

	Product save(Product product);

	List<Product> listAll();

}
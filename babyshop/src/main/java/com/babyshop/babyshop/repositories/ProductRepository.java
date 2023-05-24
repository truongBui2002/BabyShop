package com.babyshop.babyshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babyshop.babyshop.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.discount < 1 order by p.updateAt desc")
	List<Product> findProductsByDiscount();
	
//	@Query("SELECT p.productId, i.imageName " +
//		       "FROM Product p " +
//		       "INNER JOIN (" +
//		       "  SELECT ip.productId, MIN(ip.imageId) AS imageId " +
//		       "  FROM ImageProduct ip " +
//		       "  GROUP BY ip.productId " +
//		       ") AS minIp ON p.productId = minIp.productId " +
//		       "INNER JOIN Image i ON minIp.imageId = i.imageId")
//		List<Object[]> findImageByProduct();

}

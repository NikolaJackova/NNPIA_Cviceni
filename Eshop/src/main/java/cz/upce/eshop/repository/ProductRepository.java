package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = " select p from Product p where p.productName like :#{#contains}")
    List<Product> findProductsByProductNameContains(@Param("contains") String contains);
    @EntityGraph(attributePaths = {"orderProducts"})
    List<Product> findProductsByIdBetweenOrderByProductName(Long start, Long finish);
    List<Product> findProductsByProductName(String productName);
}

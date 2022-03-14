package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = " select p from Product p where p.name like :#{#contains}")
    List<Product> findProductsByNameContains(@Param("contains") String contains);
    @EntityGraph(attributePaths = {"orderProducts"})
    List<Product> findProductsByIdBetweenOrderByName(Long start, Long finish);
}

package cz.upce.eshop.repository;

import cz.upce.eshop.entity.ShopOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
    @EntityGraph(attributePaths = {"orderProducts"})
    Optional<ShopOrder> findById(Long id);
}

package cz.upce.eshop;

import cz.upce.eshop.entity.OrderProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.ShopOrder;
import cz.upce.eshop.entity.StateEnum;
import cz.upce.eshop.repository.OrderProductRepository;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.repository.ShopOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

//@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Test
    void saveProductTest() {
        Product product = new Product();
        product.setName("ProductTest");
        productRepository.save(product);

        List<Product> all = productRepository.findAll();

        product.setName("NewProduct");
        productRepository.save(product);
        List<Product> contains = productRepository.findProductsByNameContains("New");
        List<Product> between = productRepository.findProductsByIdBetweenOrderByName(2L, 3L);
        List<Product> sortAscById = productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<Product> sortDescById = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        //paging
        Page<Product> paging = productRepository.findAll(PageRequest.of(0,2, Sort.by("id").ascending()));

        //sorting according to more params
        List<Product> moreSorting = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id").and(Sort.by(Sort.Direction.ASC, "name")));
    }

    @Test
    void complexTest(){
        Product product = new Product();
        product.setName("ProductTest");
        productRepository.save(product);

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setState(StateEnum.NEW);
        shopOrderRepository.save(shopOrder);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setAmount(5);
        orderProduct.setOrder(shopOrder);
        orderProductRepository.save(orderProduct);

        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setProduct(product);
        orderProduct2.setAmount(10);
        orderProduct2.setOrder(shopOrder);
        orderProductRepository.save(orderProduct2);

        Optional<ShopOrder> shopOrders = shopOrderRepository.findById(2L);
    }
}

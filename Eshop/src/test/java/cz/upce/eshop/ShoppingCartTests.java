package cz.upce.eshop;

import cz.upce.eshop.entity.OrderProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.ShopOrder;
import cz.upce.eshop.entity.StateEnum;
import cz.upce.eshop.repository.OrderProductRepository;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.repository.ShopOrderRepository;
import cz.upce.eshop.service.ShoppingCartService;
import cz.upce.eshop.service.ShoppingCartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
//@WebAppConfiguration
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional
//@ComponentScan(basePackages = {"cz.upce.eshop.service"})
class ShoppingCartTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    void addOneToShoppingCart() {
        Product product = new Product();
        product.setProductName("TestovaciProdukt");
        productRepository.save(product);
        List<Product> all = productRepository.findProductsByProductName("TestovaciProdukt");
        Assertions.assertEquals(product, all.get(0));
        shoppingCartService.add(all.get(0).getId());

        Assertions.assertEquals(1, shoppingCartService.getCart().size());
        Assertions.assertEquals(true, shoppingCartService.getCart().containsKey(all.get(0)));
        Assertions.assertEquals(1, shoppingCartService.getCart().get(all.get(0)));
    }

    @Test
    void addMoreToShoppingCart() {
        Product product = new Product();
        product.setProductName("TestovaciProdukt");
        productRepository.save(product);
        List<Product> all = productRepository.findProductsByProductName("TestovaciProdukt");
        Assertions.assertEquals(product, all.get(0));
        shoppingCartService.add(all.get(0).getId());
        shoppingCartService.add(all.get(0).getId());

        Assertions.assertEquals(1, shoppingCartService.getCart().size());
        Assertions.assertEquals(true, shoppingCartService.getCart().containsKey(all.get(0)));
        Assertions.assertEquals(2, shoppingCartService.getCart().get(all.get(0)));
    }

    @Test
    void removeOneQuantityFromShoppingCart() {
        Product product = new Product();
        product.setProductName("TestovaciProdukt");
        productRepository.save(product);
        List<Product> all = productRepository.findProductsByProductName("TestovaciProdukt");
        Assertions.assertEquals(product, all.get(0));
        shoppingCartService.add(all.get(0).getId());
        shoppingCartService.add(all.get(0).getId());
        shoppingCartService.add(all.get(0).getId());

        shoppingCartService.remove(all.get(0).getId());

        Assertions.assertEquals(2, shoppingCartService.getCart().get(all.get(0)));
    }

    @Test
    void removeWholeProductFromShoppingCart() {
        Product product = new Product();
        product.setProductName("TestovaciProdukt");
        productRepository.save(product);
        List<Product> all = productRepository.findProductsByProductName("TestovaciProdukt");
        Assertions.assertEquals(product, all.get(0));
        shoppingCartService.add(all.get(0).getId());
        shoppingCartService.add(all.get(0).getId());
        shoppingCartService.add(all.get(0).getId());

        shoppingCartService.remove(all.get(0).getId());
        shoppingCartService.remove(all.get(0).getId());
        shoppingCartService.remove(all.get(0).getId());

        Assertions.assertEquals(0, shoppingCartService.getCart().size());
        Assertions.assertEquals(null, shoppingCartService.getCart().get(all.get(0)));
        Assertions.assertEquals(false, shoppingCartService.getCart().containsKey(all.get(0)));
    }
}

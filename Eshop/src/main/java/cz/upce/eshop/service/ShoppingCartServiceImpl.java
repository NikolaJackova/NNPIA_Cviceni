package cz.upce.eshop.service;

import cz.upce.eshop.entity.OrderProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.ShopOrder;
import cz.upce.eshop.entity.StateEnum;
import cz.upce.eshop.repository.OrderProductRepository;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.repository.ShopOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Product, Integer> cart;

    private final ProductRepository productRepository;
    private final ShopOrderRepository shopOrderRepository;
    private final OrderProductRepository orderProductRepository;

    public ShoppingCartServiceImpl(ProductRepository productRepository, ShopOrderRepository shopOrderRepository, OrderProductRepository orderProductRepository) {
        this.productRepository = productRepository;
        this.shopOrderRepository = shopOrderRepository;
        this.orderProductRepository = orderProductRepository;
        cart = new HashMap<>();
    }

    @Override
    public void add(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1) {
                cart.replace(product, cart.get(product) - 1);
            } else {
                cart.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public void checkout() {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setState(StateEnum.NEW);
        shopOrderRepository.save(shopOrder);

        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(shopOrder);
            orderProduct.setProduct(entry.getKey());
            orderProduct.setAmount(entry.getValue());

            orderProductRepository.save(orderProduct);
        }
        cart.clear();
    }
}

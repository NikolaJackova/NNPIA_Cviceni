package cz.upce.fei.nnpia.cv02.bankdispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShopDiSpringApplication {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("cz.upce.fei.nnpia.cv02.bankdispring");

        context.getBean(ShopDiSpringApplication.class).process();
    }

    private void process() {
        customerService.createCustomer();
        orderService.createOrder();
    }

}

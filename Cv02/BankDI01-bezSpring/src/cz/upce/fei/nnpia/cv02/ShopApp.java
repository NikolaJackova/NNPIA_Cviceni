package cz.upce.fei.nnpia.cv02;

public class ShopApp implements IShopApp {
    private CustomerService customerService;
    private OrderService orderService;

    public ShopApp(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        IShopApp shopApp = DependencyInjector.getShopApp();
        shopApp.process();
    }

    @Override
    public void process() {
        customerService.createCustomer();
        orderService.createOrder();
    }
}

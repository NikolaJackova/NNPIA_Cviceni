package cz.upce.fei.nnpia.cv02;

public class DependencyInjector {
    //Singleton
    private static CustomerService customerService;
    private static OrderService orderService;
    private static EmailService emailService;

    private  static  EmailService getEmailService(){
        if (emailService == null){
            emailService = new EmailService();
        }
        return  emailService;
    }

    private static CustomerService getCustomerService(){
        if (customerService == null){
            customerService = new CustomerService(getEmailService());
        }
        return customerService;
    }

    private static OrderService getOrderService(){
        if (orderService == null){
            orderService = new OrderService(getEmailService());
        }
        return orderService;
    }

    public static IShopApp getShopApp() {
        return new ShopApp(getCustomerService(), getOrderService());
    }
}

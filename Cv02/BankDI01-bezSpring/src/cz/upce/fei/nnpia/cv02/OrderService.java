package cz.upce.fei.nnpia.cv02;

public class OrderService {
    private EmailService emailService;

    public OrderService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void createOrder(){
        emailService.sendEmail("nikola.jackova@upce.cz",
                "Order created!");
    }
}

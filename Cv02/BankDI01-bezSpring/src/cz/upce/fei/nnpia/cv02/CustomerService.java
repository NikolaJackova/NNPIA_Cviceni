package cz.upce.fei.nnpia.cv02;

public class CustomerService {
    private EmailService emailService;

    public CustomerService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void createCustomer(){
        emailService.sendEmail("nikola.jackova@upce.cz",
                "Hello new customer!");
    }
}

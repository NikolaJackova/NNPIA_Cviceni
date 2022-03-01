package cz.upce.fei.nnpia.cv02.bankdispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private EmailService emailService;

    public void createOrder(){
        System.out.println("Order created");
        emailService.sendEmail("nikola.jackova@upce.cz", "Order for you has been created!");
    }
}

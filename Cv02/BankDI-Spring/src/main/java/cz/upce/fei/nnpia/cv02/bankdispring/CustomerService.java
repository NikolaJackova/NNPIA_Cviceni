package cz.upce.fei.nnpia.cv02.bankdispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private EmailService emailService;

    public void createCustomer(){
        System.out.println("Customer created");
        emailService.sendEmail("nikola.jackova@upce.cz", "Your customer account has been created!");
    }
}

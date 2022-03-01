package cz.upce.fei.nnpia.cv02.bankdispring;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public EmailService(){
        System.out.println("Email service created");
    }

    public void sendEmail(String to, String message){
        System.out.println("Email sent to " + to + " with body: " + message);
    }
}

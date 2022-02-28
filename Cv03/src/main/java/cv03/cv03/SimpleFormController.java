package cv03.cv03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Locale;

@Controller
public class SimpleFormController {

    private final UserService userServiceSession;

    private final UserService userServiceSingleton;

    public SimpleFormController(@Qualifier("userServiceSessionImpl") UserService userServiceSession, @Qualifier("userServiceSingletonImpl") UserService userServiceSingleton) {
        this.userServiceSession = userServiceSession;
        this.userServiceSingleton = userServiceSingleton;
    }

    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String requestFormGet(@RequestParam(name="name", required = false) String username,
                                        @RequestParam(name="age", required = false) Integer age,
                                        Model model){
        model.addAttribute("user", new User(username, age));
        return "simple-form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        userServiceSingleton.addUser(user);
        userServiceSession.addUser(user);
        model.addAttribute("user", user);
        return "form-greeting";
    }

    @GetMapping(value="/users")
    public String requestUsersGet(Model model){
        model.addAttribute("sessionUsers", userServiceSession.getUsers());
        model.addAttribute("allUsers", userServiceSingleton.getUsers());
        return "users";
    }
}

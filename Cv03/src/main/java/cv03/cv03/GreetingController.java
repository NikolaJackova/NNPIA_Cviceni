package cv03.cv03;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Locale;

@Controller
//prefix user for whole class: localhost:8080/user/greeting
@RequestMapping("user")
public class GreetingController {

    //Spring singleton is added to this controller with DI
    /*@Autowired
    private CounterService counterService;*/

    private final CounterService counterService;

    //More beans managed by Spring context => qualifier
    public GreetingController(@Qualifier("superCounterServiceImpl") CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(value="/greeting", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestGreetingPostAndGet(@RequestParam(name="name", required = false, defaultValue = "world") String username, Model model){
        counterService.add();
        model.addAttribute("name", StringUtils.toUpperCase(username, Locale.ENGLISH));
        model.addAttribute("counter", counterService.getCounter());
        return "greeting";
    }

    @PostMapping(value="/greetingPost")
    public String requestGreetingPost(@RequestParam(name="name", required = false, defaultValue = "world") String username, Model model){
        model.addAttribute("name", StringUtils.toUpperCase(username, Locale.ENGLISH));
        model.addAttribute("counter", counterService.getCounter());
        return "greeting";
    }

    @GetMapping(value="/greetingGet")
    public String requestGreetingGet(@RequestParam(name="name", required = false, defaultValue = "world") String username, Model model){
        model.addAttribute("name", StringUtils.toUpperCase(username, Locale.ENGLISH));
        model.addAttribute("counter", counterService.getCounter());
        return "greeting";
    }

    @RequestMapping(value="/welcome/{name}", method = RequestMethod.GET)
    public String requestGreetingPath(@PathVariable("name") String username, Model model){
        model.addAttribute("name", StringUtils.toUpperCase(username, Locale.ENGLISH));
        return "greeting";
    }
}

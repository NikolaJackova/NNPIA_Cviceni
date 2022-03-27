package cz.upce.testing.user;

import cz.upce.testing.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//issues with not found
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/1.0/users")
    public GenericResponse createUser(@Valid @RequestBody UserTestApp userTestApp) {
        userService.save(userTestApp);

        return new GenericResponse("user saved");
    }
}

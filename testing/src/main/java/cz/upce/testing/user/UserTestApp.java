package cz.upce.testing.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
public class UserTestApp {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    private String displayName;

    @NotNull
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}:;<>,/~_+=|]).{8,32}")
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    @PastOrPresent
    private Date birthDate;
}

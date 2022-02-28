package cv03.cv03;


import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user);
}

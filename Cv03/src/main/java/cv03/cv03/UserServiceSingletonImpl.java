package cv03.cv03;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceSingletonImpl implements UserService{
    private List<User> users = new LinkedList<>();

    public List<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }
}

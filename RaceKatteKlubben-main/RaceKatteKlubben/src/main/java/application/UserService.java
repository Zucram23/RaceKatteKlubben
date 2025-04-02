package application;

import domain.User;
import infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}

public int createUser(User user) {
    if (userRepository.doesEmailExists(user.getEmail())) {
        throw new IllegalArgumentException("Email already exists! Please use another email.");
    }
    userRepository.createUser(user);
    return userRepository.findIdByEmail(user.getEmail());
}

public User getUserById(int id) {
   return userRepository.findUserById(id);
}

public List<User> getAllUsers() {
    return userRepository.findAllUsers();
}
public void deleteUser(int id) {
    userRepository.deleteUser(id);
}
public User authenticateUser(String email, String password){
    return userRepository.authenticateUser(email, password);
    }


}

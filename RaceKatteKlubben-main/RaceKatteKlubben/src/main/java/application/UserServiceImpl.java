package application;

import domain.InvalidCredentialsException;
import domain.User;
import infrastructure.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements ServiceInterface<User> {

private final UserRepositoryImpl userRepository;

public UserServiceImpl(UserRepositoryImpl userRepository) {
    this.userRepository = userRepository;
}

@Override
public User save(User user) {
    if (userRepository.emailExists(user.getEmail())) {
        throw new IllegalArgumentException("Email already exists! Please use another email.");
    }
    userRepository.save(user);
    user.setId(userRepository.findIdByEmail(user.getEmail()));
    return user;
}
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    public User getUserById(int id) {
   return userRepository.findUserById(id);
}


public Optional<User> authenticateUser(String email, String password){

    Optional<User> user = userRepository.authenticateUser(email, password);

    if(user == null){
        throw new InvalidCredentialsException("Invalid email or password");
    }
    return user;

    }

    public boolean emailExist(String email) {
    return userRepository.emailExists(email);
    }

}

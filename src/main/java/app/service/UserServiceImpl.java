package app.service;

import app.domain.enums.SelectStateEnum;
import app.domain.User;
import app.repository.UserRepositoryImplCollection;
import app.service.inputports.UserService;
import app.service.outputports.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(Integer id, String name, String lastName, String email, String phone, String password, String state, String city, String preferences) {

        User user = new User(id, name, lastName, email, phone, password, state, city, preferences);

        return userRepository.save(user);
    }

    @Override
    public User selectUserById(int id) {

        return userRepository.selectById(id);
    }

    @Override
    public User updateUser(User user) {

        return userRepository.updateUser(user);
    }

    @Override
    public List<User> selectUsers() {

        return userRepository.selectAll();
    }

    @Override
    public boolean deleteUser(int id) {

        return userRepository.deleteById(id);
    }

    @Override
    public int countUsers() {

        return userRepository.count();
    }
}

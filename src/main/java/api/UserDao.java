package api;

import entity.User;
import exception.UserLoginAlreadyExistException;

import java.io.IOException;
import java.util.List;

public interface UserDao
{
    void createUser(User user);

    void deleteUserById(Long id);

    void deleteUserByLogin(String login);

    List<User> getAllUsers();

    User getUserByLogin(String userLogin);

    void updateUser(User user);
}

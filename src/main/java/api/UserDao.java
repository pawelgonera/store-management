package api;

import entity.User;
import exception.UserLoginAlreadyExistException;

import java.io.IOException;
import java.util.List;

public interface UserDao
{
    public void saveUser(User user) throws IOException;

    public void saveUsers(List<User> users) throws IOException;

    public void removeUserById(Long id) throws IOException;

    public void removeUserByLogin(String login) throws IOException;

    public List<User> getAllUsers() throws IOException;

    public User getUserById(Long id) throws IOException;

    public User getUserByLogin (String login) throws IOException;

}

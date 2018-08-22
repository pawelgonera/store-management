import entity.User;
import service.UserServiceImpl;

public class Main
{
    public static void main(String [] args)
    {
        User admin = new User(1l, "admin", "admin");

        UserServiceImpl userService = new UserServiceImpl();

        userService.addUser(admin);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(1l);

        System.out.println(userService.getAllUsers());
    }
}

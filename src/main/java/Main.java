import entity.User;
import service.UserServiceImpl;

public class Main
{
    public static void main(String [] args)
    {
        User admin = new User(1l, "admin", "admin");
        User pablo = new User(2l, "Pablo", "Omega1");
        UserServiceImpl userService = new UserServiceImpl();

        userService.addUser(admin);
        userService.addUser(pablo);
        System.out.println(userService.getAllUsers());

        userService.removeUserById(1l);

        System.out.println(userService.getAllUsers());
    }
}

import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import entity.Product;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;
import service.ProductServiceImpl;
import service.UserServiceImpl;
import validator.UserValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String [] args) throws IOException
    {

        User admin = new User(1l, "admin", "admin28");
        User pablo = new User(2l, "Pablo", "Omega1");
        User joanna = new User(3l, "Joanna", "Pablito20");

        List<User> users = new ArrayList<User>();
        users.add(admin);
        users.add(pablo);
        users.add(joanna);


        Product product = new Product(1L, "Adiddas", 99F, 1.5F, "Black", 2);

        List<Product> products = new ArrayList<Product>();
        products.add(product);
        products.add(new Product(2L, "Bluse", 49F, 0.5F, "Blue", 1));

        ProductDaoImpl productDaoImpl = new ProductDaoImpl("products", "PRODUCT");

        productDaoImpl.saveProducts(products);


        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();

        userDaoImpl.saveUsers(users);

        ProductServiceImpl productService = new ProductServiceImpl();




        /*
        UserValidator userValidator = UserValidator.getInstance();

        try
        {
            System.out.println(userValidator.isValidate(new User(4L, "BigTom", "Benek128")));

        } catch (UserShortLengthLoginException e)
        {
            e.printStackTrace();
        } catch (UserShortLengthPasswordException e)
        {
            e.printStackTrace();
        }catch (UserLoginAlreadyExistException e)
        {
            e.printStackTrace();
        }
        */
    }


}

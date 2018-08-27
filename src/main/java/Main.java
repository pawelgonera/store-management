import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import entity.Product;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String [] args) throws IOException
    {
        User admin = new User(1l, "admin", "admin");
        User pablo = new User(2l, "Pablo", "Omega1");

        List<User> users = new ArrayList<User>();
        users.add(admin);
        users.add(pablo);

        Product product = new Product(1L, "Adiddas", 99F, 1.5F, "Black", 2);

        List<Product> products = new ArrayList<Product>();
        products.add(product);
        products.add(new Product(2L, "Bluse", 49F, 0.5F, "Blue", 1));

        ProductDaoImpl productDaoImpl = new ProductDaoImpl("products", "PRODUCT");

        productDaoImpl.saveProducts(products);

        UserDaoImpl userDaoImpl = new UserDaoImpl("users");

        userDaoImpl.saveUsers(users);

    }
}

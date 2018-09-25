import dao.ProductDaoImpl;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.User;
import service.ProductServiceImpl;
import service.UserRegisterLoginFacadeImpl;
import service.UserServiceImpl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Scanner sc = new Scanner(System.in);
    private static int choose = 0, productCount, size;
    private static boolean isNaturalSkin;
    private static String login, password, productName, color, clothSize, material;
    private static float price, weight;
    private static List<Boots> boots = new ArrayList<Boots>();
    private static long userId = 0, productId = 0;
    private static ProductDaoImpl productDao;
    private static ProductServiceImpl productService;
    private static UserServiceImpl userService = UserServiceImpl.getInstance();


    static
    {
        try
        {
            productService = ProductServiceImpl.getInstance();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            productDao = ProductDaoImpl.getInstance();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) throws IOException
    {
        UserRegisterLoginFacadeImpl userRegister = UserRegisterLoginFacadeImpl.getInstance();

        do
        {
            System.out.println("Store Management Menu");
            System.out.println("1 - Zaloguj się");
            System.out.println("2 - Zarejestruj się");
            System.out.println("0 - Wyjdź");

            choose = sc.nextInt();

            switch (choose)
            {
                case 1:
                    Main.getUserData();
                    if(userRegister.loginUser(login, password))
                    {
                        Main.loggedMenu();
                    }
                    else
                        System.out.println("Błędne hasło !");
                    break;
                case 2:
                    Main.getUserData();
                    List<User> users = userService.getAllUsers();
                    if(!users.isEmpty())
                        userId = users.get(users.size()-1).getId();
                    userId++;
                        userRegister.registerLogin(new User(userId, login, password));


                    break;


            }
        }while(choose != 0);

    }

    public static void getUserData()
    {
        System.out.println("Podaj login");
        login = sc.next();
        System.out.println("Podaj hasło");
        password = sc.next();
    }

    public static void loggedMenu()
    {
        while(choose != 0)
        {
            System.out.println("Store Management Menu");
            System.out.println("1 - Dodaj nowy produkt");
            System.out.println("0 - Wyloguj się");

            if((choose = sc.nextInt()) == 1)
            {
                Main.productTypeMenu();
            }


        }
    }

    public static void productTypeMenu()
    {
        while(choose != 0)
        {
            System.out.println("1 - Dodaj buty");
            System.out.println("2 - Dodaj ubrania");
            System.out.println("3 - Inne");

            choose = sc.nextInt();

            switch (choose)
            {
                case 1:
                    Main.getProductData();
                    System.out.println("Podaj rozmiar butów");
                    size = sc.nextInt();
                    System.out.println("Czy buty sa ze skóry? (true or false");
                    isNaturalSkin = sc.nextBoolean();
                    try
                    {
                        List<Product> products = productService.getAllProducts();

                        if(!products.isEmpty())
                        {
                            productId = products.get(products.size() - 1).getId();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productId++;

                    Boots boots = new Boots(productId, productName, price, weight, color, productCount, size, isNaturalSkin);
                    try
                    {
                        productDao.saveProduct(boots);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Main.getProductData();
                    System.out.println("Podaj rozmiar ubrania (M, S, L, XL, XXL, XXXL)");
                    clothSize = sc.next();
                    System.out.println("Podaj rodzaj materiału");
                    material = sc.next();
                    try
                    {
                        List<Product> products = productService.getAllProducts();
                        if(!products.isEmpty())
                        {
                            productId = products.get(products.size() - 1).getId();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productId++;
                    Cloth cloth = new Cloth(productId, productName, price, weight, color, productCount, clothSize, material);
                    try
                    {
                        productDao.saveProduct(cloth);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    Main.getProductData();
                    try
                    {
                        List<Product> products = productService.getAllProducts();
                        if(!products.isEmpty())
                        {
                            productId = products.get(products.size() - 1).getId();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productId++;
                    Product product = new Product(productId, productName, price, weight,color, productCount);
                    try
                    {
                        productDao.saveProduct(product);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public static void getProductData()
    {
        System.out.println("Podaj nazwę produktu");
        productName = sc.next();
        System.out.println("Podaj cenę");
        price = sc.nextFloat();
        sc.nextLine();
        System.out.println("Podaj wagę");
        weight = sc.nextFloat();
        System.out.println("Podaj kolor");
        color = sc.next();
        System.out.println("Podaj ilość produktów");
        productCount = sc.nextInt();
    }
}

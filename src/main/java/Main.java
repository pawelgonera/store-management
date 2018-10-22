import dao.ProductDaoImpl;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.User;
import entity.enums.Colors;
import entity.enums.Material;
import entity.enums.SkinType;
import entity.parser.ColorParser;
import entity.parser.MaterialParser;
import entity.parser.SkinTypeParser;
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
    private static String login, password, productName;
    private static Colors colors;
    private static Material materials;
    private static SkinType skinTypes;
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
            productDao = ProductDaoImpl.getInstance();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String [] args)
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
                    System.out.println("Podaj rodzaj materiału (Skóra czy tworzywo sztuczne, wybierz: NATURAL or ARTIFICIAL)");
                    String skinType = sc.next();
                    SkinType skinTypes = SkinTypeParser.getSkinType(skinType);
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

                    Boots boots = new Boots(productId, productName, price, weight, colors, productCount, size, skinTypes);
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
                    System.out.println("Podaj rozmiar ubrania (w cyfrach)");
                    size = sc.nextInt();
                    System.out.println("Podaj rodzaj materiału (możliwe do wyboru to: LEATHER, FUR, COTTON, WOOL, POLYESTERS)");
                    String material = sc.next();
                    materials = MaterialParser.getMaterial(material);
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
                    Cloth cloth = new Cloth(productId, productName, price, weight, colors, productCount, size, materials);
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
                    Product product = new Product(productId, productName, price, weight, colors, productCount);
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
        System.out.println("Podaj kolor (możliwe do wyboru to: BLACK, WHITE, RED, GREEN, BLUE, YELLOW");
        String color = sc.next();
        colors = ColorParser.getColor(color);
        System.out.println("Podaj ilość produktów");
        productCount = sc.nextInt();
    }
}

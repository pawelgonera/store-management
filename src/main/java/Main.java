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
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Scanner sc = new Scanner(System.in);
    private static int choose = 0, productCount, size;
    private static String login, password, productName;
    private static Colors colors;
    private static Material materials;
    private static String skinType, material;
    private static float price, weight;
    private static long userId = 0, productId = 0;
    private static ProductDaoImpl productDao;
    private static ProductServiceImpl productService;
    private static UserServiceImpl userService = UserServiceImpl.getInstance();

    static
    {
        productService = ProductServiceImpl.getInstance();
        productDao = ProductDaoImpl.getInstance();
    }

    public static void main(String [] args)
    {
        UserRegisterLoginFacadeImpl userRegister = UserRegisterLoginFacadeImpl.getInstance();

        List<Product> products = productDao.getAllProducts();

        System.out.println(products);

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
            System.out.println("2 - Wyświetl dostepne produkty");
            System.out.println("3 - Usuń produkt");
            System.out.println("0 - Wyloguj się");

            choose = sc.nextInt();

            switch(choose)
            {
                case 1:
                    Main.productTypeMenu();
                    break;
                case 2:
                    Main.displayAllProducts();
                    break;
                case 3:
                    Main.deleteProduct();
            }
        }
    }

    public static void deleteProduct()
    {
        Main.displayAllProducts();
        System.out.println("Type name of product to delete");
        String productNameToDelete = sc.next();

        productDao.deleteProductByName(productNameToDelete);
    }

    public static void displayAllProducts()
    {
        List<Product> products = productDao.getAllProducts();
        for(Product product : products)
        {
            System.out.println(product);
        }
    }

    public static void productTypeMenu()
    {
        while(choose != 0)
        {
            System.out.println("1 - Dodaj buty");
            System.out.println("2 - Dodaj ubrania");
            System.out.println("3 - Inne");

            List<Product> products = productService.getAllProducts();
            choose = sc.nextInt();

            switch (choose)
            {
                case 1:
                    Main.getProductData();
                    System.out.println("Podaj rozmiar butów");
                    size = sc.nextInt();
                    System.out.println("Podaj rodzaj materiału (Skóra czy tworzywo sztuczne, wybierz: NATURAL or ARTIFICIAL)");
                    skinType = sc.next();
                    SkinType skinTypes = SkinTypeParser.getSkinType(skinType);

                    Boots boots = new Boots(productName, price, weight, colors, productCount, size, skinTypes);
                    productDao.createProduct(boots);
                    break;
                case 2:
                    Main.getProductData();
                    System.out.println("Podaj rozmiar ubrania (w cyfrach)");
                    size = sc.nextInt();
                    System.out.println("Podaj rodzaj materiału (możliwe do wyboru to: LEATHER, FUR, COTTON, WOOL, POLYESTERS)");
                    material = sc.next();
                    materials = MaterialParser.getMaterial(material);

                    if(!products.isEmpty())
                    {
                        productId = products.size();
                    }

                    productId++;

                    Cloth cloth = new Cloth(productName, price, weight, colors, productCount, size, materials);
                    productDao.createProduct(cloth);
                    break;
                case 3:
                    Main.getProductData();

                    if(!products.isEmpty())
                    {
                        productId = products.size();
                    }

                    productId++;

                    Product product = new Product(productName, price, weight, colors, productCount);
                    productDao.createProduct(product);
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
        System.out.println("Podaj kolor (możliwe do wyboru to: BLACK, WHITE, RED, GREEN, BLUE, YELLOW)");
        String color = sc.next();
        colors = ColorParser.getColor(color);
        System.out.println("Podaj ilość produktów");
        productCount = sc.nextInt();
    }
}

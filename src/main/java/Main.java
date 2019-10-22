
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
import service.*;

import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Scanner sc = new Scanner(System.in);
    private static int choose = 0, productCount, size, productId;
    private static String login, password, productName;
    private static Colors colors;
    private static Material materials;
    private static SkinType skinTypes;
    private static String color, skinType, material;
    private static float price, weight;
    private static long userId = 0;
    private static UserServiceImpl userService;
    private static ProductFacadeImpl productFacade;
    private static UserRegisterLoginFacadeImpl userRegister;
    private static ClothServiceImpl clothService;
    private static BootsServiceImpl bootsService;
    private static ClothFacadeImpl clothFacade;
    private static BootsFacadeImpl bootsFacade;

    static
    {
        userService = UserServiceImpl.getInstance();
        userRegister = UserRegisterLoginFacadeImpl.getInstance();
        productFacade = ProductFacadeImpl.getInstance();
        clothService = ClothServiceImpl.getInstance();
        bootsService = BootsServiceImpl.getInstance();
        clothFacade = ClothFacadeImpl.getInstance();
        bootsFacade = BootsFacadeImpl.getInstance();
    }

    public static void main(String [] args)
    {
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
                        System.out.println("Witamy " + login);
                        Main.loggedMenu();
                    }
                    else
                        System.out.println("Błędny login lub hasło !");
                    break;
                case 2:
                    Main.getUserData();
                    List<User> users = userService.getAllUsers();
                    if(!users.isEmpty())
                        userId = users.get(users.size()-1).getId();
                    userId++;
                    System.out.println(userRegister.registerLogin(new User(userId, login, password)));
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

        System.out.println(productFacade.removeProduct(productNameToDelete));
    }

    public static void displayAllProducts()
    {
        List<Cloth> cloths = clothService.getAllCloths();
        List<Boots> boots = bootsService.getAllBoots();

        for(Cloth cloth : cloths)
        {
            System.out.println(cloth);
        }

        for(Boots boot: boots)
        {
            System.out.println(boot);
        }
    }

    public static void productTypeMenu()
    {
        while(choose != 0)
        {
            System.out.println("1 - Dodaj buty");
            System.out.println("2 - Dodaj ubrania");
            System.out.println("3 - Wróć");
            System.out.println("0 - Wyloguj się");
            choose = sc.nextInt();

            Product product;

            switch (choose)
            {
                case 1:
                    product = Main.getProductData();
                    System.out.println("Podaj rozmiar butów");
                    size = sc.nextInt();
                    System.out.println("Podaj rodzaj materiału (Skóra czy tworzywo sztuczne, wybierz: NATURAL or ARTIFICIAL)");
                    skinType = sc.next();
                    skinTypes = SkinTypeParser.getSkinType(skinType.toUpperCase());

                    Boots boots = new Boots(product, size, skinTypes);
                    try
                    {
                        productFacade.createProduct(product);
                        bootsFacade.createBoots(boots);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    product = Main.getProductData();
                    System.out.println("Podaj rozmiar ubrania (w cyfrach)");
                    size = sc.nextInt();
                    System.out.println("Podaj rodzaj materiału (możliwe do wyboru to: LEATHER, FUR, COTTON, WOOL, POLYESTERS)");
                    material = sc.next();
                    materials = MaterialParser.getMaterial(material.toUpperCase());

                    Cloth cloth = new Cloth(product, size, materials);
                    try
                    {
                        productFacade.createProduct(product);
                        clothFacade.createCloth(cloth);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    Main.loggedMenu();
                    break;
            }
        }
    }

    public static Product getProductData()
    {
        System.out.println("Podaj nazwę produktu");
        productName = sc.next();
        System.out.println("Podaj cenę");
        price = sc.nextFloat();
        sc.nextLine();
        System.out.println("Podaj wagę");
        weight = sc.nextFloat();
        System.out.println("Podaj kolor (możliwe do wyboru to: BLACK, WHITE, RED, GREEN, BLUE, YELLOW)");
        color = sc.next();
        colors = ColorParser.getColor(color.toUpperCase());
        System.out.println("Podaj ilość produktów");
        productCount = sc.nextInt();

        List<Product> products = productFacade.getAllProducts();

        if(!products.isEmpty())
        {
            productId = products.get(products.size() - 1).getId();
        }

        productId++;
        System.out.println(productId);

        return new Product(productId, productName, price, weight, colors, productCount);
    }
}

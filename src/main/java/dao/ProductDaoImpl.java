package dao;

import api.ProductDao;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.parser.ProductParser;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductDaoImpl implements ProductDao {
    private Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "products";
    private static final String user = "root";
    private static String pswd;
    private String fileName = ".idea/pswd_data/pswd.bin";

    private static ProductDaoImpl instance = null;

    private void getPass() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            pswd = bufferedReader.readLine();

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?useSSL=false", user, pswd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductDaoImpl() {
        getPass();
        init();
    }

    public static ProductDaoImpl getInstance() {
        if (instance == null)
            instance = new ProductDaoImpl();

        return instance;
    }

    @Override
    public void createProduct(Product product) {
        PreparedStatement statement;
        try {
            char productType = product.toString().charAt(0);

            String query = "INSERT INTO " + tableName + " (productType, productName, price, weight, color, productCount, size, material, skinType) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, productType + "");
            statement.setString(2, product.getProductName());
            statement.setFloat(3, product.getPrice());
            statement.setFloat(4, product.getWeight());
            statement.setString(5, product.getColor().name());
            statement.setInt(6, product.getProductCount());

            if (productType == 'C') {
                statement.setInt(7, ((Cloth) product).getSize());
                statement.setString(8, ((Cloth) product).getMaterial().name().toUpperCase());
            } else {
                statement.setInt(7, 0);
                statement.setString(8, " ");
            }

            if (productType == 'B') {
                statement.setInt(7, ((Boots) product).getSize());
                statement.setString(9, ((Boots) product).isNaturalSkin().name().toUpperCase());
            } else {
                statement.setInt(7, 0);
                statement.setString(9, " ");
            }

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        PreparedStatement statement;
        try {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, productId);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductByName(String productName) {
        PreparedStatement statement;
        try {
            String query = "DELETE FROM " + tableName + " WHERE productName = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, productName);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<Product>();
        Statement statement;
        try
        {
            String query = "SELECT * FROM " + tableName;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            while (resultSet.next())
            {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++)
                {
                    row.addElement(resultSet.getObject(i));
                }

                Product product = ProductParser.convertProduct(row);

                products.add(product);
            }

            statement.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return products;

    }
}

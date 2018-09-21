package entity;

public class Product
{
    private static final String PRODUCT_TYPE = "P";
    private long id;
    private String productName;
    private float price;
    private float weight;
    private String color;
    private int productCount;

    public Product(long id, String productName, float price, float weight, String color, int productCount)
    {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public long getId()
    {
        return id;
    }

    public String getProductName()
    {
        return productName;
    }

    public float getPrice()
    {
        return price;
    }

    public float getWeight()
    {
        return weight;
    }

    public String getColor()
    {
        return color;
    }

    public int getProductCount()
    {
        return productCount;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public void setProductCount(int productCount)
    {
        this.productCount = productCount;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;

        hash = 31 * hash + (int) id;
        hash = 31 * hash + (productName == null ? 0 : productName.hashCode());
        hash = 31 * hash + (int) price;
        hash = 31 * hash + (int) weight;
        hash = 31 * hash + (color == null ? 0 : color.hashCode());
        hash = 31 * hash + (int) productCount;

        return hash;
    }

    public String standardToString()
    {
        return  this.id + "-" +
                this.productName + "-" +
                this.price + "-" +
                this.weight + "-" +
                this.color + "-" +
                this.productCount;
    }

    @Override
    public String toString()
    {
        return  PRODUCT_TYPE + "-" +
                standardToString();

    }
}
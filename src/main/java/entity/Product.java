package entity;

public class Product
{
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
    public String toString()
    {
        return "Product ID: " + this.id +
                "Product Name: " + this.productName +
                "Product Price: " + this.price +
                "Product Weight: " + this.weight +
                "Product Color: " + this.color +
                "Product Count: " + this.productCount;
    }
}
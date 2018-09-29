package entity.enums;

public enum ProductSeparators
{
    PRODUCT_SEPARATOR("#"),
    PRODUCT_ID("P"),
    CLOTH_ID("C"),
    BOOTS_ID("B");

    private String separator;

    ProductSeparators(String separator)
    {
        this.separator = separator;
    }

    public String getSeparator()
    {
        return separator;
    }
}

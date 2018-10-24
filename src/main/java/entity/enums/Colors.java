package entity.enums;

public enum Colors
{
    BLACK("#000000"),
    WHITE("#FFFFFF"),
    RED("#FF0000"),
    GREEN("#008000"),
    BLUE("#0000FF"),
    YELLOW("#FFFF00"),
    DEFAULT("DEFAULT");

    private String hex;

    Colors(String hex)
    {
        this.hex = hex;
    }

    public String getHex()
    {
        return this.hex;
    }

}

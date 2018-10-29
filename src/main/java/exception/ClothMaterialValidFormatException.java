package exception;

public class ClothMaterialValidFormatException extends Exception
{
    public ClothMaterialValidFormatException() {
    }

    public ClothMaterialValidFormatException(String message)
    {
        super(message);
    }
}

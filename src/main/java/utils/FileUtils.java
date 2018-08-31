package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils
{
    public static void createNewFile(String fileName) throws IOException
    {
        File file = new File(fileName);
        file.createNewFile();
    }

    public static void clearFiler(String fileName) throws IOException
    {
        PrintWriter writer = new PrintWriter(fileName);
        writer.close();
    }
}

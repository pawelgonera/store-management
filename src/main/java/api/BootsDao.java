package api;

import entity.Boots;
import java.util.List;

public interface BootsDao
{
    void createBoots(Boots boots);

    void deleteBootsById(Long bootsId);

    void deleteBootsByName(String bootsName);

    List<Boots> getAllBoots();
}

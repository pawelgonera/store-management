package api;

import entity.Boots;
import entity.Cloth;
import exception.BootsSkinTypeValidFormatException;

import java.util.List;

public interface BootsService
{
    List<Boots> getAllBoots();

    Integer getBootsCountOnList();

    boolean isBootsOnWarehouse(String bootsName);

    boolean isBootsExist(String bootsName);

    boolean isBootsExist(Integer bootsId);

    boolean saveBoots(Boots boots) throws BootsSkinTypeValidFormatException;

    Boots getBootsById(Integer bootsId);

    Boots getBootsByName(String bootsName);

    void removeBoots(Integer productId);
}

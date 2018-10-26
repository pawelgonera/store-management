package api;

import entity.Boots;
import entity.Cloth;

import java.util.List;

public interface BootsService
{
    List<Boots> getAllBoots();

    Integer getBootsCountOnList();

    boolean isBootsOnWarehouse(String bootsName);

    boolean isBootsExist(String bootsName);

    boolean isBootsExist(Long bootsId);

    boolean saveBoots(Boots boots);

    Boots getBootsById(Long bootsId);

    Boots getBootsByName(String bootsName);

    void removeBoots(String bootsName);
}

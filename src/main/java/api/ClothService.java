package api;

import entity.Cloth;
import exception.ClothMaterialValidFormatException;

import java.util.List;

public interface ClothService
{
    List<Cloth> getAllCloths();

    Integer getClothCountOnList();

    boolean isClothOnWarehouse(String clothName);

    boolean isClothExist(String clothName);

    boolean isClothExist(Integer clothId);

    boolean saveCloth(Cloth cloth ) throws ClothMaterialValidFormatException;

    Cloth getClothById(Integer productId);

    Cloth getClothByName(String clothName);

    void removeCloth(Integer productId);
}

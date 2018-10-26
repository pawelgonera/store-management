package api;

import entity.Cloth;

import java.util.List;

public interface ClothService
{
    List<Cloth> getAllCloths();

    Integer getClothCountOnList();

    boolean isClothOnWarehouse(String clothName);

    boolean isClothExist(String clothName);

    boolean isClothExist(Long clothId);

    boolean saveCloth(Cloth cloth );

    Cloth getClothById(Long productId);

    Cloth getClothByName(String clothName);

    void removeCloth(String clothName);
}

package api;

import entity.Cloth;

import java.util.List;

public interface ClothDao
{
    void createCloth(Cloth cloth);

    void deleteClothById(Long clothId);

    void deleteClothByProductId(Integer productId);

    Cloth getClothByName(String clothName);

    List<Cloth> getAllCloths();
}

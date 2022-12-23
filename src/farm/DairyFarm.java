package farm;

import java.util.UUID;

public interface DairyFarm {
    void giveBirth(UUID parentCowId, UUID childCowId, String childNickName);
    void endLifeSpan(UUID cowId);
    void printFarmData();
}

package farm.nolimit;

import farm.DairyFarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NoLimitDairyFarm implements DairyFarm {

    private final NoLimitCow rootCow;
    private final Map<UUID, NoLimitCow> cows;

    public NoLimitDairyFarm(NoLimitCow cow) {
        if (cow == null) {
            throw new IllegalArgumentException("Root cow connot be null");
        }

        this.rootCow = cow;
        this.cows = new HashMap<>();

        this.cows.put(cow.getId(), cow);
    }

    @Override
    public void giveBirth(UUID parentCowId, UUID childCowId, String childNickName) {
        NoLimitCow parentCow = cows.get(parentCowId);
        if (parentCow == null) {
            throw new IllegalArgumentException("Parent cow by received id was not found");
        }

        NoLimitCow child = new NoLimitCow(childCowId, childNickName, new ArrayList<>());
        NoLimitCow existed = cows.putIfAbsent(childCowId, child);
        if (existed != null) {
            throw new IllegalArgumentException("Cow with received child id already exists");
        }

        parentCow.getChildren().add(child);
    }

    @Override
    public void endLifeSpan(UUID cowIdToRemove) {
        NoLimitCow cowToRemove = cows.remove(cowIdToRemove);
        if (cowToRemove == null) {
            throw new IllegalArgumentException("Cow with received id was not found");
        }

        for (Map.Entry<UUID, NoLimitCow> entry: cows.entrySet()) {
            NoLimitCow currentCow = entry.getValue();
            if (currentCow.getChildren().stream().anyMatch(it -> it.getId().equals(cowToRemove.getId()))) {
                currentCow.getChildren().remove(cowToRemove);
                currentCow.getChildren().addAll(cowToRemove.getChildren());
            }
        }
    }

    @Override
    public void printFarmData() {
        printCowInternal(rootCow, 0);
    }

    private void printCowInternal(NoLimitCow cow, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        System.out.println(cow.getNickName());

        if (cow.getChildren().size() == 0) {
            return;
        }

        for (NoLimitCow child: cow.getChildren()) {
            printCowInternal(child, level + 1);
        }
    }
}

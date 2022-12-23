package farm.limited;

import farm.DairyFarm;
import farm.limited.list.NodeList;

import java.util.UUID;

public class LimitedDairyFarm implements DairyFarm {

    private final LimitedCow rootCow;
    private final NodeList<LimitedCow> cows;

    public LimitedDairyFarm(LimitedCow cow) {
        if (cow == null) {
            throw new IllegalArgumentException("Root cow connot be null");
        }

        this.rootCow = cow;
        this.cows = new NodeList<>();

        this.cows.add(cow);
    }

    @Override
    public void giveBirth(UUID parentCowId, UUID childCowId, String childNickName) {
        LimitedCow parentCow = cows.find(cow -> cow.getId().equals(parentCowId));
        if (parentCow == null) {
            throw new IllegalArgumentException("Parent cow by received id was not found");
        }

        if (cows.find(cow -> cow.getId().equals(childCowId)) != null) {
            throw new IllegalArgumentException("Cow with received child id already exists");
        }

        LimitedCow childCow = new LimitedCow(childCowId, childNickName, new NodeList<>());
        cows.add(childCow);

        parentCow.getChildren().add(childCow);
    }

    @Override
    public void endLifeSpan(UUID cowIdToRemove) {
        LimitedCow cowToRemove = cows.remove(it -> it.getId().equals(cowIdToRemove));
        if (cowToRemove == null) {
            throw new IllegalArgumentException("Cow with received id was not found");
        }

        LimitedCow parentCow = cows.find(it ->
            it.getChildren().find(child ->
                child.getId().equals(cowIdToRemove)
            ) != null
        );

        parentCow.getChildren().addAll(cowToRemove.getChildren());
        parentCow.getChildren().remove(it -> it.getId().equals(cowIdToRemove));
    }

    @Override
    public void printFarmData() {
        printCowInternal(rootCow, 0);
    }

    private void printCowInternal(LimitedCow cow, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        System.out.println(cow.getNickName());

        if (cow.getChildren().getSize() == 0) {
            return;
        }

        cow.getChildren().each(child -> printCowInternal(child, level + 1));
    }

}
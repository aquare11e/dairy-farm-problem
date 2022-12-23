import farm.Cow;
import farm.DairyFarm;
import farm.limited.LimitedCow;
import farm.limited.LimitedDairyFarm;
import farm.nolimit.NoLimitCow;
import farm.nolimit.NoLimitDairyFarm;

import java.util.UUID;

public class Main {

    public static void main(String... args) {
        NoLimitCow nlburyonka = new NoLimitCow("Buryonka");
        testFarm(new NoLimitDairyFarm(nlburyonka), nlburyonka);

        LimitedCow lBuryonka = new LimitedCow("Buryonka");
        testFarm(new LimitedDairyFarm(lBuryonka), lBuryonka);
    }

    private static void testFarm(DairyFarm farm, Cow rootCow) {
        UUID milkaId = UUID.randomUUID();
        farm.giveBirth(rootCow.getId(), milkaId, "Milka");

        farm.printFarmData();

        farm.endLifeSpan(milkaId);
        farm.printFarmData();
    }
}
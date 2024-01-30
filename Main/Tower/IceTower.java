package Main.Tower;

import java.util.List;

import Main.Monster.AMonster;
import Main.Monster.BossMonster;
import Main.Monster.RapidMonster;

public class IceTower extends ATower {
    protected static int id = 1;

    public IceTower(int x, int y) {
        super("IceTower #" + id++, 15, 2, 0.25f, x, y, 2);
        this.setPositionPrint(x / 32, y / 32);
    }

    @Override
    public boolean attack(AMonster[] monsters) {
        List<AMonster> monstersInRange = getmonstersInRange(monsters);
        AMonster target = chooseTarget(monstersInRange);
        if (target != null) {

            target.receiveDamage(getDamage());
            if (target instanceof BossMonster) {
                target.setSpeed(0.25f);
            } else if (target instanceof RapidMonster) {
                target.setSpeed(1);
            } else {
                target.setSpeed(0.5f);
            }
            return true;

        } else {
            return false;
        }
    }

    @Override
    public void upgradeDamage() {
        if (canUpgradeDamage(player)) {
            float newDamage = getDamage() + 5;
            setDamage(newDamage);
            player.decreaseGold(_upgradeDamageCost);
        } 
    }

    @Override
    public void upgradeRange() {
        if (canUpgradeRange(player)) {
            int newRange = getRange() + 1;
            setRange(newRange);
            player.decreaseGold(_upgradeRangeCost);
        } 
    }

}

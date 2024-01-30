package Main.Tower;

import java.util.List;

import Main.Monster.AMonster;

public class FireTower extends ATower {
    protected static int id = 1;

    public FireTower(int x, int y) {
        super("FireTower #" + id++, 10, 3, 0.25f, x, y, 1);
        this.setPositionPrint(x / 32, y / 32);

    }

    @Override
    public boolean attack(AMonster[] monsters) {
        List<AMonster> monstersInRange = getmonstersInRange(monsters);
        AMonster target = chooseTarget(monstersInRange);
        if (target != null) {
            target.receiveDamage(getDamage());
            target.burn();
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

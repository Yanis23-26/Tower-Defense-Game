package Main.Tower;

public class LongRangeTower extends ATower {
    protected static int id = 1;

    public LongRangeTower(int x, int y) {
        super("LongRangeTower #" + id++, 20, 7, 0.5f, x, y, 3);
        this.setPositionPrint(x / 32, y / 32);
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

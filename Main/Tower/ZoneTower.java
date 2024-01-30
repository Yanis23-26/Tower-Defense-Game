package Main.Tower;

public class ZoneTower extends ATower {

    protected static int id = 1;

    public ZoneTower(int x, int y) {
        super("ZoneTower #"+ id++ ,20, 3, 0.5f,x,y,4);
        
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

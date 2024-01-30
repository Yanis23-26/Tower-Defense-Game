package Main.Tower;

public class NormalTower extends ATower {

    protected static int id = 1;

    public NormalTower(int x, int y){
        super("NormalTower #" + id++, 5, 4, 0.5f, x, y,0);
        this.setPositionPrint(x/32, y/32);
    }
    
    @Override
    public void upgradeDamage() {
        if (canUpgradeDamage(player)) {
            float newDamage = getDamage() + 5;
            setDamage(newDamage);
            player.decreaseGold(_upgradeDamageCost); 
            System.out.println("FireTower Damage upgraded to " + newDamage);
        } else {
            System.out.println("Not enough gold to upgrade Damage.");
        }
    }

    
    @Override
    public void upgradeRange() {
        if (canUpgradeRange(player)) {
            int newRange = getRange() + 1;
            setRange(newRange);
            player.decreaseGold(_upgradeRangeCost); 
            System.out.println("FireTower range upgraded to " + newRange);
        } else {
            System.out.println("Not enough gold to upgrade range.");
        }
    }

    
}

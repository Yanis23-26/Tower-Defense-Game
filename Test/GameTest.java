import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import Main.Tower.*;
import Main.Monster.*;
import Main.Castle.*;
import Main.Player.*;

public class GameTest {

    private Player player;
    private Castle castle;
    private AMonster monster;
    private ATower tower;

    @Before
    public void setUp() {
        castle = new Castle("Test Castle");
        player = new Player("Test Player", castle);
        monster = new NormalMonster("Test Monster", 50, 5, 10, 3, 4);
        tower = new FireTower("Test Tower", 100, 5, 10, 0, 0);
    }

    @Test
    public void testPlayerReceiveGold() {
        player.receiveGold(monster);
        assertEquals(10, player.getGold());
    }

    @Test
    public void testPlayerDecreaseGold() {
        player.decreaseGold(5);
        assertEquals(5, player.getGold());
    }

    @Test
    public void testPlayerCanBuy() {
        assertTrue(player.canBuy(tower));
        assertEquals(0, player.getGold());
    }

    @Test
    public void testPlayerScore() {
        player.score(monster);
        assertEquals(1, player.getScore());
    }

    @Test
    public void testMonsterAttackCastle() {
        assertTrue(monster.attack(castle));
        assertTrue(castle.isDestroyed());
    }

    @Test
    public void testTowerAttack() {
        AMonster[] monsters = {monster};
        assertTrue(tower.attack(monsters));
    }

    @Test
    public void testTowerUpgradeRange() {
        Player player = new Player("Test Player", castle);
        assertTrue(tower.canUpgradeRange(player));
        tower.upgradeRange();
        assertEquals(tower.getRange()+5, tower.getRange());
    }

    @Test
    public void testTowerUpgradeDamage() {
        Player player = new Player("Test Player", castle);
        assertTrue(tower.canUpgradeDamage(player));
        tower.upgradeDamage();
        assertEquals(tower.getDamage(), 0.001);
    }
}

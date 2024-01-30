package Main.Player;

import Main.Castle.Castle;
import Main.Monster.*;
import Main.Tower.*;

public class Player {
    // Attributes
    private String _name;
    private static int _gold;
    private static int _score;
    private Castle _castle;

    // Getter
    public String getName() {
        return this._name;
    }

    public int getGold() {
        return _gold;
    }

    public int getScore() {
        return _score;
    }

    public Castle getCastle() {
        return this._castle;
    }

    // Setter
    public void setScore(int score) {
        _score += score;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setGold(int gold) {
        _gold += gold;
    }

    public void setCastle(Castle castle) {
        this._castle = castle;
    }

    // Constructors
    public Player(String name, Castle myCastle) {
        if (name.equals("")) {
            this._name = "Player";
        } else {
            this._name = name;
        }
        _score = 0;
        _gold = 10;
        this._castle = myCastle;
    }

    // Methods
    /**
     * Add golds to the player
     * 
     * @param monster : The monster killed
     */
    public void receiveGold(AMonster monster) {
        _gold += monster.getGold();
    }

    /**
     * Retire golds to the player
     * 
     * @param cost : The cost of the tower or of the upgrade
     */
    public void decreaseGold(int cost) {
        _gold -= cost;
    }

    /**
     * Check if the player can buy the tower and if he can
     * retire the golds according to the price of the tower
     * 
     * @param tower : The tower that we want to buy
     * @return (boolean) : return true if it's succeeded and false
     *         otherwise
     */
    public boolean canBuy(ATower tower) {
        int temp = this.getGold();
        if (temp > 0) {
            temp -= tower.getPrice();
            if (temp < 0) {
                return false;
            } else {
                // _gold -= tower.getPrice();
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Increase the score when a monster is killed
     * 
     * @param monster : The monster killed
     */
    public void score(AMonster monster) {
        if (monster instanceof NormalMonster) {
            _score++;
        } else if (monster instanceof RapidMonster) {
            _score += 2;
        } else if (monster instanceof JumperMonster) {
            _score += 3;
        } else if (monster instanceof RevivingMonster) {
            _score += 4;
        } else if (monster instanceof BossMonster) {
            _score += 5;
        }
    }
}

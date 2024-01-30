package Main.Tower;

import java.awt.image.BufferedImage;
import java.util.*;

import Main.Monster.*;
import Main.Player.Player;

public abstract class ATower implements ITower {
    // attributs
    protected String _name; // The Tower Name
    protected int _price; // The Price to buy the Tower
    protected int _range; // The range(la porté) to make the tower attack ennemy
    protected float _damage; // The damage that the Tower can do to the monster (The User can upgrade its
                             // Towers)
    protected int _level; // The Force Level of the tower if we upgrade the damage / range.
    protected int _targetNumber; // Number of monsters(monsters) in the range of the tour
    protected Player player; // The player
    protected int _upgradeRangeCost;
    protected int _upgradeDamageCost;
    protected Coordinate _towerPosition;
    protected Coordinate _towerPositionPrint;
    private BufferedImage sprite;
    private int _id;
    public BufferedImage atlas;
    private int _towerType;

    // implement tower type and make it static
    // Getters :
    public String getName() {
        return this._name;
    }

    public int getTowerType() {
        return _towerType;
    }

    public int getPrice() {
        return this._price;
    }

    public int getRange() {
        return this._range;
    }

    public float getDamage() {
        return this._damage;
    }

    public int getLevel() {
        return this._level;
    }

    public int getTargetNumber() {
        return this._targetNumber;
    }

    public Coordinate getTowerPositionPrint() {
        return this._towerPositionPrint;
    }

    public Coordinate getTowerPosition() {
        return this._towerPosition;
    }

    public int getId() {
        return _id;
    }

    // Setters :
    public void setRange(int range) {
        this._range = range;
    }

    public void setTowerType(int type) {
        this._towerType = type;
    }

    public void setDamage(float damage) {
        this._damage = damage;
    }

    public Player getPlayer() {
        return player;
    }

    public float getUpgradeDamageCost() {
        return this._damage;
    }

    public void setPosition(int x, int y) {
        this._towerPosition = new Coordinate(x, y);
    }

    public void setPositionPrint(int x, int y) {
        this._towerPositionPrint = new Coordinate(x, y);
    }
    
    /**
     * Check if the player have ennough golds to upgrade Damage.
     * 
     * @param p the player in question
     * @return true only if he have enough golds.
     */
    public boolean canUpgradeDamage(Player p) {
        return p.getGold() >= _upgradeDamageCost;
    }

    /**
     * Check if the player have ennough golds to upgrade range.
     * 
     * @param p the player in question.
     * @return true only if he have enough golds.
     */
    public boolean canUpgradeRange(Player p) {
        return p.getGold() >= _upgradeRangeCost;
    }

    // constructor :
    public ATower(String name, int price, int range, float damage, int x, int y, int towerType) {
        this._name = name;
        this._price = price;
        this._range = range;
        this._damage = damage;
        this._targetNumber = 0;
        this._towerType = towerType;
        this.setPosition(x, y);
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

    // Abstract methods :
    public abstract void upgradeDamage();

    public abstract void upgradeRange();


    /**
     * Attack method choose the target and attack it
     * 
     * @param monsters : take the monsters in range as parameter
     * @return (boolean) : true if there is target in range, else (target == null)
     *         returns false
     */
    public boolean attack(AMonster[] monsters) {

        List<AMonster> monstersInRange = getmonstersInRange(monsters);
        AMonster target = chooseTarget(monstersInRange);
        if (target != null) {
            // System.out.println(getName() + " is attacking " + target.getName());
            target.receiveDamage(getDamage());
            return true;

        } else {
            // System.out.println(" No Target found.");
            return false;
        }
        // no need to return false; it's an exception
    }

    /**
     * This method take the list of monsters and create the list of the monsters in
     * range
     * 
     * @param monsters : all the monsters
     * @return a list of the monsters in range
     */
    public List<AMonster> getmonstersInRange(AMonster[] monsters) {
        List<AMonster> monstersInRange = new ArrayList<>();
        for (AMonster monster : monsters) {
            try {
                if (ismonsterInRange(monster)) {
                    monstersInRange.add(monster);
                }
            } catch (NoMonsterInRangeException e) {
                // System.out.println("No Monster in range");
            }
        }

        // System.out.println(this.getTowerPositionPrint().getX());
        // System.out.println(this.getTowerPositionPrint().getY());
        // System.out.println("Method calculateDistance work perfectly.");
        // System.out.println("Method isMonsterInRange work perfectly.");
        // System.out.println("Method getMonsterInRange work perfectly.");
        return monstersInRange;

    }

    /**
     * Check if the monster is in range
     * 
     * @param monster : take the monster as parameter
     * @return true if the monster is in the range
     */
    public boolean ismonsterInRange(AMonster monster) throws NoMonsterInRangeException {
        double distance = calculateDistance(monster.getCoordX(), monster.getCoordY());
        if (distance <= getRange()) {
            return true;
        } else {
            throw new NoMonsterInRangeException("No Monster in range ! they are all out of range !");
        }
    }

    /**
     * This méthod calculate the exacte distance between the tower and the monster
     * Uses the pythagore theorma to get the exact distance x = sqrt(a**2+ b**2)
     * 
     * @param targetX the X position of the monster
     * @param targetY the Y position of the monster
     * @return the exact distance between tower and monster (in double)
     */
    public double calculateDistance(int targetX, int targetY) {
        return Math.sqrt(Math.pow(targetX - getTowerPositionPrint().getX(), 2) +
                Math.pow(targetY - getTowerPositionPrint().getY(), 2));
    }

    /**
     * Choose the monster to attack (the first one, I have to choose the closest
     * one)
     * In order to return the closest monster, we create a hashmap of the monsters
     * and their distance from the tower
     * We sort the hashmap
     * We return the first monster in the HashMap List
     * 
     * @param monstersInRange : take all the monsters in the range
     * @return : the first monster in list
     * 
     */
    public AMonster chooseTarget(List<AMonster> monstersInRange) {
        if (!monstersInRange.isEmpty()) {
            HashMap<AMonster, Double> monstersHM = new HashMap<AMonster, Double>();
            List<Map.Entry<AMonster, Double>> list = new ArrayList<>();

            for (AMonster monster : monstersInRange) {
                monstersHM.put(monster, (calculateDistance(monster.getCoordX(), monster.getCoordY())));
            }
            list = sortMonstersHM(monstersHM);

            Map.Entry<AMonster, Double> firstEntry = list.get(0);
            AMonster closestMonster = firstEntry.getKey();
            // System.out.println("Method chooseTarget work perfectly : The target chosen");
            return closestMonster;
        }
        // System.out.println("Method chooseTarget work perfectly : The list of monsters
        // in range is empty.");
        return null;
    }

    /**
     * This method sort the hashmap <Monster,distance> the closest is first
     * 
     * @param notSorted : the list of monster, but not yet sorted
     *                  Note : no return because it's just sort the list
     */
    public List<Map.Entry<AMonster, Double>> sortMonstersHM(HashMap<AMonster, Double> monstersHashMap) {
        Set<Map.Entry<AMonster, Double>> entrySet = monstersHashMap.entrySet();
        List<Map.Entry<AMonster, Double>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<AMonster, Double>>() {
            @Override
            public int compare(Map.Entry<AMonster, Double> o1, Map.Entry<AMonster, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return list;
    }

}
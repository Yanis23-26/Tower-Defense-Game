package Main.Monster;

import Main.Castle.*;
import Main.helpers.LoadSave;

import static Main.Monster.Direction.MyDirection.*;

import java.awt.Rectangle;

public abstract class AMonster implements IMonster {
    // Attributes
    protected String _name;
    protected float _hp;
    protected boolean _alive;
    protected int _gold;
    protected float _damage;
    protected int _range;
    protected float _speed;
    protected int _coordX;
    protected int _coordY;
    public int[][] _map;
    protected int _direction;
    protected boolean _freeze = false;
    protected Rectangle _bounds;
    protected float _coordPrintX;
    protected float _coordPrintY;
    protected int _cango = 1;
    protected float _maxHP;
    protected float _maxspeed;

    // Getter
    public float getHp() {
        return this._hp;
    }

    public float getSpeed() {
        return this._speed;
    }

    public boolean isAlive() {
        return this._alive;
    }

    public int getGold() {
        return this._gold;
    }

    public float getDamage() {
        return this._damage;
    }

    public int getRange() {
        return this._range;
    }

    public String getName() {
        return this._name;
    }

    public int getCoordX() {
        return this._coordX;
    }

    public int getCoordY() {
        return this._coordY;
    }

    public int getDirection() {
        return this._direction;
    }

    public Rectangle getBounds() {
        return this._bounds;
    }

    public float getCoordPrintX() {
        return this._coordPrintX;
    }

    public float getCoordPrintY() {
        return this._coordPrintY;
    }

    public int getCango() {
        return this._cango;
    }

    public float getHealthBar() {
        return this._hp / (float) this._maxHP;
    }

    // Setter
    public void setCoordX(int coordX) {
        this._coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this._coordY = coordY;
    }

    public void setFreeze(boolean freeze) {
        this._freeze = freeze;
    }

    public void setCoordPrintX(float coordX) {
        this._coordPrintX = coordX;
    }

    public void setCoordPrintY(float coordY) {
        this._coordPrintY = coordY;
    }

    public void setCango(int cango) {
        this._cango = cango;
    }

    public void setSpeed(float speed) {
        this._speed = speed;
    }

    // Constructors
    protected AMonster(String name, float hp, int gold, float damage, int range, float speed) {
        this._name = name;
        this._alive = true;
        this._hp = hp;
        this._gold = gold;
        this._damage = damage;
        this._range = range;
        this._speed = speed;
        _maxHP = hp;
        _maxspeed = speed;
    }

    // Methods
    /**
     * Attack the castle in parameter if it's cloose to it
     * 
     * @param mycastle : take a castle as paramaet
     * @return (boolean) : true if the monster succeeded to attack him
     */
    public boolean attack(Castle mycastle) {
        if (!this._freeze) {
            if (this.isAlive()) {
                // Attack Right
                if (this.getCoordX() + 1 < this._map[this.getCoordY()].length) {
                    if (this._map[this.getCoordY()][this.getCoordX() + 1] == 3) {
                        if (mycastle.getHp() > 0) {
                            mycastle.receiveDamage(this.getDamage());
                            this._hp = 0;
                            this._alive = false;
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

                // Attack Left
                if (this.getCoordX() - 1 >= 0) {
                    if (this._map[this.getCoordY()][this.getCoordX() - 1] == 3) {
                        if (mycastle.getHp() > 0) {
                            mycastle.receiveDamage(this.getDamage());
                            this._hp = 0;
                            this._alive = false;
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

                // Attack Up
                if (this.getCoordY() - 1 >= 0) {
                    if (this._map[this.getCoordY() - 1][this.getCoordX()] == 3) {
                        if (mycastle.getHp() > 0) {
                            mycastle.receiveDamage(this.getDamage());
                            this._hp = 0;
                            this._alive = false;
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

                // Attack Down
                if (this.getCoordY() + 1 < this._map.length) {
                    if (this._map[this.getCoordY() + 1][this.getCoordX()] == 3) {
                        if (mycastle.getHp() > 0) {
                            mycastle.receiveDamage(this.getDamage());
                            this._hp = 0;
                            this._alive = false;
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

                return false;
            } else {
                return false;
            }
        } else {
            this._freeze = false;
            return false;
        }
    }

    /**
     * This method check if the monster can move UP
     * 
     * @return (boolean) : return true if the monster can move UP and false
     *         if he can't
     */
    public boolean canMoveU() {
        if (this.isAlive()) {
            if (this.getCoordY() - 1 >= 0) {
                if (this._map[this.getCoordY() - 1][this.getCoordX()] == 2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method check if the monster can move LEFT
     * 
     * @return (boolean) : return true if the monster can move LEFT and false
     *         if he can't
     */
    public boolean canMoveL() {
        if (this.isAlive()) {
            if (this.getCoordX() - 1 >= 0) {
                if (this._map[this.getCoordY()][this.getCoordX() - 1] == 2) {

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method check if the monster can move RIGHT
     * 
     * @return (boolean) : return true if the monster can move RIGHT and false
     *         if he can't
     */
    public boolean canMoveR() {
        if (this.isAlive()) {
            if (this.getCoordX() + 1 < this._map[this.getCoordY()].length) {
                if (this._map[this.getCoordY()][this.getCoordX() + 1] == 2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method check if the monster can move DOWN
     * 
     * @return (boolean) : return true if the monster can move DOWN and false
     *         if he can't
     */
    public boolean canMoveD() {
        if (this.isAlive()) {
            if (this.getCoordY() + 1 < this._map.length) {
                if (this._map[this.getCoordY() + 1][this.getCoordX()] == 2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public abstract boolean move();

    /**
     * This method take as parameter a damage as integer and soustract
     * the damages to the HP of the monster, it's also check he is alive after
     * this soustraction
     * 
     * @param damage : the damage of the entity which as attacked him
     */
    public void receiveDamage(float damage) {
        if (this.isAlive()) {
            if (damage > 0) {
                this._hp -= damage;
            }

            if (this._hp <= 0) {
                this._hp = 0;
                this._alive = false;
            }
        }
    }

    /**
     * Open the file where there is the map and store it in memories
     */
    public void LearnMap() {
        int[][] lvl = LoadSave.GetLevelData("new_level");
        this._map = lvl;
        this.spawn();
    }

    /**
     * Search in the map where the spawn is located and where it's
     * find affect the right coordinates and after check where he
     * can move for the first time
     */
    public void spawn() {
        for (int i = 0; i < _map.length; i++) {
            for (int j = 0; j < _map[i].length; j++) {
                if (_map[i][j] == 4) {
                    this._coordX = j;
                    this._coordY = i;
                    this._coordPrintX = 32 * j;
                    this._coordPrintY = 32 * i;
                }
            }
        }

        if (canMoveU()) {
            this._direction = UP;
        } else if (canMoveL()) {
            this._direction = LEFT;
        } else if (canMoveR()) {
            this._direction = RIGHT;
        } else if (canMoveD()) {
            this._direction = DOWN;
        }
        this._bounds = new Rectangle((int) this._coordPrintX, (int) this._coordPrintY, 32, 32);
    }

    /**
     * Take 5 damages and check if the monster is alive or not after
     */
    public void burn() {
        if (this._alive) {
            for (int i = 0; i < 5; i++) {
                this.receiveDamage(0.1f);
            }
            if (this._hp <= 0) {
                this._hp = 0;
                this._alive = false;
            }
        }
    }

    public void resetSpeed() {
        if (this._speed != this._maxspeed) {
            this._speed = _maxspeed;
        }
    }
}

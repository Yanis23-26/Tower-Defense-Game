package Main.Castle;

import Main.helpers.LoadSave;

public class Castle {
    // Attributes
    private String _name;
    private float _hp;
    private boolean _destroyed;
    private int _x;
    private int _y;

    // Getter
    public String getName() {
        return this._name;
    }

    public float getHp() {
        return this._hp;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public boolean isDestroyed() {
        return this._destroyed;
    }

    // Setter
    public void setHp(float hp) {
        this._hp = hp;
    }

    public void setDestroyed(boolean destroyed) {
        this._destroyed = destroyed;
    }

    // Constructors
    public Castle(String name) {
        this._name = name;
        this._hp = 100;
        this._destroyed = false;
    }

    // Methods
    public void receiveDamage(float damage) {
        if (damage > 0) {
            this._hp -= damage;
        }

        if (this._hp <= 0) {
            this._hp = 0;
            this._destroyed = true;
        }
    }

    /**
     * Open the file where the map if located and search where he can
     * spawn. If he succeeded to find it's affect the right coordinates
     */
    public void spawn() {
        int[][] map = LoadSave.GetLevelData("new_level");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 3) {
                    this._x = 32 * j;
                    this._y = 32 * i;
                }
            }
        }
    }
}

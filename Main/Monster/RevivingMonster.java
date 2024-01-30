package Main.Monster;

import static Main.Monster.Direction.MyDirection.*;

public class RevivingMonster extends AMonster {
    protected int _revive;
    protected static int id = 1;

    // Getter
    public int getRevive() {
        return this._revive;
    }

    // Constructors
    public RevivingMonster() {
        super("RevivingMonster #" + id++, 100, 10, 9, 1, 1);
        this._revive = 1;
    }

    // Methods
    /**
     * Check where he can move and move with he's speed
     * 
     * @return (boolean) : true if the monster succeeded to move or false
     *         if he not
     */
    @Override
    public boolean move() {

        if (this._freeze) {
            this._freeze = false;
            return false;
        }

        if (this._direction == UP) {
            if (this.canMoveR()) {
                this.setCoordPrintX(this.getCoordPrintX() + this.getSpeed());
                if (this.getCoordPrintX() / 32 == (int) this.getCoordX() + 1) {
                    this.setCoordX(this.getCoordX() + 1);
                    this._direction = RIGHT;
                }
                return true;

            } else if (this.canMoveU()) {
                this.setCoordPrintY(this.getCoordPrintY() - this.getSpeed());
                if (this.getCoordPrintY() / 32 == (int) this.getCoordY() - 1) {
                    this.setCoordY(this.getCoordY() - 1);
                    this._direction = UP;
                }
                return true;

            } else if (this.canMoveL()) {
                this.setCoordPrintX(this.getCoordPrintX() - this.getSpeed());
                if (this.getCoordPrintX() / 32 == (int) this.getCoordX() - 1) {
                    this.setCoordX(this.getCoordX() - 1);
                    this._direction = LEFT;
                }
                return true;
            }

            else {
                return false;
            }
        } else if (this._direction == LEFT) {
            if (this.canMoveL()) {
                this.setCoordPrintX(this.getCoordPrintX() - this.getSpeed());
                if (this.getCoordPrintX() / 32 == (int) this.getCoordX() - 1) {
                    this.setCoordX(this.getCoordX() - 1);
                    this._direction = LEFT;
                }
                return true;

            } else if (this.canMoveU()) {
                this.setCoordPrintY(this.getCoordPrintY() - this.getSpeed());
                if (this.getCoordPrintY() / 32 == (int) this.getCoordY() - 1) {
                    this.setCoordY(this.getCoordY() - 1);
                    this._direction = UP;
                }
                return true;

            } else if (this.canMoveD()) {
                this.setCoordPrintY(this.getCoordPrintY() + this.getSpeed());
                if (this.getCoordPrintY() / 32 == (int) this.getCoordY() + 1) {
                    this.setCoordY(this.getCoordY() + 1);
                    this._direction = DOWN;
                }
                return true;
            }

            else {
                return false;
            }
        } else if (this._direction == RIGHT) {
            if (this.canMoveR()) {
                this.setCoordPrintX(this.getCoordPrintX() + this.getSpeed());
                if (this.getCoordPrintX() / 32 == (int) this.getCoordX() + 1) {
                    this.setCoordX(this.getCoordX() + 1);
                    this._direction = RIGHT;
                }
                return true;

            } else if (this.canMoveU()) {
                this.setCoordPrintY(this.getCoordPrintY() - this.getSpeed());
                if (this.getCoordPrintY() / 32 == (int) this.getCoordY() - 1) {
                    this.setCoordY(this.getCoordY() - 1);
                    this._direction = UP;
                }
                return true;

            } else if (this.canMoveD()) {
                this.setCoordPrintY(this.getCoordPrintY() + this.getSpeed());
                if (this.getCoordPrintY() / 32 == (int) this.getCoordY() + 1) {
                    this.setCoordY(this.getCoordY() + 1);
                    this._direction = DOWN;
                }
                return true;
            }

            else {
                return false;
            }
        } else if (this._direction == DOWN) {
            if (this.canMoveR()) {
                this.setCoordPrintX(this.getCoordPrintX() + this.getSpeed());
                if (this.getCoordPrintX() / 32 == (int) this.getCoordX() + 1) {
                    this.setCoordX(this.getCoordX() + 1);
                    this._direction = RIGHT;
                }
                return true;

            } else if (this.canMoveL()) {
                this.setCoordPrintX(this.getCoordPrintX() - this.getSpeed());
                if (this.getCoordPrintX() / 32 == (int) this.getCoordX() - 1) {
                    this.setCoordX(this.getCoordX() - 1);
                    this._direction = LEFT;
                }
                return true;
            }

            else if (this.canMoveD()) {
                this.setCoordPrintY(this.getCoordPrintY() + this.getSpeed());
                if (this.getCoordPrintY() / 32 == (int) this.getCoordY() + 1) {
                    this.setCoordY(this.getCoordY() + 1);
                    this._direction = DOWN;
                }
                return true;
            }

            else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public void receiveDamage(float damage) {
        if (this.isAlive()) {
            if (damage > 0) {
                this._hp -= damage;
            }

            if (this._hp <= 0) {
                if (this._revive == 1) {
                    this._hp = _maxHP;
                    this._revive = 0;
                } else {
                    this._hp = 0;
                    this._alive = false;
                }
            }
        }
    }
}

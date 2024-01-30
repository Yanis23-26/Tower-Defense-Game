package Main.Monster;

import static Main.Monster.Direction.MyDirection.*;

public class BossMonster extends AMonster {
    // Attributes
    protected static int id = 1;
    protected static int canmove = 1;

    // Constructors
    public BossMonster() {
        super("BossMonster #" + id++, 150, 15, 15, 1, 0.5f);
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
}

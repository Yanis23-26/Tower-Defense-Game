package Main.Monster;

import Main.Castle.*;

public interface IMonster {

    public void setCoordX(int coordX);

    public void setCoordY(int coordY);

    // Methods

    public boolean attack(Castle myCastle);

    public boolean canMoveU();

    public boolean canMoveL();

    public boolean canMoveR();

    public boolean canMoveD();

    public boolean move();

    public void receiveDamage(float damage);

    public void LearnMap();
}
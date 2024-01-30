package Main.Tower;

public class Coordinate {
    //Attributs :
    int _x;
    int _y;

    //Constructor :
    public Coordinate(int x, int y){
        this._x = x;
        this._y = y;
    }

    //Getters : to get the position
    public int getX(){
        return this._x;
    }
    public int getY(){
        return this._y;
    }

    //Setters : to change the position
    public void setX(int x){
        this._x = x;
    }
    public void setY(int y){
        this._y = y;
    }
}

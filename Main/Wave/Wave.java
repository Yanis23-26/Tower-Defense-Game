package Main.Wave;

import java.util.ArrayList;

import Main.Monster.*;

public class Wave {
    // Attributes
    protected static int _nbwave = 1;
    protected ArrayList<AMonster> _mywave;

    // Getter
    public int getNbwave() {
        return _nbwave;
    }

    public ArrayList<AMonster> getMywave() {
        return this._mywave;
    }

    // Constructors
    public Wave() {
        this._mywave = new ArrayList<>();
    }

    // Methods
    /**
     * Create an arraylist where we put some monsters in it.
     * The 3 first rounds this is only normal monster and after
     * we add a lot more monsters and we randomise it in order to have
     * all sort of monster. Each 5 and 11 rounds we have respectively
     * 1 BossMonster and 2 BossMonster
     * 
     * @return (ArrayList<AMonster>) : return the list where we have the monsters
     */
    public ArrayList<AMonster> createwave() {
        double random = 0;
        this._mywave = new ArrayList<>();
        if (_nbwave <= 3) {
            for (int i = 0; i < 5; i++) {
                _mywave.add(new NormalMonster());
            }
        } else if (_nbwave % 5 == 0) {
            _mywave.add(new BossMonster());
            for (int i = 0; i < 5 + _nbwave - 1; i++) {
                random = Math.random() * 3;
                switch ((int) random) {
                    case 0:
                        _mywave.add(new NormalMonster());
                        break;
                    // case 1:
                    // _mywave.add(new JumperMonster());
                    // break;
                    case 1:
                        _mywave.add(new RapidMonster());
                        break;
                    case 2:
                        _mywave.add(new RevivingMonster());
                        break;
                }
            }
        } else if (_nbwave % 11 == 0) {
            _mywave.add(new BossMonster());
            _mywave.add(new BossMonster());
            for (int i = 0; i < 5 + _nbwave - 2; i++) {
                random = Math.random() * 3;
                switch ((int) random) {
                    case 0:
                        _mywave.add(new NormalMonster());
                        break;
                    // case 1:
                    // _mywave.add(new JumperMonster());
                    // break;
                    case 1:
                        _mywave.add(new RapidMonster());
                        break;
                    case 2:
                        _mywave.add(new RevivingMonster());
                        break;
                }
            }

        } else {
            for (int i = 0; i < 5 + _nbwave; i++) {
                random = Math.random() * 3;
                switch ((int) random) {
                    case 0:
                        _mywave.add(new NormalMonster());
                        break;
                    // case 1:
                    // _mywave.add(new JumperMonster());
                    // break;
                    case 1:
                        _mywave.add(new RapidMonster());
                        break;
                    case 2:
                        _mywave.add(new RevivingMonster());
                        break;
                }
            }
        }
        _nbwave++;
        return _mywave;
    }

    /**
     * Print the name of all the monsters in the list
     */
    public void printarray() {
        for (AMonster myMonster : this._mywave) {
            System.out.println(myMonster.getName());
        }
    }
}

package Main.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.scenes.Playing;
import Main.Castle.Castle;
import Main.Monster.*;
import Main.Player.Player;
import Main.Wave.*;
import Main.helpers.LoadSave;
import java.util.*;

public class MonsterManager {
    // Attributes
    private Playing playing;
    private Player player;
    private BufferedImage[] monsterImgs;
    private Castle castle;
    protected static ArrayList<AMonster> monsterList = new ArrayList<>();
    protected static ArrayList<AMonster> monsterCopyList = new ArrayList<>();

    protected static ArrayList<AMonster> index = new ArrayList<>();
    protected Wave myWave = new Wave();
    protected static int nextCase = 0;
    protected static int toAdd = 0;
    protected int hasattacked = 0;
    protected static int wave = 1;

    // Getter
    public Playing getPlaying() {
        return playing;
    }

    public static ArrayList<AMonster> getMonsterCopyList() {
        return monsterCopyList;
    }

    public Castle getCastle() {
        return this.castle;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getWave() {
        return wave;
    }

    // Setter
    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // Constructors
    public MonsterManager(Playing playing, Player player, Castle castle) {
        this.playing = playing;
        this.player = player;
        this.castle = castle;
        this.monsterImgs = new BufferedImage[4];
        loadMonsterImgs();
        monsterList = myWave.createwave();
        monsterCopyList.add(monsterList.get(0));
        monsterList.remove(0);
        for (AMonster monster : monsterCopyList) {
            monster.LearnMap();
            if (monster.canMoveU()) {
                nextCase = monster.getCoordY() - 1;
            } else if (monster.canMoveL()) {
                nextCase = monster.getCoordX() - 1;
            } else if (monster.canMoveR()) {
                nextCase = monster.getCoordX() + 1;
            } else if (monster.canMoveD()) {
                nextCase = monster.getCoordY() + 1;
            }
        }
    }

    // Methods
    /**
     * Load all the monster images in a list
     */
    private void loadMonsterImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        monsterImgs[0] = atlas.getSubimage(0 * 32, 1 * 32, 32, 32);
        monsterImgs[1] = atlas.getSubimage(1 * 32, 1 * 32, 32, 32);
        monsterImgs[2] = atlas.getSubimage(2 * 32, 1 * 32, 32, 32);
        monsterImgs[3] = atlas.getSubimage(3 * 32, 1 * 32, 32, 32);
    }

    /**
     * The method update the path of all the monsters in a list and check everytime
     * if they are close to the castle. If one of them is close to, he attacks the
     * castle and instantly die (yeah that sadistic I know) and we remove it for the
     * list
     */
    public void update() {
        if (!(monsterCopyList.isEmpty())) {
            for (AMonster monster : monsterCopyList) {
                if (monster != null) {

                    if (monster.getCango() == 1) {
                        if (monster.getCoordX() == nextCase || monster.getCoordY() == nextCase) {
                            if (!monsterList.isEmpty()) {
                                toAdd = 1;
                                monster.setCango(0);
                            }
                        }
                    }

                    if (monster.getCoordX() + 1 < monster._map[monster.getCoordY()].length) {
                        if (monster._map[monster.getCoordY()][monster.getCoordX() + 1] == 3) {
                            monster.attack(castle);
                            hasattacked = 1;
                        }
                    }

                    if (monster.getCoordX() > 0) {
                        if (monster._map[monster.getCoordY()][monster.getCoordX() - 1] == 3) {
                            monster.attack(castle);
                            hasattacked = 1;
                        }
                    }

                    if (monster.getCoordY() - 1 > 0) {
                        if (monster._map[monster.getCoordY() - 1][monster.getCoordX()] == 3) {
                            monster.attack(castle);
                            hasattacked = 1;
                        }
                    }

                    if (monster.getCoordY() + 1 < monster._map.length) {
                        if (monster._map[monster.getCoordY() + 1][monster.getCoordX()] == 3) {
                            monster.attack(castle);
                            hasattacked = 1;

                        }
                    }

                    if (monster.getHp() == 0) {
                        if (hasattacked == 0) {
                            player.setGold(monster.getGold());
                            player.score(monster);
                        }
                        index.add(monster);
                        continue;
                    }
                    monster.move();
                    monster.resetSpeed();
                }
            }
            hasattacked = 0;
            monsterCopyList.removeAll(index);
            if (toAdd == 1) {
                monsterList.get(0).LearnMap();
                monsterCopyList.add(monsterList.get(0));
                monsterList.remove(0);
                toAdd = 0;
            }
        } else {
            wave++;
            monsterList = myWave.createwave();
            monsterCopyList.add(monsterList.get(0));
            monsterList.remove(0);
            for (AMonster monster : monsterCopyList) {
                monster.LearnMap();
            }
        }
    }

    /**
     * For all the monsters we redraw all the images in order to
     * update the where he is now in the map
     */
    public void draw(Graphics g) {
        if (!(monsterCopyList.isEmpty())) {
            for (AMonster monster : monsterCopyList) {
                drawMonster(monster, g);
                drawHealth(monster, g);
            }
        }
    }

    /**
     * Draw the image at the right coordinates
     */
    public void drawMonster(AMonster m, Graphics g) {
        // add monster type sprite
        if (m instanceof NormalMonster) {
            g.drawImage(monsterImgs[0], (int) m.getCoordPrintX(), (int) m.getCoordPrintY(), null);
            return;
        }
        if (m instanceof RapidMonster) {
            g.drawImage(monsterImgs[1], (int) m.getCoordPrintX(), (int) m.getCoordPrintY(), null);
            return;
        }
        if (m instanceof RevivingMonster) {
            g.drawImage(monsterImgs[2], (int) m.getCoordPrintX(), (int) m.getCoordPrintY(), null);
            return;
        }
        if (m instanceof BossMonster) {
            g.drawImage(monsterImgs[3], (int) m.getCoordPrintX(), (int) m.getCoordPrintY(), null);
            return;
        }
    }

    public void drawHealth(AMonster monster, Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) monster.getCoordPrintX() + 16 - (getNewBarWidth(monster) / 2),
                (int) monster.getCoordPrintY() + 40, getNewBarWidth(monster), 3);
    }

    private int getNewBarWidth(AMonster monster) {
        return (int) (20 * monster.getHealthBar());
    }

}

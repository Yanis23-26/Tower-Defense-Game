package Main.Tower;

public interface ITower {
    
    //Getters :
    public int getPrice();
    public int getRange();
    public float getDamage();
    public int getLevel();
    public int getTargetNumber();
    

    //Setters :
    public void setRange(int range);
    public void setDamage(float damage);
    


    //Methodes :
    public void upgradeRange();
    public void upgradeDamage();

    //public void attack();
    
    
    
   
    
}

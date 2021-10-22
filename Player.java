import java.util.ArrayList;

public class Player {
    private int money;
    private final String name;
    private ArrayList<Property> properties;

    public Player(String name, int money){
        this.name = name;
        this.money = money;
    }

    public void removeMoney(int money){
        this.money = this.money - money;
    }

    public void addMoney(int money){
        this.money = this.money + money;
    }

    public ArrayList<Property> getProperties() {
        return this.properties;
    }


    public int getMoney(){
        return this.money;
    }

    public String getPiece(){
        return this.name;
    }

}

public class Property {

    private String propertyName;
    private int cost;
    private boolean owned;
    private Player owner;


    public Property(String pName, int c){
        this.propertyName = pName;
        this.cost = c;
        this.owned = false;
        this.owner = null;

    }


}

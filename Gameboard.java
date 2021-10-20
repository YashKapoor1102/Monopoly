public class Gameboard {
    private String[] properties;
    private int[] prices;

    public Gameboard(){
        this.properties = new String[]{"Mediterranean Avenue/Old Kent Rd", "Baltic Avenue/Whitechapel Rd",
                "Oriental Avenue/The Angel, Islington", "Vermont Avenue/Euston Rd",
                "Connecticut Avenue/Pentonville Rd", "St. Charles Place/Pall Mall", "States Avenue/Whitehall",
                "Virginia Avenue/Northumberland Ave", "St. James Place/Bow St", "Tennessee Avenue/Marlborough St",
                "New York Avenue/Vine St", "Kentucky Avenue/Strand", "Indiana Avenue/Fleet St", "Illinois Avenue/Trafalgar Sq",
                "Atlantic Avenue/Leicester Sq", "Ventnor Avenue/Coventry St", "* Marvin Gardens [Marven Gardens]/Piccadilly",
                "Pacific Avenue/Regent St", "* North Carolina Avenue [South Carolina Avenue]/Oxford St",
                "Pennsylvania Avenue/Bond St", "Park Place/Park Lane", "Boardwalk/Mayfair"};
        this.prices = new int[]{60, 60, 100, 100, 120, 140, 140, 160, 180, 180, 200, 220, 220, 240, 260, 260, 280,
                300, 300, 320, 350, 400};
    }



}

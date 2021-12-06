/**
 * @author Robert Simionescu
 * @version Milestone 4
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * @author Robert Simionescu
 *
 * Used to read the gameboard from an XML File
 */
public class GameboardHandler extends DefaultHandler
{
    private static final String SQUARE = "Square";
    private static final String STREET = "Street";
    private static final String RAILROAD = "Railroad";
    private static final String UTILITY = "Utility";
    private static final String NAME = "Name";
    private static final String COLOUR = "Colour";
    private static final String PROPERTY_COST = "PropertyCost";
    private static final String HOUSE_COST = "HouseCost";
    private static final String HOTEL_COST = "HotelCost";

    private String name;
    private String colour;
    private int cost;
    private int houseCost;
    private int hotelCost;

    private ArrayList<Square> squares;
    private StringBuilder stringBuilder;

    /**
     * @author Robert Simionescu
     *  Get the characters that are in between the start element and the end element
     *  and add the information a StringBuilder
     *
     * @param ch        a char array, the list of characters that lie in between the start and end element
     * @param start     an int, the position in the XML document where the characters start
     * @param length    an int, the total number of characters
     *
     * @throws SAXException     Handling Exceptions
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if (stringBuilder == null)
        {
            stringBuilder = new StringBuilder();
        }
        else
        {
            stringBuilder.append(ch, start, length);
        }
    }

    /**
     * @author Robert Simionescu
     * Initializing the Squares ArrayList at the beginning of the XML document
     *
     * @throws SAXException     Handling exceptions
     */
    @Override
    public void startDocument() throws SAXException
    {
        squares = new ArrayList<>();
    }

    /**
     * @author Robert Simionescu
     * Get the start elements in an XML file and instantiate a new
     * StringBuilder
     *
     * @param uri           a String
     * @param localName     a String
     * @param qName         a String, the name of the start element in the XML file
     * @param attributes    an Attributes Object
     *
     * @throws SAXException     Handling exceptions
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName)
        {
            case SQUARE:
            case UTILITY:
            case STREET:
            case RAILROAD:
                break;
            case NAME:
            case COLOUR:
            case PROPERTY_COST:
            case HOUSE_COST:
            case HOTEL_COST:
                stringBuilder = new StringBuilder();
                break;
        }
    }

    /**
     * @author Robert Simionescu
     * Get the end elements in an XML file and add each type of square
     * to the Squares ArrayList
     *
     * @param uri               a String
     * @param localName         a String
     * @param qName             a String, the name of the end element in the XML file
     *
     * @throws SAXException         Handling exceptions
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case SQUARE -> squares.add(new GenericSquare(name));
            case STREET -> squares.add(new Street(name, colour, cost, houseCost, hotelCost));
            case RAILROAD -> squares.add(new Railroad(name, cost));
            case UTILITY -> squares.add(new Utility(name, cost));
            case NAME -> name = stringBuilder.toString();
            case COLOUR -> colour = stringBuilder.toString();
            case PROPERTY_COST -> cost = Integer.parseInt(stringBuilder.toString());
            case HOUSE_COST -> houseCost = Integer.parseInt(stringBuilder.toString());
            case HOTEL_COST -> hotelCost = Integer.parseInt(stringBuilder.toString());
        }
    }

    /**
     * @author Robert Simionescu
     * Get the gameboard that is initialized with squares
     *
     * @return      a Gameboard Object, the new gameboard that is going to be used
     */
    public Gameboard getGameboard()
    {
        return new Gameboard(squares);
    }
}

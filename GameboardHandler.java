import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

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

    @Override
    public void startDocument() throws SAXException
    {
        squares = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName)
        {
            case SQUARE:
                break;
            case STREET:
                break;
            case RAILROAD:
                break;
            case UTILITY:
                break;
            case NAME:
                stringBuilder = new StringBuilder();
                break;
            case COLOUR:
                stringBuilder = new StringBuilder();
                break;
            case PROPERTY_COST:
                stringBuilder = new StringBuilder();
                break;
            case HOUSE_COST:
                stringBuilder = new StringBuilder();
                break;
            case HOTEL_COST:
                stringBuilder = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName)
        {
            case SQUARE:
                squares.add(new GenericSquare(name));
                break;
            case STREET:
                squares.add(new Street(name, colour, cost, houseCost, hotelCost));
                break;
            case RAILROAD:
                squares.add(new Railroad(name, cost));
                break;
            case UTILITY:
                squares.add(new Utility(name, cost));
                break;
            case NAME:
                name = stringBuilder.toString();
                break;
            case COLOUR:
                colour = stringBuilder.toString();
                break;
            case PROPERTY_COST:
                cost = Integer.parseInt(stringBuilder.toString());
                break;
            case HOUSE_COST:
                houseCost = Integer.parseInt(stringBuilder.toString());
                break;
            case HOTEL_COST:
                hotelCost = Integer.parseInt(stringBuilder.toString());
                break;
        }
    }

    public Gameboard getGameboard()
    {
        return new Gameboard(squares);
    }
}

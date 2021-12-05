public class GenericSquare implements Square
{
    private final String NAME;

    public GenericSquare(String name)
    {
        NAME = name;
    }

    @Override
    public String getName() {
        return NAME;
    }
}



public class Critter
{
    public static final char DEFAULT_APPEARANCE = '*';
    public static final char EMPTY = ' ';
    private char appearance;  


    public Critter ()
    {
	setAppearance(DEFAULT_APPEARANCE);
    }

    public Critter(char ch)
    {
	setAppearance(ch);
    }

    public char getAppearance()
    {
	return appearance;
    } 

    public void setAppearance(char newAppearance)
    {
        appearance = newAppearance;
    } 
    public String toString()
    {
	String s = "" + appearance;
        return(s);
    }
}


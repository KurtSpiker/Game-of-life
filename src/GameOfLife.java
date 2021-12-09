import java.util.Scanner;


public class GameOfLife
{
    public static void main (String [] args)
    {
        Biosphere regular;
        regular = new Biosphere(FileInitialization.read());
        regular.runTurn();
    }
}





import java.util.Scanner;
import java.util.Random;

import javax.lang.model.util.ElementScanner14;
public class Biosphere
{
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final String HORIZONTAL_LINE = "  - - - - - - - - - -";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";
    private Critter [][] current;
    private Critter [][] birthDeath;
    private Critter [][] tVirus;

    public Biosphere(Critter [][] aWorld)
    {
        current = aWorld;
        birthDeath = deepCopy(current);
        tVirus = deepCopy(current);

    }

    public void display()
    { 
        int i;
        int r;
        int c;
	System.out.println("  PREVIOUS GENERATION");
	System.out.println(HORIZONTAL_COUNT);
        System.out.print(" ");
        for (i = 0; i < ROWS; i++)
            System.out.print(" -"); //Line of dashes before the array
        System.out.println();
	for (r = 0; r < ROWS; r++)
	{
	    System.out.print(r); //Line # before each row
	    for (c = 0; c < COLUMNS; c++)
	    {
		System.out.print("|" + birthDeath[r][c]); //Bounding line left of array element
            }
            System.out.println("|"); //Bounding line at the of the row for the last element
            System.out.print(" ");
            for (i = 0; i < ROWS; i++)
                System.out.print(" -");  //Bounding line below each array element
            System.out.println();
        }
    }

    public Critter [][] getCurrent() 
    {
        return(current);
    }

    public Critter[][] deepCopy(Critter [][] current)
    {
        Critter [][] temp = new Critter [current.length][current[0].length];
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLUMNS; c++)
            {
                temp[r][c] = current[r][c];
            }
        }
        return temp;
    }
    
    private void tVriusMove(int r, int c)
    {
        while (true)
        {
            Random rand = new Random();
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            
            if (tVirus[x][y].getAppearance() == Critter.EMPTY)
            {
                tVirus[x][y] = new Taminator(Taminator.DEFAULT_APPEARANCE);
                tVirus[r][c] = new Critter(Critter.EMPTY);
                break;
            }
        }
    }

    private boolean checkIndices(int r, int c, Critter [][] check)
    {
        if(r < 0 || r >= ROWS || c < 0 || c >= COLUMNS)
        {
            return false;
        }
        if(check[r][c].getAppearance() == Critter.EMPTY)
        {
            return false;
        }
  
        return true;
    }
    
    public int birthdeathAreaCheck(int r,int c)
    {
        int unitCount = 0;
        if (checkIndices(r-1,c-1,current) && current[r-1][c-1].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r-1,c,current) && current[r-1][c].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r-1,c+1,current) && current[r-1][c+1].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r,c-1,current) && current[r][c-1].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r,c+1,current) && current[r][c+1].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r+1,c-1,current) && current[r+1][c-1].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r+1,c,current) && current[r+1][c].getAppearance() == '*')
        {
            unitCount ++;
        }
        if (checkIndices(r+1,c+1,current) && current[r+1][c+1].getAppearance() == '*')
        {
            unitCount ++;
        }
        return unitCount;   
    }
    
    public void unitCheck()
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLUMNS; c++)
            {
                if (current[r][c].getAppearance() == Critter.EMPTY)
                {
                    if (birthdeathAreaCheck(r,c) == 3)
                    {
                        birthDeath[r][c] = new Critter('*');
                    }
                }
                else if (current[r][c].getAppearance() == '*')
                {
                    if (birthdeathAreaCheck(r, c) < 2 || birthdeathAreaCheck(r, c) > 3)
                    {
                        birthDeath[r][c] = new Critter(Critter.EMPTY);
                    }
                }    
            }
        }
    }

    public void tVirusCheck(int r,int c)
    {
        if (checkIndices(r-1,c-1,tVirus) && tVirus[r-1][c-1].getAppearance() == '*')
        {
            tVirus[r-1][c-1] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r-1,c,tVirus) && tVirus[r-1][c].getAppearance() == '*')
        {
            tVirus[r-1][c] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r-1,c+1,tVirus) && tVirus[r-1][c+1].getAppearance() == '*')
        {
            tVirus[r-1][c+1] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r,c-1,tVirus) && tVirus[r][c-1].getAppearance() == '*')
        {
            tVirus[r][c-1] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r,c+1,tVirus) && tVirus[r][c+1].getAppearance() == '*')
        {
            tVirus[r][c+1] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r+1,c-1,tVirus) && tVirus[r+1][c-1].getAppearance() == '*')
        {
            tVirus[r+1][c-1] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r+1,c,tVirus) && tVirus[r+1][c].getAppearance() == '*')
        {
            tVirus[r+1][c] = new Critter(Critter.EMPTY);
        }
        if (checkIndices(r+1,c+1,tVirus) && tVirus[r+1][c+1].getAppearance() == '*')
        {
            tVirus[r+1][c+1] = new Critter(Critter.EMPTY);
        }
    }
  
    public void tVirusAction()
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLUMNS; c++)
            {
                if (tVirus[r][c].getAppearance() == 'T')
                {
                    tVirusCheck(r,c);
                    current = deepCopy(tVirus);
                    tVriusMove(r,c);
                }
            }
        }
    }
     
    public void runTurn()
    {
         
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            unitCheck(); 
            System.out.println("Press n to continue, or q to quit ");
            char quit = scanner.next().charAt(0);
            if (quit == 'q')
            {
                break;
            } 
            else
            {    
            }
            tVirus = deepCopy(birthDeath);
            tVirusAction();
            display();
            current = deepCopy(tVirus);
        }

    }

}

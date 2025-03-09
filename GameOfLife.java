import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y)
    {
        // Construct a 2d array of the given x and y size.
        board = new int[x][y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                board[i][j] = 0;
            }
        }
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) 
    {
        for (int i = 0; i < data.length; i++) 
        {
            for (int j = 0; j < data[0].length; j++) 
            {
                if ((i + x) < board.length) 
                {
                    if ((j + y) < board[0].length) 
                    {
                        board[i + x][j + y] = data[i][j];
                    }
                }
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) 
    {
        int count = 0;
        while (count < turns) 
        {
            step();
            count++;
            if (count >= turns)
            {
                break; 
            }
        }
    }

    // Step the simulation forward one turn.
    public void step()
    {
        print();
        int[][] nextBoard = new int[board.length][board[0].length];
        for (int x = 0; x < board.length; x++) 
        {
            for (int y = 0; y < board[0].length; y++) 
            {
                int neighbors = countNeighbors(x, y);
                
                if (board[x][y] == 1) 
                {
                    if (neighbors == 2) 
                    {
                        nextBoard[x][y] = 1;
                    }
                    else if (neighbors == 3)
                    {
                        nextBoard[x][y] = 1;
                    }
                    else
                    {
                        nextBoard[x][y] = 0;
                    }
                } 
                else 
                {
                    if (neighbors == 3) 
                    {
                        nextBoard[x][y] = 1;
                    }
                    else
                    {
                        nextBoard[x][y] = 0;
                    }
                }
            }
        }
        board = nextBoard;
    }

    public int countNeighbors(int x, int y) 
    {
        int count2 = 0;
        for (int i = -1; i <= 1; i++) 
        {
            for (int j = -1; j <= 1; j++) 
            {
                if (!(i == 0 && j == 0)) 
                {
                    if (get(x + i, y + j) == 1) //note Aarav  get()
                    {
                        count2 = count2 + 1;
                    }
                }
            }
        }
        return count2;
    }

    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) 
    {
        int xmax = board.length; //rows
        int ymax = board[0].length; //collumns
        int xwrap = (x + xmax) % xmax; //looped
        int ywrap = (y + ymax) % ymax; //also looped
        
        return board[xwrap][ywrap];
    }

    // Test helper to get the whole board state
    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print()
    {
        // Print the header
        System.out.print("\n ");

        for (int y = 0; y < board[0].length; y++) 
        {
            System.out.print(y % 10 + " ");
        }
        for (int x = 0; x < board.length; x++) 
        {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }

        System.out.println();
    }
}

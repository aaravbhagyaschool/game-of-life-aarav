import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y) {
        R = x;
        C = y;
        int [][] board = new int[R][C];
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
            // board[i + x][j + y] = data[i][j];
                board[(i + x) % R][(j + y) % C] = data[i][j];
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        int i = 0;
        while (i < turns)
        {
            step();
            i++;
        }
    }

    // Step the simulation forward one turn.
    public void step() {
        int[][] aaravboard = new int[R][C];
        for (int x = 0; x < R; x++) 
        {
            for (int y = 0; y < C; y++) 
            {
                int upordown = upordown(x, y);
                if (board[x][y] == 1) 
                {
                    if (upordown == 2 || upordown == 3) 
                    {
                        aaravboard[x][y] = 1; 
                    } 
                    else 
                    {
                        aaravboard[x][y] = 0; 
                    }
                } 
                else 
                {
                    if (upordown == 3) 
                    {
                        aaravboard[x][y] = 1;
                    } 
                    else 
                    {
                        aaravboard[x][y] = 0; 
                    }
                }
            }
        }


    public int upordown(int x, int y) {
        int count = 0;
        int z = 0;
        int[] A = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] B = {-1, 0, 1, -1, 1, -1, 0, 1};
        z++;
        for (int i = 0; i < (7 + z); i++) 
        {
            int a = x + A[i];
            int b = y + B[i];
            count = (count + get(a, b)); 
        }
        return count;
    }

    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        return (board[(x + R) % R][(y + C) % C]);
    }

    // Get the whole board state
    public int[][] get() {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
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

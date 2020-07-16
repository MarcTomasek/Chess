import java.lang.reflect.Array;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        System.out.println("I compile");
        Game game = new Game();
        game.printBoard();
        //ArrayList<int[]> list = game.generateMoves(new int[]{7,0});
    }
}

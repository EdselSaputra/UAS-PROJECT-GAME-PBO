
package netcode.main;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        gameLogic game = new gameLogic(userId);
        game.Startgame();
 }
}
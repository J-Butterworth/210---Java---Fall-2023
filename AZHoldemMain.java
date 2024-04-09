package view_controller; 
import model.Game;


/**
 * This plays a console game of Arizona Hold-Em, 
 * a reduced version of Texas Hold-Em
 * 
 * @author Jason Butterworth.
 */
public class AZHoldemMain {

  public static void main(String[] args) {
	  Game myGame = new Game();
	  myGame.start();
  }
}
import cs3500.reversi.model.StandardBoard;
import cs3500.reversi.view.TextView;

public class Reversi {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    StandardBoard standardBoard = new StandardBoard();
    StringBuffer sb = new StringBuffer();
    TextView view = new TextView(sb);
    view.renderBoard(standardBoard);
  }
}
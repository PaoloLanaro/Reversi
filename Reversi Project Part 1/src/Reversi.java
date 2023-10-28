import org.w3c.dom.Text;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.StandardBoard;
import cs3500.reversi.view.TextView;

public class Reversi {
  public static void main(String[] args) {
//    System.out.println("Hello world!");
//    StandardBoard standardBoard = new StandardBoard(5);
//    StringBuffer sb = new StringBuffer();
//    TextView view = new TextView(sb);
//    view.renderBoard(standardBoard);

    System.out.println("hello world!");

    StandardBoard sb = new StandardBoard(6);
    for (int i = 0; i < 11; i++) {
      System.out.println(sb.getBoardState().get(i).toString());
    }

    StringBuffer sb1 = new StringBuffer();
    TextView view = new TextView(sb1, sb);
    System.out.println(view.toString());

  }
}
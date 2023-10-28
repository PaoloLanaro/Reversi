import cs3500.reversi.model.StandardBoard;
import cs3500.reversi.view.TextView;

public class Reversi {
  public static void main(String[] args) {

    StandardBoard sb = new StandardBoard(6);
    for (int i = 0; i < 11; i++) {
      System.out.println(sb.getBoard().get(i).toString());
    }

    StringBuffer sb1 = new StringBuffer();
    TextView view = new TextView(sb1, sb);
    System.out.println(view);

  }
}
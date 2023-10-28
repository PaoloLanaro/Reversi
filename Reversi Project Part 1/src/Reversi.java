import java.util.List;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.PlayerColor;
import cs3500.reversi.model.StandardBoard;
import cs3500.reversi.players.HumanPlayer;
import cs3500.reversi.view.TextView;

public class Reversi {
  public static void main(String[] args) {

    StandardBoard sb = new StandardBoard(3);

    StringBuffer sb1 = new StringBuffer();
    TextView view = new TextView(sb1, sb);
    System.out.println(view);

    Player humanPlayer = new HumanPlayer(PlayerColor.BLACK);

    sb.makeMove(humanPlayer, 1, 1);

    System.out.println(view);

  }
}
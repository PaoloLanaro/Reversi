import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.view.IView;
import cs3500.reversi.view.ReversiGraphicsView;

public final class Reversi {
  public static void main(String[] args) {
    BasicReversi model = new BasicReversi(4);
    IView view = new ReversiGraphicsView(model);
    view.setVisible(true);
  }
}



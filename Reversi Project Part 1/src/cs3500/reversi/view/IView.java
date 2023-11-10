package cs3500.reversi.view;

import java.awt.event.ActionListener;

public interface IView {

  void setListener(ActionListener actionListener);

  void setVisible(boolean visible);

  void refresh();

}

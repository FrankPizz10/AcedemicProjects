package cs3500.animator.view;
import cs3500.animator.hw5.*;
public class TextualView implements IView {
  IModel model;

  public TextualView(IModel model) {
    this.model = model;
  }

  public TextualView() {
    this(null);
  }

  @Override
  public void addModel(IModel model) {
    this.model = model;
  }

  @Override
  public String textView() {
    return model.textOutput();
  }

  @Override
  public void visualView() {

  }

  @Override
  public String svgView() {
    return null;
  }
}

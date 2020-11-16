package cs3500.animator.view;

import cs3500.animator.hw5.IModel;

public class SVGView implements IView {
  IModel model;

  public SVGView(IModel model) {
    this.model = model;
  }

  public SVGView() {
    this(null);
  }

  @Override
  public void addModel(IModel model) {
    this.model = model;
  }

  @Override
  public String textView() {
    return null;
  }

  @Override
  public void visualView() {

  }

  @Override
  public String svgView() {
    return model.svgOutput();
  }
}

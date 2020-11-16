package cs3500.animator.view;

import cs3500.animator.hw5.IModel;

public interface IView {
  public void addModel(IModel model);
  public String textView();

  public void visualView();

  public String svgView();
}

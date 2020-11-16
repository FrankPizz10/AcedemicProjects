package cs3500.animator.view;

import cs3500.animator.hw5.IModel;

public class Commands {
  private IView view;
  private IModel model;
  private String inputFile;
  private String outputFile;
  private int tickSpeed;
  private String viewType;

  public Commands(IModel model) {
    this.model = model;
  }

  public Commands() {
    this.model = null;
  }

  public void getArgs(String[] cmd) {
    for (int i = 0; i < cmd.length; i++) {
      switch (cmd[i]) {
        case "-in":
          inputFile = cmd[i + 1];
          break;
        case "-out":
          outputFile = cmd[i + 1];
          break;
        case "-speed":
          try {
            tickSpeed = Integer.parseInt(cmd[i + 1]);
            if (tickSpeed <= 0) {
              throw new IllegalArgumentException("Invalid tick speed");
            }
          } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid tick speed!");
          }
          break;
        case "-view":
          this.view = new ViewBuilder().buildView(cmd[i + 1]);
      }
    }
  }

  public void setModel(IModel model) {
    if (model == null) throw new IllegalArgumentException("Invalid model");
    model.setInputFile(this.inputFile);
    model.setOutputFile(this.outputFile);
    model.setTickSpeed(this.tickSpeed);
    this.view.addModel(model);
  }

  public IView getView() {
    return this.view;
  }

  public String getInput() {
    return this.inputFile;
  }

  public String getOutput() {
    return this.outputFile;
  }

  public int getTickSpeed() {
    return this.tickSpeed;
  }

  public String getViewType() {
    return this.viewType;
  }

  public final class ViewBuilder {

    IView buildView(String cmd) {
      switch (cmd) {
        case "text":
          viewType = "text";
          return new TextualView();
        case "svg":
          viewType = "svg";
          return new SVGView();
        case "visual":
          viewType = "visual";
          return new VisualView();
        default:
          throw new IllegalArgumentException("Invalid view!");
      }
    }
  }
}

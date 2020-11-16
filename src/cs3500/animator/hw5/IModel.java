package cs3500.animator.hw5;

import java.util.ArrayList;

public interface IModel {
  /**
   * Adds a shape to the model.
   * @param shape shape to be added to the model
   * @throws IllegalArgumentException if shape with the same name is added twice
   */
  void addShape(IShape shape);

  /**
   * Removes a shape from the model.
   * @param name the name of the shape to be removed.
   */
  void removeShape(char name);

  /**
   * Gets all the shapes added to the model.
   * @return a copy of the shapes field from the model.
   */
  ArrayList<IShape> getShapes();

  /**
   * Animates a shape by changing its position, size, or color during a specified interval of time.
   * @param shape shape to be animated
   * @param t1 starting time
   * @param t2 ending time {must be less than t1}
   * @param x2 x position of right corner
   * @param y2 y position of right corner
   * @param w2 width of animated shape
   * @param h2 height of animated shape
   * @param r2 red {0 - 255}
   * @param g2 green {0 - 255}
   * @param b2 blue {0 - 255}
   * @throws IllegalArgumentException if r2, g2, or b2 are out of range, if w2 or h2 are less than
   *        zero, or if shape does not exist.
   */
  void animate(IShape shape, int t1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
               int b2);

  ArrayList<IAnimation> getCurrentAnimation(IShape shape, int time);

  ArrayList<IShape> getCurrentShapes(int time);
  /**
   * Return the text output of the shape at start and end time of each animation.
   * @return the string output of all the animations that took place
   */
  String textOutput();

  String svgOutput();

  void setInputFile(String inputFile);

  void setOutputFile(String outputFile);

  void setTickSpeed(int tickSpeed);

  int getTickSpeed();

  String getInputFile();

  String getOutputFile();
}

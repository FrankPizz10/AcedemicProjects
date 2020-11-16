package cs3500.animator.hw5;

import java.awt.*;

public interface IShape {

  public void draw(Graphics g1, int r, int g, int b, int x, int y, int w, int h);
  /**
   * Returns x position of shape.
   * @return x position
   */
  int getX();

  /**
   * Returns y position of shape.
   * @return y position
   */
  int getY();

  /**
   * Returns r value of shape.
   * @return r value
   */
  int getR();

  /**
   * Returns g value of shape.
   * @return g value
   */
  int getG();

  /**
   * Returns b value of shape.
   * @return b value
   */
  int getB();

  /**
   * Returns width of shape.
   * @return width
   */
  int getW();

  /**
   * Returns height of shape.
   * @return height
   */
  int getH();

  /**
   * Returns current time.
   * @return time
   */
  int getTime();

  /**
   * Returns name of shape.
   * @return name
   */
  String getName();

  /**
   * Sets the x and y position of the shape.
   * @param x new x position
   * @param y new y position
   */
  void setXY(int x, int y);

  /**
   * Sets the color of the shape.
   * @param r new red value
   * @param g new green value
   * @param b new blue value
   * @throws IllegalArgumentException if r, g, or b are less than 0 or more than 255
   */
  void setRGB(int r, int g, int b);

  /**
   * Sets the width and height of the shape.
   * @param w new width
   * @param h new height
   * @throws IllegalArgumentException if w or h are less than 0
   */
  void setWH(int w, int h);

  /**
   * Sets the time of the shape.
   * @param t new time in ticks
   * @throws IllegalArgumentException if t is less than this.time
   */
  void setTime(int t);

  String getType();

  String getSVG();
}

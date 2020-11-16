package cs3500.animator.hw5;

import java.awt.*;
import java.util.Objects;

/**
 * Rectangle shape with size, starting position, and color.
 */
public final class Rectangle extends AShape implements IShape {

  /**
   * Constructs a rectangle that contains a name, position, size, and color.
   * @param name the name of the shape
   * @param x x position of shape
   * @param y y position of shape
   * @param w width of shape
   * @param h height of shape
   * @param r red value 0 - 255
   * @param g green value 0 - 255
   * @param b blue value 0 - 255
   * @throws IllegalArgumentException if size is not positive or color is out of range
   */
  public Rectangle(String name, int x, int y, int w, int h, int r, int g, int b) {
    super(name, x, y, r, g, b, w, h);
  }

  @Override
  public void draw(Graphics g1, int r, int g, int b, int x, int y, int w, int h) {
    g1.setColor(new Color(r, g, b));
    g1.fillRect(x, y, w, h);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (! (o instanceof Rectangle)) {
      return false;
    }

    Rectangle that = (Rectangle)o;

    return this.name == that.name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String getType() {
    return "rect";
  }

}

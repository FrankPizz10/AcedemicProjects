package cs3500.animator.hw5;

import java.util.HashMap;
import java.util.Map;

public abstract class AShape implements IShape {
  protected final String name;
  protected int x; // Corner is at x
  protected int y; // Corner is at y
  protected int r; // Red 0 - 255
  protected int g; // Green 0 - 255
  protected int b; // Blue 0 - 255
  protected int w; // Width
  protected int h; // Height
  protected int time; // last updated time
  protected Map<String, Integer> startingValues;

  /**
   * Constructs a shape that contains a name, position, size, and color. Some notable invariants
   * include: -1 < r < 266, -1 < g < 266, -1 < b < 266, w > -1, h > -1, name remains the same
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
  AShape(String name, int x, int y, int r, int g, int b, int w, int h) {
    if (Utilities.inValidWH(w, h)) {
      throw new IllegalArgumentException("Invalid size!");
    }
    if (Utilities.inValidRGB(r, g, b)) {
      throw new IllegalArgumentException("Invalid color!");
    }
    startingValues = new HashMap<>();
    this.name = name;
    this.time = 0;
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
    this.w = w;
    this.h = h;
    this.startingValues.put("x", x);
    this.startingValues.put("y", y);
    this.startingValues.put("w", w);
    this.startingValues.put("h", h);
    this.startingValues.put("r", r);
    this.startingValues.put("g", g);
    this.startingValues.put("b", b);
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public int getW() {
    return this.w;
  }

  @Override
  public int getH() {
    return this.h;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void setRGB(int r, int g, int b) {
    if (Utilities.inValidRGB(r, g, b)) {
      throw new IllegalArgumentException("Invalid Color!");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public void setWH(int w, int h) {
    if (Utilities.inValidWH(w, h)) {
      throw new IllegalArgumentException("Invalid Size!");
    }
    this.w = w;
    this.h = h;
  }

  @Override
  public void setTime(int t) {
    if (t < this.time) {
      throw new IllegalArgumentException("Cannot go back in time!");
    }
    this.time = t;
  }

  public String getSVG() {
    String svg = "";
    svg = "<" + this.getType() + " id=\"" + this.name + "\" x=\"" + this.startingValues.get("x")
            + "\" y=\"" + this.startingValues.get("y") + "\" width=\"" + this.startingValues.get("w")
            + "\" height=\"" + this.startingValues.get("h") + "\" fill=\"rgb(" + this.startingValues.get("r")
            + "," + this.startingValues.get("g") + "," + this.startingValues.get("b") + ")\"";
    return svg;
  }

  public static final class ShapeBuilder {
    private IShape shape;

    public IShape build() {
      return this.shape;
    }

    public void createShape(String name, String type) {
      switch (type) {
        case "rectangle":
          this.shape = new Rectangle(name, 0, 0, 0, 0, 0, 0, 0);
          break;
        case "ellipse":
          this.shape = new Ellipse(name, 0, 0, 0, 0, 0, 0, 0);
          break;
        default:
          throw new IllegalArgumentException("Invalid type!");
      }
    }
  }


}

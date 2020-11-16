package cs3500.animator.hw5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.hw5.AShape.ShapeBuilder;


/**
 * Model that represents shapes and their animations.
 */
public class Model implements IModel {
  private final Map<IShape, ArrayList<IAnimation>> animations;
  private String inputFile;
  private String outputFile;
  private int tickSpeed;

  /**
   * Constructs a new Model and creates an empty HasMap for each shape's animations.
   */
  public Model() {
    this.animations = new HashMap<IShape, ArrayList<IAnimation>>();
    this.tickSpeed = 2;
  }

  public Model(Map<IShape, ArrayList<IAnimation>> animations) {
    this.animations = animations;
    this.tickSpeed = 2;
  }

  @Override
  public void addShape(IShape shape) {
    if (this.animations.containsKey(shape)) {
      throw new IllegalArgumentException("Shape with this name is already used!");
    }
    this.animations.put(shape, new ArrayList<IAnimation>());
  }

  @Override
  public void removeShape(char name) {
    if (this.animations.containsKey(name)) {
      this.animations.remove(name);
    }
    else {
      throw new IllegalArgumentException("Shape does not exist!");
    }
  }

  @Override
  public ArrayList<IShape> getShapes() {
    return new ArrayList<IShape>(this.animations.keySet());
  }

  @Override
  public ArrayList<IAnimation> getCurrentAnimation(IShape shape, int time) {
    ArrayList<IAnimation> animations = new ArrayList<>();
    for (IAnimation an : this.animations.get(shape)) {
      if (an.getProperties().get("t1") <= time && an.getProperties().get("t2") >= time) {
        animations.add(an);
      }
    }
    return animations;
  }

  @Override
  public void animate(IShape shape, int t1, int t2, int x2, int y2, int w2, int h2, int r2,
                      int g2, int b2) {
    if (t2 < shape.getTime() || t1 > t2 || t1 < shape.getTime()) {
      throw new IllegalArgumentException("Can't go back in time!");
    }
    if (!this.animations.containsKey(shape)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }
    if (Utilities.inValidRGB(r2, g2, b2)) {
      throw new IllegalArgumentException("Invalid color!");
    }
    if (Utilities.inValidWH(w2, h2)) {
      throw new IllegalArgumentException("Invalid size!");
    }
    IAnimation a1 = new Animation(shape, t1, t2, x2, y2, w2, h2, r2, g2, b2);
    shape.setXY(x2, y2);
    shape.setRGB(r2, g2, b2);
    shape.setWH(w2, h2);
    this.animations.get(shape).add(a1);
  }

  @Override
  public ArrayList<IShape> getCurrentShapes(int time) {
    ArrayList<IShape> currentShapes = new ArrayList<>();
    for (IShape shape : this.animations.keySet()) {
      for (IAnimation animation : this.animations.get(shape)) {
        if (animation.getProperties().get("t1") <= time && animation.getProperties().get("t2") >= time) {
          currentShapes.add(shape);
        }
      }
    }
    return currentShapes;
  }


  @Override
  public String textOutput() {
    StringBuilder textOutput = new StringBuilder();
    for (IShape shape : this.animations.keySet()) {
      ArrayList<IAnimation> a2 = new ArrayList<IAnimation>();
      a2 = this.animations.get(shape);
      for (IAnimation a : a2) {
        textOutput.append(a.getText());
      }
    }
    return textOutput.toString().trim();
  }

  @Override
  public String svgOutput() {
    StringBuilder svgOutput = new StringBuilder();
    for (IShape shape : this.animations.keySet()) {
      svgOutput.append(shape.getSVG());
      ArrayList<IAnimation> a2 = new ArrayList<IAnimation>();
      a2 = this.animations.get(shape);
      for (IAnimation a : a2) {
        svgOutput.append(a.getSVGText());
      }
      svgOutput.append("\n</" + shape.getType() + ">\n");
    }
    return svgOutput.toString().trim();
  }

  @Override
  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }

  @Override
  public void setOutputFile(String outputFile) {
    this.outputFile = outputFile;
  }

  @Override
  public void setTickSpeed(int tickSpeed) {
    this.tickSpeed = tickSpeed;
  }

  @Override
  public int getTickSpeed() {
    return this.tickSpeed;
  }

  @Override
  public String getInputFile() {
    return this.inputFile;
  }

  @Override
  public String getOutputFile() {
    return this.outputFile;
  }

  public static final class Builder implements AnimationBuilder<IModel> {
    private final Map<IShape, ArrayList<IAnimation>> animations;
    private final List<Integer> bounds;

    public Builder() {
      this.animations = new HashMap<>();
      this.bounds = new ArrayList<>();
    }

    @Override
    public IModel build() {
      return new Model(this.animations);
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      this.bounds.add(x);
      this.bounds.add(y);
      this.bounds.add(width);
      this.bounds.add(height);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      if (name == null || type == null) throw new IllegalArgumentException("Invalid!");
      if (this.animations.containsKey(name)) {
        throw new IllegalArgumentException("Shape with this name is already used!");
      }
      ShapeBuilder shapeBuilder = new ShapeBuilder();
      shapeBuilder.createShape(name, type);
      this.animations.put(shapeBuilder.build(), new ArrayList<IAnimation>());
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2) {
      ArrayList<IShape> shapes = new ArrayList<>(this.animations.keySet());
      ArrayList<String> names = new ArrayList<>();
      IShape indexShape;
      for (IShape shape : shapes) {
        names.add(shape.getName());
      }
      if (names.contains(name)) {
        indexShape = shapes.get(names.indexOf(name));
      }
      else {
        throw new IllegalArgumentException("Shape does not exist!");
      }
      if (t2 < indexShape.getTime() || t1 > t2 || t1 < indexShape.getTime()) {
        throw new IllegalArgumentException("Can't go back in time!");
      }
      if (!this.animations.containsKey(indexShape)) {
        throw new IllegalArgumentException("Shape does not exist.");
      }
      if (Utilities.inValidRGB(r2, g2, b2)) {
        throw new IllegalArgumentException("Invalid color!");
      }
      if (Utilities.inValidWH(w2, h2)) {
        throw new IllegalArgumentException("Invalid size!");
      }

      IAnimation a1 = new Animation(indexShape, t1, t2, x2, y2, w2, h2, r2, g2, b2);
      indexShape.setXY(x2, y2);
      indexShape.setRGB(r2, g2, b2);
      indexShape.setWH(w2, h2);

      this.animations.get(indexShape).add(a1);

      return this;
    }

    @Override
    public AnimationBuilder<IModel> addKeyframe(String name, int t, int x, int y, int w, int h,
                                                int r, int g, int b) {
      return null;
    }
  }
}

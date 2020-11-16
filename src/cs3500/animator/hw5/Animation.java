package cs3500.animator.hw5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Animation implements IAnimation {
  private final IShape shape;
  private final Map<String, Integer> properties;


  Animation(IShape shape, int t1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.shape = shape;
    this.properties = new HashMap<String, Integer>();
    this.properties.put("t1", t1);
    this.properties.put("t2", t2);
    this.properties.put("x1", this.shape.getX());
    this.properties.put("y1", this.shape.getY());
    this.properties.put("w1", this.shape.getW());
    this.properties.put("h1", this.shape.getH());
    this.properties.put("r1", this.shape.getR());
    this.properties.put("g1", this.shape.getG());
    this.properties.put("b1", this.shape.getB());
    this.properties.put("x2", x2);
    this.properties.put("y2", y2);
    this.properties.put("w2", w2);
    this.properties.put("h2", h2);
    this.properties.put("r2", r2);
    this.properties.put("g2", g2);
    this.properties.put("b2", b2);

  }

  @Override
  public Map<String, Integer> getProperties() {
    return new HashMap<>(this.properties);
  }

  @Override
  public String getText() {
    String text = "";
    text = "motion " + this.shape.getName() + " " + this.properties.get("t1") + " "
            + this.properties.get("x1") + " " + this.properties.get("y1") + " "
            + this.properties.get("w1") + " " + this.properties.get("h1") + " "
            + this.properties.get("r1") + " " + this.properties.get("g1") + " "
            + this.properties.get("b1") + " " + " " + " " + this.properties.get("t2") + " "
            + this.properties.get("x2") + " " + this.properties.get("y2") + " "
            + this.properties.get("w2") + " " + this.properties.get("h2")
            + " " + this.properties.get("r2") + " " + this.properties.get("g2") + " "
            + this.properties.get("b2") + "\n";

    return text;
  }

//  @Override
//  public String getSVGText() {
//    String svg = "";
//    svg = "<" + this.shape.getType() + "id=\"" + this.shape.getName() + "\" x=\"" + this.properties.get("x1")
//            + "\" y=\"" + this.properties.get("y1") + "\" width=\"" + this.properties.get("w1")
//            + "\" height=\"" + this.properties.get("h1") + "\" fill=\"rgb(" + this.properties.get("r1")
//            + "," + this.properties.get("g1") + "," + this.properties.get("b1") + ")\""
//            + "<animate attributeType=\"xml\" begin=\"" + Integer.toString(this.properties.get("t1") * 1000)
//            + "\" dur=\"" + Integer.toString(this.properties.get("t2") * 1000) + "\""
//    return svg;
//  }

  @Override
  public String getSVGText() {
    String svg = "";
    svg = "\n\t<animate attributeType=\"xml\" begin=\"" + this.properties.get("t1") * 1000
            + "\" dur=\"" + (this.properties.get("t2") - this.properties.get("t1")) * 1000 + "\""
            + attributes() + "/<animate>";
    return svg;
  }

  private ArrayList<String> isChanging() {
    ArrayList<String> changing = new ArrayList<>();
    for (String property : this.properties.keySet()) {
      String secondInstance = property;
      if (Character.getNumericValue(property.charAt(1)) == 1 && Character.compare(property.charAt(0), 't') != 0) {
        secondInstance = property.charAt(0) + Integer.toString(Character.getNumericValue(property.charAt(1)) + 1);
        if (properties.get(property) - properties.get(secondInstance) != 0) {
          changing.add(secondInstance);
        }
      }
    }
    return changing;
  }

  private String attributes() {
    String attributes = "";

    for (String changed : isChanging()) {
      String firstInstance = changed.charAt(0) + Integer.toString(Character.getNumericValue(changed.charAt(1)) - 1);
      attributes = " attributeName=\"" + changed.charAt(0) + "\"" + " from=\"" + this.properties.get(firstInstance)
              + "\" to=\"" + this.properties.get(changed) + "\" fill=\"freeze\">";
    }
    return attributes;
  }
}

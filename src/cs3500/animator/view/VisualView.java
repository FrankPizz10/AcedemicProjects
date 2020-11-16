package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import cs3500.animator.hw5.IAnimation;
import cs3500.animator.hw5.IModel;
import cs3500.animator.hw5.IShape;
import cs3500.animator.hw5.Rectangle;

public class VisualView extends JFrame implements IView {
  IModel model;
  JScrollPane scrollPane;


  public VisualView(IModel model) {
    this.model = model;
  }

  public VisualView() {
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
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Canvas canvas = new Drawing();
    canvas.setPreferredSize(new Dimension(800, 800));

    scrollPane = new JScrollPane(canvas);
    add(scrollPane);



    setVisible(true);
  }

  public static final class Tweener {

    public static int linearInterpolate(int value1, int value2, int timeStart, int timeEnd, int currentTime) {
      int result = 0;
      result = (int) (((double) value1 * ((double) (timeEnd - currentTime) / (double) (timeEnd - timeStart))) +
              ((double) value2 * ((double) (currentTime - timeStart) / (double) (timeEnd - timeStart))));
      return result;
    }
  }

  public final class Drawing extends Canvas {
    int[] time = {0};
    public Drawing() {
      Timer timer = new Timer();

      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          repaint();
          time[0]++;
        }
      }, 3000, model.getTickSpeed());
    }


    public void paint(Graphics g1) {
      Graphics2D g2d = (Graphics2D) g1;
      for (IShape shape : model.getCurrentShapes(time[0])) {
        for (IAnimation an : model.getCurrentAnimation(shape, time[0])) {
          Map<String, Integer> properties = an.getProperties();
          shape.draw(g2d, Tweener.linearInterpolate(properties.get("r1"), properties.get("r2"),
                  properties.get("t1"), properties.get("t2"), time[0]), Tweener.linearInterpolate(properties.get("g1"), properties.get("g2"),
                  properties.get("t1"), properties.get("t2"), time[0]), Tweener.linearInterpolate(properties.get("b1"), properties.get("b2"),
                  properties.get("t1"), properties.get("t2"), time[0]), Tweener.linearInterpolate(properties.get("x1"), properties.get("x2"),
                  properties.get("t1"), properties.get("t2"), time[0]), Tweener.linearInterpolate(properties.get("y1"), properties.get("y2"),
                  properties.get("t1"), properties.get("t2"), time[0]), Tweener.linearInterpolate(properties.get("w1"), properties.get("w2"),
                  properties.get("t1"), properties.get("t2"), time[0]), Tweener.linearInterpolate(properties.get("h1"), properties.get("h2"),
                  properties.get("t1"), properties.get("t2"), time[0]));
        }
      }
    }
  }



  @Override
  public String svgView() {
    return null;
  }
}

package cs3500.animator.hw5;

import org.junit.Test;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.VisualView.Tweener;

import static org.junit.Assert.*;

public class ModelTest {
  @Test
  public void testTweener() {
    assertEquals(250, Tweener.linearInterpolate(250,250,0,10,1));
    assertEquals(29, Tweener.linearInterpolate(10, 200, 0, 10, 1));
  }

  @Test
  public void testSVGText() {
    IModel model = new Model();
    IShape rect = new Rectangle("F", 10, 10, 10, 10, 10, 10, 10);
    IShape ellipse = new Ellipse("E", 10, 10, 10, 10, 10, 10, 10);
    model.addShape(rect);
    model.addShape(ellipse);
    model.animate(rect, 0, 10, 20, 10, 10, 10, 10, 10, 10);
    model.animate(rect, 0, 10, 20, 20, 10, 10, 10, 10, 10);
    IView view = new SVGView(model);
    assertEquals("", view.svgView());
  }
}
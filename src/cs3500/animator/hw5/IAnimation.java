package cs3500.animator.hw5;

import java.util.Map;

public interface IAnimation {
  String getText();

  String getSVGText();

  Map<String, Integer> getProperties();
}

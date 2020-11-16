package cs3500.animator.hw5;

/**
 * This class contains public static methods to enforce certain invariants such as
 * a positive size for a shape and rgb color values between 0 - 255.
 */
public class Utilities {
  /**
   * Determines if a given width and height are positive.
   * @param w width of shape
   * @param h height of shape
   * @return true if width and height are positive
   */
  static public boolean inValidWH(int w, int h) {
    return w < 0 || h < 0;
  }

  /**
   * Determines if rgb colors are between 0 - 255 inclusive.
   * @param r red
   * @param g green
   * @param b blue
   * @return true if r, g, and b are between 0 - 255 inclusive.
   */
  static boolean inValidRGB(int r, int g, int b) {
    return r < 0 || r > 255
            || g < 0 || g > 255
            || b < 0 || b > 255;
  }
}

import org.junit.Test;

import java.util.Random;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * A Test class for Pixel.
 */
public class PixelTest {

  /**
   * Clamp works correctly when red, green, blue < 0.
   */
  @Test
  public void testClampPixelNegative() {
    Pixel pixel = new Pixel(-20, -15, -12);
    assertEquals(0, pixel.getRed());
    assertEquals(0, pixel.getGreen());
    assertEquals(0, pixel.getBlue());
  }

  /**
   * Clamp works correctly when red, green, blue > 255.
   */
  @Test
  public void testClampPixelGreaterThan255() {
    Pixel pixel = new Pixel(1000, 535, 256);
    assertEquals(255, pixel.getRed());
    assertEquals(255, pixel.getGreen());
    assertEquals(255, pixel.getBlue());
  }

  private int clamp(int value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }

  /**
   * Fuzzy Pixel test.
   */
  @Test
  public void testFuzzyTest() {
    Random r = new Random();

    for (int i = 0; i < 10000; i++) {
      int red = r.nextInt();
      int green = r.nextInt();
      int blue = r.nextInt();

      Pixel pixel = new Pixel(red, green, blue);

      red = clamp(red);
      green = clamp(green);
      blue = clamp(blue);

      assertEquals(red, pixel.getRed());
      assertEquals(green, pixel.getGreen());
      assertEquals(blue, pixel.getBlue());
    }
  }
}
package model;

/**
 * The `Pixel` class represents a color pixel with red, green, and blue components.
 * Each component is an integer value ranging from 0 to 255.
 */
public class Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructs a Pixel object with specified red, green, and blue components.
   * The components are clamped to the valid range [0, 255].
   *
   * @param red   The red component of the pixel.
   * @param green The green component of the pixel.
   * @param blue  The blue component of the pixel.
   */
  public Pixel(int red, int green, int blue) {
    this.red = clamp(red);
    this.green = clamp(green);
    this.blue = clamp(blue);
  }

  /**
   * Clamps the given value to the valid range [0, 255].
   *
   * @param value The value to be clamped.
   * @return The clamped value within the range [0, 255].
   */
  private int clamp(int value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }

  /**
   * Get the red component of the pixel.
   *
   * @return The red component value.
   */
  public int getRed() {
    return red;
  }


  /**
   * Get the green component of the pixel.
   *
   * @return The green component value.
   */
  public int getGreen() {
    return green;
  }


  /**
   * Get the blue component of the pixel.
   *
   * @return The blue component value.
   */
  public int getBlue() {
    return blue;
  }

}

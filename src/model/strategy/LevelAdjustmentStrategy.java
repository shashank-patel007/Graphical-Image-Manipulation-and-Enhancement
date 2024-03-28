package model.strategy;

import model.Image;

/**
 * The LevelAdjustmentStrategy class implements the SplitStrategy interface to provide a strategy
 * for adjusting the levels of an image.
 */
public class LevelAdjustmentStrategy implements SplitStrategy {
  private final int b;
  private final int m;
  private final int w;

  /**
   * Constructs a LevelAdjustmentStrategy with specified parameters for
   * black point, mid-tones, and white point.
   *
   * @param b The parameter for adjusting the black point.
   * @param m The parameter for adjusting the mid-tones.
   * @param w The parameter for adjusting the white point.
   */
  public LevelAdjustmentStrategy(int b, int m, int w) {
    this.b = b;
    this.m = m;
    this.w = w;
  }

  /**
   * Applies the levels adjustment strategy to the given Image using the specified parameters.
   *
   * @param image The input Image to which the levels adjustment strategy is applied.
   * @return A new Image with adjusted levels based on the specified parameters.
   */
  @Override
  public Image apply(Image image) {
    return image.levelsAdjust(b, m, w);
  }
}

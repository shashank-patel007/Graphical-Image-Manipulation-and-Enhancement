package model.strategy;

import model.Image;

/**
 * The ColorCorrectionStrategy class implements the SplitStrategy interface to provide a strategy
 * for applying color correction to an image.
 */
public class ColorCorrectionStrategy implements SplitStrategy {

  /**
   * Applies the color correction strategy to the given Image.
   *
   * @param image The input Image to which the color correction strategy is applied.
   * @return A new Image with color correction applied.
   */
  @Override
  public Image apply(Image image) {
    return image.correctImage();
  }
}

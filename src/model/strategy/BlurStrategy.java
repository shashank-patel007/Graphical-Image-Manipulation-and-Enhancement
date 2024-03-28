package model.strategy;

import model.Image;

/**
 * The BlurStrategy class implements the SplitStrategy interface to provide a strategy for
 * applying a blurring effect to an image.
 */
public class BlurStrategy implements SplitStrategy {

  /**
   * Applies the blurring strategy to the given Image.
   *
   * @param image The input Image to which the blurring strategy is applied.
   * @return A new Image with a blurring effect applied.
   */
  @Override
  public Image apply(Image image) {
    return image.blur();
  }
}

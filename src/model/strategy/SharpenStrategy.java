package model.strategy;

import model.Image;

/**
 * The SharpenStrategy class implements the SplitStrategy interface to provide a strategy
 * for applying a sharpening effect to an image.
 */
public class SharpenStrategy implements SplitStrategy {

  /**
   * Applies the sharpening effect strategy to the given Image.
   *
   * @param image The input Image to which the sharpening effect strategy is applied.
   * @return A new Image with a sharpening effect applied.
   */
  @Override
  public Image apply(Image image) {
    return image.sharpen();
  }
}

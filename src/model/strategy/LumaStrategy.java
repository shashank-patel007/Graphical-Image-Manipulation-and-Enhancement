package model.strategy;

import model.Image;

/**
 * The LumaStrategy class implements the SplitStrategy interface to provide a strategy
 * for isolating the luma (luminance) component of an image.
 */
public class LumaStrategy implements SplitStrategy {

  /**
   * Applies the luma component isolation strategy to the given Image.
   *
   * @param image The input Image to which the luma component strategy is applied.
   * @return A new Image with only the luma component retained.
   */
  @Override
  public Image apply(Image image) {
    return image.lumaComponent();
  }
}

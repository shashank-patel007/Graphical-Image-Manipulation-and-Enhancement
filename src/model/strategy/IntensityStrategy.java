package model.strategy;

import model.Image;

/**
 * The IntensityStrategy class implements the SplitStrategy interface to provide a strategy
 * for isolating the intensity component of an image.
 */
public class IntensityStrategy implements SplitStrategy {

  /**
   * Applies the intensity component isolation strategy to the given Image.
   *
   * @param image The input Image to which the intensity component strategy is applied.
   * @return A new Image with only the intensity component retained.
   */
  @Override
  public Image apply(Image image) {
    return image.intensityComponent();
  }
}

package model.strategy;

import model.Image;

/**
 * The ValueStrategy class implements the SplitStrategy interface to provide a strategy
 * for isolating the value (brightness) component of an image.
 */
public class ValueStrategy implements SplitStrategy {

  /**
   * Applies the value component isolation strategy to the given Image.
   *
   * @param image The input Image to which the value component strategy is applied.
   * @return A new Image with only the value component retained.
   */
  @Override
  public Image apply(Image image) {
    return image.valueComponent();
  }
}

package model.strategy;

import model.Image;

/**
 * The SepiaStrategy class implements the SplitStrategy interface to provide a strategy
 * for applying a sepia tone effect to an image.
 */
public class SepiaStrategy implements SplitStrategy {

  /**
   * Applies the sepia tone effect strategy to the given Image.
   *
   * @param image The input Image to which the sepia tone effect strategy is applied.
   * @return A new Image with a sepia tone effect applied.
   * @see Image#sepia()
   */
  @Override
  public Image apply(Image image) {
    return image.sepia();
  }
}

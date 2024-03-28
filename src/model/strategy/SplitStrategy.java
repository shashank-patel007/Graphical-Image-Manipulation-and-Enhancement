package model.strategy;

import model.Image;

/**
 * The SplitStrategy interface defines a contract for classes
 * that implement image splitting strategies. Image splitting
 * is the process of dividing an image into multiple parts
 * based on a specific strategy. Implementing classes should
 * provide a concrete implementation of the 'apply' method to perform the
 * image splitting according to the defined strategy.
 */
public interface SplitStrategy {

  /**
   * Applies the defined splitting strategy to the given Image.
   *
   * @param image The input Image on which the split strategy is applied.
   * @return A modified Image after applying the split strategy.
   */
  Image apply(Image image);
}

package model.strategy;

import model.Image;
import model.Pixel;

/**
 * The SplitDecorator class implements the SplitStrategy interface and serves as a decorator
 * for applying an additional splitting functionality to the result of an underlying SplitStrategy.
 * The splitting is performed based on a specified percentage of the image width.
 */
public class SplitDecorator implements SplitStrategy {
  private final SplitStrategy strategy;
  private final double splitPercentage;

  /**
   * Constructs a SplitDecorator with a specified base strategy and split percentage.
   *
   * @param strategy        The base SplitStrategy to be decorated.
   * @param splitPercentage The percentage of the image width for horizontal splitting.
   */
  public SplitDecorator(SplitStrategy strategy, double splitPercentage)
          throws IllegalArgumentException {
    if (splitPercentage < 0 || splitPercentage > 100) {
      throw new IllegalArgumentException("Invalid percentage entered!");
    }
    this.strategy = strategy;
    this.splitPercentage = splitPercentage;
  }

  /**
   * Applies the decorated splitting strategy to the given Image.
   *
   * @param image The input Image to which the decorated splitting strategy is applied.
   * @return A new Image resulting from the combination of
   *         the base strategy and horizontal splitting.
   */
  @Override
  public Image apply(Image image) {
    Image filteredImage = this.strategy.apply(image);
    int split = (int) (image.getPixels().length * (this.splitPercentage / 100.0));

    int width = image.getPixels().length;
    int height = image.getPixels()[0].length;

    Pixel[][] splitPixels = new Pixel[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (i < split) {
          splitPixels[i][j] = filteredImage.getPixels()[i][j];
        } else {
          splitPixels[i][j] = image.getPixels()[i][j];
        }
      }
    }
    return new Image(splitPixels);
  }
}

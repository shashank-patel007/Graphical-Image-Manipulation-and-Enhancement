package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The LevelsAdjustmentCommand class represents a command for adjusting the levels of an image.
 * It extends the AbstractSplitCommand class and implements the image manipulation logic
 * by invoking the levelsAdjustmentCommand operation on the specified ImageModelInterface instance.
 */
public class LevelsAdjustmentCommand extends AbstractSplitCommand {

  private int b;
  private int m;
  private int w;

  /**
   * Constructs a LevelsAdjustmentCommand with the specified parameters.
   *
   * @param model                The ImageModelInterface instance to perform levels adjustment on.
   * @param b                    The brightness adjustment value.
   * @param m                    The midtones adjustment value.
   * @param w                    The white point adjustment value.
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image after levels adjustment.
   * @param splitPercentage      An optional parameter specifying the
   *                             percentage of split for the image.
   */
  public LevelsAdjustmentCommand(ImageModelInterface model, int b, int m, int w, String imageName,
                                 String destinationImageName,
                                 Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
    this.b = b;
    this.m = m;
    this.w = w;
  }

  /**
   * This method must be implemented by subclasses to define the specific image
   * manipulation logic. It may throw an Exception to indicate an error.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.levelsAdjustmentCommand(this.b, this.m, this.w, this.imageName,
            this.destinationImageName, this.splitPercentage);
  }
}
